<%@ page contentType="text/html;charset=utf-8"%>
<%@ page language="java" import="t4j.*"%>
<%@ page language="java" import="t4j.http.*"%>
<%@ page language="java" import="t4j.data.User"%>
<%@ page language="java"
	import="com.eeplat.social.openapi.user.SocialUser,com.eeplat.social.openapi.user.SocialUserManager"%>
<%@ page language="java" import="com.exedosoft.plat.SSOController"%>
<%@ page language="java" import="com.exedosoft.plat.bo.BOInstance"%>
<%@ page language="java" import="com.exedosoft.plat.util.DOGlobals" %>
<%
	TBlog tblog = new TBlog();

	String verifier = request.getParameter("oauth_token");

	if (verifier != null) {
		System.out.println("oauth:" + verifier);
		RequestToken resToken = (RequestToken) session
				.getAttribute("resToken");

		if (resToken != null) {

			try {
				AccessToken accessToken = tblog
						.getOAuthAccessToken(resToken);
				if (accessToken != null) {
					User currentUser = tblog.verifyCredentials();

					SocialUser user = new SocialUser();
					System.out.println(currentUser);
					user.setUserId(String.valueOf(currentUser.getId()));
					user.setNickName(currentUser.getName());
					user.setUserName(currentUser.getName());
					user.setName(currentUser.getName());
					user.setLocation(currentUser.getLocation());

					user.setFigureurl(currentUser.getProfileImageURL());
					user.setFigureurl1(currentUser.getUrl());
					user.setUserDescription(currentUser
							.getDescription());

					System.out.print(currentUser.getGender() == 0);

					System.out.print(currentUser.getGender());

					if (currentUser.getGender() == 0) {
						user.setGender("M");
					} else {
						user.setGender("F");
					}

					user.setOpenSite(SocialUser.OPEN_SITE_163);

					System.out.println("user::" + user);
					user = SocialUserManager.storeUser(user);

					SSOController sso = new SSOController();

					BOInstance biUser = new BOInstance();
					biUser.fromObject(user);

					sso.makeMultiLogin(request, biUser, null);

					if("true".equals(session.getAttribute("mobileclient"))){
						if(DOGlobals.getInstance().getSessoinContext().getUser()!=null){
							DOGlobals.getInstance().getSessoinContext().getUser().putValue("jslib", "jquery_mobile");
						}
						System.out.println("use jslib:::" + DOGlobals.getValue("jslib"));
						response.sendRedirect(request.getContextPath() +  "/exedo/mobile/AppList.jsp");//pane_jyhd.pml?isApp=true

					}else{
						response.sendRedirect(request.getContextPath() +  "/pane_CRM.pml?isApp=true");
					}
					

				} else {
					out.println("请修改回调地址！");
				}
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath()
						+ "/exedo/webv3/logoff.jsp");
			}
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/exedo/webv3/logoff.jsp");
		}
	} else {
		response.sendRedirect(request.getContextPath()
				+ "/exedo/webv3/logoff.jsp");
	}
%>
