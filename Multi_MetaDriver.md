## 元数据驱动及隔离 ##

> 所谓元数据（metadata）即描述数据的数据(data about data)，在EEPlat中，对于描述业务系统(包括业务数据、逻辑和UI)的元数据我们称之为模型（model）或声明式业务对象（Declarative Domain Object），下面图文中元数据、模型、声明式业务对象的概念可以互相替换，如下图：
> <img src='http://eeplat.googlecode.com/files/eeplat_meta_data.png' />

> 对于多租户的应用，不同租户的需求几乎都是有差异的，每个租户要求定制化他们的应用也是很自然的。如果这个多租户应用是静态编译的二进制文件，那么满足这些多租户的要求及其他个性化的挑战是几乎不可能的。然而，一个多租户的应用，必须在其功能、界面等方面，满足不同租户的合理要求。

> 基于这些原因，EEPlat PaaS应用平台可以根据不同租户定义的元数据生成相对应的应用程序，而不是采用经过编译的二进制的可执行文件。模型驱动开发是EEPlat的核心和基础，在模型的基础上，EEPlat又进行了进一步的抽象，称之为元模型（metamodel）,这样又进一步提高了系统的灵活性和可扩展性。

> EEPlat执行引擎、基础功能元数据（通用模型）、每个租户的元数据（租户相关模型），每个租户的业务数据之间有一个明确的分离。这些明显的边界使我们可以安全得定制或修改租户的应用程序而不会影响其它租户。

> 在下图中，我们把模型分为了通用模型和租户相关模型，在EEPlat的具体实现上，通用模型和租户相关业务几乎没有任何关系，只是为了完成模型本身的管理，和保持元模型的版本一致性。

> EEPlat PaaS应用平台可以分为三大部分：数据存储、元模型体系、执行引擎，下图是整体架构图：
> <img src='http://eeplat.googlecode.com/files/eeplat_pass_struture.png' />

> ### 数据存储 ###

> 数据存储我们暂时只支持关系型数据库，计划支持mongodb。在关系数据库的存储实现上，我们采用和Salesforce Appforce 类似的Sparce Column技术，主要通过一个通用表来存放所有自定义信息，里面有租户字段和很多统一的数据栏位（比如500个）。像这种统一的数据栏位会使用非常灵活得存储各种类型的数据。由于在每一行中的数据栏位都会以一个Key一个Value形式存放所有自定义数据，导致通用表的行都会很宽，而且会出现很多空值，所以称之为为"Sparse Column"。好处是极高的整合度并避免了DDL操作，但是在处理数据方面难度加大。

> <img src='http://eeplat.googlecode.com/files/space_clumn.png' />

> Appforce 通过多种策略对租户数据进行优化。我们也做到了对租户数据的查询及存储优化，但是采用了和Appforce不同的策略，经过优化后，当租户查询和存储时，租户针对的并不是全部的数据而仅仅是租户本身的数据。

> EEPlat对模型存储采用了缓存技术，当第一次请求后模型被加载到Cache Server，所以当页面被请求后，以后请求的响应速度会快很多。

> ### 元模型体系 ###

> EEPlat拥有世界领先的元模型体系。元模型是声明式业务对象的模型，声明式业务对象由元模型描述。EEPlat元模型体系是对企业信息化、电子政务等信息化领域业务的高度抽象，拥有自描述和动态扩展特性，能准确得完成业务领域模型的描述。  元模型从承担职责的角度分为功能元模型、业务对象元模型、服务元模型、规则元模型、工作流元模型、组织元模型、UI元模型等。

> 业务对象元模型主要描述业务功能的静态结构，服务元模型主要完成业务逻辑，同时负责业务对象元模型之间的交互；工作流元模型主要完成业务流程及业务对象元模型的协作；组织元模型通过组织元模型之间的职责关系可以实现灵活的组织结构，UI元模型是菜单、面板、表格、表格元素、功能树等的UI模型的元模型，可以实现复杂的界面表现，如下图：

> <img src='http://eeplat.googlecode.com/files/EEPlat_metamodel_en.png' />


> ### 执行引擎 ###

> EEPlat执行引擎采用微内核和插件架构。微内核负责模型之间的关系以及消息传递。插件包含存储、查询、UI、逻辑、流程等，插件是可自定义的，也是可替换的。EEPlat的UI引擎同时支持PC Browse和智能手机界面显示。
> <img src='http://eeplat.googlecode.com/files/EEPlat_runtime_engine2.png' />

> 在现阶段，EEPlat执行引擎只提供Java版本，但是在技术上我们还可以支持Microsoft .NET、Python、Ruby等其它多种开发平台上的执行引擎，可以达到一套模型驱动同不同技术平台多套系统的效果。


## 基本部署架构 ##

> 无状态的系统设计是大规模运营的关键，即不依赖于本机的会话（session）以及本地文件系统等。EEPlat主要采用了session 集中管理、配置数据库集中管理等手段，和很多大规模运营的实践类似，如下图：

> <img src='http://eeplat.googlecode.com/files/eeplat_deploy_struture.png' />


## EEPlat和Appforce的对比 ##
> 同类也是最成功的PaaS产品为Salesforce的Force.com(Appforce)，下面是两者的对比列表，对比项包含Appforce的四项核心技术：多租户、元数据驱动、Web Service API、AppExchange。

> <img src='http://eeplat.googlecode.com/files/eeplat_appforce.png' />

