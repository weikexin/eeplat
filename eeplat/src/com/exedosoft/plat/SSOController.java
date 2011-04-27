package com.exedosoft.plat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.login.LoginDelegateList;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.Escape;

import com.exedosoft.plat.dao.ObjectTableMapper;

import com.exedosoft.plat.ui.DOController;
import com.exedosoft.plat.ui.DOPaneModel;

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
			out.println("{error:noservice}");
			return;
		}

		String contextInstanceUid = formBI.getValue("contextInstanceUid");
		if (contextInstanceUid != null && !contextInstanceUid.trim().equals("")) {

			String contextClassUid = formBI.getValue("contextClassUid");
			BOInstance bi = null;
			if (contextClassUid != null && !contextClassUid.trim().equals("")) {
				DOBO bo = DOBO.getDOBOByID(contextClassUid);
				bi = bo.refreshContext(contextInstanceUid);
			} else if (curService.getBo() != null) {
				System.out.println("RefreshContextInstance:::::::::"
						+ contextInstanceUid);
				bi = curService.getBo().refreshContext(contextInstanceUid);
			}
		}

		/**
		 * 执行用户定义的特定登录Action 如 LoginAction2 LoginAction等。
		 */
		String returnValue = null;
		try {
			returnValue = curService.invokeAll();
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		boolean isDelegate = false;
		try {
			isDelegate = LoginDelegateList.isDelegate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}



		StringBuffer outHtml = new StringBuffer();

	
		if ("jquery".equals(DOGlobals.getValue("jslib"))) {

			// ////为了保留结构，没有实际意义
			outHtml.append("{\"returnPath\":\"").append("\",\"targetPane\":\"");
			// /////////value
			String echoStr = DOGlobals.getInstance().getRuleContext()
					.getEchoValue();

			if (echoStr == null || echoStr.trim().equals("")) {
				echoStr = "success";
				if(isDelegate){
					echoStr = "delegate";
				}
			}
			echoStr = echoStr.trim();
			System.out.println("echoStr is :" + echoStr);

			if (formBI.getValue("mobileclient")==null && !formBI.getValue("randcode").equals(
					request.getSession().getAttribute("rand"))) {
				echoStr = "验证码错误！";
			}

			outHtml.append("\",\"returnValue\":\"").append(echoStr).append("\"}");

//		} else if ("ext".equals(DOGlobals.getValue("jslib"))) {
//
//			if (!"success".equals(returnValue)) {
//				returnValue = "用户名/密码错误,请重试!";
//			} else {
//
//				System.out.println(formBI.getValue("randcode"));
//				System.out.println(request.getSession().getAttribute("rand"));
//
//			}
//			outHtml.append("{success:true,msg:\'").append(returnValue).append(
//					"\'}");

		} else {
			// ////为了保留结构，没有实际意义  缺省时使用dojo的包
			outHtml.append("{\"returnPath\":\"").append("\",\"targetPane\":\"");
			// /////////value
			String echoStr = DOGlobals.getInstance().getRuleContext()
					.getEchoValue();
			if (echoStr == null) {
				echoStr = "";
			}
			echoStr = echoStr.trim();
			outHtml.append("\",\"returnValue\":\"").append(echoStr).append("\"}");
		}

		////改变所用的jslib
		if("true".equals(formBI.getValue("mobileclient"))){
			DOGlobals.globalConfigs.put("jslib", "jquery_mobile");
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
	
	public static void main(String[] args){
		
		CacheFactory.getCacheData().fromSerialObject();

//		DOGlobals.globalConfigs.put("jslib", "jquery_mobile");
//		DOController  cc = DOController.getControllerByID("0ccb3a1e06c64ca9aae12b14f906dd83");
//		System.out.println("Corr Controller::" + cc.getCorrByConfig());
		
		
		
		DOPaneModel pm = DOPaneModel.getPaneModelByName("abp_base_pane");
		
		System.out.println("pm::::" + pm.getController());

		

	}
}
