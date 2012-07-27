<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.util.I18n"%>
<div id="workbench_container">


 <div class="gtzbar gztleft">
	<div  class="title"><div class="icon"></div>
	<div class="titcon"><%=I18n.instance().get("Tools")%></div>	</div>
	<div class="gzmid"><div class="midleft"></div>
	<div class="midcon">
	    <ul>
			<li><a  href="javascript:loadPml({'pml':'PM_do_ui_controller_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Controller Manager")%>'})"><%=I18n.instance().get("Controller Manager")%></a></li>
		    <br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_actionconfig_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Action Manager")%>'})"><%=I18n.instance().get("Action Manager")%></a></li>
			<br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_resource_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Resource Manager")%>'})"><%=I18n.instance().get("Resource Manager")%></a></li>
		</ul>	
		
		<ul>	
		   <br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_icon_Main','target':'_opener_tab','title':'<%=I18n.instance().get("JavaScript(Browse)")%>'})"><%=I18n.instance().get("JavaScript(Browse)")%></a></li>
			<br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_icon_Main_rhino','target':'_opener_tab','title':'<%=I18n.instance().get("JavaScript(Server)")%>'})"><%=I18n.instance().get("JavaScript(Server)")%></a></li>
			<br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_icon_Main_css','target':'_opener_tab','title':'<%=I18n.instance().get("CSS")%>'})"><%=I18n.instance().get("CSS")%></a></li>
			<br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_icon_Main_html','target':'_opener_tab','title':'<%=I18n.instance().get("HTML Freemaker")%>'})"><%=I18n.instance().get("HTML Freemaker")%></a></li>
			<br/>
		    <br/>   	  
		</ul>	
	</div></div>
	<div class="gztfoot"><div class="footleft"></div></div>
  </div>

  <div id="gzt1" class="gztmodel">
    <div id="gztit1" class="title"><div class="icon"></div>
    <div class="titcon"><%=I18n.instance().get("FirstSetup")%></div></div>
	<div class="gzmid"><div class="midleft"></div>
		<div class="midcon">
			<br/>
			<br/>
	<%=I18n.instance().get("SomeStep1")%><b><%=I18n.instance().get("SomeStep2")%></b>
		 <%=I18n.instance().get("SomeStep3")%>
			<br/>
			<br/>
		 <div align="center">
		 	<button onclick="loadPml({'pml':'PM_DO_Application_Insert','target':'_opener_tab','title':'<%=I18n.instance().get("Build New Application")%>'})" class="ctlBtn" style="font-size:15px;height:30px;">
		 	<%=I18n.instance().get("Build New Application")%></button>
		</div>	

			<br/>
			<br/>

	<%=I18n.instance().get("About Help,Detail:")%><a href ="http://code.google.com/p/eeplat/wiki/FirstProjcet" target="_blank"><%=I18n.instance().get("First Step to Create Application.")%>  </a>
<!-- 预留页面空间，可以增加特定业务向导，如手机、SNS 等......-->
		</div>

	</div>
	<div class="gztfoot"><div class="footleft"></div></div>
  </div>
  <div class="gtzbar gztright">
	<div  class="title"><div class="icon"></div>
	<div class="titcon"><%=I18n.instance().get("Tools")%></div>	</div>
	<div class="gzmid"><div class="midleft"></div>
	<div class="midcon">
	    <ul>
	        <li><a  href="javascript:loadPml({'pml':'PM_do_log_dev_Main','target':'_opener_tab','title':'Log'})"><%=I18n.instance().get("Logs")%></a></li>
	        <br/>
	        <li><a  href="javascript:loadPml({'pml':'trans_formmodel_setl10n','target':'_opener_tab','title':'<%=I18n.instance().get("I10n")%>'})"><%=I18n.instance().get("I10n")%></a></li>
	        <br/>
	    	<li><a  href="javascript:loadPml({'pml':'PM_do_pt_processtemplate_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Workflow Designer")%>'})"><%=I18n.instance().get("Workflow Designer")%></a></li>
	    	<br/>
			<li><a  href="javascript:loadPml({'pml':'PM_DO_BO_Export_Select','target':'_opener_tab','title':'<%=I18n.instance().get("Import Config")%>'})"><%=I18n.instance().get("Import Config")%></a></li>
		    <br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_org_part_Result','target':'_opener_tab','title':'<%=I18n.instance().get("Organization Config")%>'})"><%=I18n.instance().get("Organization Config")%></a></li>
	    	<br/> 
	    </ul>	
	    <ul>	
	     <br/>
	    	<li><a  href="javascript:loadPml({'pml':'PM_do_code_main_Manager','target':'_opener_tab','title':'<%=I18n.instance().get("Sequence Config")%>'})"><%=I18n.instance().get("Sequence Config")%></a></li>
		    <br/>   	  
			<li><a  href="javascript:loadPml({'pml':'PM_do_code_item_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Sequence Item Config")%>'})"><%=I18n.instance().get("Sequence Item Config")%></a></li>
		</ul>		
	</div></div>
	<div class="gztfoot"><div class="footleft"></div></div>
  </div>
  <div id="gzt3" class="gztmodel">
	<div id="gztit3" class="title"><div class="icon"></div>
	<div class="titcon"><%=I18n.instance().get("DataBase Manager")%></div></div>
	<div class="gzmid"><div class="midleft"></div>
	<div class="midcon">
	
 
	    <ul>
	    <% if ("true".equals(DOGlobals.getValue("multi.tenancy"))) { %>
	    	<li><a  href="dbmanager/" target="_blank"><%=I18n.instance().get("DataBase Manager")%></a>&nbsp;&nbsp;
	    		      <%=I18n.instance().get("data manager utility")%> <%=I18n.instance().get("datamanager mysql command")%>
			       
				   
		   </li>
		    <br/>
		 <%} else { %>
		     <li><a  href="javascript:loadPml({'pml':'PM_do_datasource_Result','target':'_opener_tab','title':'<%=I18n.instance().get("DataBase Manager")%>'})"><%=I18n.instance().get("DataBase Manager")%></a></li>
		 <%} %>
		 
		 
	 	    <% if ("true".equals(DOGlobals.getValue("multi.tenancy"))) { %>
	    	<li><a  href="javascript:loadPml({'pml':'TabeCreator','target':'_opener_tab','title':'<%=I18n.instance().get("Table Create Wizard")%>'})"><%=I18n.instance().get("Table Create Wizard")%></a>
	    	<span style="font-size:12px"> &nbsp;&nbsp;&nbsp;&nbsp;<%=I18n.instance().get("FromTable2BO")%> </span>  </li>
		 	<%} %>
		 	
		 	<br/>
		 	<li><a  href="javascript:loadPml({'pml':'PM_DO_DataSource_getAllTables','target':'_opener_tab','title':'<%=I18n.instance().get("初始化表")%>'})">
			<%=I18n.instance().get("初始化表")%></a> &nbsp;&nbsp;<%=I18n.instance().get("inittable_note")%>
			
			</li>
		    
		</ul>	
			
	</div></div>
	<div class="gztfoot"><div class="footleft"></div></div>
	
  </div>


 </div>
