## 英文名称 ##

com.exedosoft.plat.ui.jquery.form.TUploadify

## 适用场景 ##

无刷新文件上传。可以选择多个文件。

## 说明 ##

浏览器中需要安装flash插件。

用于上传文件至硬盘路径（在global.xml文件中设置）。

“相关配置”可以设置过滤提示,如 “只能选择图像类文件”，默认是 “All Files”


“相关约束”可以设置上传过滤类型，如 ` *.jpg;*.gif;*.bmp `，默认是**.**

上传由exedo/webv3/js/main/platAjax.js中的 uploadify 函数完成。

如有特殊要求可以参考这个界面组件的实现重写。

更多设置参见[表格元素](ConfigGridItem.md)。

## 运行示例 ##


<img src='http://eeplat.googlecode.com/files/c_uploadify.png' />

## 跨域问题 ##

Tuploadfiy界面组件(用的uploadify插件)，跨域上传文件失败（非IE内核才存在这个问题），如何解决?
原因：
Uploadify本质上是一个基于Flash的jQuery上传插件（跨域上传的情况牵扯到两个安全模型,一是浏览器的同源策略，一是Flash的安全沙箱策略）。所以跨域关键点在于Flash的安全沙箱策略。Flash安全策略简单讲：同一个域的属于同一个沙箱，同一个沙箱的可以互相访问.如果要访问另外一个沙箱的内容就要在另外一个沙箱定义信任,这种信任策略就定义在crossdomain.xml中。
解决方案：
可直接按照下面的第3步设置，如未解决，再安装1至3步设置。

1、首先要将uploadify插件方法中的scriptAccess参数设置为always；

2、将uploadify.swf文件放置在上传页面的服务器上；
（注意：这里文件放置的地址不是指文件存储的目标服务器，而是指和html文件所在的服务器）；

3、将名为crossdomain.xml的XML文件保存在文件存储的机器上（目标机器）的站点根目录（如tomcat，就应该放在webapp的root下，然后访问http:localhost:port/crossdomain.xml，如果成功显示，证明配置成功）。
对于crossdomain.xml还有两点细节：


&lt;1&gt;

这个文件的要放在站点的根目录下而且文件名固定;


&lt;2&gt;

跨域访问端口在1024以下必须要通过策略文件来定义信任关系。换句话说端口大于等于1024隐式开放访问权限。
crossdomain.xml文件的内容如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE cross-domain-policy SYSTEM  
"http://www.macromedia.com/xml/dtds/cross-domain-policy.dtd" >  
<cross-domain-policy>  
<site-control permitted-cross-domain-policies="all" />  
<allow-access-from domain="*" />  


<allow-http-request-headers-from domain="*" headers="*"/>
</cross-domain-policy>

```