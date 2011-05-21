package com.exedosoft.plat.login.zidingyi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.login.zidingyi.excel.MySqlOperation;

public class RemoveGZmessage extends DOAbstractAction {

	public String excute() {

		// 接收的参数
		String resname = null;
		// 删除的记录数;
		int removeNumber = 0;
		Connection conn = MySqlOperation.getConnection();
		List users = new ArrayList();
		try {
			users = service.invokeSelect();
			// String user = service.invokeSelectGetAValue();
			// users.add(user);

		} catch (Exception e) {
			this.setEchoValue("接收工资条信息失败！error" + e.toString());
			return this.DEFAULT_FORWARD;
			//return "接收工资条信息失败！";
		}

//		System.out.println("====================");
//		System.out.println(users);
//		System.out.println("====================");
		
		// 历遍所有的数据；
		if (users != null && users.size() > 0) {
			BOInstance bo = (BOInstance) users.get(0);
			String smonth = bo.getValue("month");
			
			resname = bo.getValue("name");

//			System.out.println("====================");
//			System.out.println(smonth);
//			System.out.println(resname);
//			System.out.println("====================");
			
			try {
				if (smonth != null
						&& (resname == null || resname.length() <= 0)) {
					removeNumber = MySqlOperation
							.SMRemoveByDate(conn, smonth);
				} else if (smonth == null && resname != null
						&& resname.length() > 0) {
					removeNumber = MySqlOperation.SMRemoveByName(conn, resname);
				} else if (smonth != null && resname != null
						&& resname.length() > 0) {
					removeNumber = MySqlOperation.SMRemoveByNameAndDate(conn,
							resname, smonth);
				} else if (smonth == null
						&& (resname == null || resname.length() <= 0)) {
					this.setEchoValue("请选择条件。");
//					System.out.println("====================");
//					System.out.println("====================");
//					System.out.println("====================");
//					System.out.println("====================");
					return this.DEFAULT_FORWARD;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			System.out.println("====================");
//			System.out.println(removeNumber);
//			System.out.println("====================");
			
			if (removeNumber > 0)
				this.setEchoValue("成功删除" + removeNumber + "条记录。");
			else 
				this.setEchoValue("没有符合条件的记录。");
			
			return this.DEFAULT_FORWARD;
		} else {
			this.setEchoValue("删除失败");
			return this.DEFAULT_FORWARD;
			//return "删除失败";
		}
	}

	public static void main(String[] args) {

	}

}
