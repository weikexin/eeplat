## 英文名称 ##

com.exedosoft.plat.ui.jquery.form.DOResultListPopupNoSplitPage

## 适用场景 ##

下拉列表，但是不标准的HTML表单元素，用层（div）模拟实现，数据不分页。如没有值则默认会显示服务的业务对象在总线上的当前值。

数据来自“连接的服务”。

## 说明 ##

必须配置服务，服务返回的每个BOInstance 的 主健作为下拉项的值，值列作为下拉项的显示。（也可以自定义下拉项的值和显示：该服务定义为简单查询，然后把值和显示对应的字段以“,”分割的填写到服务配置页面的“相关配置”中，如"id,name"这样填写）

更多设置参见[表格元素](ConfigGridItem.md)。

## 运行示例 ##


<img src='http://eeplat.googlecode.com/files/c_resultpop.png' />