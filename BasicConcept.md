## 业务对象相关概念 ##

### 工程 ###
  * 基本概念
> > 一个工程一般对应于一个具体的软件开发项目。在配置平台中可以建立多个工程。平台推荐只建立一个工程。
> > 一个工程完成之后，需要建立新的工程，那么需要把原来的配置文件（WEB-INF\db下面以mydb为开头的四个文件）备份，然后把WEB-INF\db\db.zip 加压覆盖，得到新的空的工程配置库。
> > 工程之间业务对象需要复用，请使用导入、导出工具。

  * 对应JAVA 数据类型
> > com.exedosoft.plat.bo.DOApplication

  * 对应界面


### 业务包 ###

  * 基本概念
> > 业务包是从业务划分的业务对象的集合。

  * 对应JAVA 数据类型
> > com.exedosoft.plat.bo.BusiPackage

  * 对应界面


### 声明式业务对象 ###
  * 基本概念
> > 声明式业务对象简称业务对象，是对现实业务的抽象，是业务划分的单位。
> > 数据层面，在具体的实现上它通常对应数据库中的一张表，也可以表示更大的业务范围，例如一个独立的业务模块（销售模块）。数据库的一张表可以拥有多个业务对象，一个业务对象也可以包含多张表。
> > 业务对象除了包含数据之外，还包括通过服务，并通过完成业务逻辑。不同业务对象之间的服务可以声明式调用。
> > 业务对象还包含UI，下文所列的所有UI都包含在业务对象下面。

  * 对应JAVA 数据类型
> > com.exedosoft.plat.bo.DOBO

  * 对应界面

### 服务 ###

  * 基本概念


> 服务是描述业务对象的行为。这个服务既可以是底层数据层面的操作，也可以是粗粒度的业务行为。当一个数据表被初始化为业务对象后，会形成6个基本服务，命名规则是业务对象 + `_` + 后缀，后缀分别为browse、list、insert、update、delete、auto\_condition。

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
> 在一般情况下，都会写作` select * from demo `因为在需要结果集数量的场合，系统可以把后面的语句补写完整。但是如果是非常复杂的SQL语句，系统可能无法进行补写，需要写完整的两段。
    1. 修改  定义的SQL语句完成的是update 操作
    1. 新增  定义的SQL语句完成的是insert 操作
    1. 保存  是新增或者是修改
    1. 删除  定义的SQL语句完成的是delete 操作
    1. 自动条件查询 定义的SQL语句含有通配符/`*`condition`*`/，运行时可以根据用户的输入自动匹配查询条件；如果需要排序通配符为--orderby-- defaultcols desc@#，一个例子：
```
        select * from demo /*condition*/ --orderby-- defaultcols desc@#
```
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
    1. 自由保存
    1. 脚本  定义一个javascript 脚本，最后一行返回sql。
    1. 自定义动作查询  定义一个自定义动作，自定义动作返回一个结果集。 自定义动作查询属于基本服务。 _注意在这个自定义动作里面不能直接使用该服务的invokeSelect()方法，否则要引起死循环。_

> 可见性,包括公开,保护,私有.和面向对象理论可见性一致.

> public static final int VISIBILITY\_PUBLIC = 1;

> public static final int VISIBILITY\_PROTECTED = 2;

> public static final int VISIBILITY\_PRIVATE = 3;


> 参数检查
> public static final int PARA\_CHECK\_DETAIL = 2;

> public static final int PARA\_CHECK\_SIMPLE = 1;

> public static final int PARA\_CHECK\_FALSE = 0;


> 事务
> public static final int TRANSACTION\_NO = 0;

> public static final int TRANSACTION\_YES = 1;


> 日志
> public static final int LOG\_NO = 0;

> public static final int LOG\_YES = 1;

> 记录过滤

> public static final int FILTER\_NO = 0;

> public static final int FILTER\_YES = 1;

> public static final int FILTER\_MULT = 2;

> 总线刷新类型


> public static final int REFRESH\_NO = 0;// 不刷新数据总线

> public static final int REFESH\_CURRENT = 1;// /刷新数据总线，但是只刷新总线键值对

> public static final int REFESH\_SEARCH\_LIST = 2;// /刷新数据总线，但是总线的数据集

> public static final int REFESH\_ALL = 3;// /都刷新

> public static final int REFESH\_MAP\_DATA\_GLOBAL\_UID = 10;// /刷新数据总线,和全局值




  * 对应JAVA 数据类型
> > com.exedosoft.plat.bo.DOService

  * 对应界面

### 参数 ###

  * 基本概念
> > 参数的作用主要是从业务对象总线上获取对应的值。服务可以不定义参数，也可以定义一个或多个参数。服务中sql语句中的？的数量要和参数的数量相同，并且按顺序对应（带通配符的服务，如自动条件查询，执行中，平台会自动根据参数补全sql）。这样服务就通过参数获取总线上的值。

> /
    * 当前数据，来自于sessioncontext.allInstances 在service执行序列中，后一个service
    * 可能需要前一个service 的值，这些值来自sessioncontext.allInstances
    * 
    * 
> public static final int TYPE\_CURRENT = 1;

> /
    * 批量参数类型 一种特殊的CURRENT 它来自界面批量数据
    * ///////////其实寻找界面列表对应的业务对象即可，不需要配置那么多参数,batchType 是多于的配置，为了兼容性，保留
    * 
    * 
> // public static final int TYPE\_CURRENT\_BATCH = 111;

> /
    * 一般用于权限设置，当当期需要设置的cur instance ,只有这个业务对象设为isFilter == true ，这个业务对象
    * 才有可能的主键才有可能成为TYPE\_AUTH\_GLOBAL\_CUR
    * 
> public static final int TYPE\_GLOBAL\_CUR = 2;

> /
    * 登陆类型，和登陆用户相关，user,user所在的dept等。
    * 
    * 
> public static final int TYPE\_LOGIN\_ID = 3;

> public static final int TYPE\_LOGIN\_NAME = 31;

> public static final int TYPE\_LOGIN\_MAIN\_STATION = 32;

> public static final int TYPE\_LOGIN\_MAIN\_DEPT = 33;

> public static final int TYPE\_LOGIN\_KEY = 35;

> /
    * 当前时间
    * 
> public static final int TYPE\_DATE = 4;

> /
    * 当前时间
    * 
> public static final int TYPE\_TIMESTAMP = 40;

> /
    * 来自Form
    * 
> public static final int TYPE\_FORM = 5;

> /
    * 来自Form值的一些运算
    * 
> public static final int TYPE\_FORM\_CAL = 51;

> /
    * 参数为NULL 值，当进行值参替换时，替换为null.
    * 
> public static final int TYPE\_NULL = 10;

> /
    * 递归情况下 父亲的值
    * 
> public static final int TYPE\_RECURSION\_PARENT = 11;

> /
    * 主键
    * 
> public static final int TYPE\_KEY = 15;

> /
    * 使用代码生成器
    * 
> public static final int TYPE\_CODE = 151;

> /
    * SCRIPT 支持
    * 
> public static final int TYPE\_SCRIPT = 100;

> /
    * 可以调用Action
    * 
> public static final int TYPE\_ACTION = 101;

> /
    * 不可逆的MD5 加密
    * 

> public static final int TYPE\_SECURITY\_MD5 = 200;

> /
    * 可逆的加密算法
    * 

> public static final int TYPE\_SECURITY\_REVERS = 201;

> /
    * 可逆的加密算法
    * 

> /
    * 全文检索——查询
    * 
> public static final int TYPE\_LUCENE\_SEARCH = 300;

> /
    * 全文检索——创建索引
    * 
> public static final int TYPE\_LUCENE\_CREATE = 301;




  * 对应JAVA 数据类型
> > com.exedosoft.plat.bo.DOParameter

  * 对应界面

### 规则 ###

### BOInstance ###


## UI相关概念 ##

### 界面组件 ###

### 面板 ###

### 表格 ###

### 表格元素 ###

### 菜单 ###

### 树 ###


## 工作流相关概念 ##

### 工作流模板 ###

### 工作流模板变量 ###

### 工作流模板节点 ###

### 工作流模板转移 ###

### 工作流实例 ###

### 工作流实例变量 ###

### 工作流实例节点 ###

### 工作流实例转移 ###

### 工作流历史 ###


## 组织权限相关概念 ##

### PARTER ###

### SESSIONPARTER.CLASS ###

### 权限表 ###

### 角色 ###

### 用户 ###

### 组织 ###