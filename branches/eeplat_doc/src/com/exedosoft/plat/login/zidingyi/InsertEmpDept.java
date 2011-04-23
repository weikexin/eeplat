package com.exedosoft.plat.login.zidingyi;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.login.zidingyi.excel.MySqlOperationII;

public class InsertEmpDept extends DOAbstractAction {

	public String excute() {
		String deptuid = null;
		String useruid = null;
		String objuid = null;
		String sn = null;
		List users = new ArrayList();

		try {
			users = service.invokeSelect();
		} catch (Exception e) {
			return this.DEFAULT_FORWARD;
		}

		// 历遍所有的数据；
		if (users != null && users.size() > 0) {

			Connection conn = MySqlOperationII.getConnection();
			BOInstance bi = (BOInstance) users.get(0);
			deptuid = bi.getValue("dept_uid");
			useruid = bi.getValue("user_uid");
			objuid = bi.getValue("objuid");

//			System.out.println("====================================");
//			System.out.println(objuid);
//			System.out.println(deptuid);
//			System.out.println(useruid);
//			System.out.println("====================================");
			try {
				conn.setAutoCommit(false);
				MySqlOperationII.insertDeptLink(conn, objuid, deptuid, useruid);
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
			try {
				if(conn != null && !conn.isClosed())
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
