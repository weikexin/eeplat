package com.zephyr.erpscan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.zephyr.db.DBConnection;
import com.zephyr.erpscan.framework.util.Global;

public class GetBarcode {
	private static Logger log = Logger.getLogger(GetBarcode.class);
	
	public synchronized char[] getBarcode(){
		log.debug("do getBarcode class");
		ResultSet rs = null;
		Statement smt = null;
		Connection dbcon = null;
		String barcode = null;
		char ch_code[] = null;
		try {
			dbcon = Global.getConnection();
			dbcon.setAutoCommit(false);
			log.debug("db Connection AutoCommit set false");
			String sql = "select max(bar_code) as m from xxxcmbc_erpscan.code where stamp='AP'";
			log.info("getBarcode: " + sql);
			smt = dbcon.createStatement();
			rs = smt.executeQuery(sql);
			
			while(rs.next()){
				barcode = rs.getString(1);
			}
			log.info("bar code is "+barcode);
			char ch = barcode.charAt(0);
			String last_code = barcode.substring(1,barcode.length());
			long num = Long.parseLong(last_code)+1;
			String num_code = String.valueOf(num);
			int len = last_code.length()-num_code.length();
			String str = new String();
			
			if(len>0){
				for(int i = 0;i < len; i ++){
					str+='0';
				}
			}else {
				if(last_code.equals("9999999999999")){
					ch++;
					last_code="0000000000000";
					num_code = last_code;
				}
			}
			String new_code = ch + str + num_code;
			
			String locksql = "lock table xxxcmbc_erpscan.code in exclusive mode";
			log.info("locksql: " + locksql);
			smt.executeUpdate(locksql);
			
			String sql0 = "update xxxcmbc_erpscan.code set bar_code='"+new_code+"' where stamp='AP'";
			
			int count = smt.executeUpdate(sql0);

			dbcon.setAutoCommit(true);
			dbcon.commit();
			
			if(count>0){
				ch_code = new char[new_code.length()];

			    for(int i = 0;i < new_code.length();i ++){
			    	ch_code[i] = new_code.charAt(i);
			    }
			    int n = (int)ch_code[0];
			 //   System.out.println("char code [0]"+n);
			}
		    
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally{
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(smt != null) {
				try {
					smt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(dbcon != null) {
				try {
					dbcon.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return ch_code;
		
	}
	
	public	char[] CorrCode(char ch[])
	{
		int mod = 0;
		int mod1 = 0;
		int len = ch.length;
		mod1 = (ch[0]-'A'+10)*3;
		for( int k = 1; k < len; k++ )
			mod += (ch[k]-'0')*(k+3);
		
		mod =mod+mod1;	
		mod = mod+60;
		mod %= 100;	
	
		char []name = new char[2];
		name[0] = (char) (mod / 10 + '0');
		name[1] = (char)(mod % 10 + '0');
		//System.out.println("mod1 is "+mod1);
		return name;
		
	}
	
	public static void main(String arg[]){
		/*ResultSet rs = null;
		Statement smt = null;
		Connection dbcon = null;
		String barcode = null;
		char ch_code[] = null;
		try {
			dbcon = Global.getConnection();
			dbcon.setAutoCommit(false);
			String sql = "select * from xxxcmbc_erpscan.code where stamp='AP'";
			smt = dbcon.createStatement();
			rs = smt.executeQuery(sql);
			
			String locksql = "lock table xxxcmbc_erpscan.code in exclusive mode";
			smt.execute(locksql);			
			
			dbcon.setAutoCommit(true);
			dbcon.commit();
				    
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally{
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(smt != null) {
				try {
					smt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(dbcon != null) {
				try {
					dbcon.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/
		
		GetBarcode gb = new GetBarcode();
		String b="A000000000125726";
		char ch_code[] = null;
		ch_code = new char[b.length()];

	    for(int i = 0;i < b.length();i ++){
	    	ch_code[i] = b.charAt(i);
	    }
		System.out.print(gb.CorrCode(ch_code));
		
		
		
	}
}
