

package com.exedosoft.plat.login;



 import com.exedosoft.plat.bo.BOInstance;
 import java.util.List;
 import java.util.ArrayList;
 import java.net.URLDecoder;
 import java.util.Iterator;
 import java.util.Hashtable;
 import com.exedosoft.plat.util.id.UUIDHex;
 import com.exedosoft.plat.bo.*;
 import java.lang.*;
 import java.text.*;
 import java.util.Iterator;
 import java.net.URLEncoder;

import com.exedosoft.plat.util.DOGlobals;
 import com.exedosoft.plat.util.StringUtil;
  import com.exedosoft.plat.SessionContext;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.DOService;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//
//import cn.com.jit.assp.ias.principal.UserPrincipal;
//import cn.com.jit.assp.ias.saml.X509.X509Constants;
//import cn.com.jit.assp.ias.saml.saml11.SAMLAttributes;
//import cn.com.jit.assp.ias.saml.saml11.SAMLConstants;
//import cn.com.jit.assp.ias.sp.saml11.SPUtil;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;

public class LoginMainStu {

	private static Log log = LogFactory.getLog(LoginMainStu.class);
	private static DOService insertLoginLog = DOService.getService("do.log.insert");
	// ////////////刷新当前登录单位
	private static DOBO aDeptBO = DOBO.getDOBOByName("do.bx.dept");



	// private static Object lockObj = new Object();

	public static int makeLogin(BOInstance user,HttpServletRequest request) {
	

		user.putValue("deptuid", user.getValue("dept_uid"));


		BOInstance biDept = null;
		if (aDeptBO != null) {
			biDept = aDeptBO.refreshContext(user.getValue("dept_uid"));
			System.out.println("登陆部门:::::::::;" + biDept);
			if (biDept != null) {
				user.putValue("deptobject", biDept);
				user.putValue("deptname", biDept.getName());
			}
		}
		
	    List gradeCounts=null;
	    DOService studentGrade = DOService.getService("tbstudent.browseGrade");
	    gradeCounts=studentGrade.invokeSelect(user.getUid());
	    String gradeInfor="";
	    for (Iterator it =gradeCounts.iterator();it.hasNext();){
	    	BOInstance dData=(BOInstance) it.next();
	    	gradeInfor=dData.getValue("fdgrade");
	    }
	    
	    
		System.out.println("gradeInfor::" + gradeInfor);
		user.putValue("grade_login", gradeInfor);

		// /////////////国土专用，user 同时携带行政区代码 createb by weikx at 070706
		// 其它login可以去掉

		if (biDept != null) {
			user.putValue("deptcode", biDept.getValue("code"));
		}
		
		

		SessionContext us = (SessionContext) request.getSession().getAttribute(
				"userInfo");
		if(us == null){
			us = new SessionContext();
			request.getSession().setAttribute("userInfo", us);
		}


	
		us.setSysTreeRoot(user.getValue("user_name"));
		us.setUser(user);
		us.setIp(DOGlobals.getInstance().getServletContext().getRequest()
				.getRemoteAddr());
		us.setSessionuid(DOGlobals.getInstance().getServletContext().getRequest().getSession().getId());
		
//	
//		BOInstance aInsertLog = new BOInstance();
//		aInsertLog.putValue("userName", user.getName());
//		aInsertLog.putValue("ip", us.getIp());
//		aInsertLog.putValue("sessionid", us.getSessionuid());
//		try {
//			// synchronized(lockObj){
//			insertLoginLog.invokeUpdate(aInsertLog);
//			// }
//		} catch (ExedoException e) {
//			// TODO Auto-generated catch block
//			log.info(e.fillInStackTrace());
//		}

		// /////海洋局文档系统
//		DOService findMaxDegree = DOService
//				.getService("do.bx.findmaxdegreeofuser");
//
//		String maxDegree = "0";
//		if (findMaxDegree != null) {
//			maxDegree = findMaxDegree.invokeSelectGetAValue(user.getUid());
//
//			if (maxDegree == null || "".equals(maxDegree.trim())) {
//				maxDegree = "0";
//			}
//			log.info("该用户的权限级别是:" + maxDegree);
//		}

		// System.out.println("llllllllllll"
		// + DOGlobals.getInstance().getServletContext().getRequest()
		// .getSession().getAttribute("userInfo"));

		// ///////对于国土资源部项目，为了更好得区分探矿，采矿，地质勘查资质而加的属性
		// /////////1,探矿,2,采矿,3,采矿探矿都有 ..........向后扩展
		// /////////
		List roles = getCorrRoles(user.getUid());
		if (roles.size() > 0) {
			int iJudge = 0;
			for (Iterator it = roles.iterator(); it.hasNext();) {
				String aRole = (String) it.next();
				if ("qtsy_sq_phsq".equalsIgnoreCase(aRole)) {
					user.putValue("gtType", "2");
					iJudge++;
				}
				if ("qtsy_sq_tkq".equalsIgnoreCase(aRole)) {
					user.putValue("gtType", "1");
					iJudge++;
				}
				if("qtsy_leader".equalsIgnoreCase(aRole)){
					iJudge++;
					iJudge++;
					user.putValue("gtType", "3");
					user.putValue("readonly", "readonly");
				}
			}
			if (iJudge >= 2) {
				user.putValue("gtType", "3");
			}
			if("admin".equals(user.getName())){
				user.putValue("gtType", "3");
			}
			log.info("国土资源部专用：该用户的类别是:" + user.getValue("gtType"));

		}
		
	//	jit();
	
		
		/////////////////初始化全局参数,其实应该放入DOGlobals 里面作为全局变量，但是平台还不直接支持全局变量，只能先这样
		DOBO doGlobals = DOBO.getDOBOByName("do.globals");
		if(doGlobals!=null){
			doGlobals.refreshContext("000000");
		}

		//100000
		
		return 5;
	}
	
	
//	private static void jit(){
//		
//		
//		
//		UserPrincipal p = SPUtil.getUserPrincipal(DOGlobals.getInstance().getServletContext().getRequest());
////		获取用户令牌ID
//		String tokenId=(String)p.getAttribute(SAMLConstants.KEY_SAML_ATTR_ID);
////		获取用户属性集合
//		SAMLAttributes attrs =(SAMLAttributes)p.getAttribute
//		(SAMLConstants.KEY_SAML_ATTR_STATEMENT_ATTRIBUTES);
////		如果是证书用户，可查询证书的DN
//			List values=attrs.getAttributeValue(
//		X509Constants.KEY_SAML_X509_SUBJECT_DN,
//		X509Constants.VALUE_X509_NAMESPACE);
//		String subjectDN=(String)values.get(0); //证书主题为单值属性
//	
//				
//		System.out.println(subjectDN);
//		System.out.println(subjectDN);
//		System.out.println(subjectDN);
//		System.out.println(subjectDN);
//		System.out.println(subjectDN);
//		System.out.println(subjectDN);
//	
//		
//
//		
//		
//	}

	private HashMap getAllDepts() {
		DOService searchDepts = DOService.getService("do.bx.dept.list");
		HashMap allDepts = new HashMap();
		List depts = searchDepts.invokeSelect();
		if (depts != null) {
			for (Iterator it = depts.iterator(); it.hasNext();) {
				BOInstance deptBI = (BOInstance) it.next();
				allDepts.put(deptBI.getValue("code"), deptBI.getName());
			}
		}
		return allDepts;
	}

	public static List getCorrRoles(String accountUid) {

		DOService findUserService = DOService
				.getService("do.bx.role.findbyuserid");
		if (findUserService == null) {
			return null;
		}
		List listRoles = findUserService.invokeSelect(accountUid);
		List corrRoles = new ArrayList();
		if (listRoles != null) {
			for (Iterator roles = listRoles.iterator(); roles.hasNext();) {
				BOInstance boRole = (BOInstance) roles.next();
				// System.out.println(boRole);
				corrRoles.add(boRole.getValue("roleid"));
			}
			return corrRoles;
		}
		return null;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginMainStu lm = new LoginMainStu();
		List roles = lm.getCorrRoles("6669");
		System.out.println(roles);

	}

}
