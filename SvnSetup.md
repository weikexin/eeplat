# 安装要求 #
  1. JDK 1.6或以上版本。
  1. Tomcat 5.5或以上版本,Tomcat安装路径不要有空格
  1. Eclipse 3.5或以上版本。
  1. 安装有SVN插件（如http://subclipse.tigris.org/update_1.6.x）
  1. 建议安装FireFox。系统开发使用在FireFox上效率最高、速度最快。


# 安装详细步骤： #

## 在Eclipse中配置Tomcat ##
如果你已经配置完成，请略过此步。下面是配置截图：
> ![http://eeplat.googlecode.com/files/newserver.png](http://eeplat.googlecode.com/files/newserver.png)

> ![http://eeplat.googlecode.com/files/newserver2.png](http://eeplat.googlecode.com/files/newserver2.png)

> ![http://eeplat.googlecode.com/files/newserver3.png](http://eeplat.googlecode.com/files/newserver3.png)

点击Finish 完成Tomcat在Eclipse中的配置。


## 利用SVN创建工程 ##
请从[SOURCE](http://code.google.com/p/eeplat/source/checkout)处查看CheckOut地址，如果你想成为该项目的代码贡献者，请联系作者。
下面是配置截图：

> ![http://eeplat.googlecode.com/files/svncheck.png](http://eeplat.googlecode.com/files/svncheck.png)

> ![http://eeplat.googlecode.com/files/svncheck2.png](http://eeplat.googlecode.com/files/svncheck2.png)

> ![http://eeplat.googlecode.com/files/svncheck3.png](http://eeplat.googlecode.com/files/svncheck3.png)

> ![http://eeplat.googlecode.com/files/svncheck4.png](http://eeplat.googlecode.com/files/svncheck4.png)

> ![http://eeplat.googlecode.com/files/svncheck5.png](http://eeplat.googlecode.com/files/svncheck5.png)


## 配置Build Path ##
整个CheckOut过程根据网络环境的不同可能需要几分钟到几十分钟不等。
CheckOut完成之后，还需要配置Build Path，如下：

> ### 配置Tomcat Server Library ###
> ![http://eeplat.googlecode.com/files/svncheck6.png](http://eeplat.googlecode.com/files/svncheck6.png)

> ### 配置JRE System Library ###
> ![http://eeplat.googlecode.com/files/svncheck7.png](http://eeplat.googlecode.com/files/svncheck7.png)

> ### 配置EEPlat工程所依赖的类库，所在路径为 WebContent\WEB-INF\lib ###
> ![http://eeplat.googlecode.com/files/svncheck8.png](http://eeplat.googlecode.com/files/svncheck8.png)

> ### 注意工程的编译水平应该是Java 1.5 ###
> ![http://eeplat.googlecode.com/files/appconfig5.png](http://eeplat.googlecode.com/files/appconfig5.png)

## 发布工程 ##

> 把EEPlat发布于Tomcat，如下图：

> ![http://eeplat.googlecode.com/files/appconfig.png](http://eeplat.googlecode.com/files/appconfig.png)

> ![http://eeplat.googlecode.com/files/appconfig2.png](http://eeplat.googlecode.com/files/appconfig2.png)

> 配置完后，如下图(如果要修改web module的名称，请查看[FAQ](FAQ.md))：

> ![http://eeplat.googlecode.com/files/appconfig3.png](http://eeplat.googlecode.com/files/appconfig3.png)


## 测试 ##
> ### 启动tomcat ###
> > 如下图：
> > ![http://eeplat.googlecode.com/files/appconfig4.png](http://eeplat.googlecode.com/files/appconfig4.png)

> 启动完成后，即可进入配置开发界面，地址为http://127.0.0.1:8080/eeplat/exedo/webv3/，需要根据TOMCAT安装端口的不同调整端口，登录页面如下所示：
> > ![http://eeplat.googlecode.com/files/yiy_manager_login.png](http://eeplat.googlecode.com/files/yiy_manager_login.png)

> ### 系统登录 ###
> > 系统配置开发登录默认用户名/密码为：a/1，登录后进入系统，显示如下界面则表示系统安装成功：
> > > ![http://eeplat.googlecode.com/files/yiyi_admin_index.png](http://eeplat.googlecode.com/files/yiyi_admin_index.png)