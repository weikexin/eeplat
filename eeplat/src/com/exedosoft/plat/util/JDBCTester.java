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

		Connection con = DODataSource.getDefaultCon_Busi();
	
		try {
			con.setAutoCommit(false);
//			PreparedStatement pstmt = con
//					.prepareStatement();
			
			PreparedStatement pstmt = con.prepareStatement("select * from do_log");
			
			
			
			PreparedStatement pstmt2 = con.prepareStatement("update do_log set  userName='1111'  where objuid=? ");
		//	PreparedStatement pstmt

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//rs.updateString("userName", "bb");
				//System.out.println(rs.getString("userName"));
				pstmt2.setString(1, rs.getString("objuid"));
				pstmt2.executeUpdate();
			}

			pstmt.close();
			con.commit();

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

	public static void main(String[] args) {
		changeBitType();
	}

}
