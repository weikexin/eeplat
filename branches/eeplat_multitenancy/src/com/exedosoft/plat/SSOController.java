package com.exedosoft.plat;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOApplication;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.login.LoginDelegateList;
import com.exedosoft.plat.login.LoginMain;
import com.exedosoft.plat.login.MultiAccount;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.Escape;
import com.exedosoft.safe.TenancyValues;

///多租户可以使用使用“多租户表”，也可以使用实际表

public class SSOController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8828111215284288122L;

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	// private static Log log = LogFactory.getLog(ServiceController.class);

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public SSOController() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType(CONTENT_TYPE);

		PrintWriter out = response.getWriter();

		/**
		 * 植入session
		 */
		SessionContext us = new SessionContext();
		System.out.println("第一次初始化Session=================");
		request.getSession().setAttribute("userInfo", us);

		// ///////////////////////////*************************捕获上下文
		// this.getServletConfig(), this
		// .getServletContext(),

		DOGlobals.getInstance().refreshContext(
				new DOServletContext(request, response));

		BOInstance formBI = getFormInstance(request);
		System.out.println("Get the form Instance::::::::::::::");
		System.out.println(formBI);

		DOGlobals.getInstance().getSessoinContext().setFormInstance(formBI);

		// 处理登录

		String returnPath = "";
		String echoStr = "";

		if ("true".equals(formBI.getValue("mobileclient"))
				|| formBI.getValue("randcode").equals(
						request.getSession().getAttribute("rand"))) {// //验证码正确
			MultiAccount ma = MultiAccount.findUser(formBI.getValue("name"),
					formBI.getValue("password"));

			if (ma != null) {

				BOInstance user = new BOInstance();
				user.fromObject(ma);

				DOService aService = DOService
						.getService("multi_tenancy_browse_byname");
				BOInstance tenant = aService.getInstance(user
						.getValue("tenancyId"));
				if (tenant != null && tenant.getName() != null) {

					String path = this.getClass().getResource("/globals.xml")
							.getPath();
					path = path.substring(0, path.toLowerCase().indexOf(
							"classes"));

					StringBuffer modelDBPath = new StringBuffer();
					modelDBPath.append(path).append("db/tenancy/").append(
							tenant.getValue("name"));

					File modeDBFile = new File(modelDBPath.toString());
					if (modeDBFile.exists()) {

						if (user != null && user.getValue("tenancyId") != null) {
							/**
							 * tenancyId 相当于 multi_tenancy表中的name
							 */
							System.out.println("当前登录的租户为::"
									+ user.getValue("tenancyId"));

							if (tenant != null && tenant.getName() != null) {

								// /设置公司名称
								user.putValue("company", tenant
										.getValue("l10n"));

								DODataSource dds = new DODataSource();
								dds.setObjUid(user.getValue("tenancyId"));
								dds.setDialect("h2");
								dds.setDriverClass("org.h2.Driver");

								dds
										.setDriverUrl((new StringBuilder(
												"jdbc:h2:"))
												.append(modelDBPath).append("/config").toString());

								dds.setUserName("sa");
								dds.setPassword("");

								// tenant data datastore url
								String multi_datasource_uid = tenant
										.getValue("multi_datasource_uid");

								DODataSource dataDds = null;
								if (multi_datasource_uid != null) {
									DOService findDataSource = DOService
											.getService("multi_datasource_browse");
									BOInstance aBI = findDataSource
											.getInstance(multi_datasource_uid);
									if (aBI != null) {
										dataDds = (DODataSource) aBI
												.toObject(DODataSource.class);
									}
								}
								// /globals 放到session中

								// //需要更改多租户表中，租户数据库中的数据源
								// ////每个租户为定位到某个物理数据库中
								// ///租户数据库分配
								TenancyValues tv = new TenancyValues(dds,
										tenant);
								if (dataDds != null) {
									tv.setDataDDS(dataDds);
								}
								DOGlobals.getInstance().getSessoinContext()
										.setTenancyValues(tv);
								DOService findUserService = DOService
										.getService("do_org_user_browse");
								List corrUsers = findUserService
										.invokeSelect(ma.getObjUid());
								try {
									if (corrUsers == null
											|| corrUsers.size() == 0) {
										// user.putValue("objuid",
										// ma.getObjUid());
										DOService storeUser = DOService
												.getService("do_org_user_insert");
										// /建立用户间的对应关系
										storeUser.store(user);
										// //由于数据隔离的原因屏蔽
										// // /如果是创建者赋予管理员角色
										// if
										// ("2".equals(user.getValue("asrole")))
										// {
										// DOService storeUserRole = DOService
										// .getService("do_org_user_role_insert");
										// Map paras = new HashMap();
										// paras.put("user_uid", user.getUid());
										// paras.put("role_uid",
										// "40288031288a2b8501288a3d009d000d");
										// storeUserRole.store(paras);
										// }
									}
								} catch (ExedoException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								// ///获取缺省工程的URL
								String default_app_uid = user
										.getValue("default_app_uid");
								if (default_app_uid != null) {
									DOApplication dao = DOApplication
											.getApplicationByID(default_app_uid);
									if (dao != null
											&& dao.getOuterLinkPaneStr() != null) {
										returnPath = dao.getOuterLinkPaneStr();
									}
								}

								// //处理登录日志==========================
								LoginMain.makeLogin(user, request);

								System.out.println("当前业务库:::"
										+ DOGlobals.getInstance()
												.getSessoinContext()
												.getTenancyValues()
												.getDataDDS());
								System.out.println("当前缺省的配置库:::"
										+ DODataSource.parseGlobals());

							}
						}
					}else{
						echoStr = "该账号没有被激活！";
					}
				}

			} else {

				echoStr = "账号/密码出错，请重试（9月28号注册的用户请重新注册）！";
			}
		}

		// String serviceUid = request.getParameter("contextServiceUid"); //
		// /////////业务对象服务uid
		// DOService curService = null;
		// if (serviceUid != null && !serviceUid.trim().equals("")) {
		// curService = DOService.getServiceByID(serviceUid);
		// } else {
		// String serviceName = request.getParameter("contextServiceName");
		// System.out.println("use contextServiceName::" + serviceName);
		// if (serviceName != null && !serviceName.trim().equals("")) {
		// curService = DOService.getService(serviceName);
		// }
		// }
		//
		// if (curService == null) {
		// out.println("{\"error\":\"noservice\"}");
		// return;
		// }
		//
		// String contextInstanceUid = formBI.getValue("contextInstanceUid");
		// if (contextInstanceUid != null &&
		// !contextInstanceUid.trim().equals("")) {
		//
		// String contextClassUid = formBI.getValue("contextClassUid");
		// BOInstance bi = null;
		// if (contextClassUid != null && !contextClassUid.trim().equals("")) {
		// DOBO bo = DOBO.getDOBOByID(contextClassUid);
		// bi = bo.refreshContext(contextInstanceUid);
		// } else if (curService.getBo() != null) {
		// System.out.println("RefreshContextInstance:::::::::"
		// + contextInstanceUid);
		// bi = curService.getBo().refreshContext(contextInstanceUid);
		// }
		// }
		//
		// /**
		// * 执行用户定义的特定登录Action 如 LoginAction2 LoginAction等。
		// */
		// String returnValue = null;
		// try {
		// returnValue = curService.invokeAll();
		// } catch (ExedoException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//		
		//
		//		
		//
		boolean isDelegate = false;
		// try {
		// isDelegate = LoginDelegateList.isDelegate();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// // e.printStackTrace();
		// }

		StringBuffer outHtml = new StringBuffer();

		if ("jquery".equals(DOGlobals.getValue("jslib"))) {

			// ////为了保留结构，没有实际意义
			outHtml.append("{\"returnPath\":\"").append(returnPath).append(
					"\",\"targetPane\":\"");
			// /////////value

			if (echoStr == null || echoStr.trim().equals("")) {
				echoStr = "success";
				if (isDelegate) {
					echoStr = "delegate";
				}
			}
			echoStr = echoStr.trim();
			System.out.println("echoStr is :" + echoStr);

			if (formBI.getValue("mobileclient") == null
					&& !formBI.getValue("randcode").equals(
							request.getSession().getAttribute("rand"))) {
				echoStr = "验证码错误！";
			}

			outHtml.append("\",\"returnValue\":\"").append(echoStr).append(
					"\"}");

			// } else if ("ext".equals(DOGlobals.getValue("jslib"))) {
			//
			// if (!"success".equals(returnValue)) {
			// returnValue = "用户名/密码错误,请重试!";
			// } else {
			//
			// System.out.println(formBI.getValue("randcode"));
			// System.out.println(request.getSession().getAttribute("rand"));
			//
			// }
			// outHtml.append("{success:true,msg:\'").append(returnValue).append(
			// "\'}");

		} else {
			// ////为了保留结构，没有实际意义 缺省时使用dojo的包
			outHtml.append("{\"returnPath\":\"").append("\",\"targetPane\":\"");
			// /////////value
			if (echoStr == null) {
				echoStr = "";
			}
			echoStr = echoStr.trim();
			outHtml.append("\",\"returnValue\":\"").append(echoStr).append(
					"\"}");
		}

		// //改变所用的jslib
		if ("true".equals(formBI.getValue("mobileclient"))) {
			if (DOGlobals.getInstance().getSessoinContext().getUser() != null) {
				DOGlobals.getInstance().getSessoinContext().getUser().putValue(
						"jslib", "jquery_mobile");
			}
			System.out.println("use jslib:::" + DOGlobals.getValue("jslib"));
		}
		out.println(outHtml);

	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private BOInstance getFormInstance(HttpServletRequest request) {

		BOInstance formInstance = new BOInstance();
		Map formValues = request.getParameterMap();

		for (Iterator it = formValues.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			String[] values = (String[]) formValues.get(key);

			if (values != null && values.length == 1) {
				formInstance.putValue(key, Escape.unescape(values[0]));
			} else {
				escapeValues(values);
				formInstance.putValue(key, values);
			}
		}
		return formInstance;
	}

	private void escapeValues(String[] values) {
		if (values == null) {
			return;
		}
		for (int i = 0; i < values.length; i++) {
			String aValue = values[i];
			values[i] = Escape.unescape(aValue);
		}

	}

	public static void main(String[] args) {

		CacheFactory.getCacheData().fromSerialObject();

		// DOGlobals.globalConfigs.put("jslib", "jquery_mobile");
		// DOController cc =
		// DOController.getControllerByID("0ccb3a1e06c64ca9aae12b14f906dd83");
		// System.out.println("Corr Controller::" + cc.getCorrByConfig());

		DOApplication.clearAppCache();

		// System.out.println(CacheFactory.getCacheRelation().get(DOApplication.class.getCanonicalName()));

		List<DOApplication> apps = DOApplication.getApplications();

		System.out.println(DAOUtil.INSTANCE().getDataSource());

		System.out.println("apps::::" + apps);

		// DOService aService = DOService
		// .getService("multi_tenancy_browse_byname");
		//
		// BOInstance aBI = aService.getInstance("carrefour");

		// System.out.println("aBI::::" + aBI);

	}
}
