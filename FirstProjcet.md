# 最新版本请参考： http://cn.eeplat.com/eeplatshare/web/site/firstprj.htm #

## 第一个工程业务说明 ##

> 我们的第一个工程选择做一个非常简单的项目管理分为两个模块，项目信息管理和人员信息管理，一个项目必须指定一个项目负责人，项目负责人的信息存储在人员信息表中。下面介绍中用到的数据源以MySql为例。

## 建立工程 ##
> 首先建立一个工程，点击“所有工程”下“新建”菜单或右边工作区域里面基础设施管理的“新建工程”，进入新建工程页面(最新版本少了一些字段)：

> <img width='600' height='240' src='http://eeplat.googlecode.com/files/yiyi_addproject_new.png' />

> 填写名称，如firstprj，注意名称必须为英文数字，为小写，不支持中文！
> 填写中文名，如我的第一个工程
> 填写WEB应用名称，如firstprj 如下界面：

> <img width='600' height='240' src='http://eeplat.googlecode.com/files/yiyi_addproject_enter.png' />

> 其他可以暂时不填写，然后点击保存。



## 修改数据源 ##

> <b>注意：在提供的EC2示例中，可以使用的数据源方言为h2的数据源，不需要修改；在提供的GAE示例中，可以使用的数据源方言为gae的数据源，不需要修改。</b>

> 点击首页>>基础设施管理>>数据源管理，进入下面的界面：
> <img width='600' height='200' src='http://eeplat.googlecode.com/files/yiyi_db_list.png' />

> 系统默认建立了6个示例数据源，可以直接修改或复制。

> 选择工程使用的数据库类型，并进行相应的配置。

> 在这里我们以MySql数据库为例子进行介绍，本地MySql数据库名称为test。

> 选择MySql，点击修改，修改配置信息，如下图：
> <img width='600' height='400' src='http://eeplat.googlecode.com/files/yiyi_db_modify.png' />

> 修改相应的信息，如MySql数据库的地址、用户名、密码等，进行保存。

> 由于TOMCAT启动时会初始化数据库连接池，所以修改后需要重新启动tomcat，并重新登陆系统。


## 初始化组织权限 ##

> 选择MySql配置好的数据源(点击选中)，然后点击“初始化组织权限”。如下：

> <img width='600' height='222' src='http://eeplat.googlecode.com/files/yiyi_db_init.png' />

> 初始化默认的权限相关的数据对象的数据源，同时创建权限相关表。

> 初始化完成后，不可再进行初始化。

> 打开数据库可以看到，系统自动创建了相关表。如下：

> <img width='508' height='410' src='http://eeplat.googlecode.com/files/yiyi_db_table_list.png' />

> 至此，系统初始化工作基本完成。

## 初始化自己的业务表 ##

> <b>注意：在提供的GAE或EC2示例中，请利用平台提供的简易的表结构管理工具 <a href='ManagerTable.md'>在线创建和维护表</a></b>。

> 利用数据库的管理工具，根据[附录示例表的SQL](AboutSQL.md)中的SQL语句来创建项目表（PM\_ProjectInfor）和人员表（PM\_CustomInfor）。

> 上述表创建后，进入配置界面，数据源管理，选择MySql数据源，点击“初始化数据表”，进入如下界面：

> <img width='600' height='200' src='http://eeplat.googlecode.com/files/yiyi_db_table_init.png' />

> 选择主键列（关键字），特征值列（名称列），然后选择业务包（可以创建一个新的），点击“初始化业务表”

> 请**耐心等待…初始化的过程根据所选择表的数量可能要持续几十秒至几分钟不等**. 直到如下界面：
> <img width='600' height='200' src='http://eeplat.googlecode.com/files/yiyi_db_table_init_finish.png' />

> 双击左边的树节点即可刷新该节点下面的数据，可以看到已经初始化完成；初始化成功的一个简单特点是每个业务对象下面包含6个服务和7个面板。如下图：
> <img width='600' height='500' src='http://eeplat.googlecode.com/files/yiyi_db_table_to_bo.png' />

## 初始化表间关联 ##

> 在此需要设置表间关联，选择业务对象pm\_projectinfor，点击右侧“处理表关联”，填写相应对应信息，在这里通过pm\_projectinfor表中的custuid字段关联项目负责人，进入如下界面：

> <img width='600' height='120' src='http://eeplat.googlecode.com/files/yiyi_bo_link.png' />

> 点击“初始化”，完成表关联处理。

## 界面翻译 ##

> 进入配置界面，首页-->常用工具-->导入字典翻译，把[附本地化代码](Translating.md)中的代码拷贝到文本域，如下图：
> > <img width='500' height='500' src='http://eeplat.googlecode.com/files/yiyi_translate.png' />


> 点击确定按钮，完成翻译工作。


## 系统测试 ##

> 点击界面右上方的“清空缓存”，刷新缓存。

> ### 注意，每次更改配置，如果需要系统测试，都需要刷新缓存。 ###

> 下面可以进行简单测试，点击“我的第一个工程”，在右侧界面上点击“直接打开工程”或者“打开工程登录”，如下图：
> <img width='600' height='200' src='http://eeplat.googlecode.com/files/yiyi_bs_open.png' />

> 打开工程登录默认的用户名/密码为:u/1111，对应的数据表为do\_org\_user。

> 打开的界面为：

> <img width='600' height='200' src='http://eeplat.googlecode.com/files/yiyi_bs_simple.png' />

> 现在我们可以对项目信息和人员信息进行简单的管理，并且可以为项目指定负责人了，至此第一个简单项目开发完成。_我们看到菜单还没有翻译，菜单的内容请查看下面的“菜单配置”_










