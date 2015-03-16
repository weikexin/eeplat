## 英文名称 ##

com.exedosoft.plat.ui.jquery.form.TPaneSelectedParasForm

## 适用场景 ##

定义一个按钮，用来打开“连接的面板”。

## 说明 ##

如果定义有“目标面板”，“连接的面板”替代的位置是目标面板，否则替代的位置为按钮所在的面板。

和form.TPane的不同：弹出面板和当前选择的记录相关。如果没有选择的记录，系统会提示并不会弹出面板。当弹出面板前，把当前选择的数据只更新到业务对象总线的FORM节点（对应contextValue键）。

## 运行示例 ##

如下图红点位置：

<img src='http://eeplat.googlecode.com/files/T_PaneSelected.png ' />