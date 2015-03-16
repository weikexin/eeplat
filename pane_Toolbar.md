## 英文名称 ##

com.exedosoft.plat.ui.jquery.pane.Toolbar

## 适用场景 ##

该界面组件适用于工具条情况，整个页面没有滚动条。

## 说明 ##

工具条中每个按钮的图标样式定义在 /js/jquery-plugin/toolbar/core.css中，有new、edit、save、delete、search、role-setup、copy、linked等多种，还可以自行扩展。

工具条有两种方式：

  1. 该界面组件pane\_Toolbar，定义面板的时候连接的内容是空，界面组件选pane\_Toolbar ，并且要 定义子面板，子面板的标题为工具条每个button显示的名称。这种方式的工具条只能连接面板，不能连接服务。
  1. 如果工具条位于表格的上部，那么工具条就可以采用定义表格元素来实现。这种实现方式即可以连接面板，也可以连接服务。


## 运行示例 ##


<img src='http://eeplat.googlecode.com/files/p_toolbar.png' />