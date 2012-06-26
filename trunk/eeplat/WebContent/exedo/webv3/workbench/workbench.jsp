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
			<li><a  href="javascript:loadPml({'pml':'PM_do_ui_controller_Main','target':'_opener_tab','title':'控制器管理'})"><%=I18n.instance().get("Controller Manager")%></a></li>
		    <br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_actionconfig_Main','target':'_opener_tab','title':'自定义动作管理'})"><%=I18n.instance().get("ActionConfig Manager")%></a></li>
			<br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_resource_Main','target':'_opener_tab','title':'资源管理'})"><%=I18n.instance().get("Resource Manager")%></a></li>
		</ul>	
		
		<ul>	
		   <br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_icon_Main','target':'_opener_tab','title':'浏览器端JS管理'})"><%=I18n.instance().get("JavaScript(Browse)")%></a></li>
			<br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_icon_Main_rhino','target':'_opener_tab','title':'服务器端JS管理'})"><%=I18n.instance().get("JavaScript(Server)")%></a></li>
			<br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_icon_Main_css','target':'_opener_tab','title':'CSS代码管理'})"><%=I18n.instance().get("CSS")%></a></li>
			<br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_icon_Main_html','target':'_opener_tab','title':'Html&FreeMarker代码管理'})"><%=I18n.instance().get("HTML Freemaker")%></a></li>
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
		 	<button onclick="loadPml({'pml':'PM_DO_Application_Insert','target':'_opener_tab','title':'新建工程'})" class="ctlBtn" style="font-size:15px;height:30px;">
		 	<%=I18n.instance().get("Build New Application")%></button>
		</div>	

			<br/>
			<br/>

	<%=I18n.instance().get("About Help,Detail:")%><a href ="http://code.google.com/p/eeplat/wiki/FirstProjcet" target="_blank"><%=I18n.instance().get("First Step to Create Application")%>  </a>。
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
	        <li><a  href="javascript:loadPml({'pml':'trans_formmodel_setl10n','target':'_opener_tab','title':'字典翻译'})"><%=I18n.instance().get("I10n")%></a></li>
	        <br/>
	    	<li><a  href="javascript:loadPml({'pml':'PM_do_pt_processtemplate_Main','target':'_opener_tab','title':'工作流建模'})"><%=I18n.instance().get("Workflow Designer")%></a></li>
	    	<br/>
			<li><a  href="javascript:loadPml({'pml':'PM_DO_BO_Export_Select','target':'_opener_tab','title':'导入配置'})"><%=I18n.instance().get("Import Config")%></a></li>
		    <br/>
			<li><a  href="javascript:loadPml({'pml':'PM_do_org_part_Result','target':'_opener_tab','title':'组织定义'})"><%=I18n.instance().get("Organization Config")%></a></li>
	    	<br/> 
	    </ul>	
	    <ul>	
	     <br/>
	    	<li><a  href="javascript:loadPml({'pml':'PM_do_code_main_Manager','target':'_opener_tab','title':'编码定义'})"><%=I18n.instance().get("Sequence Config")%></a></li>
		    <br/>   	  
			<li><a  href="javascript:loadPml({'pml':'PM_do_code_item_Main','target':'_opener_tab','title':'编码项定义'})"><%=I18n.instance().get("Sequence Item Config")%></a></li>
		</ul>		
	</div></div>
	<div class="gztfoot"><div class="footleft"></div></div>
  </div>
  <div id="gzt3" class="gztmodel">
	<div id="gztit3" class="title"><div class="icon"></div>
	<div class="titcon"><%=I18n.instance().get("DataBase Manager")%></div></div>
	<div class="gzmid"><div class="midleft"></div>
	<div class="midcon">
	
		    <% if ("true".equals(DOGlobals.getValue("multi.tenancy"))) { %>
		    <br/>       
	      <b>您可以通过"数据库管理"，创建新的数据表，修改或删除存在的数据表。</b>
	       <br/>"数据库管理"除了不能操作本地文件外，兼容所有的 MYSQL 5.5命令。
	       
		   <br/>数据表需要经过<b>"初始化表"</b>，才能生成平台相关元数据。
				 <%} %>   
	    <ul>
	    <% if ("true".equals(DOGlobals.getValue("multi.tenancy"))) { %>
	    	<li><a  href="dbmanager/" target="_blank"><%=I18n.instance().get("DataBase Manager")%></a></li>
		    <br/>
			<li><a  href="javascript:loadPml({'pml':'PM_DO_DataSource_getAllTables','target':'_opener_tab','title':'初始化表'})">初始化表</a></li>
		    <br/>
		 <%} else { %>
		     <li><a  href="javascript:loadPml({'pml':'PM_do_datasource_Result','target':'_opener_tab','title':'数据源管理'})"><%=I18n.instance().get("DataBase Manager")%></a></li>
		 <%} %>
		 
		 
	 	    <% if ("true".equals(DOGlobals.getValue("multi.tenancy"))) { %>
	    	<li><a  href="javascript:loadPml({'pml':'TabeCreator','target':'_opener_tab','title':'创建表'})">表创建向导</a>
	    	<span style="font-size:12px"> &nbsp;&nbsp;&nbsp;&nbsp;表生成及初始化简单向导 </span>。  </li>
		 	<%} %>
		    
		</ul>	
			
	</div></div>
	<div class="gztfoot"><div class="footleft"></div></div>
	
  </div>


 </div>
