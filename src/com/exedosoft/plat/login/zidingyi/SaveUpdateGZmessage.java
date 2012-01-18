package com.exedosoft.plat.login.zidingyi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.login.zidingyi.excel.LDAPPeopleUtil;
import com.exedosoft.plat.login.zidingyi.excel.MySqlOperation;

public class SaveUpdateGZmessage extends DOAbstractAction {

	public String excute() {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n");
		List users = new ArrayList();
		try {
			users = service.invokeSelect();
			// String user = service.invokeSelectGetAValue();
			// users.add(user);

		} catch (Exception e) {
			e.printStackTrace();
			return this.DEFAULT_FORWARD;
		}

		// 历遍所有的数据；
		if (users != null && users.size() > 0) {
			String s = users.get(0).toString();
			String st = s.substring(s.indexOf("{") + 1, s.lastIndexOf("}"));
			String[] sarray = st.split(",");
			System.out.println("++++++++++++++++++++++++++++++++++++++");
			System.out.println(s + "\n" + st + "\n" + sarray);
			System.out.println("++++++++++++++++++++++++++++++++++++++");
			SalaryMessage sm = new SalaryMessage();
			// 对每条数据进行处理，取得有效属性；
			for (int i = 0; i < sarray.length; i++) {
				String temp = sarray[i];
				String[] nv = temp.split("=");

				if (nv.length == 2 && "objuid".equals(nv[0].trim()))
					sm.setObjuid(nv[1]);

				if (nv.length == 2 && "month".equals(nv[0].trim())) {
					Date month = Date.valueOf(nv[1]);
					System.out.println(month);
					sm.setMonth(month);
				}
				if (nv.length == 2 && "name".equals(nv[0].trim()))
					sm.setName(nv[1]);
				if (nv.length == 2 && "basesalary".equals(nv[0].trim()))
					sm.setBasesalary(castdouble(nv[1]));
				if (nv.length == 2 && "buckshee".equals(nv[0].trim()))
					sm.setBuckshee(castdouble(nv[1]));
				if (nv.length == 2 && "rentdeduct".equals(nv[0].trim()))
					sm.setRentdeduct(castdouble(nv[1]));
				if (nv.length == 2 && "leavededuct".equals(nv[0].trim()))
					sm.setLeavededuct(castdouble(nv[1]));
				if (nv.length == 2 && "payyanglaoinsure".equals(nv[0].trim()))
					sm.setPayyanglaoinsure(castdouble(nv[1]));
				if (nv.length == 2 && "payshiyeinsure".equals(nv[0].trim()))
					sm.setPayshiyeinsure(castdouble(nv[1]));
				if (nv.length == 2 && "payyilaioinsure".equals(nv[0].trim()))
					sm.setPayyilaioinsure(castdouble(nv[1]));
				if (nv.length == 2 && "payhousingsurplus".equals(nv[0].trim()))
					sm.setPayhousingsurplus(castdouble(nv[1]));
				if (nv.length == 2 && "remark".equals(nv[0].trim()))
					sm.setRemark(nv[1]);
			}
			
			System.out.println("++++++++++++++++++++++++++++++++++++++");
			System.out.println(sm.getObjuid() + "=objuid\n" + sm.getMonth() + "=month\n"
					+ sm.getName() + "=name\n" + sm.getBasesalary() + "=base\n"
					+ sm.getBuckshee() + "=buck\n" + sm.getRentdeduct() + "=rent\n"
					+ sm.getLeavededuct() + "=leave\n" + sm.getRemark() + "remark\n"
					);
			System.out.println("++++++++++++++++++++++++++++++++++++++");

			// 应发工资
			double base = sm.getBasesalary();
			double buck = sm.getBuckshee();
			double rent = sm.getRentdeduct();
			double leave = sm.getLeavededuct();
			double fact = base + buck - rent - leave;
			if(fact > 0)
				sm.setFactsalary(fact);
			else
				sm.setFactsalary(0D);

			// 社保小计
			double yanglao = sm.getPayyanglaoinsure();
			double shiye = sm.getPayshiyeinsure();
			double yiliao = sm.getPayyilaioinsure();
			double shebao = yanglao + shiye + yiliao;
			sm.setPayshebaofee(shebao);

			// 税前应发
			double housing = sm.getPayhousingsurplus();
			double before = fact - shebao - housing;
			if(before > 0)
				sm.setTaxbefore(before);
			else 
				sm.setTaxbefore(0D);
			// 应税所得G=F-2000
			double taxget = before - 2000;
			if(taxget >= 0)
				sm.setTaxget(taxget);
			else
				sm.setTaxget(0D);
			// 税率H和速算扣除
			double taxlv = 0D;
			double taxrm = 0D;
			if (taxget <= 0) {
				sm.setTaxlv("0%");
				sm.setTaxrm(0D);
			} else if (taxget <= 500) {
				taxlv = 0.05;
				sm.setTaxlv("5%");
				sm.setTaxrm(0D);
			} else if (taxget <= 2000) {
				taxlv = 0.10;
				taxrm = 25D;
				sm.setTaxlv("10%");
				sm.setTaxrm(25D);
			} else if (taxget <= 5000) {
				taxlv = 0.15;
				taxrm = 125D;
				sm.setTaxlv("15%");
				sm.setTaxrm(125D);
			} else if (taxget <= 20000) {
				taxlv = 0.20;
				taxrm = 375D;
				sm.setTaxlv("20%");
				sm.setTaxrm(375D);
			} else {
				sm.setTaxlv("其他情况");
				sm.setTaxrm(0D);
			}
			// 税
			double tax = 0D;
			if (taxget <= 0)
				sm.setTax(0D);
			else {
				tax = taxget * taxlv - taxrm;
				int it = (int) (tax * 100);
				tax = (double) (it / 100.00);
				if (tax >= 0)
					sm.setTax(tax);
			}
			// 税后实发
			double after = before - tax;
			if (after > 0)
				sm.setTaxafter(after);
			else
				sm.setTaxafter(0D);
			try {
				Connection conn = MySqlOperation.getConnection();
				if (sm.getObjuid() != null
						&& sm.getObjuid().trim().length() > 0)
					MySqlOperation.update(conn, sm, sm.getObjuid().trim());
				else {
					long time = new java.util.Date().getTime();
					MySqlOperation.insert(conn, sm, time);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return this.DEFAULT_FORWARD;
		} else {
			return this.DEFAULT_FORWARD;
		}
	}

	public static double castdouble(String value) {
		double number = 0D;
		if (value != null && value.trim().length() > 0
				&& value.matches("^\\d+.\\d+|\\d+$")) {
			number = Double.parseDouble(value);
		}
		System.out.println(number);
		return number;
	}

	public static void main(String[] args) {
	}

}
