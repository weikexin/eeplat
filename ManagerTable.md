## 基本概念 ##

> 平台提供了数据表结构维护的功能，包括：

  1. 创建新表  创建新表需要选中数据源。
  1. 修改表结构  通过业务对象，才可以修改对应表的结构，修改后配置数据会自动更新。

> 无论是创建新表还是修改表结构，选择类型时需要确切的类型，如oracle 时需要使用varchar2，mysqlserver、db2、mydb 使用 varchar。


> <b> 推荐使用相应的数据库管理工具进行表结构的维护。</b>



## 配置界面 ##

> ### 创建新表 ###

> 创建新表配置入口，首页==>基础设施管理==>数据源管理 ,点击弹出数据源管理TAB页面，在列表中选中数据源，点击在该数据源下创建新表，如下图：

> ![http://eeplat.googlecode.com/files/datasource_createtable.png](http://eeplat.googlecode.com/files/datasource_createtable.png)

> ### 修改表结构 ###

> 修改表结构入口， 选中业务对象，选择右侧的“表结构管理”，如下图：

> ![http://eeplat.googlecode.com/files/datasource_moditable.png](http://eeplat.googlecode.com/files/datasource_moditable.png)