<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="exedo/webv3/css/delegatepane.css" />
<title>商飞</title>
</head>

<body>
<div class="bg">
<img src="exedo/webv3/images/delegate/bg.jpg" style="width:100%; height:100%; z-index:-10000; position:absolute; top:0px;" />
</div>
<div style="overflow:auto; height:100%; width:100%;">
<table cellpadding="0" cellspacing="0" align="center" width="100%" height="100%">
    <tr>
	    <td align="center" valign="middle" height="100%">
		   <table class="shadow" align="center" cellpadding="0" cellspacing="0">
		       <tr>
			   <td width="338px" style="height:130px;">&nbsp;</td>
			   <td>&nbsp;</td>
			   <td width="120px">&nbsp;</td>
			   </tr>
			   <tr>
			   <td>&nbsp;</td>
			   <td style="width:357px; height:240xp;" align="center">
			      <table class="main font12 color letter"><tr><td>
				   <li>以委托人
					  <em>
						  <#list delegages as item> 
						  	<a style="letter-spacing:normal" class="blue" href="javascript:callService({'serviceName':'do_org_user_delegate_loginDelegate','pml':'${pml?if_exists}','target':'_opener_location','paras':'delegateuid=${item.uid}','callType':'uf'})">
							${item.map.name}</a> 
						  </#list>
					  </em>的身份进入系统
					</li>
					<li>以
						<a style="letter-spacing:normal" class="blue" href="javascript:callAction ({'actionConfigName':'com_exedosoft_plat_login_RemoveDelegateAction','pml':'${pml?if_exists}','target':'_opener_location'})">自己</a>
						的身份进入系统
					</li>
				  </td></tr></table>
				  <div class="notice letter font12 color"><em class="colorr">*注意：</em>如果您以委托人身份进入系统，请您谨慎操作..</div>
			   </td>
			   <td>&nbsp;</td>
			   </tr>
			   <tr>
			   <td style="height:80px;_height:120px;">&nbsp;</td>
			   <td>&nbsp;</td>
			   <td >&nbsp;</td>
			   </tr>
		   </table>
		</td>
	</tr>
</table>
</div>
</body>
</html>