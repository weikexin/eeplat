package com.exedosoft.plat.action.customize.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.plat.util.StringUtil;
import com.exedosoft.safe.TenancyValues;

public class DOExportApplication extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 568992871873045123L;

	@Override
	public String excute() throws ExedoException {

		if (this.service == null) {
			
			this.setEchoValue(I18n.instance().get("未配置SQL 语句"));
			return NO_FORWARD;
		}

		StringBuilder sb = new StringBuilder("<export>");
		DOBO bo = DOBO.getDOBOByName("do_application");
		BOInstance selectApp = bo.getCorrInstance();
		
		DOService findShare = DOService.getService("multi_appshare_findbyshareappid");
		List findApps = findShare.invokeSelect(selectApp.getUid());
		if(findApps!=null && findApps.size() >0 ){
			this.setEchoValue(I18n.instance().get("分享的应用必须原创APP，该应用已经分享到AppShare，不能重复分享!"));
			return NO_FORWARD;
		}

		sb.append("<app>").append(selectApp.toJSONString()).append("</app>\n");

		DOService findBP = DOService
				.getService("DO_BusiPackage_byapplicationuid");

		DOService findBO = DOService.getService("DO_BO_FindByBPUid_Form");

		Transaction t = findBP.currentTransaction();
		List<String> allIDs = new ArrayList<String>();
		DOExport export = new DOExport();
		try {
			t.begin();
			List bps = appendJSONS(sb, "package", findBP);
			// 服务下面的参数

			DOBO aBO = DOBO.getDOBOByName("do_bo");
			for (Iterator itBp = bps.iterator(); itBp.hasNext();) {
				BOInstance biBP = (BOInstance) itBp.next();
				List bos = findBO.invokeSelect(biBP.getUid());
				for (Iterator itBO = bos.iterator(); itBO.hasNext();) {
					BOInstance biBO = (BOInstance) itBO.next();
					aBO.refreshContext(biBO.getUid());
					export.exportBO(sb, biBO);
				}
			}
			t.end();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}

		sb.append("</export>");
		
		URL url = DODataSource.class.getResource("/globals.xml");
		String s = url.getPath();
		String s2 = s;
		s = s.substring(0, s2.toLowerCase().indexOf("web-inf"));
		
		TenancyValues tv = (TenancyValues) DOGlobals.getInstance()
		.getSessoinContext().getTenancyValues();

		
		File tenantFile = new File(s + "appshare/" +  tv.getTenant().getValue("name"));
		tenantFile.mkdir();
		
		StringBuffer fileName = new StringBuffer(tenantFile.getAbsolutePath())
		.append("/")
		.append(selectApp.getValue("name"))
		.append(".xml");
		try {
			 // TODO GOOGLE IO
//			BufferedWriter out =  null;
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName.toString()), "utf-8"));
			out.append(sb.toString());
			out.flush();
			out.close();
			
			DOService insertAppShare = DOService.getService("multi_appshare_insert");
			Map paras = new HashMap();
			paras.put("xml_path", new StringBuffer(tv.getTenant().getValue("name")).append("/")
					.append(selectApp.getValue("name"))
					.append(".xml").toString());
			paras.put("app_name", selectApp.getValue("l10n"));
			paras.put("app_desc", selectApp.getValue("description"));
			paras.put("share_date", new java.sql.Date(System.currentTimeMillis()));
			paras.put("share_app_id",selectApp.getUid());
			paras.put("auth_tenant_name", tv.getTenant().getValue("l10n"));
			paras.put("auth_tenant_id", tv.getTenant().getUid());
			insertAppShare.invokeUpdate(paras);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		this.setEchoValue(I18n.instance().get("成功分享到AppShare!"));
		return DEFAULT_FORWARD;

	}

	protected void getChildBIs(List menus, BOInstance parent,
			DOService servChild) {

		if (parent == null) {
			return;
		}
		for (Iterator it = servChild.invokeSelect(parent.getUid()).iterator(); it
				.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			menus.add(bi);
			getChildBIs(menus, bi, servChild);
		}
	}

	protected List appendJSONS(StringBuilder sb, String label, DOService service) {

		sb.append("<").append(label).append(">");
		List list = service.invokeSelect();
		appendLi(sb, list);
		sb.append("</").append(label).append(">\n");
		return list;
	}

	protected void appendLi(StringBuilder sb, List list) {

		// / toJSONSTring 需要进行转义
		for (Iterator it = list.iterator(); it.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			sb.append("<li>").append(StringUtil.filter(bi.toJSONString()))
					.append("</li>\n");
		}
	}
	
	public static void main(String[] args){
		
		File tenantFile = new File("c:/" + "appshare/" +"caf");
		tenantFile.mkdir();
		System.out.println("SSSSSSSSSSSS::" + tenantFile.getAbsolutePath());
	}

}
