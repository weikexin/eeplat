package com.exedosoft.plat.action.customize.tools;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.safe.TenancyValues;

public class DOInstallApplication extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 568992871873045123L;

	@Override
	public String excute() throws ExedoException {

		if (this.service == null) {
			System.out.println("服务为定义！");
			return NO_FORWARD;
		}

		StringBuilder sb = new StringBuilder("<export>");
		DOBO bo = DOBO.getDOBOByName("multi_appshare");
		BOInstance appShare = bo.getCorrInstance();

		DOService insertInstallRecord = DOService
				.getService("multi_appshareinstall_install");

		Transaction t = insertInstallRecord.currentTransaction();
		t.begin();

		try {
			TenancyValues tv = (TenancyValues) DOGlobals.getInstance()
					.getSessoinContext().getTenancyValues();
			
			if(tv.getTenant().getUid().equals(appShare.getValue("auth_tenant_id"))){
				this.setEchoValue(I18n.instance().get("应用已经存在！"));

				return this.NO_FORWARD;
			}

			Map paras = new HashMap();
			paras.put("use_tenant_id", tv.getTenant().getUid());
			paras.put("use_tenant_name", tv.getTenant().getValue("l10n"));
			insertInstallRecord.invokeUpdate(paras);

			URL url = DODataSource.class.getResource("/globals.xml");
			String s = url.getPath();
			String s2 = s;
			s = s.substring(0, s2.toLowerCase().indexOf("web-inf"));

			StringBuffer fileName = new StringBuffer(s).append("appshare/")
					.append(appShare.getValue("xml_path"));

			DOImport imp = new DOImport();
			imp.importXml("", fileName.toString());
		} catch (Exception e) {

			t.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			t.end();
		}
		this.setEchoValue(I18n.instance().get("安装成功"));

		return DEFAULT_FORWARD;

	}

	public static void main(String[] args) {

		File tenantFile = new File("c:/" + "appshare/" + "caf");
		tenantFile.mkdir();
		System.out.println("SSSSSSSSSSSS::" + tenantFile.getAbsolutePath());
	}

}
