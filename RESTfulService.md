# 平台的服务可以通过restful web service 的方式供外部访问。 #

RESTful Web Service 是一种轻量级的Web Service 的实现方式，简单而且高效。

1,"调用方"访问平台的服务，必须经过授权，需要平台提供的用户名和密码，"调用方"需要小心保护用户名密码，最好经常更换密码。

2，如果一个服务需要被外部访问那么必须设置服务的可见性，可见性为public 或
public类型的服务:只要"调用方"取得平台提供的用户名密码即可访问。
protected类型的服务："调用方"除了取得平台提供的用户名密码即可访问，还要单独授权。

3，对"调用方"的验证方式是可以自定义的：
在globals.xml中：

`    <property name="webservice.login.service">do_org_user_findbynameandpwd</property> `

这个是验证"调用方"的用户名和密码，可以和普通用户登录的服务一样，这样"调用方"的用户名密码也存放在用户表中，从安全性角度讲，最好分开。

`    <property name="webservice.auth.service">exists_rest</property> `

这个验证proteced的服务是否获得单独授权。

4，restclient是用java 客户端访问平台服务的例子，是一个eclipse 工程，详见RestExample 类。<a href='http://eeplat.googlecode.com/files/restclient.zip'>restclient下载</a>
