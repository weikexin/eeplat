/*

Author: bob
Feature: Second-level menus
Update: 2008/12/20
Tutorial URL: http://www.bbon.cn/
*/

/** 类 */
var Class = {
	create: function() {
		return function() {
			this.initialize.apply(this, arguments);
		}
	}
}

/** 菜单列表 */
var MenuList = Class.create();
MenuList.prototype = {

	/**
	 * 构造方法
	 * id: 菜单列表
	 * opacity: 透明度 (0.0 - 1.0, 0.0 为全透明, 1.0 为不透明)
	 */
	initialize: function(id, opacity) {
		// 获取菜单列表
		this.obj = document.getElementById(id);
		if (!this.obj) { return; }

		// 对菜单列表内的所有菜单进行处理
		var menus = this.obj.childNodes;
		for (var i = 0; i < menus.length; i++) {
			var menu = menus[i];
			if (menu.tagName == 'LI') {
				// 构建菜单
				new Menu(menu, opacity);
			}
		}
	}
}

/** 菜单 */
var Menu = Class.create();
Menu.prototype = {

	/**
	 * 构造方法
	 * target: 目标菜单
	 * opacity: 透明度 (0.0 - 1.0, 0.0 为全透明, 1.0 为不透明)
	 */
	initialize: function(target, opacity) {
		this.util = new MenuUtil();

		// 获取目标菜单 (没多余元素)
		this.obj = this.util.cleanWhitespace(target);
		// 定义透明度, 默认为不透明
		this.opacity = opacity || 1;

		// 获取菜单
		this.menu = this.obj.childNodes

		// 重要! 如果菜单不包含菜单项, 则不进行处理
		if (this.menu.length < 2) { return; }

		// 菜单标题和菜单体
		this.title = this.menu[0];
		this.body = this.menu[1];

		// 定义初始样式
		this.util.setStyle(this.body, 'visibility', 'hidden');
		this.util.setStyle(this.body, 'position', 'absolute');
		this.util.setStyle(this.body, 'overflow', 'hidden');
		this.util.setStyle(this.body, 'display', 'block');

		// 添加监听器
		this.addListener(this.obj, 'mouseover', this.util.bind(this, this.activate), false);
		this.addListener(this.obj, 'mouseout', this.util.bind(this, this.deactivate), false);
	},

	/**
	 * 激活方法
	 * 当鼠标移动到菜单标题是激活
	 */
	activate: function() {
		// 获取当前菜单体的位置
		var pos = this.util.cumulativeOffset(this.title);
		var left = pos[0];
		var top = pos[1] + this.util.getHeight(this.title);

		// 定义激活时样式
		this.util.setStyle(this.body, 'left', left-10 + 'px');
		this.util.setStyle(this.body, 'top', top + 'px');
		this.util.setStyle(this.body, 'visibility', 'visible');
		this.util.setStyle(this.body, 'opacity', this.opacity);
		this.util.setStyle(this.body, 'filter', 'alpha(opacity=' + this.opacity * 100 + ')');
	},

	/**
	 * 解除方法
	 * 当鼠标移动出菜单标题是激活
	 */
	deactivate: function(){
		// 定义解除时样式
		this.util.setStyle(this.body, 'visibility', 'hidden');
	},

	/**
	 * 监听方法
	 * element: 监听对象
	 * name: 监听方法
	 * observer: 执行的方法
	 * useCapture: 浏览器调用事件的方式 (true 为 Capture 方式, false 为 Bubbling 方式)
	 */
	addListener: function(element, name, observer, useCapture) {
		if(element.addEventListener) {
			element.addEventListener(name, observer, useCapture);
		} else if(element.attachEvent) {
			element.attachEvent('on' + name, observer);
		}
	}
}

/** 一些实用的方法 */
var MenuUtil = Class.create();
MenuUtil.prototype = {
	initialize: function() {
	},

	$: function(id) {
		return document.getElementById(id);
	},

	$A: function(iterable) {
		if(!iterable) {
			return [];
		}
		if(iterable.toArray) {
			return iterable.toArray();
		} else {
			var results = [];
			for(var i = 0; i < iterable.length; i++) {
				results.push(iterable[i]);
			}
			return results;
		}
	},

	bind: function() {
		var array = this.$A(arguments);
		var func = array[array.length - 1];
		var _method = func, args = array, object = args.shift();
		return function() {
			return _method.apply(object, args.concat(array));
		}
	},

	getHeight: function(element) {
		return element.offsetHeight;
	},

	setStyle: function(element, key, value) {
		element.style[key] = value;
	},

	getStyle: function(element, key) {
		return element.style[key];
	},

	cleanWhitespace: function(list) {
		var node = list.firstChild;
		while (node) {
			var nextNode = node.nextSibling;
			if(node.nodeType == 3 && !/\S/.test(node.nodeValue)) {
				list.removeChild(node);
			}
			node = nextNode;
		}
		return list;
	},

	cumulativeOffset: function(element) {
		var valueT = 0, valueL = 0;
		do {
			valueT += element.offsetTop  || 0;
			valueL += element.offsetLeft || 0;
			element = element.offsetParent;
		} while (element);
		return [valueL, valueT];
	}
}

/** 添加到页面加载事件 */
window.onload = function(e) {
	new MenuList('menus', 0.9);
}