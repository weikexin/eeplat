<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.net.NetworkInterface"%>
<%@ page import="java.net.InetAddress"%>
<%@ page import="com.exedosoft.plat.bo.DOService"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.Iterator"%>

<%

try {
	Enumeration e1 = NetworkInterface.getNetworkInterfaces();
	while (e1.hasMoreElements()) {
		NetworkInterface ni = (NetworkInterface) e1.nextElement();

		Enumeration e2 = ni.getInetAddresses();
		while (e2.hasMoreElements()) {
			InetAddress ia = (InetAddress) e2.nextElement();
			String ipAddress = ia.getHostAddress();
			if (ipAddress.indexOf(":") == -1) {
				out.println("IPAddress:::" + ipAddress);
			}
		}
	}
} catch (Exception e) {
	e.printStackTrace();
}
%>
