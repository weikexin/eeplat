<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>

<%
			String paneModelContent = (String) request
			.getAttribute("paneModelContent");
	

			SessionContext context = (SessionContext)session.getAttribute("userInfo");
			if(null==session.getAttribute("userInfo") ||  context.getUser()==null){
			 response.sendRedirect("logoff.jsp");
			
			}
	
%>
<div id="workbench_container">
  <div id="gzt1" class="gztmodel">
    <div id="gztit1" class="title"><div class="icon"></div>
    <div class="titcon">Section1</div></div>
	<div class="gzmid"><div class="midleft"></div>
		<div class="midcon">
		 
		</div>
	</div>
	<div class="gztfoot"><div class="footleft"></div></div>
  </div>
  <div id="gzt2" class="gztmodel">
	<div id="gztit2" class="title"><div class="icon"></div>
	<div class="titcon">Section2</div>	</div>
	<div class="gzmid"><div class="midleft"></div>
	<div class="midcon">
	    		
	</div></div>
	<div class="gztfoot"><div class="footleft"></div></div>
  </div>
  <div id="gzt3" class="gztmodel">
	<div id="gztit3" class="title"><div class="icon"></div>
	<div class="titcon">Section3</div></div>
	<div class="gzmid"><div class="midleft">
	</div><div class="midcon"></div>
	</div>
	<div class="gztfoot"><div class="footleft"></div></div>
  </div>
  <div id="gzt4" class="gztmodel">
	<div id="gztit4" class="title"><div class="icon"></div>
	<div class="titcon">Section4</div></div>
	<div class="gzmid"><div class="midleft"></div>
	<div class="midcon"></div></div>
	<div class="gztfoot"><div class="footleft"></div></div>
 </div>
