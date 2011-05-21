package com.exedosoft.plat.login.zidingyi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.login.zidingyi.excel.MySqlOperation;
import com.exedosoft.plat.login.zidingyi.excel.OperationUtil;
import com.exedosoft.plat.search.customize.SearchTransCode;
import com.exedosoft.plat.util.DOGlobals;

public class WLogCountBuZhu extends DOAbstractAction {
	public String excute() {

		/***
		 * 带薪休假出、休假归没更改完成 带薪休假出、休假归没更改完成 带薪休假出、休假归没更改完成
		 * */
		// 上一条记录的数据
		String upperwaddress = null;
		String upperwseladdress = null;

		// 是否若为休假审批后更新
		String xj_type = null;
		String cw_type = null;
		String cw_xjsp = null;
		String is_xinjia = null;

		// 计算当天的补助
		double allbzbasic = 0.00;
		String curr_uid = null;
		boolean isXinjia = true;
		boolean ifhavexj = false;
		boolean isXJSp = false;
		/**
		 * ifhavexc:是否存在出差或休假的数据 cg出差归来, cq出差去, cgcq归来后再出差去 xg休假归, xq休假去,
		 * xgxq休假归来后在去休假 xgcg休假归来后出差归 xgcq休假归来后出差去 cgxq出差归来后休假去 cqxq出差后去休假
		 * */
		String ifhavexc = null;
		Date dateWdate = null;

		String wlog_uid = null;
		String xc_days = null;
		String xc_money = null;

		String waddress = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("waddress");
		String wseladdress = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("wseladdress");
		String wdate = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("wdate");
		String user_uid = DOGlobals.getInstance().getSessoinContext()
				.getInstance().getUser().getUid();
		
		if(wdate == null || "".equals(wdate)) {
			isXJSp = true;
		}
		List users = new ArrayList();
		try {
			DOBO cwtheBO = DOBO.getDOBOByName("cw_worklog");
			BOInstance bi = cwtheBO.getCorrInstance();
			if (bi != null) {
				curr_uid = bi.getUid();
				wlog_uid = curr_uid;
				if (waddress == null || "".equals(waddress.trim())) {
					waddress = bi.getValue("waddress");
				}
				xj_type = bi.getValue("xj_type");

				if (wdate == null || "".equals(wdate.trim())) {
					wdate = bi.getValue("wdate");
				}
				if (cw_type == null || "".equals(cw_type.trim())) {
					cw_type = bi.getValue("cw_type");
				}
				if (is_xinjia == null || "".equals(is_xinjia.trim())) {
					is_xinjia = bi.getValue("is_xinjia");
				}

				String is_xinjia1 = bi.getValue("is_xinjia");
				if (is_xinjia1 != null && "xj".equals(is_xinjia1)) {
					isXinjia = false;
				} else {
					isXinjia = true;
				}

				DOService bzservice = DOService
						.getService("cw_modifycity_browse_by_wlog");
				List bzList = new ArrayList();
				bzList = bzservice.invokeSelect(user_uid, wdate);
				if (bzList != null && bzList.size() > 0) {
					BOInstance bzBi = (BOInstance) bzList.get(0);
					upperwaddress = bzBi.getValue("waddress");
					upperwseladdress = bzBi.getValue("wseladdress");
				}
				DOService service = DOService
						.getService("cw_modifycity_browse_by_wlog");
				users = service.invokeSelect(curr_uid);
			}

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			dateWdate = format.parse(wdate);
		} catch (Exception e) {
			this.setEchoValue("新增失败！error" + e.toString());
			return "notpass";
		}

		/**
		 * 取第一条记录，然后按二种情况历遍
		 * */
		if (users != null && users.size() > 0) {

			// 标志：判断上一次类型，
			boolean isChucha = false;
			boolean isZhongzhuan = false;
			boolean isFanhui = false;
			boolean isXiujiaChu = false;
			boolean isXiujiaGui = false;

			BOInstance bi = (BOInstance) users.get(0);
			String modifytype = bi.getValue("modifytype");
			String lasttime = bi.getValue("modifytime");
			String lasttime2 = bi.getValue("modifytime2");

			String stbz = bi.getValue("bzbasice");
			double bzbasic = 0.00;
			if (stbz != null) {
				bzbasic = Double.parseDouble(stbz);
			}

			String stbzend = bi.getValue("bzbasiceend");
			double bzbasicend = 0.00;
			if (stbzend != null) {
				bzbasicend = Double.parseDouble(stbzend);
			}

			// 统一 补贴标准，每次需设置
			double basic = bzbasicend;
			// 上一次起始时间和终止时间
			double lastBeginTime = getBeginHours(lasttime);
			double lastEndTime = getEndHours(lasttime2);

			WLogBuzhuTongjiUtil.deleteCW_XC(wlog_uid);

			// 只有一条记录时
			if (users.size() == 1) {
				// 出差：
				if (modifytype != null && "1".equals(modifytype)) {
					basic = bzbasicend;
					allbzbasic = (lastEndTime - lastBeginTime) * 1.00 * basic
							/ 24;

					allbzbasic = allbzbasic + (24 - lastEndTime) * 1.00 * basic
							/ 24;
					//
					// 以小时计算
					double hours = (24 - lastBeginTime);
					xc_days = OperationUtil.round(hours/24, 2);
					String cx_endtime = OperationUtil.round(24, 2);
					ifhavexc = "cq";
					xc_money = OperationUtil.round(allbzbasic, 2);
					WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "cq",
							xc_days, xc_money, cx_endtime);
				}
				// 中转
				else if (modifytype != null && "2".equals(modifytype)) {
					// 以小时计算中转前
					basic = bzbasic;
					allbzbasic = lastBeginTime * 1.00 / 24 * basic;

					// 以小时计算 中转中
					basic = bzbasicend;
					allbzbasic = allbzbasic + (lastEndTime - lastBeginTime)
							* 1.00 / 24 * basic;

					// 以小时计算 中转后
					basic = bzbasicend;
					allbzbasic = allbzbasic + (24 - lastEndTime) * 1.00 / 24
							* basic;

					double hours = 24;
					xc_days = OperationUtil.round(1, 2);
					String cx_endtime = OperationUtil.round(24, 2);
					ifhavexc = "cq";
					xc_money = OperationUtil.round(allbzbasic, 2);
					WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "cq",
							xc_days, xc_money, cx_endtime);

				}
				// 返回
				else if (modifytype != null && "3".equals(modifytype)) {
					basic = bzbasic;
					allbzbasic = lastEndTime * 1.00 / 24 * basic;

					// 以小时计算
					double hours = lastEndTime;
					xc_days = OperationUtil.round(hours / 24, 2);
					String cx_endtime = OperationUtil.round(lastEndTime, 2);
					ifhavexc = "cg";
					xc_money = OperationUtil.round(allbzbasic, 2);
					WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "cg",
							xc_days, xc_money, cx_endtime);
				}
				// 休假出
				else if (modifytype != null && "4".equals(modifytype)) {
					basic = bzbasic;
					String cx_endtime = "24.00";
					double xc_bzbasic = 0.00D;
					xc_bzbasic = lastBeginTime * 1.00 / 24 * basic;
					xc_days = OperationUtil.round(lastBeginTime / 24, 2);
					cx_endtime = OperationUtil.round(lastBeginTime, 2);
					ifhavexc = "cq";
					xc_money = OperationUtil.round(xc_bzbasic, 2);
					WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "cq",
							xc_days, xc_money, cx_endtime);

					allbzbasic = xc_bzbasic;
					if (isXinjia) {
						xc_bzbasic = (24 - lastBeginTime) * 1.00 / 24 * basic;
						is_xinjia = "xx";
					} else {
						xc_bzbasic = 0;
						is_xinjia = "xj";
					}

					xc_days = OperationUtil.round((24 - lastBeginTime), 2);
					cx_endtime = OperationUtil.round(24, 2);

					ifhavexc = "xq";
					xc_money = OperationUtil.round(xc_bzbasic, 2);
					WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "xq",
							xc_days, xc_money, cx_endtime);
					allbzbasic = allbzbasic + xc_bzbasic;
					ifhavexj = true;
				}
				// 休假归
				else if (modifytype != null && "5".equals(modifytype)) {
					basic = bzbasicend;
					String cx_endtime = "24.00";
					double xc_bzbasic = 0.00D;
					if (isXinjia) {
						xc_bzbasic = lastEndTime * 1.00 / 24 * basic;
					} else {
						xc_bzbasic = 0;
					}
					xc_days = OperationUtil.round(lastEndTime, 2);
					cx_endtime = OperationUtil.round(lastEndTime, 2);
					ifhavexc = "xg";
					xc_money = OperationUtil.round(xc_bzbasic, 2);
					WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "xg",
							xc_days, xc_money, cx_endtime);
					allbzbasic = xc_bzbasic;

					xc_bzbasic = (24 - lastEndTime) * 1.00 / 24 * basic;
					xc_days = OperationUtil.round((24 - lastEndTime), 2);
					cx_endtime = OperationUtil.round(24, 2);
					ifhavexc = "xg";
					xc_money = OperationUtil.round(xc_bzbasic, 2);
					WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "xg",
							xc_days, xc_money, cx_endtime);
					allbzbasic = allbzbasic + xc_bzbasic;
				}

				// 多条记录时
			} else {

				// 初始化第一条记录数据

				// 出差：
				if (modifytype != null && "1".equals(modifytype)) {
					basic = bzbasicend;
					allbzbasic = (lastEndTime - lastBeginTime) * 1.00 * basic
							/ 24;
					//
					// 以小时计算
					double hours = (lastEndTime - lastBeginTime);
					xc_days = OperationUtil.round(hours / 24, 2);
					String cx_endtime = OperationUtil.round(
							(lastEndTime - lastBeginTime), 2);
					ifhavexc = "cq";
					xc_money = OperationUtil.round(allbzbasic, 2);
					WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "cq",
							xc_days, xc_money, cx_endtime);
				}
				// 中转
				else if (modifytype != null && "2".equals(modifytype)) {
					// 以小时计算中转前
					basic = bzbasic;
					allbzbasic = lastBeginTime * 1.00 / 24 * basic;

					// 以小时计算 中转中
					basic = bzbasicend;
					allbzbasic = allbzbasic + (lastEndTime - lastBeginTime)
							* 1.00 / 24 * basic;

					// 以小时计算 中转后
					// 不用计算

					double hours = lastEndTime;
					xc_days = OperationUtil.round(hours / 24, 2);
					String cx_endtime = OperationUtil.round(lastEndTime, 2);
					ifhavexc = "cq";
					xc_money = OperationUtil.round(allbzbasic, 2);
					WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "cq",
							xc_days, xc_money, cx_endtime);

				}
				// 返回
				else if (modifytype != null && "3".equals(modifytype)) {
					basic = bzbasic;
					allbzbasic = lastEndTime * 1.00 / 24 * basic;

					// 以小时计算
					double hours = lastEndTime;
					xc_days = OperationUtil.round(hours / 24, 2);
					String cx_endtime = OperationUtil.round(lastEndTime, 2);
					ifhavexc = "cg";
					xc_money = OperationUtil.round(allbzbasic, 2);
					WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "cg",
							xc_days, xc_money, cx_endtime);
				}
				// 休假出
				else if (modifytype != null && "4".equals(modifytype)) {
					basic = bzbasic;
					String cx_endtime = "24.00";
					double xc_bzbasic = 0.00D;
					xc_bzbasic = lastBeginTime * 1.00 / 24 * basic;
					xc_days = OperationUtil.round(lastBeginTime / 24, 2);
					cx_endtime = OperationUtil.round(lastBeginTime, 2);
					ifhavexc = "cq";
					xc_money = OperationUtil.round(xc_bzbasic, 2);
					WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "cq",
							xc_days, xc_money, cx_endtime);

					allbzbasic = xc_bzbasic;
					if (isXinjia) {
						xc_bzbasic = (lastEndTime - lastBeginTime) * 1.00 / 24
								* basic;
						is_xinjia = "xx";
					} else {
						xc_bzbasic = 0;
						is_xinjia = "xj";
					}

					xc_days = OperationUtil.round(
							(lastEndTime - lastBeginTime), 2);
					cx_endtime = OperationUtil.round(lastEndTime, 2);

					ifhavexc = "xq";
					xc_money = OperationUtil.round(xc_bzbasic, 2);
					WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "xq",
							xc_days, xc_money, cx_endtime);
					allbzbasic = allbzbasic + xc_bzbasic;
					is_xinjia = "xx";ifhavexj = true;
				}
				// 休假归
				else if (modifytype != null && "5".equals(modifytype)) {
					basic = bzbasicend;
					String cx_endtime = "24.00";
					double xc_bzbasic = 0.00D;
					if (isXinjia) {
						xc_bzbasic = lastEndTime * 1.00 / 24 * basic;
					} else {
						xc_bzbasic = 0;
					}
					xc_days = OperationUtil.round(lastEndTime, 2);
					cx_endtime = OperationUtil.round(lastEndTime, 2);
					ifhavexc = "xg";
					xc_money = OperationUtil.round(xc_bzbasic, 2);
					WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "xg",
							xc_days, xc_money, cx_endtime);
					allbzbasic = xc_bzbasic;
				}

				boolean isFinally = false;
				for (int i = 1; i < users.size(); i++) {
					if (i == users.size() - 1) {
						isFinally = true;
					}

					BOInstance nextbi = (BOInstance) users.get(i);
					String nexttype = nextbi.getValue("modifytype");
					String nexttime = nextbi.getValue("modifytime");
					String nexttime2 = nextbi.getValue("modifytime2");

					String nextstbz = nextbi.getValue("bzbasice");
					double nextbasic = 0.00;
					if (nextstbz != null) {
						nextbasic = Double.parseDouble(nextstbz);
					}

					String nextbzend = nextbi.getValue("bzbasiceend");
					double nextbasicend = 0.00;
					if (nextbzend != null) {
						nextbasicend = Double.parseDouble(nextbzend);
					}

					// 上一次起始时间和终止时间
					double nextBeginTime = getBeginHours(nexttime);
					double nextEndTime = getEndHours(nexttime2);

					// 上条记录为出差或中转:本次记录可能为中转(modifytype==2)、返回(modifytype==3)、休假出(modifytype==4)
					if (isChucha || isZhongzhuan) {
						// 中转：需计算始发地的补贴，若>0，则需重新设定数据;否则，只更改lastEndTime=nextEndTime;
						if (nexttype != null && "2".equals(nexttype.trim())) {
							if (isFinally) {
								// 以小时计算中转前
								basic = bzbasic;
								double tempbzbasic = (nextBeginTime - lastEndTime)
										* 1.00 / 24 * basic;

								// 以小时计算 中转中
								basic = bzbasicend;
								tempbzbasic = tempbzbasic
										+ (nextEndTime - nextBeginTime) * 1.00
										/ 24 * basic;

								// 以小时计算 中转后
								basic = bzbasicend;
								tempbzbasic = tempbzbasic + (24 - nextEndTime)
										* 1.00 / 24 * basic;

								double hours = (24 - lastEndTime);
								xc_days = OperationUtil.round(hours / 24, 2);
								String cx_endtime = OperationUtil.round(24, 2);
								ifhavexc = "cq";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "cq", xc_days, xc_money,
										cx_endtime);

								allbzbasic = allbzbasic + tempbzbasic;
							} else {
								// 以小时计算中转前
								basic = bzbasic;
								double tempbzbasic = (nextBeginTime - lastEndTime)
										* 1.00 / 24 * basic;

								// 以小时计算 中转中
								basic = bzbasicend;
								tempbzbasic = tempbzbasic
										+ (nextEndTime - nextBeginTime) * 1.00
										/ 24 * basic;

								double hours = (nextEndTime - lastEndTime);
								xc_days = OperationUtil.round(hours / 24, 2);
								String cx_endtime = OperationUtil.round(
										nextEndTime, 2);
								ifhavexc = "cq";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "cq", xc_days, xc_money,
										cx_endtime);

								allbzbasic = allbzbasic + tempbzbasic;
							}
						}
						// 返回：计算补贴即可，无需设定数据
						else if (nexttype != null && "3".equals(nexttype)) {
							if (isFinally) {
								basic = bzbasic;
								double tempbzbasic = (nextEndTime - lastEndTime)
										* 1.00 / 24 * basic;

								// 以小时计算
								double hours = (nextEndTime - lastEndTime);
								xc_days = OperationUtil.round(hours / 24, 2);
								String cx_endtime = OperationUtil.round(
										(nextEndTime - lastEndTime), 2);
								ifhavexc = "cg";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "cg", xc_days, xc_money,
										cx_endtime);
								allbzbasic = allbzbasic + tempbzbasic;
							} else {
								basic = bzbasic;
								double tempbzbasic = (nextEndTime - lastEndTime)
										* 1.00 / 24 * basic;

								// 以小时计算
								double hours = (nextEndTime - lastEndTime);
								xc_days = OperationUtil.round(hours / 24, 2);
								String cx_endtime = OperationUtil.round(
										(nextEndTime - lastEndTime), 2);
								ifhavexc = "cg";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "cg", xc_days, xc_money,
										cx_endtime);
								allbzbasic = allbzbasic + tempbzbasic;
							}
						}
						// 休假出：计算补贴即可，无需设定数据
						else if (nexttype != null && "4".equals(nexttype)) {
							if (isFinally) {
								basic = bzbasic;
								String cx_endtime = "24.00";
								double tempbzbasic = 0.00D;

								tempbzbasic = (nextBeginTime - lastEndTime)
										* 1.00 / 24 * basic;
								xc_days = OperationUtil.round(
										(nextBeginTime - lastEndTime) / 24, 2);
								cx_endtime = OperationUtil.round(nextBeginTime,
										2);
								ifhavexc = "cq";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "cq", xc_days, xc_money,
										cx_endtime);

								allbzbasic = tempbzbasic;

								if (isXinjia) {
									tempbzbasic = (24 - nextBeginTime) * basic
											/ 24;
									xc_days = OperationUtil.round(
											(24 - nextBeginTime) / 24, 2);
									cx_endtime = OperationUtil.round(24, 2);
									is_xinjia = "xx";
								} else {
									tempbzbasic = 0;
									xc_days = OperationUtil.round(
											(24 - nextBeginTime) / 24, 2);
									cx_endtime = OperationUtil.round(24, 2);
									is_xinjia = "xj";ifhavexj = true;
								}

								ifhavexc = "xq";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "xq", xc_days, xc_money,
										cx_endtime);
								allbzbasic = allbzbasic + tempbzbasic;
								ifhavexj = true;
							} else {
								basic = bzbasic;
								String cx_endtime = "24.00";
								double tempbzbasic = 0.00D;

								tempbzbasic = (nextBeginTime - lastEndTime)
										* 1.00 / 24 * basic;
								xc_days = OperationUtil.round(
										(nextBeginTime - lastEndTime) / 24, 2);
								cx_endtime = OperationUtil.round(nextBeginTime,
										2);
								ifhavexc = "cq";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "cq", xc_days, xc_money,
										cx_endtime);

								if (isXinjia) {
									tempbzbasic = (nextEndTime - lastEndTime)
											* basic / 24;
									xc_days = OperationUtil
											.round((nextEndTime - lastEndTime) / 24,
													2);
									cx_endtime = OperationUtil.round(
											nextEndTime, 2);
									is_xinjia = "xx";
								} else {
									tempbzbasic = 0;
									xc_days = OperationUtil
											.round((nextEndTime - lastEndTime) / 24,
													2);
									cx_endtime = OperationUtil.round(
											nextEndTime, 2);
									is_xinjia = "xj";
								}

								ifhavexc = "xq";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "xq", xc_days, xc_money,
										cx_endtime);
								allbzbasic = allbzbasic + tempbzbasic;
								ifhavexj = true;
							}

						} else {
							System.out.println("类型填写出错1001");
							System.out.println("类型填写出错1001");
						}
					}

					// 上条记录为返回:这次则可能为出差(modifytype==1)、休假出(modifytype==4)
					else if (isFanhui) {
						// 出差：只需设定数据
						if (nexttype != null && "1".equals(nexttype)) {
							if (isFinally) {
								double tempbzbasic = 0.00D;
								basic = bzbasicend;
								tempbzbasic = (nextEndTime - nextBeginTime)
										* 1.00 * basic / 24;

								tempbzbasic = tempbzbasic + (24 - nextEndTime)
										* 1.00 * basic / 24;
								//
								// 以小时计算
								double hours = 24 - nextBeginTime;
								xc_days = OperationUtil.round(hours / 24, 2);
								String cx_endtime = OperationUtil.round(hours,
										2);
								ifhavexc = "cq";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "cq", xc_days, xc_money,
										cx_endtime);
								allbzbasic = allbzbasic + tempbzbasic;
							} else {
								double tempbzbasic = 0.00D;
								basic = bzbasicend;
								tempbzbasic = (nextEndTime - nextBeginTime)
										* 1.00 * basic / 24;
								//
								// 以小时计算
								double hours = (nextEndTime - nextBeginTime);
								xc_days = OperationUtil.round(hours / 24, 2);
								String cx_endtime = OperationUtil.round(hours,
										2);
								ifhavexc = "cq";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "cq", xc_days, xc_money,
										cx_endtime);
								allbzbasic = allbzbasic + tempbzbasic;
							}

						}
						// 休假出：因为上一次为返回，故这次休假出无需操作
						else if (modifytype != null && "4".equals(modifytype)) {
							if (isFinally) {
								basic = bzbasic;
								String cx_endtime = "24.00";
								double tempbzbasic = 0.00D;

								tempbzbasic = (nextBeginTime - lastEndTime)
										* 1.00 / 24 * basic;
								xc_days = OperationUtil.round(
										(nextBeginTime - lastEndTime) / 24, 2);
								cx_endtime = OperationUtil.round(nextBeginTime,
										2);
								ifhavexc = "cq";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "cq", xc_days, xc_money,
										cx_endtime);

								allbzbasic = tempbzbasic;

								if (isXinjia) {
									tempbzbasic = (24 - nextBeginTime) * basic
											/ 24;
									xc_days = OperationUtil.round(
											(24 - nextBeginTime) / 24, 2);
									cx_endtime = OperationUtil.round(24, 2);
									is_xinjia = "xx";
								} else {
									tempbzbasic = 0;
									xc_days = OperationUtil.round(
											(24 - nextBeginTime) / 24, 2);
									cx_endtime = OperationUtil.round(24, 2);
									is_xinjia = "xj";
								}

								ifhavexc = "xq";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "xq", xc_days, xc_money,
										cx_endtime);
								allbzbasic = allbzbasic + tempbzbasic;
								ifhavexj = true;
							} else {
								basic = bzbasic;
								String cx_endtime = "24.00";
								double tempbzbasic = 0.00D;

								tempbzbasic = (nextBeginTime - lastEndTime)
										* 1.00 / 24 * basic;
								xc_days = OperationUtil.round(
										(nextBeginTime - lastEndTime) / 24, 2);
								cx_endtime = OperationUtil.round(nextBeginTime,
										2);
								ifhavexc = "cq";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "cq", xc_days, xc_money,
										cx_endtime);

								if (isXinjia) {
									tempbzbasic = (nextEndTime - lastEndTime)
											* basic / 24;
									xc_days = OperationUtil
											.round((nextEndTime - lastEndTime) / 24,
													2);
									cx_endtime = OperationUtil.round(
											nextEndTime, 2);
									is_xinjia = "xx";
								} else {
									tempbzbasic = 0;
									xc_days = OperationUtil
											.round((nextEndTime - lastEndTime) / 24,
													2);
									cx_endtime = OperationUtil.round(
											nextEndTime, 2);
									is_xinjia = "xj";
								}

								ifhavexc = "xq";
								xc_money = OperationUtil.round(tempbzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "xq", xc_days, xc_money,
										cx_endtime);
								allbzbasic = allbzbasic + tempbzbasic;
								ifhavexj = true;
							}
						}

						// 上条记录为休假出:本次记录可能为出差(modifytype==1)、中转(modifytype==2)、返回(modifytype==3)、休假归(modifytype==5)

						else if (isXiujiaChu) {
							// 出差：只需设定数据，时间已开始时间为计算点，若为最后一条记录，则需计算
							if (nexttype != null && "1".equals(nexttype)) {
								if (isXinjia) {
									if (isFinally) {
										double tempbzbasic = 0.00D;
										basic = bzbasicend;
										tempbzbasic = (nextEndTime - lastEndTime)
												* 1.00 * basic / 24;

										tempbzbasic = tempbzbasic
												+ (24 - nextEndTime) * 1.00
												* basic / 24;
										//
										// 以小时计算
										double hours = 24 - lastEndTime;
										xc_days = OperationUtil.round(
												hours / 24, 2);
										String cx_endtime = OperationUtil
												.round(hours, 2);
										ifhavexc = "cq";
										xc_money = OperationUtil.round(
												tempbzbasic, 2);
										WLogBuzhuTongjiUtil
												.insertOrUpdateCW_XC(wlog_uid,
														"cq", xc_days,
														xc_money, cx_endtime);
										allbzbasic = allbzbasic + tempbzbasic;
									} else {
										double tempbzbasic = 0.00D;
										basic = bzbasicend;
										tempbzbasic = (nextEndTime - lastEndTime)
												* 1.00 * basic / 24;
										//
										// 以小时计算
										double hours = (nextEndTime - lastEndTime);
										xc_days = OperationUtil.round(
												hours / 24, 2);
										String cx_endtime = OperationUtil
												.round(hours, 2);
										ifhavexc = "cq";
										xc_money = OperationUtil.round(
												tempbzbasic, 2);
										WLogBuzhuTongjiUtil
												.insertOrUpdateCW_XC(wlog_uid,
														"cq", xc_days,
														xc_money, cx_endtime);
										allbzbasic = allbzbasic + tempbzbasic;
									}
								} else {
									if (isFinally) {
										double tempbzbasic = 0.00D;
										basic = bzbasicend;
										tempbzbasic = (nextEndTime - nextBeginTime)
												* 1.00 * basic / 24;

										tempbzbasic = tempbzbasic
												+ (24 - nextEndTime) * 1.00
												* basic / 24;
										//
										// 以小时计算
										double hours = 24 - nextBeginTime;
										xc_days = OperationUtil.round(
												hours / 24, 2);
										String cx_endtime = OperationUtil
												.round(hours, 2);
										ifhavexc = "cq";
										xc_money = OperationUtil.round(
												tempbzbasic, 2);
										WLogBuzhuTongjiUtil
												.insertOrUpdateCW_XC(wlog_uid,
														"cq", xc_days,
														xc_money, cx_endtime);
										allbzbasic = allbzbasic + tempbzbasic;
									} else {
										double tempbzbasic = 0.00D;
										basic = bzbasicend;
										tempbzbasic = (nextEndTime - nextBeginTime)
												* 1.00 * basic / 24;
										//
										// 以小时计算
										double hours = (nextEndTime - nextBeginTime);
										xc_days = OperationUtil.round(
												hours / 24, 2);
										String cx_endtime = OperationUtil
												.round(hours, 2);
										ifhavexc = "cq";
										xc_money = OperationUtil.round(
												tempbzbasic, 2);
										WLogBuzhuTongjiUtil
												.insertOrUpdateCW_XC(wlog_uid,
														"cq", xc_days,
														xc_money, cx_endtime);
										allbzbasic = allbzbasic + tempbzbasic;
									}
								}

							}
							// 中转：只需设定数据，时间已开始时间为计算点，若为最后一条记录，则需计算(按出差方式计算)
							else if (nexttype != null && "2".equals(nexttype)) {
								if (isXinjia) {
									if (isFinally) {
										double tempbzbasic = 0.00D;
										basic = bzbasicend;
										tempbzbasic = (nextEndTime - lastEndTime)
												* 1.00 * basic / 24;

										tempbzbasic = tempbzbasic
												+ (24 - nextEndTime) * 1.00
												* basic / 24;
										//
										// 以小时计算
										double hours = 24 - lastEndTime;
										xc_days = OperationUtil.round(
												hours / 24, 2);
										String cx_endtime = OperationUtil
												.round(hours, 2);
										ifhavexc = "cq";
										xc_money = OperationUtil.round(
												tempbzbasic, 2);
										WLogBuzhuTongjiUtil
												.insertOrUpdateCW_XC(wlog_uid,
														"cq", xc_days,
														xc_money, cx_endtime);
										allbzbasic = allbzbasic + tempbzbasic;
									} else {
										double tempbzbasic = 0.00D;
										basic = bzbasicend;
										tempbzbasic = (nextEndTime - lastEndTime)
												* 1.00 * basic / 24;
										//
										// 以小时计算
										double hours = (nextEndTime - lastEndTime);
										xc_days = OperationUtil.round(
												hours / 24, 2);
										String cx_endtime = OperationUtil
												.round(hours, 2);
										ifhavexc = "cq";
										xc_money = OperationUtil.round(
												tempbzbasic, 2);
										WLogBuzhuTongjiUtil
												.insertOrUpdateCW_XC(wlog_uid,
														"cq", xc_days,
														xc_money, cx_endtime);
										allbzbasic = allbzbasic + tempbzbasic;
									}
								} else {
									if (isFinally) {
										double tempbzbasic = 0.00D;
										basic = bzbasicend;
										tempbzbasic = (nextEndTime - nextBeginTime)
												* 1.00 * basic / 24;

										tempbzbasic = tempbzbasic
												+ (24 - nextEndTime) * 1.00
												* basic / 24;
										//
										// 以小时计算
										double hours = 24 - nextBeginTime;
										xc_days = OperationUtil.round(
												hours / 24, 2);
										String cx_endtime = OperationUtil
												.round(hours, 2);
										ifhavexc = "cq";
										xc_money = OperationUtil.round(
												tempbzbasic, 2);
										WLogBuzhuTongjiUtil
												.insertOrUpdateCW_XC(wlog_uid,
														"cq", xc_days,
														xc_money, cx_endtime);
										allbzbasic = allbzbasic + tempbzbasic;
									} else {
										double tempbzbasic = 0.00D;
										basic = bzbasicend;
										tempbzbasic = (nextEndTime - nextBeginTime)
												* 1.00 * basic / 24;
										//
										// 以小时计算
										double hours = (nextEndTime - nextBeginTime);
										xc_days = OperationUtil.round(
												hours / 24, 2);
										String cx_endtime = OperationUtil
												.round(hours, 2);
										ifhavexc = "cq";
										xc_money = OperationUtil.round(
												tempbzbasic, 2);
										WLogBuzhuTongjiUtil
												.insertOrUpdateCW_XC(wlog_uid,
														"cq", xc_days,
														xc_money, cx_endtime);
										allbzbasic = allbzbasic + tempbzbasic;
									}
								}
							}

							// 返回：只需设定数据，时间已开始时间为计算点，计算补贴
							else if (nexttype != null && "3".equals(nexttype)) {
								double tempbzbasic = 0.00D;
								basic = bzbasicend;
								if (isXinjia) {
									
									tempbzbasic = (nextEndTime - lastBeginTime)
											* 1.00 * basic / 24;
									
								} else {
									tempbzbasic = 0;
								}
								
								//
								// 以小时计算
								double hours = (nextEndTime - nextBeginTime);
								xc_days = OperationUtil
										.round(hours / 24, 2);
								String cx_endtime = OperationUtil.round(
										nextEndTime, 2);
								ifhavexc = "xg";
								xc_money = OperationUtil.round(tempbzbasic,
										2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
										wlog_uid, "xg", xc_days, xc_money,
										cx_endtime);
								allbzbasic = allbzbasic + tempbzbasic;

							}
							/**
							 * 休假归：这里无论是休假归到原来地还是另一个地方，都已nextEndTime为休假结束时间
							 * 只需设定数据:lastBeginTime和lastEndTime都为nextEndTime
							 * 若为最后一条记录，则需计算
							 * */
							else if (modifytype != null
									&& "5".equals(modifytype)) {
								basic = bzbasicend;
								String cx_endtime = "24.00";
								double xc_bzbasic = 0.00D;
								if (isXinjia) {
									xc_bzbasic = (nextEndTime-lastEndTime) * 1.00 / 24 * basic;
								} else {
									xc_bzbasic = 0;
								}
								xc_days = OperationUtil.round((nextEndTime-lastEndTime), 2);
								cx_endtime = OperationUtil.round(nextEndTime, 2);
								ifhavexc = "xg";
								xc_money = OperationUtil.round(xc_bzbasic, 2);
								WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "xg",
										xc_days, xc_money, cx_endtime);
								allbzbasic = allbzbasic + xc_bzbasic;
								if(isFinally) {
									xc_bzbasic = (24 - nextEndTime) * 1.00 / 24 * basic;
									xc_days = OperationUtil.round((24 - nextEndTime), 2);
									cx_endtime = OperationUtil.round(24, 2);
									ifhavexc = "xg";
									xc_money = OperationUtil.round(xc_bzbasic, 2);
									WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(wlog_uid, "xg",
											xc_days, xc_money, cx_endtime);
									allbzbasic = allbzbasic + xc_bzbasic;
								}
								

							} else {
								System.out.println("类型填写出错1003");
								System.out.println("类型填写出错1003");
							}
						}

						// 上条记录为休假归:本次记录可能为出差、中转、返回、休假出
						else if (isXiujiaGui) {
							// 出差：只需设定数据
							if (nexttype != null && "1".equals(nexttype)) {
								if (isFinally) {
									double tempbzbasic = 0.00D;
									basic = bzbasicend;
									tempbzbasic = (nextEndTime - nextBeginTime)
											* 1.00 * basic / 24;

									tempbzbasic = tempbzbasic
											+ (24 - nextEndTime) * 1.00 * basic
											/ 24;
									//
									// 以小时计算
									double hours = 24 - nextBeginTime;
									xc_days = OperationUtil
											.round(hours / 24, 2);
									String cx_endtime = OperationUtil.round(
											hours, 2);
									ifhavexc = "cq";
									xc_money = OperationUtil.round(tempbzbasic,
											2);
									WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
											wlog_uid, "cq", xc_days, xc_money,
											cx_endtime);
									allbzbasic = allbzbasic + tempbzbasic;
								} else {
									double tempbzbasic = 0.00D;
									basic = bzbasicend;
									tempbzbasic = (nextEndTime - nextBeginTime)
											* 1.00 * basic / 24;
									//
									// 以小时计算
									double hours = (nextEndTime - nextBeginTime);
									xc_days = OperationUtil
											.round(hours / 24, 2);
									String cx_endtime = OperationUtil.round(
											hours, 2);
									ifhavexc = "cq";
									xc_money = OperationUtil.round(tempbzbasic,
											2);
									WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
											wlog_uid, "cq", xc_days, xc_money,
											cx_endtime);
									allbzbasic = allbzbasic + tempbzbasic;
								}
							}

							// 中转：需计算始发地的补贴，若>0，则需重新设定数据;否则，只更改lastEndTime=nextEndTime;
							else if (nexttype != null
									&& "2".equals(nexttype.trim())) {
								if (isFinally) {
									// 以小时计算中转前
									basic = bzbasic;
									double tempbzbasic = (nextBeginTime - lastEndTime)
											* 1.00 / 24 * basic;

									// 以小时计算 中转中
									basic = bzbasicend;
									tempbzbasic = tempbzbasic
											+ (nextEndTime - nextBeginTime)
											* 1.00 / 24 * basic;

									// 以小时计算 中转后
									basic = bzbasicend;
									tempbzbasic = tempbzbasic
											+ (24 - nextEndTime) * 1.00 / 24
											* basic;

									double hours = (24 - lastEndTime);
									xc_days = OperationUtil
											.round(hours / 24, 2);
									String cx_endtime = OperationUtil.round(24,
											2);
									ifhavexc = "cq";
									xc_money = OperationUtil.round(tempbzbasic,
											2);
									WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
											wlog_uid, "cq", xc_days, xc_money,
											cx_endtime);

									allbzbasic = allbzbasic + tempbzbasic;
								} else {
									// 以小时计算中转前
									basic = bzbasic;
									double tempbzbasic = (nextBeginTime - lastEndTime)
											* 1.00 / 24 * basic;

									// 以小时计算 中转中
									basic = bzbasicend;
									tempbzbasic = tempbzbasic
											+ (nextEndTime - nextBeginTime)
											* 1.00 / 24 * basic;

									double hours = (nextEndTime - lastEndTime);
									xc_days = OperationUtil
											.round(hours / 24, 2);
									String cx_endtime = OperationUtil.round(
											nextEndTime, 2);
									ifhavexc = "cq";
									xc_money = OperationUtil.round(tempbzbasic,
											2);
									WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
											wlog_uid, "cq", xc_days, xc_money,
											cx_endtime);

									allbzbasic = allbzbasic + tempbzbasic;
								}
							}

							// 返回：计算补贴即可，无需设定数据
							else if (nexttype != null && "3".equals(nexttype)) {
								if (isFinally) {
									basic = bzbasic;
									double tempbzbasic = (nextEndTime - lastEndTime)
											* 1.00 / 24 * basic;

									// 以小时计算
									double hours = (nextEndTime - lastEndTime);
									xc_days = OperationUtil
											.round(hours / 24, 2);
									String cx_endtime = OperationUtil.round(
											(nextEndTime - lastEndTime), 2);
									ifhavexc = "cg";
									xc_money = OperationUtil.round(tempbzbasic,
											2);
									WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
											wlog_uid, "cg", xc_days, xc_money,
											cx_endtime);
									allbzbasic = allbzbasic + tempbzbasic;
								} else {
									basic = bzbasic;
									double tempbzbasic = (nextEndTime - lastEndTime)
											* 1.00 / 24 * basic;

									// 以小时计算
									double hours = (nextEndTime - lastEndTime);
									xc_days = OperationUtil
											.round(hours / 24, 2);
									String cx_endtime = OperationUtil.round(
											(nextEndTime - lastEndTime), 2);
									ifhavexc = "cg";
									xc_money = OperationUtil.round(tempbzbasic,
											2);
									WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
											wlog_uid, "cg", xc_days, xc_money,
											cx_endtime);
									allbzbasic = allbzbasic + tempbzbasic;
								}
							}

							// 休假出：计算补贴即可，无需设定数据
							else if (modifytype != null
									&& "4".equals(modifytype)) {
								if (isFinally) {
									basic = bzbasic;
									String cx_endtime = "24.00";
									double tempbzbasic = 0.00D;

									tempbzbasic = (nextBeginTime - lastEndTime)
											* 1.00 / 24 * basic;
									xc_days = OperationUtil.round(
											(nextBeginTime - lastEndTime) / 24, 2);
									cx_endtime = OperationUtil.round(nextBeginTime,
											2);
									ifhavexc = "cq";
									xc_money = OperationUtil.round(tempbzbasic, 2);
									WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
											wlog_uid, "cq", xc_days, xc_money,
											cx_endtime);

									allbzbasic = tempbzbasic;

									if (isXinjia) {
										tempbzbasic = (24 - nextBeginTime) * basic
												/ 24;
										xc_days = OperationUtil.round(
												(24 - nextBeginTime) / 24, 2);
										cx_endtime = OperationUtil.round(24, 2);
										is_xinjia = "xx";
									} else {
										tempbzbasic = 0;
										xc_days = OperationUtil.round(
												(24 - nextBeginTime) / 24, 2);
										cx_endtime = OperationUtil.round(24, 2);
										is_xinjia = "xj";
									}

									ifhavexc = "xq";
									xc_money = OperationUtil.round(tempbzbasic, 2);
									WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
											wlog_uid, "xq", xc_days, xc_money,
											cx_endtime);
									allbzbasic = allbzbasic + tempbzbasic;
									ifhavexj = true;
								} else {
									basic = bzbasic;
									String cx_endtime = "24.00";
									double tempbzbasic = 0.00D;

									tempbzbasic = (nextBeginTime - lastEndTime)
											* 1.00 / 24 * basic;
									xc_days = OperationUtil.round(
											(nextBeginTime - lastEndTime) / 24, 2);
									cx_endtime = OperationUtil.round(nextBeginTime,
											2);
									ifhavexc = "cq";
									xc_money = OperationUtil.round(tempbzbasic, 2);
									WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
											wlog_uid, "cq", xc_days, xc_money,
											cx_endtime);

									if (isXinjia) {
										tempbzbasic = (nextEndTime - lastEndTime)
												* basic / 24;
										xc_days = OperationUtil
												.round((nextEndTime - lastEndTime) / 24,
														2);
										cx_endtime = OperationUtil.round(
												nextEndTime, 2);
										is_xinjia = "xx";
									} else {
										tempbzbasic = 0;
										xc_days = OperationUtil
												.round((nextEndTime - lastEndTime) / 24,
														2);
										cx_endtime = OperationUtil.round(
												nextEndTime, 2);
										is_xinjia = "xj";
									}

									ifhavexc = "xq";
									xc_money = OperationUtil.round(tempbzbasic, 2);
									WLogBuzhuTongjiUtil.insertOrUpdateCW_XC(
											wlog_uid, "xq", xc_days, xc_money,
											cx_endtime);
									allbzbasic = allbzbasic + tempbzbasic;
									ifhavexj = true;
								}

							} else {
								System.out.println("类型填写出错1004");
								System.out.println("类型填写出错1004");
							}
						}

					}
					lastBeginTime = nextBeginTime;
					lastEndTime = nextEndTime;

				}
			}
		} else {
//			if (waddress != null && waddress.equals(upperwaddress)) {
//				if (!waddress.equals("1")) {
//					if (upperwaddress != null && wseladdress != null
//							&& !upperwaddress.equals(wseladdress)) {
//						this.setEchoValue("地点已变更，请填写变更信息。");
//						return "notpass";
//					}
//				}
//			} else if (waddress != null) {
//				this.setEchoValue("地点已变更，请填写变更信息。");
//				return "notpass";
//			}

			allbzbasic = WLogBuzhuTongjiUtil.getBZbasic(waddress);
		}

		
		if(!isXJSp) {
//			cw_worklog_browse_getcw_type_last
			if(users != null && users.size() > 0) {
				//cw_type:状态:在公司'gs',先回公司再出差'gscc',在出差'cc',休假出'xj',,薪假出'xx',
				//cw_xjsp:当天内请假并归回，或其它请假，由部门经理审批的。y
				//只按照最后一条记录判断
				//modifytype = 1: 出差，
				//modifytype = 2: 中转，
				//modifytype = 3: 返回，
				//modifytype = 4: 休假出，
				//modifytype = 5: 休假归
				

				//全部考虑，为实现
				boolean isHaveCC = false;
				boolean isHaveFH = false;
				boolean isHaveXJC = false;
				boolean isHaveXJG = false;
				for(int n = 0; n < users.size(); n++) {
					BOInstance bi = (BOInstance) users.get(n);
					String modifytype = bi.getValue("modifytype");
					String vacationtype = bi.getValue("vacationtype");
					String modifytime2 = bi.getValue("modifytime2");
					String endcitytype = bi.getValue("endcitytype");
					
					if(n == users.size()-1) {
						if("1".equals(modifytype) || "2".equals(modifytype)) {
							if(isHaveFH) {
								cw_type = "gscc";
							} else {
								cw_type = "cc";
							}
							if(isHaveXJC && isHaveXJG) {
								cw_xjsp = "y";
							}
						} else if("3".equals(modifytype)) {
							if(isHaveXJC && isHaveXJG) {
								cw_xjsp = "y";
							}
							if(modifytime2 == null || "".equals(modifytime2.trim())) {
								cw_type = "cc";
							} else {
								cw_type = "gs";
							}
						} else if("4".equals(modifytype)) {
							xj_type = vacationtype;
							is_xinjia = "xj";
							cw_type = "xj";
							cw_xjsp = "y";
						} else if("5".equals(modifytype)) {
							if(modifytime2 == null || "".equals(modifytime2.trim())) {
								cw_type = "xj";
							} else {
								if("1".equals(endcitytype)) {
									cw_type = "gs";
								} else {
									cw_type = "cc";
								}
							}
						}
						
						
					} else {
						if("1".equals(modifytype) || "2".equals(modifytype)) {
							isHaveCC = true;
						}
						if("3".equals(modifytype)) {
							isHaveFH = true;
						}
						if("4".equals(modifytype)) {
							xj_type = vacationtype;
							is_xinjia = "xj";
							isHaveXJC = true;
						}
						if("5".equals(modifytype)) {
							isHaveXJG = true;
						}
					}
					
				}
				
				

				//考虑不完整，只取最后一条记录
//				BOInstance bi = (BOInstance) users.get(users.size()-1);
//				String modifytype = bi.getValue("modifytype");
//				String modifytime2 = bi.getValue("modifytime2");
//				String endcitytype = bi.getValue("endcitytype");
//				if("1".equals(modifytype) || "2".equals(modifytype)) {
//					cw_type = "cc";
//				} else if("3".equals(modifytype)) {
//					if(modifytime2 == null || "".equals(modifytime2.trim())) {
//						cw_type = "cc";
//					} else {
//						cw_type = "gs";
//					}
//				} else if("4".equals(modifytype)) {
//					cw_type = "xjc";
//				} else if("5".equals(modifytype)) {
//					if(modifytime2 == null || "".equals(modifytime2.trim())) {
//						cw_type = "xjc";
//					} else {
//						if("1".equals(endcitytype)) {
//							cw_type = "gs";
//						} else {
//							cw_type = "cc";
//						}
//					}
//				}
				
				
			} else {
				//没有变更地点信息时，需取上一条记录判断
				cw_type = getCw_type(waddress);
			}
		} else {
			cw_type = is_xinjia;
			cw_xjsp = "y";
		}
		if(ifhavexj) {
			cw_xjsp = "y";
		}
		
		// 数据存储到数据库
		DOService bzservice = DOService
				.getService("cw_worklog_update_logManage_buzhu");
		String buzhu = OperationUtil.round(allbzbasic, 2);
		Double dbz = Double.valueOf(buzhu);
		try {
			bzservice.invokeUpdate(dbz.toString(), cw_type, cw_xjsp, xj_type,
					is_xinjia, ifhavexc, curr_uid);
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.setEchoValue("补贴存储出错！");
		}

		return DEFAULT_FORWARD;
	}

	 public String getCw_type(String waddress) {
	 // 没有变更地点信息时，需取上一条记录判断
	 String cw_type = "";
	 DOService cw_typeSevr = DOService
	 .getService("cw_worklog_browse_getcw_type_last");
	 List cw_typeList = new ArrayList();
	 try {
	 cw_typeList = cw_typeSevr.invokeSelect();
	 if (cw_typeList != null && cw_typeList.size() > 0) {
	 // 有记录，则取cw_type
	 BOInstance bi = (BOInstance) cw_typeList.get(0);
	 cw_type = bi.getValue("cw_type");
	 if ("gscc".equals(cw_type)) {
	 cw_type = "cc";
	 }
	 if (cw_type == null || "".equals(cw_type.trim())) {
	 if ("1".equals(waddress)) {
	 cw_type = "gs";
	 } else {
	 cw_type = "cc";
	 }
	 }
	 } else {
	 // 没有记录，则取当前记录的工作地点来判断
	 if ("1".equals(waddress)) {
	 cw_type = "gs";
	 } else {
	 cw_type = "cc";
	 }
	 }
	 } catch (Exception e) {
	 this.setEchoValue("error" + e.toString());
	 return "notpass";
	 }
	
	 return cw_type;
	 }

	/**
	 * return 0.00:beginTime为null 其它 :工作时间当天，返回数为（时间数+分/时）
	 * */
	public double getBeginHours(String beginTime) {

		if (beginTime == null || beginTime.trim().equals("")) {
			return 0.00;
		} else {
			String[] one = beginTime.trim().split(":");
			String hour = one[0];
			String min = one[1];

			double beginHours = Integer.parseInt(hour) * 1.00 + Integer
					.parseInt(min) * 1.00 / 60;
			String strDou = OperationUtil.round(beginHours, 2);
			return Double.parseDouble(strDou);
		}

	}

	/**
	 * return 0:比工作时间小一天以上 24.00:endTime为null 或 大于工作时间 其它 :工作时间当天，返回数为（时间数+分/时）
	 * */
	public double getEndHours(String endTime) {

		if (endTime == null || endTime.trim().equals("")) {
			return 24.00;
		} else {
			String[] one = endTime.trim().split(":");
			String hour = one[0];
			String min = one[1];

			double beginHours = Integer.parseInt(hour) * 1.00 + Integer
					.parseInt(min) * 1.00 / 60;
			String strDou = OperationUtil.round(beginHours, 2);
			return Double.parseDouble(strDou);
		}
	}

	public static void main(String[] args) {
		WLogCountBuZhu wlc = new WLogCountBuZhu();

	}
}
