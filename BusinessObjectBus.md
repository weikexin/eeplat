# 总线的基本概念 #
  1. 数据总线是业务对象之间共享数据、交换数据的唯一媒介。
  1. 数据总线存在存、取两种操作。
  1. 数据总线的生命周期是是Session，也就是说从用户会话开始到用户会话结束数据总线是一贯和连续的。
  1. 数据总线是线程独享的、线程安全的，一个线程对数据总线的改变不会影响到其他线程对数据总线的使用，每个线程都是使用的总线的副本。

# 数据总线的构成方式 #

数据总线有下面几部分构成，每个组成部分我们称之为节点：

> <img width='500' height='400' src='http://eeplat.googlecode.com/files/yiyi_bus_node.png' />

## FORM节点 ##

### FORM的构建 ###
FORM的类型是BOInstance。 FORM指对HTTP GET的QueryString的封装或对HTTP POST 数据的封装，它可以是界面上FORM的值，也可以是通过AJAX方式传递的参数。如/mvccontroller/a=1&b=2&c=3,那么FORM的构建过程是这样的：
BOInstance  form = new BOInstance();
form.putValue(“a”,”1”);
form.putValue(“b”,”2”);
form.putValue(“c”,”3”);

### FORM的获取 ###

DOService的参数是怎样获取FORM的值的？

首先，参数的类型是FORM,确定是从总线的FORM中取值。
其次，查看参数是否配置了属性？
  * 如果配置了属性
String parameterValue = form.getValue(属性的全名)。
属性的全名的构成：属性所在业务对象的名称+ `_`+属性的名称。

如果parameterValue得到的是null，参数还会按照“没有配置属性”取值。
  * 如果没有配置属性
String parameterValue = form.getValue(参数的名称)。

如果parameterValue得到的是null，参数会查看是否配置了默认值，如果配置了默认值则取默认值，否则直接返回null。

> <b> 当使用API时，可以通过SessionContext.getInstance().getFormInstance()获取Form 节点</b>

## USER节点 ##

### USER的构建 ###
USER的类型是BOInstance。 FORM指对登陆用户封装，USER是在登陆自定义动作里面创建的。
```
//service 是指根据用户名、密码查询登陆用户的服务
		users = service.invokeSelect();
		if (users != null && users.size() > 0) {
			BOInstance user = (BOInstance) users.get(0);
SessionContext us = (SessionContext) request.getSession().getAttribute(
				"userInfo");
			if(us == null){
				us = new SessionContext();
				request.getSession().setAttribute("userInfo", us);
			}
/////为用户添加他的岗位：
			user.putValue("stationuid", user.getValue("fdgangweiid"));
///为用户添加他所在的部门：
			user.putValue("deptuid", "tt");
			us.setUser(user);
	
		}
```
### USER的更改 ###
> 例: 把当前用户名改为admin,界面皮肤改为red
  * WEBService 方式
> > /eeplat/servicecontroller?dataBus=setUserContext&contextKey=username&contextValue=admin& contextKey=skin&contextValue=red
  * 代码方式
```
   SessionContext.getInstance().getUser().putValue("username", "admin");
   SessionContext.getInstance().getUser().putValue("skin", "red");
```


### USER的获取 ###
参数关于对USER获取有5中类型分别是:

  1. TYPE\_LOGIN\_ID:
> > 获取登陆用户的UID，如果为空则返回’666666’。
  1. TYPE\_LOGIN\_NAME:
> > 获取登陆用户的名称，如果为空则返回null。
  1. TYPE\_LOGIN\_MAIN\_DEPT:
> > 获取用户所在的部门uid。USER在构建过程中必须添加deptuid的值才可以获取到,否则返回null。
  1. TYPE\_LOGIN\_MAIN\_STATION:
> > 获取用户的岗位uid。USER在构建过程中必须添加stationuid的值才可以获取到,否则返回null。
  1. TYPE\_LOGIN\_KEY:
> > 根据缺省值里面的配置返回USER在构建过程中对应的值，可以得到USER里面的所有的值。可以用这种类型得到前四种类型的值，前四种类型其实是这种类型的特例,如TYPE\_LOGIN\_MAIN\_STATION 这个类型可以用TYPE\_LOGIN\_KEY 代替，只要在缺省值里面配置字符串’stationuid’;并且还要更灵活，如果表达岗位不用stationuid, 我们只要在缺省值里面配置相应的字符串。
> > <b>  当使用API时，可以通过SessionContext.getInstance().getUser()获取User节点</b>

## GLOBAL节点 ##

_注意，下面描述的GLOBAL节点内容，只有EEPlat 2014或后续版本才支持。_

创建:
  * WEBService 方式
　/eeplat/servicecontroller?dataBus=setGlobalContext&&contextKey=?contextValue=?
  * 代码方式
　 SessionContext.getInstance().getGlobal().putValue(KEY,VALUE)


获取:
  * WEBService 方式
　/eeplat/servicecontroller?dataBus=getGlobalContext
  * 代码方式
　 SessionContext.getInstance().getGlobal()


## ECHO\_STR节点 ##

创建:
SessionContext.getInstance().getThreadContext().setEchoValue(echoValue)

获取:
  * WEBService 方式
> > /eeplat/servicecontroller?dataBus=getContext
  * 代码方式
　 SessionContext.getInstance().getThreadContext().getEchoValue()

## SEARCH\_LIST节点 ##

创建:

  * WEBService方式

　/eeplat/servicecontroller?dataBus=setContextColl&contextKey=?contextValue=?&contexValue=?

  * 代码方式

> SessionContext.getInstance().getThreadContext().setInstances(paras)


获取:

  * WEBService方式

　/eeplat/servicecontroller?dataBus= getContextColl

  * 代码方式
> SessionContext.getInstance().getThreadContext().getInstances()


## CURRENT节点 ##

  * 代码方式
> > 获取当前业务对象被选择的数据：
> > > SessionContext.getInstance().get("业务对象ID或业务对象")

> > 把数据设置为当前选择:
> > > SessionContext.getInstance().put("业务对象ID或业务对象",BOInstance)

  * WEBService方式

> 见下述“总线操作”。




# 总线操作 #

假设webmodule名称为：eeplat，总线的操作一般不单独进行，为了减少网络调用，一般和面板操作和服务操作放在一起进行。总线操作中 URL：/eeplat/mvccontroller 和 /eeplat/servicecontroller操作是等效的，下面以/eeplat/servicecontroller为例,假设操作的业务对象UID分别为class0001,class0002,class0003…名称分别为classanole1, classanole2, classanole...业务对象的数据的UID为instance1, instance2, instance3…。

总线操作一般是指对总线的CURRENT节点进行的操作。

## 第一类Restful WebService总线写操作 ##
/eeplat/servicecontroller? callType=initOnly&contextClassUid=class0001contextInstanceUid=instance1

这个URL表示更新总线上的业务对象class0001的值为instance1。
initOnly表示只操作总线不做其它动作。

第一类Restful WebService总线写操作方式只有对总线的写操作，没有读操作。
## 第二类Restful WebService总线写操作 ##

### 新的参数 ###
  * dataBus
> > 对总线的操作类型
  * contextKey
> > 总线上键的名称，一般指的是业务对象的名称，注意contextClassUid是业务对象的uid.
  * contextValue
> > 总线上对应于键的值，和contextInstanceUid概念基本一致。
### 总线操作的分类 ###

  * 总线操作按读写分为：总线读操作，总线写操作

  * 按操作内容的不同分为：
    * 平台级操作：操作的内容是业务对象、业务对象实例。
    * 用户级操作：操作的内容是自定义的JSON格式对象。

### 总线写操作 ###
  * 平台级操作：支持批量写操作，可以同时更新多个业务对象的总线上的值。如：
/eeplat/servicecontroller? callType=initOnly &dataBus=setContext&contextKey=classanole1&contextValue=instance1&contextKey= classanole2&contextValue= instance2
在这里，contextKey是业务对象的主键, contextValue是业务对象实例的主键；如果contextKey和contextValue只写一对，就是对单个对象的的总线操作。

  * 用户级操作：可以在总线上写入自定义的数据结构，并且键也可以不是业务对象的主键。如：
/eeplat/servicecontroller?callType=initOnly&dataBus=setContext&contextCustKey=menumodel&contextCustValue={'value':'500000'}
在这里，contextCustKey可以不是业务对象的主键, contextCustValue必须是json格式的字符串。

### 总线读操作 ###

  * 平台级操作：
> > 获取总线上业务对象对应的实例的UID

/eeplat/servicecontroller?dataBus=getContext&contextKey=classanole1

> 获取总线上业务对象对应的实例的完整信息(JSON格式)
/eeplat/servicecontroller?dataBus=getFullContext&contextKey=classanole1

  * 用户级操作：
/eeplat/servicecontroller?dataBus=getFullContext&contextCustKey=classanole1
/eeplat/servicecontroller?dataBus=getCustContext&contextCustKey=classanole1
这两种写法等效，都是获取用户自定义数据的完整信息(JSON格式)

## JAVA代码操作 ##

### 读操作 ###
```

    DOBO theBO = DOBO.getByName("业务对象名称");//或者去业务对象

    BOInstance bi = theBO.getCorrInstance();
  或
    BOInstance bi = DOGlobals.getInstance().getSessoinContext().get(theBO);
  或
    BOInstance bi = DOGlobals.getInstance().getSessoinContext().get(theBO.getObjUid());

```

### 写操作 ###

```

    DOBO theBO = DOBO.getByName("业务对象名称");//或者去业务对象

    theBO.refreshContext(boinstance);
或
    DOGlobals.getInstance().getSessoinContext().put(业务对象, boinstance)
或
    DOGlobals.getInstance().getSessoinContext().put(业务对象ID, boinstance)


```


# 面板操作 #
假设webmodule名称为：wd，面板的ID为：0000001；面板Name为：pane\_anole
## 根据面板ID获取面板的内容 ##
/eeplat/mvccontroller?paneModelUid=0000001
## 根据面板Name获取面板的内容 ##
/eeplat/pane\_anole.pml




# 服务操作 #
服务操作的数据类型全部是json格式的字符串。假设webmodule名称为：wd：

## 基本URL及传递参数 ##
  * 基本URL为：/eeplat/servicecontroller
  * 传递参数采用标准URL的方式,如
/eeplat/servicecontroller? contextServiceName=test&userName=anole&password=anolesoft

## 如何调用服务 ##
  * 以ID的方式调用(ID为0000001)
/eeplat/servicecontroller? contextServiceUid=0000001
  * 以Name 的方式调用（服务名称为test）：
/eeplat/servicecontroller? contextServiceName=test

## 查询操作类型 ##
  * 查询返回的结果是以数组组织的数据
[{‘id’:’china’,’location’:’beijing’},{},{}]
/eeplat/servicecontroller? contextServiceName=test &callType=sa
  * 查询返回的结果是like map方式的结构的数据
{‘china’:{‘id’:’china’,’location’:’beijing’},{},{}}
/eeplat/servicecontroller? contextServiceName=test &callType=sm
  * 查询返回的结果是全部以数组方式组织的数据，不过是返回业务对象的id和name两个值
/eeplat/servicecontroller? contextServiceName=test &callType=ss
  * 只返回一个值
/eeplat/servicecontroller? contextServiceName=test &callType=so
  * 查询分页
/eeplat/servicecontroller?contextServiceName=test&callType=sa&sc\_page\_no=10&sc\_page\_size=50

上述url表示获取第10页的数据，每页含有50条数据。

## 修改操作类型 ##
  * 只执行当前的服务（不执行对应的规则），返回的结果是一条json记录数据
/eeplat/servicecontroller? contextServiceName=test&callType=us
  * 执行当前的服务包括规则：
/eeplat/servicecontroller? contextServiceName=test callType=uf

返回的结果是一个json 字符串：格式为
{‘returnPath’:’’,   //执行服务后界面上需要连接的路径(可以是多个)
’ targetPane’:’’,  //路径所显示的目标面板 (可以是多个)
> ‘returnValue’:’’}//返回数据总线上ECHO\_STR节点的值



## 调用JAVA Class ##
假设需要调用的类全名为:com.exedosoft.Hello，Hello必须实现com.exedosoft.plat.action.Action接口。
下面介绍有返回值的情况，如果不需要返回记录，下面的三种调用类型任选其一就可以。

### 返回的结果是一条记录 ###
/eeplat/servicecontroller? userDefineClass=com.exedosoft.Hello&callType=as

返回的结果被存放在总线的 GLOBAL\_UID节点中。
所以com.exedosoft.Hello 需要调用
> SessionContext.getInstance().getThreadContext().setInstance(bi);对总线赋值。

### 返回的结果是以数组组织的多条记录 ###
/eeplat/servicecontroller? userDefineClass=com.exedosoft.Hello&callType=aa

返回的结果被存放在总线的 SEARCH\_LIST节点中。
所以com.exedosoft.Hello 需要调用
> SessionContext.getInstance().getThreadContext().setInstances(list);对总线赋值。
### 只返回一个值 ###
/eeplat/servicecontroller? userDefineClass=com.exedosoft.Hello&callType=ao

> SessionContext.getInstance().getThreadContext().setEchoStr(list)

返回的结果是Echo\_str