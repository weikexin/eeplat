
Toolbar = function (config) {
	//承载容器
  this.renderTo = config.renderTo;
  //边框显示在哪里
  this.border = config.border || "bottom";
	//子组件
  this.items = config.items || [];
	//过滤组件
  this.filters = config.filters || [];
	//初始化激活哪个按钮
  this.active = config.active;
	//是否有AZ组件
  this.azable = config.azable;
	//AZ隐藏变量
  this.azparam = $("#" + config.azparam);
	//承载容器
  this.renderContent = typeof this.renderTo == "string" ? $("#" + this.renderTo) : this.renderTo;
	//AZ集合
  this.azs = ["ALL", "0~9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
};
Toolbar.prototype = {init:function () {
		
  //工具按钮层
  this.toolbar = $(document.createElement("DIV"));
  this.toolbar.addClass("toolbar");
  if (this.border != "none") {
  //  this.toolbar.css("border-" + this.border, "solid 1px #8DB2E3");
  // this.toolbar.css("border-top", "solid 1px #8DB2E3");
  
    this.toolbar.css("border", "solid 1px #8DB2E3");
  }
  this.toolbar.appendTo(this.renderContent);
  this.toolbar_table = $(document.createElement("TABLE"));
  this.toolbar_table.appendTo(this.toolbar);
  this.toolbar_table_row = $(document.createElement("TR"));
  this.toolbar_table_row.appendTo(this.toolbar_table);
    
  //距离左边有空
  var paddingTd = $(document.createElement("TD"));
  paddingTd.css("width", 2);
  paddingTd.appendTo(this.toolbar_table_row);
		
  //循环子组件
  for (var i = 0; i < this.items.length; i++) {
    this.add(this.items[i]);
  }
		
  //循环过滤组件
  for (var i = 0; i < this.filters.length; i++) {
    this.addFilter(this.filters[i]);
  }
}, render:function () {
  this.init();
}, genAZ:function () {
  //如果有az控件
  if (this.azable) {
    this.createAZFix();
    this.createAZButton();
  }
}, add:function (t) {
  var toolbarEntity = this;
  if (t == "-") {
    var it = $(document.createElement("TD"));
    it.appendTo(this.toolbar_table_row);
    var spacer = $(document.createElement("SPAN"));
    spacer.addClass("spacer");
    spacer.appendTo(it);
  } else {
    if (t.type == "button" || t.type == "az") {
      var it = $(document.createElement("TD"));
      it.appendTo(this.toolbar_table_row);
      var itemTable = $(document.createElement("TABLE"));
      itemTable.attr("cellPadding", 0);
      itemTable.attr("cellSpacing", 0);
      itemTable.appendTo(it);
      var itemTable_tr = $(document.createElement("TR"));
      itemTable_tr.appendTo(itemTable);
      var b_left = $(document.createElement("TD"));
      b_left.addClass("b_left");
      var b_center = $(document.createElement("TD"));
      b_center.addClass("b_center");
      var button = $(document.createElement("BUTTON"));
      button.text(t.text);
      if (t.bodyStyle) {
        button.addClass(t.bodyStyle);
      }
      button.appendTo(b_center);
      if (t.title) {
        button.attr("title", t.title);
      }
      //判断是否包含中文,有的话添加IE补丁
      if (/^[u4E00-u9FA5]/.test(t.text) == false && $.browser.msie) {
        if ($.browser.version == "6.0") {
          button.css("padding-top", "2px");
        } else {
          button.css("padding-top", "4px");
        }
      }
      var b_right = $(document.createElement("TD"));
      b_right.addClass("b_right");
      itemTable_tr.append(b_left);
      itemTable_tr.append(b_center);
      itemTable_tr.append(b_right);
      if (t.type == "button") {
        //是否有权限
        if (t.useable != "F") {
          if (t.handler) {
            itemTable_tr.bind("click", t.handler);
          }
          itemTable_tr.bind("mouseover", function () {
            var b = $(this);
            b.addClass("over");
            b.bind("mouseout", function () {
              b.removeClass("over");
              b.removeClass("down");
            });
          });
          itemTable_tr.bind("mousedown", function () {
            var b = $(this);
            b.addClass("down");
            b.bind("mouseup", function () {
              b.removeClass("down");
              b.unbind("mouseup");
            });
          });
        } else {
          itemTable_tr.children("TD").each(function () {
            $(this).css("cursor", "default");
          });
          button.attr("disabled", true);
        }
      } else {
        itemTable_tr.bind("mouseover", function () {
          var b = $(this);
          b.addClass("over");
          b.bind("mouseout", function () {
            b.removeClass("over");
            b.removeClass("down");
          });
        });
        itemTable_tr.bind("mousedown", function () {
          var b = $(this);
          b.addClass("down");
          b.bind("mouseup", function () {
            b.removeClass("down");
            b.unbind("mouseup");
          });
        });
        itemTable.attr("title", "\u5feb\u901f\u8fc7\u6ee4\u7b2c\u4e00\u4e2a\u5b57\u6bb5");
        itemTable.attr("id", "toolbar_az");
        itemTable_tr.bind("click", function () {
          toolbarEntity.showAZ(toolbarEntity);
        });
      }
    } else {
      if (t.type == "textfield") {
        var it = $(document.createElement("TD"));
        it.appendTo(this.toolbar_table_row);
        var cop = $(document.createElement("INPUT"));
        cop.attr("id", t.id);
        cop.attr("name", t.id);
        cop.attr("type", "text");
        cop.addClass("textfield");
        cop.appendTo(it);
        if (t.bodyStyle) {
          cop.addClass(t.bodyStyle);
        }
        if (t.handler) {
          cop.bind("click", t.handler);
        }
      } else {
        var it = $(document.createElement("TD"));
        it.appendTo(this.toolbar_table_row);
        var itemTable = $(document.createElement("TABLE"));
        itemTable.attr("cellPadding", 0);
        itemTable.attr("cellSpacing", 0);
        itemTable.appendTo(it);
        var itemTable_tr = $(document.createElement("TR"));
        itemTable_tr.appendTo(itemTable);
        var itemTable_tr_td = $(document.createElement("TD"));
        itemTable_tr_td.appendTo(itemTable_tr);
        if (typeof t.html == "string") {
          itemTable_tr_td.html(t.html);
        } else {
          itemTable_tr_td.append(t.html);
        }
      }
    }
  }
}, addFilter:function (filter) {
  var toolbarEntity = this;

		//判断是否存在过滤组件
  if ($("#filterTable").length == 0) {
    var filterTable = $(document.createElement("TABLE"));
    filterTable.attr("cellPadding", 0);
    filterTable.attr("cellSpacing", 0);
    filterTable.attr("id", "filterTable");
    filterTable.addClass("filterTable");
    filterTable.appendTo(this.toolbar);
    var filterTr = $(document.createElement("TR"));
    filterTr.addClass("over");
    filterTr.appendTo(filterTable);
    this.filterLeft = $(document.createElement("TD"));
    this.filterLeft.addClass("b_left");
    this.filterRight = $(document.createElement("TD"));
    this.filterRight.addClass("b_right");
    filterTr.append(this.filterLeft);
    filterTr.append(this.filterRight);
  }
		
		//查找所有过滤组件按钮
  var t_centers = $("#filterTable").find(".b_center");
		//如果有过滤按钮,先添加一个分隔符
  if (t_centers.length > 0) {
    var sp = $(document.createElement("TD"));
    this.filterRight.before(sp);
    var spacer = $(document.createElement("SPAN"));
    spacer.addClass("filter-spacer");
    spacer.appendTo(sp);
  }
		
		//创建按钮TD
  var buttonTd = $(document.createElement("TD"));
  buttonTd.attr("id", filter.id);
  buttonTd.addClass("b_center");
		//创建按钮
  var button = $(document.createElement("BUTTON"));
  button.text(filter.text);
  if (filter.title) {
    button.attr("title", filter.title);
  }
  if (filter.bodyStyle) {
    button.addClass(filter.bodyStyle);
  }
    
		//为按钮绑定点击事件
  button.bind("click", function () {
			//激活BUTTON
    toolbarEntity.activeFilter($(this).parent().attr("id"));
  });
  if (filter.handler) {
    button.bind("click", filter.handler);
  }
		
		//将按钮放到TD中
  button.appendTo(buttonTd);
		
		//将TD插入到右边框前面
  this.filterRight.before(buttonTd);
		
		//激活BUTTON
  if (filter.id == this.active) {
    this.activeFilter(filter.id);
  }
		
		//更新边框
  this.updateFilterBorder();
}, activeFilter:function (tdId) {
  if (tdId && $("#" + tdId).length == 1) {
    $("#filterTable").find(".border-center-active").each(function () {
      $(this).removeClass("border-center-active");
    });
    $("#" + tdId).addClass("border-center-active");
    this.updateFilterBorder();
  }
}, updateFilterBorder:function () {
		//改变左右边框的样式表
  if (this.filterLeft.next().hasClass("border-center-active")) {
    this.filterLeft.addClass("border-left-active");
  } else {
    this.filterLeft.removeClass("border-left-active");
  }
  if (this.filterRight.prev().hasClass("border-center-active")) {
    this.filterRight.addClass("border-right-active");
  } else {
    this.filterRight.removeClass("border-right-active");
  }
}, createAZFix:function () {
  var az_trigger = $("#toolbar_az");
  this.az_fix = $(document.createElement("TABLE"));
		//原本是左右边框各4像素，但是按钮有2px的左右margin，因此只-2
  this.az_fix.css("left", (az_trigger.offset().left - 2));
		//-2的原因是：1为边框，1为边框上面的1像素白色
  if (this.border == "bottom") {
    this.az_fix.addClass("az-fix");
    this.az_fix.css("top", (az_trigger.offset().top + az_trigger.get(0).offsetHeight - 2));
  } else {
    this.az_fix.addClass("az-fix-2");
    this.az_fix.css("top", (az_trigger.offset().top - 3));
  }
  this.az_fix.attr("cellSpacing", 0);
  this.az_fix.attr("cellPadding", 0);
  this.az_fix.appendTo($(document.body));
  var az_fix_tr = $(document.createElement("TR"));
  az_fix_tr.appendTo(this.az_fix);
  var az_fix_tr_left = $(document.createElement("TD"));
  az_fix_tr_left.addClass("left");
  var az_fix_tr_center = $(document.createElement("TD"));
  az_fix_tr_center.css("width", (az_trigger.get(0).offsetWidth - 4));
  az_fix_tr_center.addClass("center");
  var az_fix_tr_right = $(document.createElement("TD"));
  az_fix_tr_right.addClass("right");
  az_fix_tr_left.appendTo(az_fix_tr);
  az_fix_tr_center.appendTo(az_fix_tr);
  az_fix_tr_right.appendTo(az_fix_tr);
}, createAZButton:function () {
  var toolbarEntity = this;
    //创建az层
  this.azbar = $(document.createElement("DIV"));
  this.azbar.addClass("az");
    
    //设置border
  this.azbar.css("border-" + this.border, "solid 1px #8DB2E3");
    //判断是位移方向
  if (this.border == "bottom") {
    this.azbar.css("top", this.toolbar.offset().top + this.toolbar.height() + 1);
  } else {
    this.azbar.css("top", (this.toolbar.offset().top - this.toolbar.height() - 2));
  }
    
    //添加到toolbar中
  this.azbar.appendTo($(document.body));
    
    //创建aztable
  var aztable = $(document.createElement("TABLE"));
  aztable.appendTo(this.azbar);
  aztable.attr("cellSpacing", 0);
  aztable.attr("cellPadding", 0);
    
    //创建tr
  var aztable_tr = $(document.createElement("TR"));
  aztable_tr.appendTo(aztable);
    
    //循环添加搜索的条件
  for (var i = 0; i < this.azs.length; i++) {
    var aztable_tr_td = $(document.createElement("TD"));
    if (this.azs[i] == "ALL") {
      aztable_tr_td.attr("title", "\u6d4f\u89c8\u5168\u90e8\u8bb0\u5f55");
    } else {
      if (this.azs[i] == "0~9") {
        aztable_tr_td.attr("title", "\u4ee5\u6570\u5b570~9\u4e3a\u9996\u5b57\u7b26\u6d4f\u89c8\u8bb0\u5f55");
      } else {
        aztable_tr_td.attr("title", "\u4ee5\u5b57\u6bcd/\u62fc\u97f3\u4e3a\u9996\u5b57\u7b26\u6d4f\u89c8\u8bb0\u5f55");
      }
    }
    aztable_tr_td.text(this.azs[i]);
      
      //如果是根据这个字符查询的,将其标红
    if (this.azs[i] == this.azparam.val()) {
      aztable_tr_td.addClass("tdovered");
    }
    aztable_tr_td.bind("click", function (i) {
      return function () {
        toolbarEntity.azparam.val(toolbarEntity.azs[i]);
        var pForm = toolbarEntity.azparam.parents("form");
        window.location = pForm.attr("action").split("?")[0] + "?azparam=" + toolbarEntity.azs[i];
      };
    }(i));
    if (!aztable_tr_td.hasClass("tdovered")) {
      aztable_tr_td.bind("mouseover", function () {
        $(this).addClass("tdover");
      });
      aztable_tr_td.bind("mouseout", function () {
        $(this).removeClass("tdover");
      });
    }
    aztable_tr_td.appendTo(aztable_tr);
  }
}, showAZ:function (toolbarEntity) {
  if (toolbarEntity.azbar.css("display") == "none") {
    $("#toolbar_az").addClass("over");
    toolbarEntity.az_fix.fadeIn(100, function () {
      toolbarEntity.azbar.fadeIn(100);
    });
  } else {
    $("#toolbar_az").removeClass("over");
    toolbarEntity.azbar.fadeOut(100, function () {
      toolbarEntity.az_fix.fadeOut(100);
    });
  }
}};