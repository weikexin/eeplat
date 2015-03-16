## 英文名称 ##

com.exedosoft.plat.ui.jquery.form.TServiceUf

## 适用场景 ##

继承于form.TService。区别是执行“连接的服务”时：

  * 如果没有定义自定义动作、规则，则执行服务本身的SQL语句。
  * 如果没有定义自定义动作，则执行服务本身的SQL语句及规则。
  * 如果定义自定义动作，则执行自定义动作及规则，不执行本身的SQL语句。

form.TServiceUf比form.TService的用途更广泛，只有特定环境需要使用form.TService时才使用，否则使用form.TServiceUf。

## 说明 ##

同form.TService。


## 运行示例 ##

如下图红点位置：

<img src='http://eeplat.googlecode.com/files/c_TService.png ' />