package com.exedosoft.plat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOApplication;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.org.OrgParter;
import com.exedosoft.plat.login.LoginDelegateList;
import com.exedosoft.plat.login.LoginMain;
import com.exedosoft.plat.login.MultiAccount;
import com.exedosoft.plat.login.MultiTenant;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.Escape;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.safe.TenancyValues;

public class SSOController extends HttpServlet {

	private static final long serialVersionUID = 8828111215284288122L;

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	private static Log log = LogFactory.getLog(SSOController.class);
	
	static Map<String,DODataSource> dsConfig = new HashMap<String,DODataSource>();


	public SSOController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();

		/**
		 * 植入session
		 */
		SessionContext us = new SessionContext();
		request.getSession().setAttribute("userInfo", us);

		// ///////////////////////////*************************捕获上下文
		// this.getServletConfig(), this
		// .getServletContext(),

		DOGlobals.getInstance().refreshContext(
				new DOServletContext(request, response));

		BOInstance formBI = getFormInstance(request);
		log.info("Get the form Instance::::::::::::::");
		log.info(formBI);

		DOGlobals.getInstance().getSessoinContext().setFormInstance(formBI);

		String returnValue = null;
		String returnUrl = "";
		if ("true".equals(DOGlobals.getValue("multi.tenancy"))) {
			log.info("Running in MultiTenent env=================================");
			String[] retTenant = makeMultiLoginAndTenant(request, formBI); 
			returnValue = retTenant[0];
			if(retTenant[1]!=null){
				returnUrl = retTenant[1];
			}
		} else {
			log.info("Running in normal env=================================");
			returnValue = makeSimpleLogin(request, formBI);
		}

		boolean isDelegate = false;
		try {
			isDelegate = LoginDelegateList.isDelegate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		StringBuffer outHtml = new StringBuffer();

		if ("jquery".equals(DOGlobals.getValue("jslib"))) {

			// ////为了保留结构，没有实际意义
			outHtml.append("{\"returnPath\":\"").append(returnUrl).append("\",\"targetPane\":\"");
			// /////////value
			String echoStr = DOGlobals.getInstance().getRuleContext()
					.getEchoValue();

			if (echoStr == null || echoStr.trim().equals("")) {
				if (returnValue != null && !returnValue.trim().equals("")) {
					echoStr = returnValue;
				} else {
					echoStr = "success";
				}
				// //代理的情况
				if (isDelegate) {
					echoStr = "delegate";
				}
			}
			echoStr = echoStr.trim();
			outHtml.append("\",\"returnValue\":\"").append(echoStr)
					.append("\"}");

		} else {
			// ////为了保留结构，没有实际意义 缺省时使用dojo的包
			
			outHtml.append("{\"returnPath\":\"").append(returnUrl).append("\",\"targetPane\":\"");
			// /////////value
			String echoStr = DOGlobals.getInstance().getRuleContext()
					.getEchoValue();
			if (echoStr == null) {
				echoStr = "";
			}
			echoStr = echoStr.trim();
			outHtml.append("\",\"returnValue\":\"").append(echoStr)
					.append("\"}");
		}

		// //改变所用的jslib
		if ("true".equals(formBI.getValue("mobileclient"))) {
			if (DOGlobals.getInstance().getSessoinContext().getUser() != null) {
				DOGlobals.getInstance().getSessoinContext().getUser()
						.putValue("jslib", "jquery_mobile");
			}
			log.info("use jslib:::" + DOGlobals.getValue("jslib"));
		}

		out.println(outHtml);
	}

	private String makeSimpleLogin(HttpServletRequest request, BOInstance formBI) {

		// //首先判断验证码，手机访问不判断
		if (formBI.getValue("mobileclient") == null
				&& !formBI.getValue("randcode").equals(
						request.getSession().getAttribute("rand"))) {

			return I18n.instance().get("验证码不正确！");
		}

		String returnValue = null;
		String serviceUid = request.getParameter("contextServiceUid"); // /////////业务对象服务uid
		DOService curService = null;
		if (serviceUid != null && !serviceUid.trim().equals("")) {
			curService = DOService.getServiceByID(serviceUid);
		} else {
			String serviceName = request.getParameter("contextServiceName");
			System.out.println("use contextServiceName::" + serviceName);
			if (serviceName != null && !serviceName.trim().equals("")) {
				curService = DOService.getService(serviceName);
			}
		}

		if (curService == null) {
			// out.println("{error:noservice}");
			returnValue = I18n.instance().get("没有定义服务！");
			return returnValue;
		}

		String contextInstanceUid = formBI.getValue("contextInstanceUid");
		if (contextInstanceUid != null && !contextInstanceUid.trim().equals("")) {

			String contextClassUid = formBI.getValue("contextClassUid");
			BOInstance bi = null;
			if (contextClassUid != null && !contextClassUid.trim().equals("")) {
				DOBO bo = DOBO.getDOBOByID(contextClassUid);
				bi = bo.refreshContext(contextInstanceUid);
			} else if (curService.getBo() != null) {
				log.info("RefreshContextInstance:::::::::" + contextInstanceUid);
				bi = curService.getBo().refreshContext(contextInstanceUid);
			}
		}

		/**
		 * 执行用户定义的特定登录Action 如 LoginAction2 LoginAction等。
		 */

		try {
			returnValue = curService.invokeAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnValue;
	}

	public String makeMultiLogin(HttpServletRequest request, BOInstance formBI) {

		// //首先判断验证码，手机访问不判断
		if (formBI.getValue("mobileclient") == null
				&& !formBI.getValue("randcode").equals(
						request.getSession().getAttribute("rand"))) {

			return I18n.instance().get("验证码不正确！");
		}

		DOGlobals.getInstance().getSessoinContext().setFormInstance(formBI);

		// 处理登录

		String echoStr = "";

		/**
		 * 查找账号
		 */
		MultiAccount ma = MultiAccount.findAccount(formBI.getValue("name"),
				formBI.getValue("password"));
		if (ma == null) {
			return I18n.instance().get("账号/密码出错，请重试！");
		}

		BOInstance user = new BOInstance();
		user.fromObject(ma);
		DOService aService = DOService
				.getService("multi_tenancy_browse_byname");
		// ////////////根据用户查找租户
		BOInstance tenant = aService.getInstance(user.getValue("tenancyId"));
		// ////////如果租户不存在
		if (tenant == null || tenant.getName() == null) {
			return I18n.instance().get("该账号没有被激活！");
		}
		log.info("当前登录的租户为::" + user.getValue("tenancyId"));

		// tenant data datastore url
		String multi_datasource_uid = tenant.getValue("multi_datasource_uid");
		// tenant config datastore url
		String model_datasource_uid = tenant.getValue("model_datasource_uid");

		DODataSource dataDds = null;
		DODataSource dds = null;
		if (multi_datasource_uid != null && model_datasource_uid != null) {

			DOService findDataSource = DOService
					.getService("multi_datasource_browse");

			// //data datasource
			BOInstance aBI = findDataSource.getInstance(multi_datasource_uid);
			if (aBI != null) {
				dataDds = (DODataSource) aBI.toObject(DODataSource.class);
				// /现在多租户情况下默认都是mysql
				dataDds.setDialect(DODataSource.DIALECT_MYSQL);
			}

			// /model datasource
			aBI = findDataSource.getInstance(model_datasource_uid);
			if (aBI != null) {
				dds = (DODataSource) aBI.toObject(DODataSource.class);
				// /现在多租户情况下默认都是mysql
				dds.setDialect(DODataSource.DIALECT_MYSQL);
			}
		}

		if (dataDds == null || dds == null) {
			return I18n.instance().get("该账号没有被激活或者没有正确初始化，请与管理员联系！");
		}

		// /globals 放到session中

		// //需要更改多租户表中，租户数据库中的数据源
		// ////每个租户为定位到某个物理数据库中
		// ///租户数据库分配
		TenancyValues tv = new TenancyValues(dds, tenant);

		tv.setDataDDS(dataDds);
		DOGlobals.getInstance().getSessoinContext().setTenancyValues(tv);
		DOService findUserService = DOService.getService("do_org_user_browse");

		List corrUsers = findUserService.invokeSelect(ma.getObjUid());
		BOInstance employee = null;
		try {
			if (corrUsers == null || corrUsers.size() == 0) {
				// user.putValue("objuid",
				// ma.getObjUid());
				DOService storeUser = DOService
						.getService("do_org_user_insert");
				// /建立用户间的对应关系
				user.putValue("user_code", user.getValue("name"));
				employee = storeUser.store(user);

			} else {
				employee = (BOInstance) corrUsers.get(0);
			}
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.putValue("name", employee.getValue("name"));

		// /设置公司名称
		user.putValue("company", tenant.getValue("l10n"));
		
//		log.info("Login User:::" + user);

		// //处理登录日志==========================
		LoginMain.makeLogin(user, request);

		log.info("Current Data DB:::"
				+ DOGlobals.getInstance().getSessoinContext()
						.getTenancyValues().getDataDDS());

		log.info("Current Config DB:::" + DODataSource.parseGlobals());

		return echoStr;

	}
	

	public String[] makeMultiLoginAndTenant(HttpServletRequest request, BOInstance formBI) {
		
		String[] ret = new String[2];

		// //首先判断验证码，手机访问不判断
		if (formBI.getValue("mobileclient") == null
				&& !formBI.getValue("randcode").equals(
						request.getSession().getAttribute("rand"))) {

			ret[0] = I18n.instance().get("验证码不正确！");
			return ret;
		}

		DOGlobals.getInstance().getSessoinContext().setFormInstance(formBI);

		// 处理登录

		String echoStr = "";		
		String returnUrl = "";


		/**
		 * 查找账号
		 */
		MultiAccount ma = MultiAccount.findAccount(formBI.getValue("name"),
				formBI.getValue("password"),formBI.getValue("tenancyId"));
		if (ma == null) {
			ret[0] = I18n.instance().get("账号/密码出错，请重试！");
			return ret;
		}

		BOInstance user = new BOInstance();
		user.fromObject(ma);
		DOService aService = DOService
				.getService("multi_tenancy_browse_byname");
		// ////////////根据用户查找租户
		BOInstance tenant = aService.getInstance(user.getValue("tenancyId"));
		// ////////如果租户不存在
		if (tenant == null || tenant.getName() == null) {
			ret[0] = I18n.instance().get("该账号没有被激活！");
			return ret;
		}
		log.info("当前登录的租户为::" + user.getValue("tenancyId"));

		// tenant data datastore url
		String multi_datasource_uid = tenant.getValue("multi_datasource_uid");
		// tenant config datastore url
		String model_datasource_uid = tenant.getValue("model_datasource_uid");

		DODataSource dataDds = dsConfig.get(multi_datasource_uid);
		DODataSource dds = dsConfig.get(model_datasource_uid);
		
		if (dataDds == null && dds == null) {

			DOService findDataSource = DOService
					.getService("multi_datasource_browse");

			// //data datasource
			BOInstance aBI = findDataSource.getInstance(multi_datasource_uid);
			if (aBI != null) {
				dataDds = (DODataSource) aBI.toObject(DODataSource.class);
				// /现在多租户情况下默认都是mysql
				dataDds.setDialect(DODataSource.DIALECT_MYSQL);
				dsConfig.put(multi_datasource_uid, dataDds);
			}

			// /model datasource
			aBI = findDataSource.getInstance(model_datasource_uid);
			if (aBI != null) {
				dds = (DODataSource) aBI.toObject(DODataSource.class);
				// /现在多租户情况下默认都是mysql
				dds.setDialect(DODataSource.DIALECT_MYSQL);
				dsConfig.put(model_datasource_uid, dds);
			}
		}

		if (dataDds == null || dds == null) {
			ret[0] = I18n.instance().get("该账号没有被激活或者没有正确初始化，请与管理员联系！");
			return ret;
		}

		// /globals 放到session中

		// //需要更改多租户表中，租户数据库中的数据源
		// ////每个租户为定位到某个物理数据库中
		// ///租户数据库分配
		TenancyValues tv = new TenancyValues(dds, tenant);

		tv.setDataDDS(dataDds);
		DOGlobals.getInstance().getSessoinContext().setTenancyValues(tv);
		DOService findUserService = DOService.getService("do_org_user_browse");

		List corrUsers = findUserService.invokeSelect(ma.getObjUid());
		BOInstance employee = null;
		try {
			if (corrUsers == null || corrUsers.size() == 0) {
				// user.putValue("objuid",
				// ma.getObjUid());
				DOService storeUser = DOService
						.getService("do_org_user_insert");
				// /建立用户间的对应关系
				user.putValue("user_code", user.getValue("name"));
				employee = storeUser.store(user);

			} else {
				employee = (BOInstance) corrUsers.get(0);
			}
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.putValue("name", employee.getValue("name"));

		// /设置公司名称
		user.putValue("company", tenant.getValue("l10n"));
		
		
		///ret return path
		List<DOApplication> doas = DOApplication.getApplications();
		

		
		returnUrl = doas.get(0).getOuterLinkPaneStr();
		
		log.info("returnUrl::" + returnUrl);
		for(DOApplication doa: doas){
			if(doa.isDefault()){
				returnUrl = doa.getOuterLinkPaneStr();
				break;
			}
		}
		log.info("returnUrl2::" + returnUrl);
		
//		log.info("Login User:::" + user);

		// //处理登录日志==========================
		LoginMain.makeLogin(user, request);

		log.info("Current Data DB:::"
				+ DOGlobals.getInstance().getSessoinContext()
						.getTenancyValues().getDataDDS().getDriverUrl());

		log.info("Current Config DB:::" + DODataSource.parseGlobals().getDriverUrl());

		ret[0] = echoStr;
		ret[1] = returnUrl;
		return ret;

	}
	
//	public String makeMultiLoginOfTenant(HttpServletRequest request, BOInstance formBI) {
//
//		// //首先判断验证码，手机访问不判断
//		if (formBI.getValue("mobileclient") == null
//				&& !formBI.getValue("randcode").equals(
//						request.getSession().getAttribute("rand"))) {
//
//			return I18n.instance().get("验证码不正确！");
//		}
//
//		DOGlobals.getInstance().getSessoinContext().setFormInstance(formBI);
//
//		
//		MultiTenant  mt = MultiTenant.findTenant(formBI.getValue("tenantId"));
//	
//		log.info("当前登录的租户为::" +mt);
//
//		// tenant data datastore url
//		String multi_datasource_uid = mt.getMulti_datasource_uid();
//		// tenant config datastore url
//		String model_datasource_uid = mt.getModel_datasource_uid();
//
//		DODataSource dataDds = null;
//		DODataSource dds = null;
//		if (multi_datasource_uid != null && model_datasource_uid != null) {
//
//			DOService findDataSource = DOService
//					.getService("multi_datasource_browse");
//
//			// //data datasource
//			BOInstance aBI = findDataSource.getInstance(multi_datasource_uid);
//			if (aBI != null) {
//				dataDds = (DODataSource) aBI.toObject(DODataSource.class);
//				// /现在多租户情况下默认都是mysql
//				dataDds.setDialect(DODataSource.DIALECT_MYSQL);
//			}
//
//			// /model datasource
//			aBI = findDataSource.getInstance(model_datasource_uid);
//			if (aBI != null) {
//				dds = (DODataSource) aBI.toObject(DODataSource.class);
//				// /现在多租户情况下默认都是mysql
//				dds.setDialect(DODataSource.DIALECT_MYSQL);
//			}
//		}
//
//		if (dataDds == null || dds == null) {
//			return I18n.instance().get("该账号没有被激活或者没有正确初始化，请与管理员联系！");
//		}
//
//		// /globals 放到session中
//
//		// //需要更改多租户表中，租户数据库中的数据源
//		// ////每个租户为定位到某个物理数据库中
//		// ///租户数据库分配
//		
//		BOInstance tenant = new BOInstance();
//		tenant.fromObject(mt);
//		TenancyValues tv = new TenancyValues(dds, tenant);
//
//		tv.setDataDDS(dataDds);
//		DOGlobals.getInstance().getSessoinContext().setTenancyValues(tv);
//		DOService findUserService = DOService.getService("do_org_user_browse");
//
//		List corrUsers = findUserService.invokeSelect(ma.getObjUid());
//		BOInstance employee = null;
//		try {
//			if (corrUsers == null || corrUsers.size() == 0) {
//				// user.putValue("objuid",
//				// ma.getObjUid());
//				DOService storeUser = DOService
//						.getService("do_org_user_insert");
//				// /建立用户间的对应关系
//				user.putValue("user_code", user.getValue("name"));
//				employee = storeUser.store(user);
//
//			} else {
//				employee = (BOInstance) corrUsers.get(0);
//			}
//		} catch (ExedoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		user.putValue("name", employee.getValue("name"));
//
//		// /设置公司名称
//		user.putValue("company", tenant.getValue("l10n"));
//		
////		log.info("Login User:::" + user);
//
//		// //处理登录日志==========================
//		LoginMain.makeLogin(user, request);
//
//		log.info("Current Data DB:::"
//				+ DOGlobals.getInstance().getSessoinContext()
//						.getTenancyValues().getDataDDS());
//
//		log.info("Current Config DB:::" + DODataSource.parseGlobals());
//
//		return echoStr;
//
//	}


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

	////多租户，根据租户的不同初始化到不同的租户业务库中
	///不过写这个函数没有任何意义，通过配置文件一样可以搞定。
//	initLog4j();
//	private void initLog4j() {
//		
//		
//		
//
//		Properties pro = new Properties();
//		try {
//			pro.load(DOGlobals.class
//					.getResourceAsStream("/log4j.properties"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//		pro.put("log4j.appender.threadLog", "com.exedosoft.plat.log.ThreadAppender");
//		pro.put("log4j.appender.threadLog.layout", "org.apache.log4j.PatternLayout");
//		pro.put("log4j.appender.threadLog.layout.ConversionPattern", "%-d{yyyy-MM-dd HH:mm:ss} [%t] - [%p] %37c(:%L) %3x %m%n");
//		
//		pro.put("log4j.logger.com.exedosoft.plat.dao.DAOSearch", "error, threadLog");
//		pro.put("log4j.logger.com.exedosoft.plat.dao.DAOTools", "error, threadLog");
//		
//		
//		pro.put("log4j.logger.com.exedosoft.plat.bo.DOService", "info,threadLog");
//		pro.put("log4j.logger.com.exedosoft.plat.bo.search.SearchImp", "info,threadLog");
//		pro.put("log4j.logger.com.exedosoft.plat.bo.BOInstance", "info,threadLog");
//		pro.put("log4j.logger.com.exedosoft.plat.MVCController", "info,threadLog");
//		pro.put("log4j.logger.com.exedosoft.plat.ServiceController", "info,threadLog");
//		pro.put("log4j.logger.com.exedosoft.plat.js.*", "info,threadLog");
//		pro.put("log4j.logger.ExceptionOutPrint", "info,threadLog");
//		pro.put("log4j.logger.SystemOutPrint", "info,threadLog");
//
//		PropertyConfigurator.configure(pro);
//		log.info("Logging initialized.");
//
//	}

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
		DOService findDataSource = DOService
				.getService("multi_datasource_browse");
		
		// //data datasource
		BOInstance aBI = findDataSource.getInstance("402881eb38a231d20138a23b4ab70015");
		DODataSource dataDds = null;
		if (aBI != null) {
			dataDds = (DODataSource) aBI.toObject(DODataSource.class);
			// /现在多租户情况下默认都是mysql
			dataDds.setDialect(DODataSource.DIALECT_MYSQL);
		}
		
		
		BOInstance aBI2 = findDataSource.getInstance("402881eb38a231d20138a23b4ab70015");
		DODataSource dataDds2 = null;
		if (aBI2 != null) {
			dataDds2 = (DODataSource) aBI2.toObject(DODataSource.class);
			// /现在多租户情况下默认都是mysql
			dataDds2.setDialect(DODataSource.DIALECT_MYSQL);
		}

		System.out.println("equals::::" + dataDds.equals(dataDds2));

//		CacheFactory.getCacheData().fromSerialObject();
//		DOApplication.clearAppCache();
//
//		List<DOApplication> apps = DOApplication.getApplications();
//
//		System.out.println("apps::::" + apps);

	}
}
