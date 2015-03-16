# 配置数据库与业务数据库 #

> EEPlat的配置数据存于配置数据库中，配置数据库不同于业务数据库，业务数据库用来存储你的业务数据，配置数据库存储你的所有配置数据，如界面、逻辑、工作流模型等。


> 无论是配置数据库还是业务数据库都没有限制数据库的类型。但是配置数据库只在hsqldb、h2、mysql三种数据库上做过严格测试，并成功运行过多个商业项目。（在早期版本中配置数据库也在sqlserver上支持过多个项目，所以sqlserver理论上也是支持的，业务数据库在Oracle、DB2、Mysql、SqlServer都成功支持过多个商业项目）。


# 更改配置数据库 #

> 默认的配置数据库是H2文件方式，当然也可以更改为Server方式。下面介绍一下如何切换成hsqldb和mysql。

## 更改为hsqldb ##

> 更改为hsqldb较为简单，直接把classes(源代码方式为src)下面的 globals.xml默认的数据源修改为：

```
	<property name="connection.driver_class">org.hsqldb.jdbcDriver</property>
	<property name="connection.url">mydb</property>
        <property name="connection.username">sa</property>
        <property name="connection.password">1111</property>
```

## 更改为mysql ##

  * 在mysql中新建一个数据库名称例如为:conf\_init ,运行下载的脚本，地址为：http://eeplat.googlecode.com/files/conf_init.sql

  * 直接把classes(源代码方式为src)下面的 globals.xml默认的数据源修改为如下（数据库名称、用户名/密码修改为自己的）：

```
        <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
	<property name="connection.url">jdbc:mysql://localhost:3306/conf_init?useUnicode=true&amp;characterEncoding=utf-8</property>  
        <property name="connection.username">root</property>
        <property name="connection.password"></property>
```