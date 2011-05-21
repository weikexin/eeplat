package com.exedosoft.plat.login.zidingyi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.login.zidingyi.excel.MySqlOperation;

public class GZtiaoLiShi {

	public static void InsertLiShi(String name,String month) {
		if (name != null)
			name = name.trim();
		/**
		 * 对数据整理后生成Excel文件
		 */

		Connection conn = MySqlOperation.getConnection();
		try {
			String sql = "select * from gz_salarymessage where taxlv = '1234567890987654321'";
			ResultSet rs = MySqlOperation.SMfindBySQL(conn, sql,null);
			boolean flag = false;
			String objuid = null;
			Date date = new Date();
			while (rs.next()) {
				String gzName = rs.getString("name");
				objuid = rs.getString("objuid");
				if (gzName != null) {
					gzName = gzName.trim();
					if (name.equals(gzName)) {
						flag = true;
						break;
					}
				}
			}
			long time = date.getTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String nowdate = format.format(date);

			if (flag) {
				MySqlOperation.SMRemoveByObjuid(conn, objuid);
				MySqlOperation.insertLiShi(conn, name, time, nowdate,month);
			} else {
				MySqlOperation.insertLiShi(conn, name, time, nowdate,month);
			}

		} catch (Exception e) {
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Double d = null;
		Double d1 = 0.10;
		System.out.println(d + d1);

	}

}
