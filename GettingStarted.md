1	安装
下载地址：http://weikexinblog.appspot.com/如下图：


下载完成后，解压为文件yiyi.war，拷贝到tomcat目录下webapps目录下面。拷贝完成后，启动tomcat。（tomcat和java环境请大家自行安装配置）
注意：tomcat安装目录绝对路径建议不要有空格，否则会出错。
> 启动完成后，即可进入系统进行初始化配置。
> 系统地址http://127.0.0.1:8082/yiyi/exedo/webv3/，浏览器建议采用FireFox最新版本，系统开发使用在FireFox上效率最高速度最快。
> 系统登录页面如下所示：

> 后台登录默认用户名密码为：a/1
> 登录后进入系统，如下所示：

> 至此系统开始正式运转。
2	我的第一个工程
首先建立一个工程，点击“所有工程”下“新建”菜单或右边工作区域里面基础设施管理的“新建工程”，进入新建工程页面：


> 填写名称，如firstprj，注意名称必须为英文数字，为小写，不支持中文！
> 填写中文名，如测试工程
> 填写WEB应用名称，如firstprj
> 其他可以暂时不填写，然后点击保存。显示如下界面：

> 点击工程页面的“初始化工程”按钮。

> 双击左边树节点“所有工程”刷新工程，如下：

> 初始化工程完成。
> 点击首页>>基础设施管理>>数据源管理，进入下面的界面：

选择工程使用的权限主业务数据库类型，并进行相应的配置。系统默认建立四个示例数据源数据，可以直接修改或复制。配置自己的业务库，这里我们以SQLServer数据库为例子进行介绍，本地SQLSERVER数据库bs\_test。同时确保在SQLServer中建立test/test用户，确保该用户对bs\_test数据库有使用权，可配置test为数据库管理员，方便测试。
选择sqlserver，点击修改，修改配置信息，如下图：

> 点击保存，进行保存。由于数据库需要自动创建相应的数据库连接池，所以到此需要重新启动tomcat，并重新登陆后台。然后回到数据源管理界面。
> 选择sqlserver配置好的数据源，点击“初始化组织权限”，初始化默认的权限相关的数据对象的数据源，同时创建权限相关表。

> 初始化完成后，记住不可再进行初始化。

> 打开数据库可以看到，系统自动创建了相关表。如下：

> 对于SQLSERVER需要建立存储过程，SQL脚本如下，请拷贝执行：

> 至此，系统初始化工作基本完成。

在此，简单以两个表示例一下业务对象得创建。示例为两个表，一个为项目表，一个为客户表，其中，项目表有客户表的引用。
> 两个表创建SQL（SQL Server专用）如下所示：

执行，在数据库中创建表PM\_ProjectInfor和KH\_CustomInfor。
> 进入配置界面，数据源管理，选择sqlserver数据源，点击“初始化数据表”，进入如下界面：

> 选择主键列（关键字），特征值列（名称列），然后选择业务包（可以创建一个新的），点击“初始化业务表”

> 请耐心等待…. 知道如下界面：

> 可以看到已经初始化完成，如下图：

> 在此需要设置表关联，选择业务对象pm\_projectinfor，点击右侧“处理表关联”，填写相应对应信息，在这里通过pm\_projectinfor表中的custuid字段关联客户，进入如下界面：

> 点击“初始化”，完成表关联处理。
> 然后通过地址http://127.0.0.1:8082/yiyi/exedo/webv3/ClearCache.jsp刷新缓存，或者点击右上角 刷新缓存。
> 工程列表中，选择工程，如下图：

然后点“打开工程登录”或“直接打开工程”，系统已经完成了业务对象初始化，并创建了相应的增删改查界面，如下图：


> 下面可以进行简单测试，先输入两个客户，然后输入项目。可以看出项目相关客户的表格元素自动进行了处理，如下：


3	界面翻译
界面翻译可以通过写翻译文件，导入自动翻译的过程进行。首页>>常用工具>>导入字典翻译，进入界面：

> 如果这里显示不出浏览文件按钮，说明firefox没有安装flash控件，打开新浪首页，如下：

点击右上角按钮  安装缺失插件







> 点击浏览文件，选择翻译文件。翻译文件格式为每行一个字段：field=字段1。本例子中翻译内容如下（注意大小写敏感，系统自动把所有字段名变为小写）：
customid=客户编码
mainsaleruid=负责销售
customname=客户名称
mainlinkmanuid=联系人
telephone=联系人电话
customstate=客户状态
aeracode=大区编码
citycode=城市
address=详细地址
zipcode=邮政编码
customdesc=客户描述
custnotes=备注
projectid=项目编码
projectname=项目名称
custuid=客户
acceptdeptuid=负责部门
projectmanageruid=项目经理
projectstate=项目状态
planbegintime=计划开始
planfinishtime=计划结束
actbegintime=实际开始
actfinishtime=实际结束
projectdesc=项目描述
projectnotes=备注




> 注意：最新版本不需要上传文件，只需要把翻译列表拷贝到界面中，直接翻译即可,如下图所示：

点击确定，完成翻译。然后刷新缓存，查看界面，如下图所示：

> 注意：字段翻译使用了文件上传界面组件，需要设置上传文件存放路径，打开配置文件D:\Tomcat55\webapps\yiyi\WEB-INF\classes\globals.xml，找到配置项

&lt;property name="uploadfiletemp"&gt;

D:\\upload\\

&lt;/property&gt;

 修改路径即可，注意一定要两个\\。

4	附录1  创建表SQL语句
MySQL版本：


CREATE TABLE KH\_CustomInfor
(
> CustomUID varchar (50) ,
> CustomID varchar (50)  ,
> MainSalerUID varchar (50)  ,
> CustomName varchar (200)  ,
> MainLinkManUID varchar (50)  ,
> TelePhone varchar (50)  ,
> CustomState varchar (20)  ,
> AeraCode varchar (50)  ,
> CityCode varchar (50)  ,
> Address varchar (500)  ,
> ZipCode varchar (10)  ,
> CustomDesc text  ,
> CustNotes text
) ;

CREATE TABLE PM\_ProjectInfor
(
> ProjectUID varchar (50) ,
> ProjectID varchar (50)  ,
> ProjectName varchar (100)  ,
> > CustUID varchar (50) ,

> AcceptDeptUID varchar (50)  ,
> ProjectManagerUID varchar (50)  ,
> ProjectState int NULL ,
> PlanBeginTime datetime NULL ,
> PlanFinishTime datetime NULL ,
> ActBeginTime datetime NULL ,
> ActFinishTime datetime NULL ,
> ProjectDesc text  ,
> ProjectNotes text
) ;


# 工作流模板 #

# 工作流模板变量 #



# 工作流模板节点 #



# 工作流模板转移 #



# 工作流实例 #


# 工作流实例变量 #


# 工作流实例节点 #



# 工作流实例转移 #


# 工作流历史 #