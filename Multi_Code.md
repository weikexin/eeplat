# 开发语言与沙箱 #

> EEPlat的现在支持的开发语言为Javascript(Rhino)和Java，可以选择是否启用Java支持。Javascipt可以在线编写，Java必须采用部署jar包方式。分别提供了两种沙箱机制Rhino沙箱和Java沙箱。 如果启用Java沙箱，Javascipt也必须遵守Java沙箱的限制。Java沙箱提供了和Google App Engine一致的JRE白名单。



# 框架限制 #

  * 所有的代码和界面都须运行在EEPlat Framework下。在EEPlat Framework中，需要使用HTML或者FreeMarker，而不可使用JSP。

  * 对数据库的所有操作都须使用MetaData中的服务。

  * SQL语句执行时间限制，超过限制则该SQL语句相关的服务被禁用。

  * 文件的上传采用标准界面组件，或以标准界面组件为基础的扩展界面组件，文件的获取采用EEPlat API。


# 沙箱限制 #

> 只启用Rhino沙箱，不允许jar包部署， EEPlat Engine可以做到：

> ### 代码运行时间 ###

> 可以定义代码的执行时间，例如只允许代码执行10秒。

> ### 反射 ###

> 可以定义是否允许使用反射。

> ### 类黑名单或白名单 ###

> 可以限定或允许相关的类在Rhino下执行。

> ### 黑名单方法列表 ###

> 可以限制哪些方法不可以被调用。

> 启用Rhino沙箱和Java沙箱，并允许jar包部署， EEPlat Engine还可以做到：

> ### JRE类白名单 ###

> 应用程序仅限于对 Java 标准库（Java 运行时环境 (JRE)）中的白名单中的类拥有访问权限。

# 请求响应限制 #


> ### 请求大小 ###
> > 可以限制请求的大小，例如2M


> ### 表单限制 ###
> > 可以限制提交表单的大小


> ### 响应 ###
> > 响应可以完全在EEPlat Framework下