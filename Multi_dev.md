EEPlat支持[浏览器端JavaScript](JavaScriptAPI.md)、 [服务器端JavaScript(Rhino)](JavaScriptAPIBack.md)、HTML(HTML5)、CSS在线编写。

**通过在线开发，你可以定制化界面、定制化后台逻辑、集成第三方应用等等（[MetaData](Multi_MetaDriver.md)的配置可能不能完全满足要求）。**

EEPlat提供导入导出功能，在线开发的代码可以很好的进行分享和重用。


EEPlat的代码编辑采用的组件为CodeMirror和ACE，它们的特点有：

  * Autocompletion
  * Mode overlays
  * Search/replace
  * Code folding
  * Auto-resizing editor
  * Setting breakpoints
  * Highlighting the current line
  * Highlighting selection matches
  * Theming
  * Stand-alone highlighting
  * Mode auto-changing
  * Visible tabs
  * Autoformatting of code
  * Emacs keybindings
  * Vim keybindings
  * Auto indentation and outdent
  * Work with huge documents (100,000 lines and more are no problem)



当浏览器客户端为IE时，用的代码编辑组件全部为CodeMirror。其它浏览器主要编辑场景用的ACE，嵌入式（如自定义动作、校验规则、界面组件编辑界面）场景用的CodeMirror。推荐使用Firefox浏览器。


EEPlat 扩展了ACE的Autocompletion，常用API可以自动完成，使用Ctrl(Command) + . , 唤起下拉提示。


# 服务器端JavaScript(Rhino) #

> <img src='http://eeplat.googlecode.com/files/rhino_js.png' />

# 浏览器端JavaScript #

> <img src='http://eeplat.googlecode.com/files/js.png' />

# HTML(HTML5) #

> <img src='http://eeplat.googlecode.com/files/html.png' />

# CSS #

> <img src='http://eeplat.googlecode.com/files/css.png' />