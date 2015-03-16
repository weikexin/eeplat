## 英文名称 ##

com.exedosoft.plat.ui.jquery.form.DOValueResultListService

## 适用场景 ##

表格元素对应的值不是业务对象的主健；但是希望通过该值定位到一条记录。 这时候就需要根据表格元素配置的服务把该值作为参数进行查询。

如果查询到结果，取第一条记录（BOInstance），如果该BOInstance.getName() 不为空，则显示BOInstance.getName()；其他情况直接显示表格元素对应的值。


## 说明 ##

显示的值一般有对“连接的面板”的超链接。

## 运行示例 ##


<img src='http://eeplat.googlecode.com/files/c_valueresultlist.png' />