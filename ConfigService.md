## 基本概念 ##

> 服务是描述业务对象的行为。这个服务既可以是底层数据层面的操作，也可以是粗粒度的业务行为。当一个数据表被初始化为业务对象后，会形成6个基本服务，命名规则是业务对象 + `_` + 后缀，后缀分别为browse、list、insert、update、delete、auto\_condition。一个面向底层数据操作的业务对象必须含有后缀为browse的服务，自动生成的该服务不要修改。


> 服务按照复杂程度可以分为两类:
    1. 基本服务，只定义的一个sql语句。
    1. 一般服务，定义了自定义动作或者定义了规则。
> > _一般服务如果定义有sql语句，那么可以“退化”作为基本服务使用。 在配置中跟界面组件的选择有关系，界面组件的后缀名如果含有uf，那么它既可以执行基本服务也可以执行一般服务，否则只能执行基本服务。_



> 服务按照操作类型可以分为：
    1. 简单查询 定义的SQL语句只包含针对一个表的查询。
    1. 查询  定义的SQL语句完成的是select 操作 查询类的SQL语句，可以是分为两段，第一段是需要完成查询的SQL,第二段是第一段SQL语句取回结果的数量，如
```
   select * from demo;select count(*) from demo
```
> 在一般情况下，都会写作` select * from demo `因为在需要结果集数量的场合，系统可以把后面的语句补写完整。但是如果是非常复杂的SQL语句，比如通过表连接、子句或包括union等，系统可能无法进行补写，需要写完整的两段。
    1. 修改  定义的SQL语句完成的是update 操作
    1. 新增  定义的SQL语句完成的是insert 操作
    1. 保存  是新增或者是修改
    1. 删除  定义的SQL语句完成的是delete 操作
    1. 自动条件查询 定义的SQL语句含有通配符/`*`condition`*`/，运行时可以根据用户的输入自动匹配查询条件；如果需要排序，通配符为--orderby-- defaultcols desc@#， 一个例子(democo1为默认的排序字段)：
```
        select * from demo /*condition*/ --orderby-- democol1 desc@#
```
> > 改变排序字段和排序方式需要通过表单传递，在查询表单上加两个下列列表，EXEDO\_ORDER表示排序字段，EXEDO\_ORDER\_TYPE表示排序方式，这两个下列列表的界面组件均为form.DOStaticList或form.DOStaticListPopup，前者的相关配置： 排序字段名称,排序字段中文名; 排序字段名称,排序字段中文名; 排序字段名称,排序字段中文名;  后者为： desc,降序;asc,升序。


> 如果有其它条件，` /*condition*/ `要放到最后，如where a.column=1 ` /*condition*/ `。

  1. 自动条件修改 修改的一种特殊形式，update语句必须按照类似系统生成的严格格式编写，执行时系统会自动重新组装sql语句，用户录入的才修改，没有录入的不是置为null，而是不作修改。
  1. 自由查询    和自动条件查询的功能类似。可以适用更复杂的情况，一个例子：
```
	select *  from v_tbclass where objuid  
	in (select objuid from v_tbclass minus 	(select fdclassid from V_STU_ALRDY_KAOQING WHERE 1=1  	FDCLASSNUM[AND  V_STU_ALRDY_KAOQING.FDCLASSNUM='?'])) 
			and 1=1 and fdhaspersons>0 and fdifpassed=0 FDCLASSNUM[and fdtotaltimes>='?'],
			fdnianfen[AND  fdnianfen='?'],fdxueqiname[AND  fdxueqi='?'],fdgrade[AND  fdgrade='?'],
			fdsubject[AND  fdsubject='?'],fdclasslevel[AND  fdclasslevel='?'],fdarea[AND  fdarea='?'],
			fdxuequ[AND  fdxuequ='?'],fdclassareaname[AND  fdclassareaname='?'],
			fdclassroomid[AND  fdclassroomid='?'],fdteacherid[AND  fdteacherid='?'],
			fdclasstemplateid[AND  fdclasstemplateid='?'],fdstarttime[AND  fdstarttime='?']

```

> 其中：fdstarttime[fdstarttime='?'](AND.md)
> 这种写法会自动处理为自动条件 前面的fdstarttime是界面元素的名字 后面为sql片段 根据前面去获取界面的值 如果为空 则该条件不加入

  1. 自由保存
  1. 脚本  定义一个javascript 脚本，最后一行返回sql。
  1. 自定义动作查询  定义一个自定义动作，自定义动作返回一个结果集。 自定义动作查询属于基本服务。自定义动作通过setInstances设置返回的结果集，结果集是一个BOInstance的List。  _注意在这个自定义动作里面不能直接使用该服务的invokeSelect()方法，否则要引起死循环。_

> 服务定义的SQL语句会默认处理为小写，除非前面加上`/*keep*/`以保持原来的大小写。


### 服务的执行 ###


  1. 简单执行　即只执行所定义的SQL语句，JAVA代码
```
               DOService aService = DOService.getService("服务名称");
               aService.invokeUpdate();//或者aService.inovkeSelect
```
  1. 完全执行  指定所定义的规则、自定动作等。 如果定义了自定义动作，则不执行所定义的SQL语句。JAVA代码
```
               DOService aService = DOService.getService("服务名称");
               aService.invokeAll();
```

> 调用服务的表格元素界面组件，如果是完全执行， 一般以uf为后缀。


> 服务可以通过修改“业务对象”改变其隶属的业务对象，放到其它业务对象下面。


### restful webservice 调用及可见性 ###

> 可见性,包括公开(public),保护(protected),私有(private)。

> 平台的服务可以通过restful web service 的方式供外部访问。
> restful web service 是一种轻量级的web service 的实现方式，简单而且高效。

  1. "调用方"访问平台的服务，必须经过授权，需要平台提供的用户名和密码，"调用方"需要小心保护用户名密码，最好经常更换密码。

> 2，如果一个服务需要被外部访问那么必须设置服务的可见性，可见性为public 或
> public类型的服务:只要"调用方"取得平台提供的用户名密码即可访问。
> protected类型的服务："调用方"除了取得平台提供的用户名密码即可访问，还要单独授权。

> 3，对"调用方"的验证方式是可以自定义的：
> 在globals.xml中：
```
         <property name="webservice.login.service">do_org_user_findbynameandpwd</property>
```
> 这个是验证"调用方"的用户名和密码，可以和普通用户登录的服务一样，这样"调用方"的用户名密码也存放在用户表中，从安全性角度讲，最好分开。
```
         <property name="webservice.auth.service">exists_rest</property>
```
> 这个验证proteced的服务是否获得单独授权。

### 总线刷新类型 ###

> 总线刷新类型包括REFRESH\_NO（不刷新）、REFESH\_CURRENT（刷新业务对象当前值）、REFESH\_SEARCH\_LIST（刷新总线SEARCH\_LIST节点）、REFESH\_ALL（全部刷新包括SEARCH\_LIST，GLOBAL\_UID节点及业务对象总线当前值）、REFESH\_MAP\_DATA\_GLOBAL\_UID（刷新业务对象当前值及总线GLOBAL\_UID节点）。

> 总线相关资料，参见 [业务对象总线](BusinessObjectBus.md)。

> 总线刷新后会影响到参数的获取，参见[参数](ConfigParameter.md)。

> 如果服务没有设定总线刷新类型，新增和修改类型的服务的缺省类型是REFESH\_CURRENT，其它类型服务缺省是REFRESH\_NO。


### 参数检查 ###

> 参数检查的详细说明参见[参数检查](ConfigParameterCheck.md)。

> 这里的参数检查选项是指定服务是否进行“参数检查”。
  1. PARA\_CHECK\_FALSE　　不进行参数检查默认选项
  1. PARA\_CHECK\_SIMPLE   只要参数不合法就返回
  1. PARA\_CHECK\_DETAIL   检查完所有的参数，把不合法的信息进行组合后返回

### 事务 ###

> 支持声明式事务。

> 只有服务定义规则的时候定义事务才有意义，默认情况下没有定义事务。

> 当服务定义事务即“事务控制”选择“是”时，服务及服务所包含的规则在一个事务下执行；服务包含的规则中定义的服务即使“事务控制”选择“否”也不起作用。



### 日志 ###

> 日志定义默认为否。当服务的日志设为“是”时， 服务的类型只有是新增、修改、删除才记录日志，默认日志记录被写入到do\_log\_data表中。可以设置“日志别称”，如果不设置日志名称将取自服务的类型（“删除”、“修改”、“新增”）。

> 如果需要自定义记录日志的行为，请把自定义类全名定义到“过滤类”里面，改自定义类需要实现com.exedosoft.plat.action.DOAction接口。


### 记录过滤 ###

> 记录过滤指查询类型的服务返回的结果经过重新过滤，返回查询结果的子集。

> 不推荐使用记录过滤的方式处理条数非常大的结果集。

> 记录过滤必须制定“过滤类”，过滤类需要实现com.exedosoft.plat.DOAccess接口。

> 不能同时自定义记录日志的行为（见上）和实现过滤类。

> 记录过滤支持和工作流节点权限的混合过滤。



## 对应JAVA 数据类型 ##
> com.exedosoft.plat.bo.DOService

## 配置 ##

> ### 基本信息配置 ###

> ![http://eeplat.googlecode.com/files/do_service.png](http://eeplat.googlecode.com/files/do_service.png)

> 通过配置，我们可以：

> <b> 修改服务： </b>  修改服务的基本信息

> <b> 复制服务：  </b>  以当前选择的服务为模板，克隆一个新的服务，复制后可以双击左侧树“服务”的文件夹，文件夹下面的服务会重新加载，新的服务也会显示出来。

> <b> 生成规则：   </b>  以当前服务为基础生成一个规则，规则的默认执行条件为true，默认执行次数是1次，即让该规则执行并且只执行一次。

> ![http://eeplat.googlecode.com/files/do_service_gene_rule.png](http://eeplat.googlecode.com/files/do_service_gene_rule.png)

> 当生成规则后，生成的规则可以通过生成的规则TAB页查看，如下图：

> ![http://eeplat.googlecode.com/files/do_service_gene_rule_result.png](http://eeplat.googlecode.com/files/do_service_gene_rule_result.png)

> <b> 新增：   </b>  新增一个服务，该服务的业务对象为当前服务的服务对象。

> <b> 删除：  </b> 删除当前服务，并且把当前服务与参数的连接删除。

> <b> 导出：  </b> 导出当前服务的XML格式的交换文件，包含服务的配置数据及服务连接参数的配置数据。

> ### 管理服务参数 ###

> 管理服务参数界面即上图的“服务参数列表”部分。它可以新增、修改、删除“服务参数”，在这里“服务参数”不是“参数”而是服务和参数的连接。

> 新增和修改连接的参数，可以是服务所在的业务对象的参数，也可以是其它业务对象的参数。下面是新增参数的界面：
> ![http://eeplat.googlecode.com/files/do_service_add_para.png](http://eeplat.googlecode.com/files/do_service_add_para.png)

> “连接的参数”：是参数选择器，可以选择不同业务对象下面的参数。

> “排序表标示”：填写的是整形的数字，表示参数的顺序，参数顺序必须和服务中的sql语言中的?的顺序严格匹配。

> 下面两个项目 只对“自动条件查询”有效：

> 自动条件查询，平台采用的是PrepareStatement预编译和参数绑定的方法。

> “模式”: 设置字段的查询条件，对参数的值进行必要的补充或格式化，如在模糊查询like的情况下，需要%参数的值%，一般模糊查询的情况模式的通用写法是这样的：#col# like ?;%#value#%。这个模式被;分成了两个部分，第一部分是参数的查询条件，可以用通配符#col#代替列名，也可以直接写成表名.列名 like ? 也还可以加函数如lower(表名.列名) like ?，后面一部分是对参数值的处理，也可以加函数如 lower('#value#%') ,如果“模式是否支持脚本”选是的话还可以写复杂的javascript脚本。

> 还有一种特殊的模式没有分号，但含有#value#,在这种情况下是使用的sql语句拼装，会直接拼装进sql语句，而不是使用的参数绑定。 如标准写法而参数绑定的方式的写法为t.xiang\_mu\_lb\_uid in(?);#value#，用这种模式的写法为t.xiang\_mu\_lb\_uid in(#value#)，会把值直接把#value#替换。这种模式的使用场景主要是in ，由于in的内容可能为多个，而参数绑定无法提前得知，所以采用拼装的方式。

> “模式是否支持脚本”：模式是否支持javascript脚本。





> 执行“删除”操作时，必须首先选择参数，可以选择1个或多个参数。

> “复制到”可以把选中的参数复制给其它的服务。

> ![http://eeplat.googlecode.com/files/do_service_para_copy.png](http://eeplat.googlecode.com/files/do_service_para_copy.png)


> ### 规则定义 ###

> 当一次处理需要多次SQL语句调用时，需要定义规则。如当新增A表时，需要修改B表的数据，删除C表的数据，这时需要把修改B表数据的服务发布为规则，删除C表的服务发布为规则。  在新增A表的服务上定义上述两个规则。如下图：

> ![http://eeplat.googlecode.com/files/do_servicerule_add.png](http://eeplat.googlecode.com/files/do_servicerule_add.png)



> ### 转向定义 ###

> 服务执行后，可以定义转向面板，转向的面板可以定义多个。 当转向面板定义在按钮（表格元素）上时，只能定义一个，如果转向面板恰好只有一个，请使用按钮定义。
> ![http://eeplat.googlecode.com/files/do_service_redirect.png](http://eeplat.googlecode.com/files/do_service_redirect.png)


> ### 所有引用 ###
> > 待实现。




