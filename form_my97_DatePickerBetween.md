## 英文名称 ##

com.exedosoft.plat.ui.jquery.form.my97.DatePickerBetween

## 适用场景 ##

录入一个时间段。

## 说明 ##

和form.my97.DatePicker相同。

由于使用复杂，一般会使用form.my97.DatePickerBetweenSuite代替它。

该界面组件会直接显示两个文本录入框，每个录入框是一个my97日期控件，两个录入框之间由“至”相连。

第一个文本录入框的名称即为表格元素的名称。

第二个文本录入框的名称为表格元素的名称 + “2“。

如表格元素的名称为schoolname，那么第一个文本框的名称为schoolname，第二个文本框的名称为schoolname2。当用户提交表单时，表单数据会更新业务对象总线的FORM节点，为了获取FORM节点key=schoolname2的值，需要新建一个参数schoolname2。

这个界面组件一般用于查询某时间段的数据。


## 运行示例 ##


<img src='http://eeplat.googlecode.com/files/	t_timebetween.png' />