package com.zephyr.erpscan;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.zephyr.erpscan.framework.exception.DatastoreException;
import com.zephyr.erpscan.framework.util.Global;

public class GetBarCodeImpl{
	
	public char[] getBarCode(){
		Connection con = null;
		CallableStatement cs = null;
		String bu_code = null;
		char ch_code[] = null;
		
		java.text.SimpleDateFormat formatter = 
			new java.text.SimpleDateFormat("yyyyMMdd");

		java.util.Date d = new java.util.Date();
		String dateStr = formatter.format(d);
		String sql = "begin renew.renew_barcode(:1, :2, :3); end;";
		try {
			con =Global.getConnections();
			cs = con.prepareCall(sql);
			
			cs.registerOutParameter(1,oracle.jdbc.OracleTypes.VARCHAR);
			cs.setString(2, dateStr);
			cs.registerOutParameter(3,oracle.jdbc.OracleTypes.VARCHAR);
			cs.execute();
			
			bu_code = cs.getString(1);
			String error = cs.getString(3);
			
			System.out.println("oracle make  error is "+error);
			System.out.println("build code is   !!!   "+bu_code);
			ch_code = new char[bu_code.length()];
			for(int i = 0;i < bu_code.length(); i ++){
				ch_code[i] = bu_code.charAt(i);
				System.out.println("char code ["+i+"]"+ch_code[i]);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Global.releaseCon(cs, con);
		}
		return ch_code;
	}
	
	// get two verify codes
	public	char[] CorrCode(char ch[])
	{
		int mod = 0;
		int len = ch.length;
		for( int k = 0; k < len; k++ )
			mod += (ch[k]-'0')*(k+3);
		mod = mod+60;
		mod %= 100;	
	
		char []name = new char[2];
		name[0] = (char) (mod / 10 + '0');
		name[1] = (char)(mod % 10 + '0');
	
		return name;
	}
	
	public static void main(String aa[]){
		 char ch_code[] = new GetBarCodeImpl().getBarCode();
		 char ve_code[] = new GetBarCodeImpl().CorrCode(ch_code);
	
		 for(int i = 0 ;i < ve_code.length; i ++){
			 System.out.println("ve_code is "+ve_code[i]);
			
		 }
		
	}
}
