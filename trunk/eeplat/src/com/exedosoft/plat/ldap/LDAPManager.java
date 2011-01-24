package com.exedosoft.plat.ldap;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.dao.SearchSupport;

import com.exedosoft.plat.search.customize.SearchTransCode;
import com.exedosoft.plat.util.StringUtil;

/**
 * 
 * o(Organizational) c(Country)
 * 
 * http://baike.baidu.com/view/159263.htm DN (Distinguished Name) RDN（Relative
 * Distinguished Name) DC (Domain Component) CN (Common Name) SN （SURNAME） OU
 * (Organizational Unit)
 * 
 * ldapadd -x -D "cn=Manager,o=zephyr.com.cn" -W -f a.ldif
 * 
 * String SQL =
 * "INSERT INTO cn,ou (objectClass,objectClass,objectClass,ou,sn,cn) " +
 * "VALUES (top,person,organizationalPerson,Product Development,Boorshtein," +
 * "Marc Boorshtein)"; Statement insert = con.createStatement(); int count =
 * insert.executeUpdate(SQL); if (count < 1) {
 * System.out.println("Insert Failed"); } else {
 * System.out.println("Insert Succeeded"); }
 * 
 * 
 * Ou,sn,cn是新条目的入口标识。
 * 
 * Jdbc-LDAP不可以完成串行化binding，因此，只适宜对已有的LDAP进行临时访问（如程序员不熟悉，或保持旧程序，仅修改必要的连接项），
 * 不宜把整个项目建筑在JDBC-LDAP上。实际上，LDAP本身就是一种访问的前端协议，硬要把SQL再作为前端使用，是完全没有必要的。
 * 
 * 
 * 
 * @author Administrator
 * 
 */

public class LDAPManager {

	public static final LDAPManager INSTANCE = new LDAPManager();

	public LDAPManager() {

	}

	public Connection getConnection() {

		DODataSource dss = DODataSource.parseConfigHelper("/ds_ldap.xml",
				"ds_ladp");
		java.sql.Connection conn = null;
		try {
			Class.forName(dss.getDriverClass());
			conn = DriverManager.getConnection(dss.getDriverUrl(), dss
					.getUserName(), dss.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void t() throws ClassNotFoundException {

		// INSERT INTO CN=kavin,CN=Users,DC=osn,DC=com
		// (objectClass,objectClass,objectClass,objectClass,cn,sn,sAMAccountName,userPassword
		// VALUES
		// ('top','person','organizationalPerson','user','kavin','kavin','kavin','kavin');

		Connection conn = null;
		try {
			conn = LDAPManager.INSTANCE.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT * FROM ou=people,o=zephyr.com.cn,o=domains,o=zephyr.com.cn");// WHERE
			// cn
			// like
			// %?%
			// pstmt.setString(1, "wang");
			ResultSet rs = pstmt.executeQuery();// sql
			String cn = null;
			String sn = null;
			SearchTransCode stc = new SearchTransCode(null);

			while (rs.next()) {

				BOInstance bi = stc.transRSToBOInstance(rs, null);
				bi.setUid(bi.getValue("uid"));
				System.out.println("bi::" + bi);
				// cn = rs.getString(1);
				// sn = rs.getString(2);
				// System.out.println("cn=" + cn + " sn=" + sn + "description="
				// + StringUtil.decodeBase64(rs.getString(3)));
			}
			rs.close();

			// test insert
			// pstmt.clearParameters();
			// pstmt.close();
			// pstmt =
			// conn.prepareStatement("insert into cn=wanghongliang2,ou=Plat,dc=weikexin,dc=com(objectClass,cn,sn,description) values(?,?,?,?)");
			// pstmt.setString(1, "person");
			// pstmt.setString(2, "wanghongliang2");
			// pstmt.setString(3, "wang2");
			// pstmt.setString(4, "精彩");
			// pstmt.executeUpdate();

			// test delete
			// pstmt.clearParameters();
			// pstmt.close();
			// pstmt =
			// conn.prepareStatement("delete from uid=wkx,ou=people,o=zephyr.com.cn");
			// pstmt.setString(1, "wkx");
			// pstmt.executeUpdate();

			// test update
			// pstmt.clearParameters();
			// pstmt.close();
			// pstmt =
			// conn.prepareStatement("UPDATE  uid=advices,ou=people,o=zephyr.com.cn  set telephoneNumber=8808");
			// pstmt.setString(1, "songhp");
			// pstmt.setString(2, "5a6L5ZKM5bmz");
			// pstmt.setString(3, "1");
			// pstmt.setString(4, "1");
			// pstmt.setString(5, "songhp");

			// pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static void jndiCheck() {

		String root = "dc=weikexin,dc=com"; // root

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://127.0.0.1/" + root);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=Manager,dc=weikexin,dc=com");
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		DirContext ctx = null;
		try {
			ctx = new InitialDirContext(env);
			System.out.println("认证成功");
		} catch (javax.naming.AuthenticationException e) {
			e.printStackTrace();
			System.out.println("认证失败");
		} catch (Exception e) {
			System.out.println("认证出错：");
			e.printStackTrace();
		}

		if (ctx != null) {
			try {
				ctx.close();
			} catch (NamingException e) {
				// ignore
			}
		}
		System.exit(0);

	}

	public static void check() {


		InitialContext iCnt = null;
		Hashtable envi = new Hashtable();
		try {
			envi.put("java.naming.factory.initial",
					"com.sun.jndi.ldap.LdapCtxFactory");
			envi.put("java.naming.provider.url", "ldap://127.0.0.1/dc=weikexin,dc=com");
			envi.put(Context.SECURITY_AUTHENTICATION, "simple");
			envi.put(Context.SECURITY_PRINCIPAL,
					"uid=wkx,ou=people,o=zephyr.com.cn");
			envi.put(Context.SECURITY_CREDENTIALS, "123567");
			iCnt = new InitialContext(envi);
			System.out.println("认证通过!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (iCnt != null) {
					iCnt.close();
				}
			} catch (Exception ie) {

			}
		}

	}

	public static void main(String[] args) throws SQLException,
			ClassNotFoundException {

		
		
	 	DOService aService = DOService.getService("t_expense_browse");
	 	BOInstance bi = aService.getInstance("40288024292af50d01292af8121e0001");
	 	System.out.println("Data::" + bi);
	 	
		
		
//		LDAPManager.check();
//		LDAPManager.t();
//		try {
//			MessageDigest sha = MessageDigest.getInstance("SHA");
//			System.out.println("SHA 加密之后::"
//					+ StringUtil.byte2hex(sha.digest("123567".getBytes())));
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// LDAPManager.INSTANCE.getConnection();
	}

}
