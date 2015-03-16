# grid.OutGridExcel界面组件 #

> grid.OutGridExcel界面组件是平台缺省提供的，也可以根据grid.OutGridExcel进行扩展以满足自己的需求。grid.OutGridExcel导出的是CSV(逗号分隔符)文件，默认可以用EXCEL或WPS表格打开。


> 创建导出EXCEL表格的面板容器名称为 “PM\_t\_expense\_ExportExcel” ，一般可以使用复制，然后修改名称

> ![http://eeplat.googlecode.com/files/excel_simple_pane.png](http://eeplat.googlecode.com/files/excel_simple_pane.png)

> 创建导出EXCEL的表格，一般可以使用复制，然后修改名称，在这儿可以定义导出的字段，可以定义导出的服务等，注意界面组件器grid.OutGridExcel，另外一个界面组件grid.OutGridExcelSelected可以导出选中的数据

> ![http://eeplat.googlecode.com/files/excel_simple_grid.png](http://eeplat.googlecode.com/files/excel_simple_grid.png)


> 创建按钮，点击它下载导出文件，界面组件为form.DODownLoadExcel，连接的面板为上述配置的 PM\_t\_expense\_ExportExcel

> ![http://eeplat.googlecode.com/files/excel_simple_button.png](http://eeplat.googlecode.com/files/excel_simple_button.png)



# JXLS报表 #
> 平台默认使用的JXLS报表。 JXLS是基于Jakarta POI API的Excel报表生成工具，可以生成精美的Excel格式报表。它采用标签的方式，类似JSP标签，写一个Excel模板，然后生成报表，非常灵活，简单。excel模板例子在\WEB-INF\xls 下面。

> 创建报表模板，这个报表比使用grid.OutGridExcel界面组件导出的报表多了统计功能

> ![http://eeplat.googlecode.com/files/excel_report_template.png](http://eeplat.googlecode.com/files/excel_report_template.png)

> 创建界面组件，对应JAVA Class为 com.exedosoft.plat.ui.jquery.form.DODownLoadExcelReport。这个界面组件是为报表模板准备数据

> ![http://eeplat.googlecode.com/files/excel_report_controller.png](http://eeplat.googlecode.com/files/excel_report_controller.png)


> 创建按钮，连接的界面组件是上述所创建的

> ![http://eeplat.googlecode.com/files/excel_report_button.png](http://eeplat.googlecode.com/files/excel_report_button.png)


