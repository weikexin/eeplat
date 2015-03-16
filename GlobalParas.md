# GLOBAL.XML #

> globals.xml 位于classes 下。开发环境下修改src下面的既可。 是平台的核心全局配置。配置参数：

```
 	<!--平台中工程的名称-->
        <property name="application">yiyi</property>、

 	<!--web module的名称，当更改web module的名称时，一定要更改这个地方-->
	<property name="webmodule">yiyi</property>
	
 	<!--JDBC事务隔离级别

            int TRANSACTION_NONE	     = 0;

            int TRANSACTION_READ_UNCOMMITTED = 1;

            int TRANSACTION_READ_COMMITTED   = 2;

            int TRANSACTION_REPEATABLE_READ  = 4;

            int TRANSACTION_SERIALIZABLE     = 8;

      -->

        <property name="transaction.isolation">2</property>

    	<!--定义JDBC连接-->
        <property name="connection.driver_class">org.hsqldb.jdbcDriver</property>
	<property name="connection.url">mydb</property> 
        <property name="connection.username">sa</property>
        <property name="connection.password">1111</property>

      
	<!--Servlet 初始化类，当servlet初始化时，可以加载自定义的类需要实现com.exedosoft.plat.Action接口 -->
	<property name="servlet.init.class"></property>

	<!-- sessionparter.class的实现类 -->
	    <property name="sessionparter.class">com.exedosoft.plat.SessionParterDefault</property>

	    <!-- 使用自定义的查询，需要实现com.exedosoft.plat.bo.search.DOISearch接口，一般可以通过继承com.exedosoft.plat.bo.search.SearchImp实现
   	    <property name="plat.search.class">com.exedosoft.plat.search.customize.SearchTransCode</property>
-->

     
	<!-- 通过AOP实现权限过滤的类定义-->
   	    <property name="aop.menumodel.class"></property>
	    <property name="aop.panemodel.class"></property>
	    <property name="aop.formmodel.class"></property>
	    <property name="aop.parameter.check.class"></property>
		
	<!-- Restful WebService 的用户验证服务-->
		
			    <property name="webservice.login.service">do_org_user_findbynameandpwd</property>

	<!-- Restful WebService 的授权验证服务-->
	    <property name="webservice.auth.service">exists_rest</property>
		
	<!-- 所使用的js框架，现在只能支持jquery-->
		<property name="jslib">jquery</property>
		
	<!-- 两种:serial no，是否把序列化的配置类读入内存 -->
		<property name="useSerial">serial</property>

	<!-- 上传文件路径 -->
		<property name="uploadfiletemp">E:\\eclipse\\workspace\\yiyi_dev\\WebContent\\upload\\</property>
```