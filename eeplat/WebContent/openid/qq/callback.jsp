<%@ page contentType="text/html;charset=utf-8"%>
<%@ page language="java"
	import="com.eeplat.social.openapi.http.HttpClient"%>
<%@ page language="java"
	import="com.eeplat.social.openapi.http.Response"%>
<%@ page language="java" import="com.eeplat.social.openapi.qq.AuthUtil"%>
<%@ page language="java" import="com.eeplat.social.openapi.qq.URIUtil"%>
<%@ page language="java" import="org.json.JSONObject"%>
<%@ page language="java"
	import="com.eeplat.social.openapi.user.SocialUser,com.eeplat.social.openapi.user.SocialUserManager"%>
<%@ page language="java" import="com.exedosoft.plat.SSOController"%>
<%@ page language="java" import="com.exedosoft.plat.bo.BOInstance"%>
<%@ page language="java" import="com.exedosoft.plat.login.LoginMain" %>
<%
	try {
		String oauth_code = request.getParameter("code");

		String accessToken = AuthUtil.getAccessToken(oauth_code);

		String openid = AuthUtil.getOpenID(accessToken);

		JSONObject currentUser = AuthUtil.getUserInfo(accessToken,
				openid);

		System.out.println("CurrentUser:::" + currentUser);
		SocialUser user = new SocialUser();
		user.setUserId(openid);
		user.setNickName((String) currentUser.get("nickname"));
		user.setName((String) currentUser.get("nickname"));
		user.setFigureurl((String) currentUser.get("figureurl"));
		try {
			user.setFigureurl1((String) currentUser.get("figureurl1"));
			user.setFigureurl2((String) currentUser.get("figureurl2"));
		} catch (Exception e) {

		}
		if ("男".equals(currentUser.get("gender"))) {
			user.setGender("M");
		} else {
			user.setGender("F");
		}
		user.setOpenSite(SocialUser.OPEN_SITE_QQ);
		user = SocialUserManager.storeUser(user);
		
		//数据库中并没有设计name字段，storeUser如果存在就会
		//从数据库中查询加载，把name对应的值覆盖为null了
		user.setName((String) currentUser.get("nickname"));

		BOInstance biUser = new BOInstance();
		biUser.fromObject(user);

		LoginMain.makeLogin(biUser, request);
		response.sendRedirect(request.getContextPath() +  "/pane_jyhd.pml?isApp=true");


	} catch (Exception e) {
		response.sendRedirect(request.getContextPath()
				+ "/exedo/webv3/logoff.jsp");
	}
%>
