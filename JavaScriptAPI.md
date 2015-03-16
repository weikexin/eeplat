# JavaScript参数对象p #
```
  p.btn              触发按钮的ID
  p.actionName       自定义动作类名称
  p.actionConfigName 自定义动作的名陈
  p.serviceUid       服务的ID
  p.serviceName      服务名称
  p.formName         表单名称
  P.paras            需要传递的参数
  P.pml               连接的面板
  p.pmlHeight        连接的面板的高度(弹出面板) 
  p.pmlWidth         连接的面板的宽带（弹出面板）
  P.target           目标面板
  p.echoJs	     执行服务之前的前台提示
  p.async            异步执行，默认为true; false为同步

  p.callType         触发类别: 
                type='us',只执行当前的服务（增删改类型，不执行对应的规则），返回的结果是一条json记录数据

	        type='uf', 执行当前的服务（增删改类型）包括规则，返回的结果是一个string 字符  传， 包含的一般是提示信息

                type='sa', 执行查询类型的服务，返回的结果是每条完整记录的json数组
                type='ss', 执行查询类型的服务，返回的结果是只包含主键列和特征值类记录的json数组
                type='sm', 执行查询类型的服务，返回的结果是每条完整记录的类Map json结构，记录的ID为Key
  	        type='as'  由定义的class 返回数据（只用于下文callAction方法），这个class需要实现DOAction这个接口，具体把数据进行setInstance(一条记录) 注入到内存，传入的参数应该有user_define_class 这个属性

 	        type='aa'  由定义的class 返回数据（只用于下文callAction方法），这个class需要实现DOAction这个接口，具体把数据进行setInstances(多条记录) 注入到内存，传入的参数应该有user_define_class 这个属性

	        type='ao'  由定义的class 返回数据（只用于下文callAction方法），这个class需要实现DOAction这个接口，直接返回这个class 执行返回的string，传入的参数应该有user_define_class 这个属性

  p.callback         执行完后要执行的回调函数
```


# 核心方法调用 #

主要提供四个方法 callService，callAction，callPlatBus，loadPml。

## callService(p) ##
```
用途：
执行一个服务,这个服务可以为任意类型。
注意事项：
如果服务为更改数据的服务（如增加，修改，删除），并且连接规则或者配置了自定义动作，p.callType 必须设为uf。

使用callback处理返回数据（v）时，v是一个数组，如果单条记录   v[0].字段名，就可以得到改字段具体的值；多条记录可以用循环取值。


例子(和freemarker结合的例子)：
       callService({'btn':this,
		         'serviceUid':'${model.linkService.objUid}',
		         'callback':fnCallback,
		         'formName':'${model.targetForms}'
		         <#if (model.linkPaneModel)?exists>
		         ,'pml':'${model.linkPaneModel.fullCorrHref}'
		         <#else>
		         ,'target':'${model.gridModel.containerPane.name}'	
		         ,'pml':'${model.gridModel.containerPane.fullCorrHref}'
		         </#if>
		         <#if (model.targetPaneModel)?exists>	         
		         ,'target':'${model.targetPaneModel.name}'
		         </#if>
		         <#if (model.echoJs)?exists>	         
		         ,'echoJs':'${model.echoJs}'
		         </#if>
		         });
		         
       }
/////回调函数
       function fnCallback(v){
	if(v!=null && v.length>0){
		alert("名称为::" + v[0].name);
	}
	$('#gm_do_org_user_insert_name').val(v);
     }

```

## callAction(p) ##

```
用途：
直接执行后台的一个类，该类需实现com.exedosoft.plat.Action 接口。
注意事项：
p.actionName 为类的全名。
this.actionForm  这个成员变量不可用。
如果要获取参数，必须从总线获取。SessionContext.getInstance().getForm()

例子：
       
       callAction({'btn':btn,
	   			   'actionName':"com.exedosoft.plat.action.customize.archive.SaveArchiveAndDetails",
	   			   'paras':paras,'callback':cbtest});


/////回调函数
       function cbtest(data) {
	var flag = data.empty;
	var msg = data.msg;
	if(flag == "errordata") {
		var message = msg + "。年假或 倒休类型的日期不能为双休日，请更改。"
		alert(message);
		bzVacationPaneBack();
		return false;
	} else if(flag == "errordata2") {
		var message = msg + "。工作日类型的日期只能选择双休日，请更改。"
		alert(message);
		bzVacationPaneBack();
		return false;
	}
	
	if(flag == "notempty") {
		$('body').append("<div style='height: 100%; outline: 0pt none; width: 100%;' " +
				"class='simplemodal-wrap' tabindex='-1' id='vacationdiv'>" +
				"<div style='display: block;' class='simplemodal-data' id='confirm'> " + 	
				"<div class='header_vacation'><span>导入节假日期表</span></div>" + 
				"<div class='message_vacation'></div>" + 
				"<div class='buttons_vacation'>" + 
					"<div class='quxiao'>取消</div>" + 
					"<div class='nocover'>否</div>" + 
					"<div class='cover'>是</div>" + 
				"</div></div></div>");
		$('#vacationdiv').hide();
		confirm_vacation("数据有重复，确定全部覆盖吗？");
	} else {
		var isexist = data.isexist;
		if(isexist == 'isexist')
			return false;
		else 
			bzVacationPaneBack();
	}
}

///////////在自定义动作中设置返回值
   		if ("0".equals(empty[0])) {
			BOInstance bi = new BOInstance();
			bi.putValue("empty", "notempty");
			bi.putValue("btn", btn);
			//设置返回值
			this.setInstance(bi);
			return this.DEFAULT_FORWARD;
		} else if ("1".equals(empty[0])) {
			return DEFAULT_FORWARD;
		} else if("2".equals(empty[0])){
			BOInstance bi = new BOInstance();
			//设置返回值
			String msg = empty[1];
			bi.putValue("msg", msg);
			bi.putValue("empty", "errordata");
			this.setInstance(bi);
			return this.DEFAULT_FORWARD;
		} else /*if("3".equals(empty[0]))*/{
			//设置返回值
			BOInstance bi = new BOInstance();
			String msg = empty[1];
			bi.putValue("msg", msg);
			bi.putValue("empty", "errordata2");
			this.setInstance(bi);
			return this.DEFAULT_FORWARD;
		} 


```



## callPlatBus(p) ##
```
用途：
调用总线， 总线的调用写法见 “总线操作”
注意事项：
     该操作为同步操作。
例子：
        callPlatBus({'col1':'a','col2':'b'})
```

## loadPml(p) ##

```
用途：
加载一个面板。
注意事项：
目标面板_opener 为层弹出。
目标面板_opener_tab 为tab方式查看。

例子(和freemarker相结合)：
	    loadPml({
		   		 'pml':'${model.linkPaneModel.name}',
		   		 'title':'${model.linkPaneModel.title}',
		   		 'formName':'a${model.gridModel.objUid}'
		   		  <#if (item.targetPaneModel)?exists>	         
				,'target':'${model.targetPaneModel.name}'
				 </#if> }
		);

```