## 基本概念 ##

> 参数检查是指当服务执行操作前先检查服务对应的参数是否合法，如果不合法则不执行。

> 参数检查定义在参数上面，可以被多个服务所复用。

> 服务必须指定参数检查类型，如果不指定则不作检查。指定服务检查类型

  1. 不成功即返回：如果服务的参数检查如果包含多个，只要出现一个不成功，服务就不被执行，返回错误原因
  1. 检查全部参数：如果服务的参数检查如果包含多个，检查完全部的参数，只要有一个不成功，服务就不被执行，返回全部的错误原因


> ![http://eeplat.googlecode.com/files/do_service_check_filter.png](http://eeplat.googlecode.com/files/do_service_check_filter.png)

## 对应JAVA 数据类型 ##
> com.exedosoft.plat.bo.DOParameterCheck

## 配置 ##

> 参数检查配置在参数上面，通过业务对象的“参数” TAB页，然后选中某个参数，进行参数检查配置，如下图：

> ![http://eeplat.googlecode.com/files/do_service_para_check.png](http://eeplat.googlecode.com/files/do_service_para_check.png)

> ### 类型检查 ###

> 参数的值必须符合满足某种类型的要求，这些类型包括：

  1. STRING  参数的值必须为STRING类型
  1. LONG    参数的值必须为长整型
  1. DOUBLE  参数的值必须为浮点型
  1. EMAIL   参数的值必须符合EMAIL格式
  1. DATE    参数的值必须为平台缺省支持的DATE类型，需满足正则表达式(\\d{4}-\\d{1,2}-\\d{1,2})|(\\d{4}[.]{1}\\d{1,2}[.]{1}\\d{1,2})|(\\d{4}[/]{1}\\d{1,2}[/]{1}\\d{1,2})
  1. PATTERM  参数的值需满足指定的正则表达式
  1. SCRIPT  参数需要通过脚本检查
  1. JAVA    参数需要通过扩展类检查

> ### 约束检查 ###

  1. CHECK\_CONTRAINT\_NOTNULL  不能为空
  1. CHECK\_CONTRAINT\_D\_DIC  来自动态字段，动态字典为一个服务，即参数的值必须在服务列表中
  1. CHECK\_CONTRAINT\_S\_DIC  来自静态字典，例如：a,b,c,d  即参数的值必须是 a b c d中的一个，分割符为半角的逗号
  1. CHECK\_CONTRAINT\_ONLY 不能重复
  1. 参数值小数点精度约束
  1. 参数值长度约束


> ### 比较检查 ###

  1. 检查操作符 可以是>,<,>=,<=,!=等
  1. 比较标准值  与一个固定值比较
  1. 比较参数   与该参数的值比较
  1. 比较服务   与该服务的返回值比较

> ### 检查后续处理 ###

  1. 返回错误信息，被定义在错误信息里面
  1. 启动指定服务


### 配置页面 ###

> ![http://eeplat.googlecode.com/files/do_service_para_check_1.png](http://eeplat.googlecode.com/files/do_service_para_check_1.png)

> ![http://eeplat.googlecode.com/files/do_service_para_check_2.png](http://eeplat.googlecode.com/files/do_service_para_check_2.png)



　

