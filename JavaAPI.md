Java  API的 分为四部分介绍：1，DOService；  2，其它重点类； 3，接口或抽象类说明；4，帮助类。

DOService 是Java API的核心实现类，单独作说明；Javascript API也仅仅是 DOService 的客户端（BROWSE）简单实现。

重点类主要提供了对业务对象、参数、面板、表格、表格元素等平台核心功能的操作能力。

接口或抽象类主要为平台提供了扩展机制。

帮助类提供了很多有用的方法，减少使用者的代码开发量。


# DOService #
DOService是云鹤平台Java API最重要的一个类。DOService 主要方法有 invokeSelect，invokeSelectGetAValue，invokeUpdate，invokeAll，getInstance。下文的aService 已经被实例化，具体见“实例化DOService”。


## 实例化DOService ##
> ### 通过服务的名称调用服务 ###
如后台配置的学生查找服务名称为”student.list”,
DOService aService = DOService.getService(“student.list”);
> ### 通过服务的ID调用服务 ###
如后台配置的学生查找服务ID为”039d1f6aac41450ca2ac5895d5ffc93d”，
DOService aService = DOService.getServiceByID(” 039d1f6aac41450ca2ac5895d5ffc93d”);
> ### 错误的实例化方法,不要通过new操作符 ###
DOService aService = new DOService();
aService.setName(“studeng.list”);



## DOService参数传递说明 ##

DOService 的主要方法invokeSelect，invokeSelectGetAValue，invokeUpdate，invokeAll，getInstance。都可以采用两种方式传递参数。
> ### 直接传递 ###

> 通过直接调用方法传递，由于主要方法被多次重载，并且可以接受 List BOInstance 类型的参数，所以基本能满足绝大部分参数传递的要求，不需要使用间接参数传递。
```
	DOService findPI = DOService
					.getService("do.wfi.processinstance.browse.findbyinstanceUid");
	String curInstnaceUid = pt.getDoBO().getCorrInstance().getUid();
	List list = findPI.invokeSelect(pt.getObjUid(), curInstnaceUid);
```

> 注意事项：invokeSelect(paras:List) 和 invokeSelect(paras:Map) 可以传递的参数数量不限，但是paras:List 方式要和服务配置的参数个数、顺序严格匹配，如果不匹配则不起作用。Paras:Map方式在自动参数条件查询的情况，个数和顺序都可以不匹配，依赖于平台的自动条件组装。invokeUpdate 类似。

> invokeSelect(para:string …)方式传递参数和服务配置的参数个数、顺序严格匹配，如果不匹配则不起作用，上文中传递了两个参数，后台服务也配置了两个参数。 invokeUpdate类似。

> ### 间接传递 ###

> 先为DOService 传递参数，然后调用不带参数的方法。 有两种方式：

> 通过List 间接传递

```
DOService findPI = DOService
		.getService("do.wfi.processinstance.browse.findbyinstanceUid");
List paras = new ArrayList();
paras.add(ptobjuid);
paras.add(curInstanceuid);

findPI.addTempParaList(paras);
List list = findPI.invokeSelect();

```


> 通过Map 间接传递

```
DOService findPI = DOService
		.getService("do.wfi.processinstance.browse.findbyinstanceUid");
Map paras = new HashMap();
paras.put(“ptobjuidcolname”, ptobjuid)
paras.add(“tbstudentuid”,curInstanceuid);

findPI.addTempParaMap(paras);
List list = findPI.invokeSelect();
```

> 以上三种写法都是等效的。


> ### 注意事项 ###

> 通过list 方式间接传递，传递参数和服务配置的参数个数、顺序严格匹配，如果不匹配则不起作用。采用间接传递，调用的服务方法一定是不带参数的。


## DOService函数返回说明 ##

> DOService 的参数返回一般是BOInstance 或` List<BOInstance> `。为了支持java 1.4 没有使用泛型，也就是说只要是返回的List，那么内容一定是BOInstance。BOInstance 的说明见下文（其它重点类说明:BOInstance）。

> 执行invokeSelect 或 invokeSelectXXXX相关的方法时，服务的类型必须是查询类型（包括一般查询、自动条件查询、自定义查询等），服务配置的sql语句是对数据库的检索

> 执行invokeUpdate 或 invokeUpdateXXXX相关的方法时，服务的类型必须是更改类型（包括增加、删除、修改、自动条件修改等），服务配置的sql语句是对数据库的更新。

## DOService调用说明 ##

### invokeSelect() ###
```
public java.util.List invokeSelect()
执行查询操作。
1，	如果服务没有定义DOAction,那么查询操作执行的是该服务定义的sql语句，
2，	如果定义了DOAciton，没有定义sql语句，那么执行这个DOAciton。
3，	如果两者都有定义，那么还是执行服务定义的sql语句。
4，	如果两者都有定义，需要执行DOAciton，那么需要执行invokeAll方法,不是这个方法。
参数： 见参数传递说明。
返回： 见函数返回说明。
```

### invokeSelect(paraNo:int,pageNum:int) ###
```
public java.util.List invokeSelect(int pageNo,
                                   int pageNum)
分页形式查询返回一个List。

参数： 
pageNo - 页码 
pageNum - 每页多少行 
令见参数传递说明。
返回： 见函数返回说明。
```

### invokeSelect(aMap:Map) ###
```
public java.util.List invokeSelect(java.util.Map aMap)
执行查询操作。

参数： 见参数传递说明。
返回： 见函数返回说明。
```



### invokeSelect(String... strings) ###
```
public java.util.List invokeSelect(String... strings)
查询返回列表

参数： 见参数传递说明，不定长度
返回： 见函数返回说明。
```



### invokeSelect(paras:List) ###
```
public java.util.List invokeSelect(java.util.List paras)
执行查询操作。

参数： 见参数传递说明。
返回： 见函数返回说明。
```


### invokeSelect(instance:BOInstance) ###
```
public java.util.List invokeSelect(BOInstance instance)
查询返回列表 服务的参数由 BOInstnace 定义 

参数： 见参数传递说明。
返回： 见函数返回说明。
```

### getInstance(String... strings) ###
```
public BOInstance getInstance(String... strings)
获取一个BOInstance 实例。

参数： 见参数传递说明。
返回： 见函数返回说明。
```


### invokeSelectGetAValue() ###
```
public java.lang.String invokeSelectGetAValue()

执行查询，返回一个值。 
一般执行的 sql语句本身只返回一个值，如：
Select count(*) from tbstudent where ……
Select sum(a) from tbstudeng where …..
如果sql语句返回多列值。那么这个函数返回的值是第一列。
如果sql语句返回多行值。那么这个函数返回的值是第一行。

参数： 
见参数传递说明。
返回： 返回的是String类型的值。
```

### invokeSelectGetAValue(String... strings) ###
```
public java.lang.String invokeSelectGetAValue(String... strings)
执行查询，返回一个值。 
一般执行的 sql语句本身只返回一个值，如：
Select count(*) from tbstudent where ……
Select sum(a) from tbstudeng where …..
如果sql语句返回多列值。那么这个函数返回的值是第一列。
如果sql语句返回多行值。那么这个函数返回的值是第一行。

参数： 
见参数传递说明，为不定参数
返回： 返回的是String类型的值。
```


### invokeUpdate() ###
```
public BOInstance invokeUpdate()
                        throws com.exedosoft.plat.ExedoException
该服务可以执行新增、修改、删除等操作。 
。
返回： 见函数返回说明。
 
抛出： 
com.exedosoft.plat.ExedoException
```

### invokeUpdateForm() ###
```
public BOInstance invokeUpdateForm()
                            throws com.exedosoft.plat.ExedoException
该服务可以执行新增、修改、删除等操作，修改的参数来自于ui界面
返回： 见函数返回说明。
 
抛出： 
com.exedosoft.plat.ExedoException
```

### invokeUpdate(aMap:Map) ###
```
public BOInstance invokeUpdate(java.util.Map aMap)
                        throws com.exedosoft.plat.ExedoException
该服务可以执行新增、修改、删除等操作。 
参数： 见参数传递说明。
返回： 见函数返回说明。
抛出： 
java.sql.SQLException 
com.exedosoft.plat.ExedoException
```


### invokeUpdate(instance:BOInstance) ###
```
public BOInstance invokeUpdate(BOInstance instance)
                        throws com.exedosoft.plat.ExedoException
该服务可以执行新增、修改、删除等操作，采用instance 作为参数,接口更加友好 
参数： 见参数传递说明。
返回： 见函数返回说明。
 
抛出： 
com.exedosoft.plat.ExedoException
```


### invokeUpdate(String... strings) ###
```
public BOInstance invokeUpdate(String... strings)
                        throws com.exedosoft.plat.ExedoException
该服务可以执行新增、修改、删除等操作。 String类型不定的参数。
参数： 见参数传递说明。
返回： 见函数返回说明。
抛出： 
com.exedosoft.plat.ExedoException
```



### invokeUpdate(paras:List) ###
```
public BOInstance invokeUpdate(java.util.List paras)
                        throws com.exedosoft.plat.ExedoException
该服务可以执行新增、修改、删除等操作。 参数采用List 方式传入 
参数： 见参数传递说明。
返回： 见函数返回说明。
： 
抛出： 
com.exedosoft.plat.ExedoException
```

### invokeAll() ###
```
public java.lang.String invokeAll()
触发对应的服务，对应一个事务。
参数： 见参数传递说明。
返回： 见函数返回说明。
```

### invokeAll(aBI:BOInstance) ###
```
public java.lang.String invokeAll(BOInstance aBI)
完整得执行这个Serivce,包括这个服务对应的规则，在事务控制下 
参数： 见参数传递说明。
返回： 见函数返回说明。
```

### invokeAllNoTransation(aBI:BOInstance) ###
```
public java.lang.String invokeAllNoTransation(BOInstance aBI)
完整得执行这个Serivce,包括这个服务对应的规则，不在事务控制下 即使服务本身设置了事务控制也不执行事务控制。 如果需要事务控制，需要在方法的前后手动控制。
参数： 见参数传递说明。
返回： 见函数返回说明。
```

# 其它重点类说明 #

## DOBO ##

> 业务对象是平台最重要的概念，它是对业务实体的声明性抽象。业务对象有两种类型，一种是对数据库表的映射，一种是映射业务逻辑。

> ### 可以通过id或name获取业务对象 ###
```
  getDOBOByID(String)
  getDOBOByName(String)

  不要通过new 操作符获取业务对象。
```

> ### 访问业务对象数据总线，可以通过下列方法获取总线存放的值 ###
```
	public BOInstance getCorrInstance() 
```

> ### 可以通过下列方法 根据id获取数据库中的一条记录 ###
```
	public BOInstance getInstance(String uid) 
```

> ### 可以通过下列方法 获取数据库中的全部记录 ###
```
	public List getResult() 
```
> ### 可以通过下列方法执行业务对象下面定义的服务 ###
```
invokeListService(String)
invokeListService(String, BOInstance)
invokeListService(String, Map)
invokeUpdateService(String)
invokeUpdateService(String, BOInstance)
invokeUpdateService(String, Map)
```




## BOInstance ##
```
一般对应于数据库里面的一条记录，在平台中是最基本的也是最常用的数据类型。

1，	可以直接通过new 操作符实例化，如BOInstance anInstance = new BOInstance();
2，	可以通过json字符串或 org.json.JSONObject 转换为BOInstance: 
fromJSONObject(JSONObject)
fromJSONString(String)
3，	BOInstance 一般来自DOService 操作后的返回值。
4，	BOInstance 可以作为HashMap使用，BOInstance 本身就是对HashMap又做了一次包装，可以通过anInstance.getMap()获取原生的java.util.HashMap。
5，	和平台紧密结合使用时，必须要指定BOInstance 所在的业务对象。即
anInstnace.setBO(para:DOBO)。DOService的返回值会自动指定。
6，	当和平台紧密使用时，BOInstance可以使用缺省的增删改操作：
a)	invokeDelete(),使用缺省的删除服务删除本条记录。
b)	invokeUpdate()，使用缺省的新增或修改服务。
c)	invokeUpdate(aService:DOService),，使用指定的新增或修改服务。
d)	invokeUpdate(aServieName: String) ，使用指定的新增或修改服务。

7，	支持类型转换，可以通过下列方法获取相应的数据类型。
getValue(DOBOProperty)
getValue(String)
getValueArray(DOBOProperty)
getValueArray(String)
getBOInstanceValue(DOBOProperty)
getBOInstanceValue(String)
getCollection(DOBOProperty)
getCollection(String)
getDateValue(DOBOProperty)
getDateValue(String)
getDateValue(String, String)
getDependInstance()
getDoubleValue(DOBOProperty)
getDoubleValue(String)
getLongValue(DOBOProperty)
getLongValue(String)
getObjectValue(DOBOProperty)
getObjectValue(String)

8，	支持Null值替换。

getValue4Null(key：String)  如果根据key返回的value为null，则返回””。
getValue4Null(key：String, rep：String) 如果根据key返回的value为null，则返回指定的rep。

9，	支持深度克隆。
```

## DOPaneModel ##
```
 面板业务模型。面板是容器它可以包含面板、表格、功能树、插件等。	它可以通过name或id得到：

	public static DOPaneModel getPaneModelByID(String id) 

	public static DOPaneModel getPaneModelByName(String name) 

它有两个常用的方法。
1，public String getFullCorrHref(BOInstance contextInstance,DOService contextService)
获取该面板的完整URL,通过这个URL可以独立访问改面板。缺省情况下需要的两个参数都可以不传递（即可以传递null值）。如果面板所含元素需要显示单条记录的信息才需要传递参数。
			
2，public String getCorrHref(BOInstance contextInstance,
			DOService contextService) 
   和上个方法职责相同，区别在于返回的URL不含有web module的前缀。
```

## DOGridModel ##
```
表格业务模型。表格是数据显示的主要载体，是最常用搞得UI模型。它可以通过id或name得到:
	public static DOGridModel getGridModelByID(String id) 

	public static DOGridModel getGridModelByName(String name) 
常用方法：
1，	public List getAllGridFormLinks() 获取表格包含的所有的表格元素。
2，	public List getGridFormLinks()  获取表格包含的经过权限过滤的的普通位置的表格元素。
3，	public List getBottomOutGridFormLinks()获取表格包含的经过权限过滤的的下部位置的表格元素。
4，	public List getRightOutGridFormLinks()获取表格包含的经过权限过滤的的右部位置的表格元素。
5，	public List getTopOutGridFormLinks()获取表格包含的经过权限过滤的的上部位置的表格元素。
6，	public Integer getRowSize() 获取在分页情况下每页显示的条数。当返回值==0时，则不分页。
```

## DOFormModel ##
```
  表格元素业务模型，依附于表格存在，在平台中是粒度最小的UI单位。它可以通过id或者通过属性id得到：

	public static DOFormModel getFormModelByProperty(String propertyUid) 
	public static DOFormModel getFormModelByID(String formUid) 
  常用方法：
  1，public List getLinkForms() 获取连接的其它的表格元素
  2，public String  getTargetForms()   获取可以处理的表格的范围
```





# 接口或抽象类说明 #

## DOAccess ##
```
用途（一），多用于模型关联之间的显示控制，如一个表格元素是否在表格下面显示，具体：
a)	控制表格元素是否显示。 配置于表格元素。
b)	控制面板是否显示。配置于面板之间的关联。
c)	控制规则是否执行。配置于规则。


用途（二），多用于模型本身显示控制，如控制菜单是否显示，具体：
a)	控制菜单是否显示。 配置于菜单。
b)	控制面板是否显示。 配置于面板。
c)	控制服务数据集的可见性。配置于服务。
```


## DOAction ##
```
DOAction 在平台中称为自定义动作，是平台中的一个重要接口，应用于多处：
a)	配置于DOService,作为服务扩展，这时服务的执行完全由自定义动作控制。
b)	配置于DOParameter,作为参数扩展，这时参数的值为自定义动作的返回值。
c)	由快速开发 JavaScript API调用，Javascript 端的返回值为自定义动作的返回值。
```

## DOAbstractAction ##
```
  DOAbstractAction是继承DOAction的抽象类，提供了几个帮助方法。
```

## DOIModel ##

> 平台所有UI模型的类都实现了DOIModel。DOIModel没有任何方法需要实现，是一个标示性接口。

## DOIView ##

> 平台所有界面组件都实现了DOIView。DOIView只有一个必须要实现的方法getHtmlCode(DOIModel doimodel)。 这个方法的目的是输出html片段。


## DOBaseForm ##

> DOBaseForm是继承DOIVew的抽象类，为实现表格元素的界面组件提供了几个帮助方法。

## DOBaseMenu ##

> DOBasMenu 是继承DOIVew的抽象类，为实现菜单的界面组件提供了几个帮助方法。

## DOBasePaneView ##

> DOBasePaneView是继承DOIVew的抽象类，为实现面板的界面组件提供了几个帮助方法。


## SessionParter ##
```
SessionParter 主要指定系统要此采用的权限验证策略。在 globals.xml指定要使用的SessionParter实现。默认情况下为	com.exedosoft.plat.bo.org.DefaultSessionParter。
可以更改其实现，如学而思系统采用的是com.exedosoft.plat.SessionParterXes。
```

## DOFormModelAOP ##

> 对FormModel模型进行AOP控制。

# 帮助类说明 #

## StringUtil ##
```
主要是对String相关操作的类，还提供其它一些常见的帮助方法，是平台中使用最多的帮助类，具体方法列表：

static void	copyDirectiory(java.lang.String file1, java.lang.String file2) 
          Copy 整个目录一般方式
static void	copyFile(java.io.File in, java.io.File out) 
          Copy 某个特定文件，nio 方式
static java.lang.String	dealSpecChar(java.lang.String src) 
          替换< > " ' 等特殊字符
static java.lang.String	decodeBase64(byte[] data) 
          Decodes a base64 aray of bytes.
static java.lang.String	decodeBase64(java.lang.String data) 
          Decodes a base64 String.
static java.lang.String	encodeBase64(byte[] data) 
          Encodes a byte array into a base64 String.
static java.lang.String	encodeBase64(java.lang.String data) 
           
 java.lang.String	encodeFromISO2GBK(java.lang.String str) 
           
 java.lang.String	encodeFromISO2UTF(java.lang.String str) 
           
 java.lang.String	encodeUTF2GBK(java.lang.String str) 
           
static java.lang.String	filter(java.lang.String value) 
          防止跨站脚本攻击xss
static java.lang.String	get_Name(java.lang.String boName) 
           
static java.lang.StringBuffer	getAttachMentFile(java.lang.String theFileName) 
          获取附件文件，相对路径（相对与系统设定的下载上传路径）
static java.lang.StringBuffer	getAttachMentFileAbstract(java.lang.String theFilePath, java.lang.String theFileName) 
          * 获取附件文件，绝对路径
static java.lang.String	getCalException(java.lang.String expression, com.exedosoft.plat.bo.BOInstance bi, java.util.Collection boProperties, java.lang.String placeHolder) 
          处理一个表达式，这个表达式可能在 DOFormModel 或者 NodeInstance 中用到。
static java.lang.String	getCurrentDayStr() 
           
 java.lang.String	getDateStr(java.util.Date aDate, java.lang.String format) 
           
static java.lang.String	getDotName(java.lang.String boName) 
           
static java.util.List	getMapList(java.lang.String mapFormatStr, boolean isAddLastZero) 
          划分字符串
static java.util.List	getMapListCK(java.lang.String mapFormatStr, boolean isAddZero) 
          划分字符串
static java.lang.String	getOrderByCol(java.lang.String str) 
          db2 的排序强制不能采用table.col这种形式 只能采用col的形式，如果col重复，请as othername
static int	getPageSize(int totalSize, int rowSize) 
          根据总的记录数和每页显示的行数判断共有多少页
static java.util.List	getStaticList(java.lang.String src) 
          把字符串转化为列表形式
static java.lang.String	getValueByKey(java.lang.String src, java.lang.String aKey) 
           
static boolean	isEMail(java.lang.String str) 
           
static boolean	isFloat(java.lang.String str) 
           
static boolean	isFloat(java.lang.String str, int precision) 
           
static boolean	isHTTPNET(java.lang.String str) 
           
static boolean	isLong(java.lang.String str) 
           
static boolean	isNumber(java.lang.String str) 
           
static boolean	isSupportDate(java.lang.String str) 
           
static boolean	isValid(java.lang.String str, java.lang.String regExt) 
          检查正则表达式
static void	main(java.lang.String[] args) 
           
static java.lang.String	MD5(java.lang.String plainTxt) 
           
static StringUtil
newInstance() 
          Encodes a String as a base64 String.
static java.lang.String	replaceSqlCol(java.lang.String aWholeStr, java.lang.String anOldCol, java.lang.String aNewCol) 
           
static void	tabSpace(java.lang.StringBuffer buffer, int times) 
          添加Tab空格
static void	test_replace() 
           
static java.lang.String	transDot(java.lang.String beforStr) 
          把小数点形式转换为经纬度坐标
static java.lang.String	unFilterXss(java.lang.String txt) 
```

## RunJs ##

> 提供了服务器端执行javascript 的便捷方法。

## ZipUtil ##

> 提供了对ZIP文件操作的方法。

## PinYin ##

> 可以把汉字翻译成汉语拼音。

## DOMXmlUtil ##

> 提供了使用DOM的简便方法。

## SimpleXMLUtil ##

> 提供了方便读写XML文件的方法。