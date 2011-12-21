package com.exedosoft.plat.action.customize.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class DOGeneMultiTenancyDefaultTable_V_Old extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 558266835833057020L;

	private static Log log = LogFactory
			.getLog(DOGeneMultiTenancyDefaultTable_V_Old.class);

	private static DODataSource dss = DODataSource
			.getDataSourceByID("mysqlexample");
	private static String tenantFileBase = DOGlobals.getValue("tenant_db");

	@Override
	public String excute() throws ExedoException {

		DOBO aBO = DOBO.getDOBOByName("multi_tenancy");

		if (aBO != null) {
			BOInstance curTenant = aBO.getCorrInstance();
			
			File aFile = new File(tenantFileBase + curTenant.getValue("name"));
			if (aFile.exists()) {
				this.setEchoValue("该租户已经创建!");
				return this.NO_FORWARD;
			}
			if (curTenant != null) {
				String multi_datasource_uid = curTenant
						.getValue("multi_datasource_uid");
				if (multi_datasource_uid != null) {
					DOService findDataSource = DOService
							.getService("multi_datasource_browse");
					BOInstance aBI = findDataSource
							.getInstance(multi_datasource_uid);
					String tenantName = curTenant.getValue("name");// carrefour
					dss = (DODataSource) aBI.toObject(DODataSource.class);
					
					log.info("DODataSource:::::::::::" + dss);
					copyFiles(tenantName);

				}
			}
		}
		this.setEchoValue("初始化成功！");
		return DEFAULT_FORWARD;

	}

	void copyFiles(String tenantName) {

		DODataSource multiDss = DODataSource.parseConfigHelper("/ds_multi.xml",
				String.valueOf("/ds_multi.xml".hashCode()));
		Connection conMulti = multiDss.getConnection();
		Connection conData = dss.getConnection();

		try {
			log.info("Dss Connection Info:::" + conData.getMetaData().getURL());
			StringUtil.copyDirectiory(tenantFileBase + tenantName,
					tenantFileBase + "base");
			if (multiDss != null && dss != null) {
				conMulti = multiDss.getConnection();
				conData = dss.getConnection();
				if (conMulti != null && conData != null) {
					conMulti.setAutoCommit(false);
					conData.setAutoCommit(false);

				}
				File aFile = new File(tenantFileBase + tenantName
						+ "/base_tenant_view.sql");
				dealAFile(tenantName,  aFile,  conData);

				aFile = new File(tenantFileBase + tenantName
						+ "/base_tenant_meta.sql");

				dealAFile(tenantName,  aFile, conMulti);
			}

			conMulti.commit();
			conData.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conMulti.rollback();
				conData.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (conMulti != null && conData != null) {
				try {
					conMulti.close();
					conData.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	private void excuteSql(String tenantName) {

		File aFile = new File(tenantFileBase + tenantName
				+ "/base_tenant_view.sql");

		aFile = new File(tenantFileBase + tenantName + "/base_tenant_meta.sql");

	}

	private void dealAFile(String tenanName,  File aFile,
			Connection con)
			throws UnsupportedEncodingException, FileNotFoundException,
			IOException, SQLException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(aFile), "utf-8"));
		StringBuffer sb = new StringBuffer();
		while (true) {
			String aLine = in.readLine();
			if (aLine == null) {
				break;
			}
			aLine = aLine.replace("carrefour", tenanName);
			System.out.println("正在执行SQL语句:::" +  aLine);
			PreparedStatement pstmt = con.prepareStatement(aLine);
			pstmt.execute();
			
			sb.append(aLine).append("\n");// \n\r
		}
		in.close();

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(aFile), "utf-8"));
		out.append(sb.toString());
		out.flush();
		out.close();
	}

	public static void main(String[] args) {

//		DOGeneMultiTenancyDefaultTable gmtd = new DOGeneMultiTenancyDefaultTable();
//		gmtd.copyFiles("ufida");
		
		DODataSource dss = DODataSource
		.getDataSourceByID("mysqlexample");
		
		System.out.println("DODataSouce:::" + dss);

	}

}
