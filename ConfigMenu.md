## 基本概念 ##

> 菜单一般是功能的索引。在平台的默认配置里，系统菜单放置到左边，有OutLook风格和Tree风格两种样式。
> 菜单类型并不是从同一个维度划分的，所以在自定义的界面组件实现上要注意，包括：

  1. NAVIGATION  导航菜单
  1. NORMAL      一般菜单
  1. SEPARATOR   分割线
  1. CHECKED  带checkbox的菜单
  1. LINK  点击菜单时，直接弹出页面
  1. DELEGATE  可以使用代理

> 菜单是否过滤：
  1. YES  菜单需要经过权限过滤，经过授权的登录用户才可以看到菜单
  1. NO   菜单不需要经过权限过滤，登录用户都可以看到菜单


> 菜单的展示样式由菜单界面组件控制。


## 对应JAVA 数据类型 ##
> com.exedosoft.plat.bo.ui.DOMenuModel

## 配置 ##


> ### 配置主界面 ###

> 在菜单配置主界面上，我们可以
  1. 修改当前菜单
  1. 复制当前菜单  复制当前菜单，复制后的菜单和当前菜单同级
  1. 新增下级  新增下一级菜单
  1. 删除  删除当前菜单，注意不要删除有下层菜单的菜单

> ![http://eeplat.googlecode.com/files/menu_main.png](http://eeplat.googlecode.com/files/menu_main.png)


> ### 修改菜单界面 ###

> 连接的面板：在点击菜单时，响应的面板。

> 目标面板：在点击菜单时，响应的面板所展示的位置。

> 连接的服务：在点击菜单时，后台执行的服务。

> ![http://eeplat.googlecode.com/files/menu_modify.png](http://eeplat.googlecode.com/files/menu_modify.png)