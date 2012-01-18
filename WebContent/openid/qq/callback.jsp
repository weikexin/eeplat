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

		SSOController sso = new SSOController();

		BOInstance biUser = new BOInstance();
		biUser.fromObject(user);

		sso.makeMultiLogin(request, biUser, null);

		response.sendRedirect(request.getContextPath()
				+ "/pane_CRM.pml?isApp=true");

	} catch (Exception e) {
		response.sendRedirect(request.getContextPath()
				+ "/exedo/webv3/logoff.jsp");
	}
%>
