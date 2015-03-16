## 英文名称 ##

com.exedosoft.plat.ui.jquery.form.DOValueResultList

## 适用场景 ##

用于显示以form.DOResultList 开头的界面组件录入的值。


## 说明 ##


会把一条记录的主健列翻译为值列。

默认为有超链接。如果表格元素配置有“连接的面板”，则连接“连接的面板”；否则链接 该记录所在业务对象的主面板。

虽然可以快速把关联表的主键列翻译成特征值列。但是这种翻译是牺牲效率为前提的，会造成n+1查询问题（只有启用缓存，并且缓存命中的情况下才不会反复查询数据库）。

如果是快速原型或者是关联表数据量不太大或者启用缓存的情况下的话可以使用DOValueResultList ，其它情况应避免使用。


更多设置参见[表格元素](ConfigGridItem.md)。

## 运行示例 ##


<img src='http://eeplat.googlecode.com/files/c_valueresultlist.png' />