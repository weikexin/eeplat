### 总体技术架构 ###
云鹤平台(EEPlat)的核心理念是提供模型驱动、在线声明式配置的方式完成应用的开发。为此，EEPlat确立了微内核、元模型、插件体系、总线集成的体系结构。为了概念一致性，EEPlat独立实现了O/R Mapping 框架，HMVC界面框架，流程引擎等。系统为SOA架构，在元模型层面提供了对WebService的支持，即平台中每个服务都是可被调用的Restful WebService。总体技术架构见下图：

> <img width='600' height='400' src='http://eeplat.googlecode.com/files/yiyi_archive.png' />

### UI技术架构 ###
> EEPlat平台在界面控制模式方面，充分考虑到传统的MVC模式的优缺点，最终采取了层叠式MVC模式，即HMVC。

> <img src='http://eeplat.googlecode.com/files/yiyi_ui_hmvc.png' />。

> HMVC模式即Hierarchical-Model-View-Controller模式，也可以叫做Layered MVC。HMVC模式把客户端应用程序分解为有层次的父子关系的MVC。反复应用这个模式，形成结构化的客户端架构。 　它的优点主要有：
    1. 把界面分成了多个部分，降低了依赖性。 
    1. 支持鼓励重用代码，组件或者模块。
    1. 在今后的维护中，提高了可扩展性。




> <img width='600' height='400' src='http://eeplat.googlecode.com/files/yiyi_ui_archive.png' />


### 服务层技术架构 ###
> EEPlat的服务层和UI层是松耦合的。一般情况下，UI层通过服务层提供的Restful WebService进行交互。服务层实现的核心是 [声明式业务对象](BasicTheory.md)。
    1. 声明式业务对象的服务可以通过[业务对象总线](BusinessObjectBus.md) 进行互操作。
    1. 声明式业务对象的粒度可大可小，可以是一个实体表，也可以是一个较大的业务模块。
    1. 服务可以调用多个规则，规则本身可以根据上下文环境判断是否可以执行，从而完成复杂的业务逻辑而无需编码。
    1. 服务可以调用脚本或Java Class完成平台不能配置或不易配置的业务逻辑。

> <img width='600' height='300' src='http://eeplat.googlecode.com/files/yiyi_bo_archive.png' />