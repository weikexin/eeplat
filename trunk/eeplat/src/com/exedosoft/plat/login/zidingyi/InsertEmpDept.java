package com.exedosoft.plat.login.zidingyi;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.login.zidingyi.excel.LDAPPeopleUtil;
import com.exedosoft.plat.login.zidingyi.excel.MySqlOperationII;

public class InsertEmpDept extends DOAbstractAction {

	public String excute() {
		String deptuid = null;
		String useruid = null;
		String userName = null;
		String objuid = null;
		
		List users = new ArrayList();

		try {
			users = service.invokeSelect();
			// String user = service.invokeSelectGetAValue();
			// users.add(user);

		} catch (Exception e) {
			return this.DEFAULT_FORWARD;
		}

		System.out.println("=================1===================");
		System.out.println(objuid);
		System.out.println(deptuid);
		System.out.println(useruid);
		System.out.println(userName);
		System.out.println("==================1==================");
		// 历遍所有的数据；
		if (users != null && users.size() > 0) {

			
			Connection conn = MySqlOperationII.getConnection();			
			for (int n = 0; n < users.size(); n++) {
				String s = users.get(n).toString();
				String st = s.substring(s.indexOf("{") + 1, s.lastIndexOf("}"));
				String[] sarray = st.split(",");
				
				System.out.println("=================2===================");
				System.out.println(s);
				System.out.println(st);
				System.out.println(sarray);
				System.out.println("==================2==================");
				
				// 对每条数据进行处理，取得有效属性；
				for (int i = 0; i < sarray.length; i++) {
					String temp = sarray[i];
					String[] nv = temp.split("=");

					if (nv.length == 2 && "dept_uid".equals(nv[0].trim()))
						deptuid = nv[1];
					if (nv.length == 2 && "user_uid".equals(nv[0].trim()))
						useruid = nv[1];
					if (nv.length == 2 && "objuid".equals(nv[0].trim()))
						objuid = nv[1];
					
					if(useruid != null) {
						userName = LDAPPeopleUtil.getLDAPCNBySN(useruid);
					}	
				}	
					System.out.println("====================================");
					System.out.println(objuid);
					System.out.println(deptuid);
					System.out.println(useruid);
					System.out.println(userName);
					System.out.println("====================================");
					try {
						conn.setAutoCommit(false);
						MySqlOperationII.insertDeptLink(conn, objuid, deptuid, useruid, userName);
						conn.commit();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						try {
							conn.rollback();
							conn.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
					
				}
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return this.DEFAULT_FORWARD;
		}
		return this.DEFAULT_FORWARD;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
