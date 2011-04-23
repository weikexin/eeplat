package com.exedosoft.plat.login.zidingyi.excel;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.util.DOGlobals;

public class BaoxiaoExcelJXLS extends DOAbstractAction {

	public String excute() {

		// ģ�������ļ�·��
		String templateName = null;
		String templatePath = null;
		String template = null;
		String createExlPath = null;
		// �����
		BaoxiaoMessages bxms = null;
		BXTranfee bxtf = null;
		BXFixfee bxff = null;
		BXOtherfee bxof = null;
		List<BXTranfee> lptranf = new ArrayList<BXTranfee>();
		List<BXFixfee> lpfixf = new ArrayList<BXFixfee>();
		List<BXOtherfee> lpotherf = new ArrayList<BXOtherfee>();
		// ����ݿ�ȡ�������
		String baoxiaouid = null;
		String baoxiaoempuid = null;
		String baoxiaotype = null;
		Date baoxiaotime = null;
		String baoxiaostate = null;
		String baoxiaodesc = null;

		String projectuid = null;
		String mgrdeptuid = null;
		String deptmgruid = null;
		String caiwumgruid = null;
		String totalmgruid = null;

		
			List users = new ArrayList();
			try {
				users = service.invokeSelect();
			} catch (Exception e) {
				return "��������Excel�ļ�ʧ�ܣ�1";
			}

			if (users != null && users.size() > 0) {
				String s = users.get(0).toString();
				String st = s.substring(s.indexOf("{") + 1, s.lastIndexOf("}"));
				String[] sarray = st.split(",");
				// ��ÿ����ݽ��д��?ȡ����Ч���ԣ�
				for (int i = 0; i < sarray.length; i++) {
					String temp = sarray[i];
					String[] nv = temp.split("=");

					if (nv.length == 2 && "baoxiaouid".equals(nv[0].trim()))
						baoxiaouid = nv[1];
					if (nv.length == 2 && "baoxiaoempuid".equals(nv[0].trim()))
						baoxiaoempuid = nv[1];
					if (nv.length == 2 && "baoxiaotype".equals(nv[0].trim()))
						baoxiaotype = nv[1];
					if (nv.length == 2 && "baoxiaodesc".equals(nv[0].trim()))
						baoxiaodesc = nv[1];
					if (nv.length == 2 && "projectuid".equals(nv[0].trim()))
						projectuid = nv[1];
					if (nv.length == 2 && "mgrdeptuid".equals(nv[0].trim()))
						mgrdeptuid = nv[1];
					if (nv.length == 2 && "deptmgruid".equals(nv[0].trim()))
						deptmgruid = nv[1];
					if (nv.length == 2 && "caiwumgruid".equals(nv[0].trim()))
						caiwumgruid = nv[1];
					if (nv.length == 2 && "totalmgruid".equals(nv[0].trim()))
						totalmgruid = nv[1];
				}
				/**
				 * �������������Excel�ļ�
				 */
				
				Connection conn = MySqlOperation.getConnection();
				// ȡ��cw_bxfixfeedetail, �����BXFixf, ������lpfixf
				try {
					ResultSet rs = MySqlOperation.BXfixfee(conn, baoxiaouid);
					while (rs.next()) {
						bxff = new BXFixfee();
						Date begintime = null;
						Timestamp time1 = rs.getTimestamp("begintime");
						if (time1 != null) {
							long timeN1 = time1.getTime();
							begintime = new Date(timeN1);
						}
						long timeN1 = time1.getTime();
						begintime = new Date(timeN1);
						Date finishtime = null;
						Timestamp time2 = rs.getTimestamp("finishtime");
						if (time2 != null) {
							long timeN2 = time2.getTime();
							finishtime = new Date(timeN2);
						}
						String desc = rs.getString("fixfeedesc");

						String citycode = rs.getString("citycode");
						String address = null;
						Double fixbasic = null;
						Double days = null;
						Double fee = null;
						if (begintime != null && finishtime != null) {
							days = (finishtime.getTime() - begintime
									.getTime()) / (1000 * 60 * 60 * 24.00);
						}
						ResultSet rscity = MySqlOperation.cityBasic(conn, citycode);
						while (rscity.next()) {
							address = rscity.getString("cityname");
							fixbasic = rscity.getDouble("fixfee");
						}
						if (days != null && fixbasic != null)
							fee = days * fixbasic;
						bxff.setBeginDate(begintime);
						bxff.setEndDate(finishtime);
						bxff.setDays(days);
						bxff.setAddress(address);
						bxff.setFixBasic(fixbasic);
						bxff.setDesc(desc);
						bxff.setFee(fee);
						bxff.setKongge(" ");
						lpfixf.add(bxff);
					}

					// ȡ��from cw_bxusefeedetail,
					// �����BXTranf��BXOtherf, ���ֱ����lptranf��lpother;
					ResultSet r = MySqlOperation.BXusefee(conn, baoxiaouid);
					while (r.next()) {

						Timestamp time1 = r.getTimestamp("begintime");
						Date begintime = null;
						if (time1 != null) {
							long timeN1 = time1.getTime();
							begintime = new Date(timeN1);
						}
						Date finishtime = null;
						Timestamp time2 = r.getTimestamp("finishtime");
						if (time2 != null) {
							long timeN2 = time2.getTime();
							finishtime = new Date(timeN2);
						}

						String begincitycode = r.getString("begincitycode");
						String finishcitycode = r.getString("begincitycode");
						String beginaddress = getCityname(conn, begincitycode);
						String finishaddress = getCityname(conn, finishcitycode);

						Integer billCount = r.getInt("billcount");
						String hotelName = r.getString("hotelname");
						String desc = r.getString("usefeedesc");
						Double fee = r.getDouble("usefeedetail");

						String itemcode = r.getString("itemcode");
						String itemtype = null;
						ResultSet rscity = MySqlOperation.itemType(conn, itemcode);
						while (rscity.next()) {
							itemtype = rscity.getString("itemname");
						}

						if (itemtype != null
								&& ("�ɻ�Ʊ".equals(itemtype.trim()) || "��Ʊ"
										.equals(itemtype.trim()))) {
							String trans = null;
							if ("�ɻ�Ʊ".equals(itemtype.trim()))
								trans = "�ɻ�";
							else if ("��Ʊ".equals(itemtype.trim()))
								trans = "��";

							bxtf = new BXTranfee();
							bxtf.setBeginDate(begintime);
							bxtf.setBeginAddress(beginaddress);
							bxtf.setEndDate(finishtime);
							bxtf.setEndAddress(finishaddress);
							bxtf.setTransportation(trans);
							bxtf.setDanjushu(billCount);
							bxtf.setFee(fee);
							bxtf.setDesc(desc);
							bxtf.setKongge(" ");
							lptranf.add(bxtf);

						} else {
							bxof = new BXOtherfee();
							bxof.setKemu(itemtype);
							bxof.setHotel(hotelName);
							bxof.setDanjushu(billCount);
							bxof.setFee(fee);
							bxof.setDesc(desc);
							bxof.setKongge(" ");
							lpotherf.add(bxof);
						}
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// �����BaoxiaoMessages
				bxms = new BaoxiaoMessages();
				try {
					Connection conii = MySqlOperationII.getConnection();
//					String dept = MySqlOperationII.getDeptByUser(conii,
//							baoxiaoempuid);
					//�е����ţ���ʱû��
					String mgrdept = MySqlOperationII.getDeptByUid(conii,
							mgrdeptuid);

					String project = MySqlOperation.getProject(conn, projectuid);

					ResultSet rb = MySqlOperation.baoxiaoms(conn, baoxiaouid);
					while (rb.next()) {
						Timestamp time = rb.getTimestamp("baoxiaotime");
						if (time != null) {
							long timeN = time.getTime();
							baoxiaotime = new Date(timeN);
						}
						baoxiaostate = MySqlOperation.baoxiaostate(conn, rb
								.getInt("baoxiaostate"));
					}

					Double tranfee = null;
					for (BXTranfee tf : lptranf) {
						Double d = tf.getFee();
						if (d != null)
							if (tranfee == null)
								tranfee = d;
							else
								tranfee = tranfee + d;
					}
					Double fixfee = null;
					for (BXFixfee ff : lpfixf) {
						Double d = ff.getFee();
						if (d != null)
							if (fixfee == null)
								fixfee = d;
							else
								fixfee = fixfee + d;
					}
					
					if(fixfee != null) {
						int fix = (int)Math.round(fixfee*100);
						fixfee = (double)(fix/100.00);
					}
					
					Double otherfee = null;
					for (BXOtherfee of : lpotherf) {
						Double d = of.getFee();
						if (d != null)
							if (otherfee == null)
								otherfee = d;
							else
								otherfee = otherfee + d;
					}

					Double totalfee = null;
					if (tranfee != null)
						totalfee = tranfee;

					if (fixfee != null)
						if (totalfee == null)
							totalfee = fixfee;
						else
							totalfee = totalfee + fixfee;

					if (otherfee != null)
						if (totalfee == null)
							totalfee = otherfee;
						else
							totalfee = totalfee + otherfee;
//					//LDAP sn ȡ��cn	
//					String baoxiaoemp =  LDAPPeopleUtil.getLDAPCNBySN(baoxiaoempuid);
//					String totalmgr = LDAPPeopleUtil.getLDAPCNBySN(totalmgruid);
//					String deptmgr = LDAPPeopleUtil.getLDAPCNBySN(deptmgruid);
//					String caiwumgr = LDAPPeopleUtil.getLDAPCNBySN(caiwumgruid);					
					//do_org_user_link user_uid ȡ��user_cn				
					String baoxiaoemp =  MySqlOperationII.getUserCNByUserUid(conii, baoxiaoempuid);
					String totalmgr = MySqlOperationII.getUserCNByUserUid(conii, totalmgruid);
					String deptmgr = MySqlOperationII.getUserCNByUserUid(conii, deptmgruid);
					String caiwumgr = MySqlOperationII.getUserCNByUserUid(conii, caiwumgruid);
					
					bxms.setDept(mgrdept);
					bxms.setBx_date(baoxiaotime);
					bxms.setBx_people(baoxiaoemp);
					bxms.setProject(project);
					bxms.setLptranf(lptranf);
					bxms.setTranfee(tranfee);
					bxms.setLpfixf(lpfixf);
					bxms.setFixfee(fixfee);
					bxms.setLpotherf(lpotherf);
					bxms.setOtherfee(otherfee);
					bxms.setTotalfee(totalfee);

					bxms.setMgrdept(deptmgr);
					bxms.setMgrcaiwu(caiwumgr);
					bxms.setMgrtotal(totalmgr);
					bxms.setShenhe(baoxiaostate);
					//�ر���ݿ�����
					conii.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// /��������ļ�·��
				templatePath = DOGlobals.getInstance().getValue("uploadfiletemp");
				templateName = "zifengbxdetail_template.xls";
				template = templatePath + templateName;
				createExlPath = templatePath + baoxiaoempuid + "_zfbxdetail.xls";
				// ��ɱ���Excel�ļ�
				try {
					createExcel(template, createExlPath, bxms);
				} catch (Exception e) {
					e.printStackTrace();
					return "��������Excel�ļ�ʧ�ܣ�3";
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				BOInstance bi = new BOInstance();
				bi.putValue("exlfile", createExlPath);
				this.setInstance(bi);
				return this.DEFAULT_FORWARD;
				
			} else {				
				return "��ɱ���Excel�ļ�ʧ�ܣ�2";
			}

			
			
			
	}

	// �ɳ��б��ȡ�ó�����
	private String getCityname(Connection conn, String citycode)
			throws SQLException {
		ResultSet rscity = MySqlOperation.cityBasic(conn, citycode);
		String address = null;
		while (rscity.next()) {
			address = rscity.getString("cityname");
		}
		return address;
	}

	// ��ɱ���Excel
	private void createExcel(String templateFileName, String targetFileName,
			BaoxiaoMessages bms) throws ParsePropertyException, IOException, InvalidFormatException {
		List<BaoxiaoMessages> departments = new ArrayList<BaoxiaoMessages>();
		departments.add(bms);
		Map<String, List<BaoxiaoMessages>> beans = new HashMap<String, List<BaoxiaoMessages>>();
		beans.put("department", departments);
		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS(templateFileName, beans, targetFileName);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Double d = null;
		Double d1 = 0.10;
		System.out.println(d + d1);

	}

}
