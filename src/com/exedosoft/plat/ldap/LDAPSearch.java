package com.exedosoft.plat.ldap;

import com.novell.ldap.*;
import com.novell.ldap.util.Base64;


import java.util.Enumeration;
import java.util.Iterator;
import java.util.Random;

public class LDAPSearch {
	private static String[] querys = new String[] { "id=456", "gender=0",
			"qq=4566642", "mobile=ef", "icq=9032454", "province=hunan",
			"age=4938345", "country=china", "phone=5ff-a67", "state=1",
			"msn=jtyd@hotmail.com", "address=4e3" };
	private static Random random = new Random();

	public static String randomQuery() {
		return querys[random.nextInt(querys.length)];
	}

	public static void main(String[] args) {
		

		
		int searchScope = LDAPConnection.SCOPE_ONE;
		String searchBase = "ou=People,dc=weikexin,dc=com";
		String searchFilter = randomQuery();
		System.out.println(searchFilter);

		try {
			LDAPConnection lc = getConnection();

			long start = System.currentTimeMillis();
			LDAPSearchResults searchResults = lc.search(searchBase,
					searchScope, searchFilter, null, // return all attributes
					false); // return attrs and values

			System.out.println("query   cost   time:   "
					+ (System.currentTimeMillis() - start));
			/*
			 * To print out the search results, -- The first while loop goes
			 * through all the entries -- The second while loop goes through all
			 * the attributes -- The third while loop goes through all the
			 * attribute values
			 */
			while (searchResults.hasMore()) {
				LDAPEntry nextEntry = null;
				try {
					nextEntry = searchResults.next();
				} catch (LDAPException e) {
					System.out.println("Error:   " + e.toString());

					// Exception is thrown, go for next entry

					continue;
				}

				System.out.println("\n" + nextEntry.getDN());
				System.out.println("     Attributes:   ");

				LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
				Iterator allAttributes = attributeSet.iterator();

				while (allAttributes.hasNext()) {
					LDAPAttribute attribute = (LDAPAttribute) allAttributes
							.next();
					String attributeName = attribute.getName();

					System.out.println("         " + attributeName);

					Enumeration allValues = attribute.getStringValues();

					if (allValues != null) {
						while (allValues.hasMoreElements()) {
							String Value = (String) allValues.nextElement();
							if (Base64.isLDIFSafe(Value)) {
								// is printable
								System.out.println("             " + Value);
							} else {
								// base64 encode and then print out
								Value = Base64.encode(Value.getBytes());
								System.out.println("             " + Value);
							}
						}
					}
				}
			}
			// disconnect with the server
			dispose(lc);
		} catch (LDAPException e) {
			System.out.println("Error:   " + e.toString());
		}
		/*
		 * catch (UnsupportedEncodingException e) {
		 * System.out.println("Error:   " + e.toString()); }
		 */
		System.exit(0);
	}

	public static LDAPConnection getConnection() {
		int ldapPort = LDAPConnection.DEFAULT_PORT;
		int ldapVersion = LDAPConnection.LDAP_V3;
		String ldapHost = "127.0.0.1";
		String loginDN = "cn=Manager,dc=weikexin,dc=com";
//		String loginDN = "cn=Manager,o=zephyr.com.cn";
		String password = "secret";
		LDAPConnection lc = new LDAPConnection();
		try {
			// connect to the server
			lc.connect(ldapHost, ldapPort);
			// bind to the server
			lc.bind(ldapVersion, loginDN, password.getBytes("UTF8"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return lc;

	}

	public static void dispose(LDAPConnection lc) {
		try {
			if (lc.isConnected()) {
				lc.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
