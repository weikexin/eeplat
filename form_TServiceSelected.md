## 英文名称 ##

com.exedosoft.plat.ui.jquery.form.TServiceSelected

## 适用场景 ##

和form.TService基本相同。区别是form.TServiceSelected执行的服务是针对当前选择的记录。如果没有选择的记录，系统会提示并不会执行服务。当服务执行前，把当前选择的数据更新到业务对象总线的CURRENT节点和FORM节点（对应contextValue键）。

## 说明 ##

同form.TService。


## 运行示例 ##

如下图红点位置：

<img src='http://eeplat.googlecode.com/files/c_TServiceSelected.png' />