## 基本概念 ##
> 声明式业务对象简称业务对象，是对现实业务的抽象，是业务划分的单位。
> 数据层面，在具体的实现上它通常对应数据库中的一张表，也可以表示更大的业务范围，例如一个独立的业务模块（销售模块）。数据库的一张表可以拥有多个业务对象，一个业务对象也可以包含多张表。
> 业务对象除了包含数据之外，还包括通过服务，并通过完成业务逻辑。不同业务对象之间的服务可以声明式调用。
> 业务对象还包含所有相关的UI。

## 对应JAVA 数据类型 ##
> com.exedosoft.plat.bo.DOBO

## 配置界面 ##

> ![http://eeplat.googlecode.com/files/bo_edit.png](http://eeplat.googlecode.com/files/bo_edit.png)


> <b> 注意：</b>

> 可以通过业务对象的配置界面，创建业务对象，创建服务，创建面板，创建菜单，创建动态树，关联业务对象，关联工作流。

> 业务对象生成后，名称不要更改；如果更改名称，那么要把相应的缺省实现的服务的名称也要改掉。

> 主键类型包含：UUID,HI/LOW,NAITIVE(数据库原生类型如自增),KEY\_TYPE\_ASSIGN\_STRING(程序手动指定STING类型),KEY\_TYPE\_ASSIGN\_LONG（程序手动指定整形）；现在平台默认支持UUID,其它类型需要对生成的配置数据进行修改。

> 特征值列：可以不用指定，一般指实体的名称，例如学生表里面的姓名。

> 数据源：如果业务对象的类型为基本表，必须指定。

> 可以通过这个界面创建服务、面板、菜单、动态树。

> <b> 关联业务对象 </b>
> 通过外键关联其它业务对象。在下面界面上选择本业务对象的列表，选择需要关联的其它业务对象。
> 点击“生成关联”即可，生成的过程可能持续10几秒时间。

> ![http://eeplat.googlecode.com/files/bo_link.png](http://eeplat.googlecode.com/files/bo_link.png)

> <b> 关联流程 </b>
> 把业务对象关联到某个流程下面，使该流程流转的是该业务对象的数据。
> 点击“生成关联”即可，生成的过程可能持续10几秒时间。

> ![http://eeplat.googlecode.com/files/bo_link_wf.png](http://eeplat.googlecode.com/files/bo_link_wf.png)


> <b> 导出 </b>
> 导出该业务对象的XML格式的交换文件，交换文件包含业务对象的配置数据以及业务对象下面的所有属性、参数、服务、规则等配置数据，业务对象下面UI的所有配置数据。

> <b> 删除 </b>
> 谨慎使用删除操作，删除将会把业务对象下面的所有配置数据全部删除。

> ![http://eeplat.googlecode.com/files/bo_delete.png](http://eeplat.googlecode.com/files/bo_delete.png)