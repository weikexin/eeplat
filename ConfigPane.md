## 基本概念 ##

> UI布局可以通过分块把整体界面分别分配到不同的容器中，同时通过容器的嵌套排列完成各种界面布局。容器内部放各种内容，如表格、功能树、资源、菜单等。“容器”在平台中称为面板。 当一个数据表被初始化为业务对象后，会形成7个基本面板，命名规则是PM + 业务对象名称 + `_` + 后缀，后缀分别为Condition、Main、Result、browse、insert、list、update。

> 面板必须定义界面组件， 面板的显示由面板界面组件来控制。在各类界面组件中，pane.ContentPane是最常用的面板界面组件。如果面板放的内容可能超出边界，需要滚动条，可以使用pane.ContentPaneScroll界面组件。pane.Tab界面组件可以把面板下面关联的子面板以tab页的形式显示。pane.Toolbar界面组件则通过工具条按钮的形式来链接下面关联的各个子面板，pane.Toolbar.Schroll支持滚动条。


> 面板可以定义高度和宽度，但是只有在面板被弹出的情况下起作用。

> 面板可以通过“连接内容的控制”修改其连接内容的界面组件。

> 面板的内容是表格的情况下，面板可以定义服务来代替其表格的服务，这样可以达到重用表格的目的。

> 面板的目标面板是指当面板被显示的时候显示在什么位置。

> 几个特殊的目标面板：

  1. opener   弹出面板（层）
  1. opener\_tab   以TAB方式显示 和“首页”并列放置
  1. opener\_window  弹出window窗口


> 面板可以通过“连接的业务对象”改变其隶属的业务对象，放到其它业务对象下面。

> TODO：面板的所有引用，并没有实现。所有引用是指连接到该面板的所有平台元素，如业务对象、服务、菜单、面板、表格元素等。



## 对应JAVA 数据类型 ##
> com.exedosoft.plat.ui.DOPaneModel

## 配置 ##

> ### 配置主界面 ###

> 在面板配置主界面上，我们可以
  1. 修改当前面板
  1. 创建新面板，默认在当前面板的业务对象下面
  1. 维护子面板列表
  1. 删除当前面板，不推荐删除面板，因为面板在平台的UI体系中处于核心位置，其它UI元素对面板多有引用。
  1. 简单复制：复制产生一个新的面板，只复制面板本身，不复制面板下面的表格及表格元素。
  1. 深度复制：复制产生一个新的面板，不仅复制面板本身，并且复制面板下面的表格及表格元素。
  1. 导出：导出XML格式的交换数据
  1. 导出HTML：面板导出为html页面，可以作为个性化的模板所用。
> 如下图：

> ![http://eeplat.googlecode.com/files/pane_main.png](http://eeplat.googlecode.com/files/pane_main.png)

> ### 维护子面板列表 ###

> 面板一旦定义了子面板，那么该面板即变为一个容器，本身连接的表格或树或菜单都不会被显示，只显示子面板的内容。可以新增、修改、删除子面板，如下图：

> ![http://eeplat.googlecode.com/files/pane_links.png](http://eeplat.googlecode.com/files/pane_links.png)

> 新增子面板可以位于同一个业务对象下面，也可以位于不同的业务对象下面，排序标示是指子面板的位置，条件类型和条件是指子面板可以根据一定的条件显示，可以实现权限控制或逻辑控制，条件类型为1,javascript 2,java  可以通过脚本或者实现com.exedosoft.plat.DOAccess接口，判断其是否可以显示，如下图：

> ![http://eeplat.googlecode.com/files/pane_links_add.png](http://eeplat.googlecode.com/files/pane_links_add.png)