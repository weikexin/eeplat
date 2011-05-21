package com.exedosoft.plat.login.zidingyi.excel;


import java.security.MessageDigest;

import sun.security.provider.SHA;
import sun.security.provider.SHA2;

public class LDAPOpertion {
	//º”√‹
	public static String passwordKey(String str) {
		String ret = "";
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			sha.update(str.getBytes());
			ret = new sun.misc.BASE64Encoder().encode(sha.digest());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return ret;
	}
	    public static void main(String args[]) {
	    	String pwd = LDAPOpertion.passwordKey("123");
	        System.out.println(pwd);
	    }
}