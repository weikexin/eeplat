<%@ page pageEncoding="UTF-8" contentType="image/jpeg" import="com.exedosoft.plat.util.CaptchaUtils" %>
<%

String sRand = CaptchaUtils.random();
//将认证码存入SESSION
session.setAttribute("rand",sRand);
out.clear();
out = pageContext.pushBody();
CaptchaUtils.generate(sRand, response.getOutputStream());
%>
