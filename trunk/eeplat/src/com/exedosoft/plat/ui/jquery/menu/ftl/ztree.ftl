<#macro dealMenuNode menu isOpen=false isParent=false>
	<#if menu?? && menu.children?? && (menu.children)?has_content >
		<#list menu.children as child>
			{name:"${(child.name)!"未知"}"<#---->
			<#if child_index==0 && isOpen>, open:true</#if>
			<#if isParent>, isParent:true</#if>
			<#if (child.icon)??>, icon: "${child.icon}"</#if>
			<#if (child.template)??>, template: "${child.template}"</#if>
			<#if (child.desc)??>, dealClilk: "${child.desc}"</#if>
			<#if child.children?? && (child.children)?has_content>, children: [<@dealMenuNode menu=child isOpen=false isParent=false />]</#if>
			}<#if child_has_next>,</#if>
		</#list>
	</#if>
</#macro>
<SCRIPT type="text/javascript">
	<!--
	var curMenu = null, zTree_Menu = null;
	var setting = {
		view: {
			showLine: true,
			selectedMulti: false,
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: false
			}
		},
		callback: {
			onNodeCreated: this.onNodeCreated,
			beforeClick: this.beforeClick,
			onClick: this.onClick
		}
	};

	var zNodes =[
	<@dealMenuNode menu=root isOpen=false isParent=true />
	];

	function beforeClick(treeId, node) {
		if (node.isParent) {
			if (node.level === 0) {
				var pNode = curMenu;
				while (pNode && pNode.level !==0) {
					pNode = pNode.getParentNode();
				}
				if (pNode != null && pNode !== node) {
					var a = $("#" + pNode.tId + "_a");
					a.removeClass("cur");
					zTree_Menu.expandNode(pNode, false);
				} else if(node.open) {//实现再次点击时折叠
					zTree_Menu.expandNode(node, !node.open);
					var a = $("#" + node.tId + "_a");
					a.removeClass("cur");
					//return !node.isParent;
					return $(node).attr("dealClilk")!=undefined;
					
				}
				a = $("#" + node.tId + "_a");
				a.addClass("cur");

				var isOpen = false;
				if(node.children!=undefined)
					for (var i=0,l=node.children.length; i<l; i++) {
						if(node.children[i].open) {
							isOpen = true;
							break;
						}
					}
				if (isOpen || node.children==undefined) {
					zTree_Menu.expandNode(node, true);
					curMenu = node;
				} else {
					zTree_Menu.expandNode(node.children[0].isParent?node.children[0]:node, true);
					curMenu = node.children[0];
				} 
			} else {
				zTree_Menu.expandNode(node);
			}
		}
		//return !node.isParent;
		return $(node).attr("dealClilk")!=undefined;
	}
	function onClick(e, treeId, node) {
		//alert($(node).attr("dealClilk"));
		var click = $(node).attr("dealClilk");
		if(click!=undefined)
			eval(click);
		//alert("Do what you want to do!");
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
		if(zTree_Menu.getNodes()[0].children==undefined){
			return;
		}
		curMenu = zTree_Menu.getNodes()[0].children[0];
		zTree_Menu.selectNode(curMenu);
		var a = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
		a.addClass("cur");
		//eval('alert("hello")');
	});
	//-->
</SCRIPT>
<style type="text/css">
.ztree {margin:0; padding:0px; color:#333}
.ztree li a:hover {text-decoration:none}
.ztree li a.level0 {width:100%;height: 28px; text-align: left; display:block; background-color: #0B61A4; border:0px silver solid;background-image: url("./exedo/webv3/images/main/lan/menu_tit_s.jpg");}
.ztree li a.level0.cur {background-color: #66A3D2; }
.ztree li a.level0 span {display: block; color: #3C74A6; line-height:27px; padding-left:30px; vertical-align:middle; cursor:pointer; font-size:12px; font-weight: bold;word-spacing: 2px;}
.ztree li a.level0 button {	float:right; margin-left: 10px; visibility: visible;display:none;}
.ztree li button.switch.level0 {display:none;}
</style>
<ul id="treeDemo" class="ztree"></ul>