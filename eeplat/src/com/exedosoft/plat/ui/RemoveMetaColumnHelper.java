package com.exedosoft.plat.ui;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.exedosoft.plat.bo.DODataSource;

public class RemoveMetaColumnHelper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DODataSource  dds = DODataSource.getDataSourceByL10n("国土资源部配号系统数据库");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dds.getContextConnection();
			pstmt = con.prepareStatement("select * from DB_PERAMBULATE_ITEM");
			Statement alertStmt = con.createStatement();


			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsMeta = rs.getMetaData();
			
			String leftParas = "OBJUID,NA_ITEM_NAME,NA_APPLY_PERSON,NA_PERAMBULATE_COMP,ID_QUALIFICATION_CARD,NA_COMP_ADDRESS,QT_PERAMBULATE_MINE,IN_PERAMBULATE_PHASE,IN_ITEM_KIND,IN_ECONOMY_TYPE,NA_GEOGRAPHY_POSITION,NA_AREA_COORDINATE,QT_BASIC_SECTION,QT_QTRBASIC_SECTION,QT_SMALL_SECTION,QT_CONVERTBASIC_SECTION,QT_TOTAL_AREA,QT_LONGEAST_START,QT_LONGEAST_END,QT_LATNORTH_START,QT_LATNORTH_END,IN_PROSPECT_KIND,QT_COST,IN_ITEM_TYPE,ID_LICENCEID,approve_org,approve_org_code";
			String[] paras = leftParas.split(",");


			for (int col = 1; col <= rsMeta.getColumnCount(); col++) {

				String metaName = rsMeta.getColumnName(col).toLowerCase().trim();
				boolean isLeft = false;
				for (int i = 0; i < paras.length; i++) {
					if (metaName.equalsIgnoreCase(paras[i])) {
						isLeft = true;
						break;
					}
				}
				if(!isLeft){
					System.out.println(metaName);

					alertStmt.executeUpdate("alter table DB_PERAMBULATE_ITEM drop column " + metaName);
					
				}
			}

		} catch (Exception ex) {
		ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
		

	}

}
