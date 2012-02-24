<%@ page contentType="text/html;charset=utf-8" %>
<%@ page language="java" import="weibo4j.http.*" %>
<%@ page language="java" import="weibo4j.*" %>
<%@ page language="java" import="com.eeplat.social.openapi.user.SocialUser,com.eeplat.social.openapi.user.SocialUserManager" %>
<%@ page language="java" import="com.exedosoft.plat.SSOController" %>
<%@ page language="java" import="com.exedosoft.plat.bo.BOInstance" %>
<%@ page language="java" import="com.exedosoft.plat.util.DOGlobals" %>

<%


    System.out.println("Enter Callback=======================================");
	
	String verifier=request.getParameter("oauth_verifier");
	Weibo weibo = new Weibo();
	if(verifier!=null)
	{
		RequestToken resToken=(RequestToken) session.getAttribute("resToken");

		if(resToken!=null)
		{
			try{
			    AccessToken accessToken=weibo.getOAuthAccessToken(resToken,verifier);
				if(accessToken!=null)
				{

					User currentUser = weibo.verifyCredentials();
					SocialUser user = new SocialUser();
					user.setCity(String.valueOf(currentUser.getCity()));
					user.setFigureurl(currentUser.getProfileImageURL().toString());
					user.setFigureurl1(currentUser.getURL().getPath().toString());
					user.setGender(currentUser.getGender().toUpperCase());
					user.setLocation(currentUser.getLocation());
					user.setNickName(currentUser.getScreenName());
					user.setProvince(String.valueOf( currentUser.getProvince()));
					user.setUserDescription(currentUser.getDescription());
					user.setUserId(String.valueOf(currentUser.getId()));
					user.setUserName(currentUser.getName());
					user.setName(currentUser.getName());
					user.setOpenSite(SocialUser.OPEN_SITE_WEIBO);
					
				    user =  SocialUserManager.storeUser(user);
					
				    
					//数据库中并没有设计name字段，storeUser如果存在就会
					//从数据库中查询加载，把name对应的值覆盖为null了
					user.setName(currentUser.getName());

				    
					SSOController  sso = new SSOController();
					
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
					
					
								
					
					out.println("Just for a test,您使用新浪微博成功登录！");				
				}else
					{
					response.sendRedirect(request.getContextPath() +  "/exedo/webv3/logoff.jsp");
					//out.println("access token request error");
					}
			}catch(Exception e){
				response.sendRedirect(request.getContextPath() +  "/exedo/webv3/logoff.jsp");
			}
		
		}
		else
			{
			response.sendRedirect(request.getContextPath() +  "/exedo/webv3/logoff.jsp");
			//out.println("request token session error");
			}
	}
	else
		{
		response.sendRedirect(request.getContextPath() +  "/exedo/webv3/logoff.jsp");
		//out.println("verifier String error");
		}

%>   