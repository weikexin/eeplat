## 如何修改web module 的名称？ ##
> 平台缺省的web module名称为 eeplat，如果需要改成其它名称请修改 web-inf/classes 下面globals.xml文件

```
    <property name="webmodule">eeplat</property>
```

> 把webmodule 名称改为你需要的。

> 较老的版本还需要修改exedo/webv3/js/main/main.js
```
    var globalURL = "/eeplat/";
```

## 平台对主键的支持 ##

> 平台的建模工具缺省支持UUID方式。平台引擎本身可以支持UUID、HI/LOW、自增等各种主键。
> 修改globals.xml里面的 model.uuid = false 可以让建模工具支持其它类型的主键。
```
	    <property name="model.uuid">false</property>

```
> 当用平台的建模工具时，主键列必须是32位或以上的字符型，特征值列可以是任意字段。
> 如果需要其它主键类型，可以先通过先“生成”后“修改”的办法，修改的地方有业务对象对主键的配置、默认生成的SQL语句及参数等。

> 平台提供UUID生成类：com.exedosoft.plat.util.id.UUIDHex

## 如何使用DOValueResultList界面组件？ ##

> 平台缺省提供的DOValueResultList 可以快速把关联表的主键列翻译成特征值列。但是这种翻译是牺牲效率为前提的，会造成n+1查询问题（只有启用缓存，并且缓存命中的情况下才不会反复查询数据库）。
> 如果是快速原型或者是关联表数据量不太大或者启用缓存的情况下的话可以使用DOValueResultList ，其它情况应避免使用。


## 对GAE的支持有什么限制？ ##

> GAE的存储采用的是BIGTABLE,并不是关系型数据库。BIGTABLE本身也有一些限制(http://code.google.com/intl/zh-CN/appengine/docs/java/datastore/queriesandindexes.html)。  EEPlat 采用jiql(http://www.jiql.org/) 来进行数据操作。和关系数据库相比，使用GAE:

  * 不能采用模糊查询
  * 不能查询大批量数据
  * 对联合查询支持的不好

## 如何手动执行组织权限SQL语句？ ##
> 组织权限的SQL语句存放在exedo\initsql，按数据库的不同分别为sqlserver2000.sql，oracle.sql,mysql.sql,gae.sql,db2.sql。如果系统没有正确初始化，可以根据相应的文件别名进行执行该文件。如果运行在其它未被直接支持的数据库上面，请选择语法相近的文件进行修改，如支持Sybase等的话，请用sqlserver2000.sql进行修改。


## 工作流节点中的自连接情况如何支持？ ##

> 分支节点和汇合节点的作用可以处理会签的情况。比如三个副院长都审完了，才能到院长那审批，这三个副院长没有先后之分。但是，比如副院长这如果人数不确定，有可能三个人有可能4个人，需要根据角色查询，这样就不要用分支和汇合节点了。
> 使用普通的人工节点，做一个自定义动作， 只要按条件执行完，才执行该节点的 perform方法。使用自定义动作可以处理更复杂的业务，比如，还可以设置个百分数，5个人中有4个人提交了就可以了。

## 如何固定住左侧菜单和改变其宽度？ ##

> 现在平台缺省提供两种风格的左侧菜单：OutLook风格和树形风格。并且用户可以自由扩展。当使用OutLook风格的菜单时，往往不需要左右拖动，这时候可以删掉相关代码，exedo.jsp中的代码:
```

	//初始化左右拖动
	  $(".resizeTd").mousedown(function(e){
		  var oldPageX = e.pageX;
		  var old_gLeW = $(".gLe").width();
		  var old_gRiW = $(".gRi").width();
		  $(document).bind('mousemove',function(e){
//////////////不能太小
			  if(e.pageX > 10){
				  $(".gLe").width(old_gLeW + e.pageX - oldPageX);
				  $(".gRi").width(old_gRiW - e.pageX + oldPageX);
				  $(".gFpage").width(old_gLeW + e.pageX - oldPageX-1);
				  resscrEvt();
			  }
			  window.status = e.pageX;	   
		  }).bind('mouseup',function(e){
			  $(document).unbind('mousemove');
			  $(document).unbind('mouseup');	
			  }
		  );

	 });

```


宽度在css中定义，修改的内容：
```

  .gLe{width:161px;padding-bottom:15px}
  .gFpage{width:160px;overflow-x:hidden;overflow-y:auto;float:left}

```



## 系统是否完全开源？ ##

> 目前EEPlat采用部分开源，社区版本永久免费的策略。社区版仅允许自己使用，不得用于分发和出售，如果要用于分发和出售，请购买并使用商业版。平台80%的代码开源，包括平台的核心HMVC机制中通用界面组件的实现，工作流引擎，工作流建模工具，UI层全部代码及第三方集成代码（如EXCEL导出集成、FusionChartsFree集成、nkoa集成）等。
> 商业版本和社区版本的区别，社区版本代码一般比商业版本晚一年左右，并且在多租户、手机、工作流等方面没有商业版本成熟。社区版本在底部必须加入Power By EEPlat的Logo：![http://eeplat.googlecode.com/files/poweredby_s.jpg](http://eeplat.googlecode.com/files/poweredby_s.jpg)


## 如何获得商业支持？ ##

> 商业应用上线后，如果用户数大于20，需要向作者申请商业LICNECE。如果需要培训、商业支持等可以和作者联系(contact@eeplat.com)。

## 商业版本和社区版本的区别？ ##

> 社区版本代码一般比商业版本晚一年左右，并且在多租户、手机、工作流等方面没有商业版本成熟。社区版本在底部必须加入Power By EEPlat的Logo。商业版本在UI、数据缓存、数据校验等方面有全新的实现。