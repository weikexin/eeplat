package com.exedosoft.plat.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import com.exedosoft.plat.bo.DODataSource;

public class JDBCTester {
	
	//

	public static StringBuffer changeBitType() {

		StringBuffer buffer = new StringBuffer();

		Connection con = DODataSource.getDefaultCon();
		try {


				PreparedStatement pstmt = con
						.prepareStatement("select * from C1923912");

				ResultSet rs = pstmt.executeQuery();
				ResultSetMetaData rsMeta = rs.getMetaData();
				rsMeta.getTableName(0);
				for (int col = 1; col <= rsMeta.getColumnCount(); col++) {
				
				}

				pstmt.close();
	
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}

		}
		return buffer;

	}
	
	public static void main(String[] args){
		
		
	}


}
