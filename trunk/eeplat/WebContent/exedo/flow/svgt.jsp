<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.bo.DOBO"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>
<%
  		DOBO bo = DOBO.getDOBOByName("do_pt_processtemplate");
		BOInstance curPt = bo.getCorrInstance();
  
        String ptName = "test";
        if(curPt!=null){
			ptName = curPt.getName();
		}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
     "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>易元工作流建模工具</title>
<style type="text/css">
@import "jquery.svg.css";

#svgbasics { width: 825px; height: 1095px; border: 1px solid #484;  }
</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="jquery.svg.js"></script>
<script type="text/javascript" src="jquery.svgdom.js"></script>

<script type="text/javascript" src="../../exedo/webv3/js/main/main.js"></script>
<script type="text/javascript" src="../../exedo/webv3/js/main/platAjax.js"  ></script>

<script type="text/javascript">

    if($.browser.msie){

    	document.write("Workflow Tools not Supports IE! Please Try FireFox.");
    } 
	
 	var selectedMother = null;//选中的模板
	var selectedNode = null;//选中的节点
	var selectedNodeBak = null;//选中的节点
	var selectedLine = null;//选中的线
  var selectedLineTxt = null;//选中的txt
	var canDrawLine = false;//是否可以画线了
	var iIndex = 0;//全局元素个数
	var startNode = null;
	var globalX = 825;
	var globalY = 1095;
	var svgDoc = null;
	var tmplName = "<%=ptName%>";
	
//<link rel="stylesheet" href="../webv3/css/main/main_lan.css" type="text/css" />
//var topLeftPoint = function(){
//	
//	  this.x = 0;
//	  this.y = 0;	
//	  
//	  this.inFect = function(evtx,evty){
//	  	if ( (evtx > this.x && evtx < (this.x + 40))
//	  	  && (evty > this.y && evty < (this.y + 40))
//	  	  ){
//	  	  	return true;	  	    
//	  	  }
//	  	  return false;
//	  }
//}
//
//				var tl = new  topLeftPoint();
//				tl.x = x;
//				tl.y = y;
//				
//				$('body').data("flow"+iIndex, tl);


var FlowNode = function(){
 this.id = "";
 this.nodeName = "";	
 this.nodeType = "";
 
	this.nodeStateShow='';
	this.nodeStateShowBack='';
	this.accessClass='';
	this.passTxt='';
	this.rejectTxt='';
	this.autoService='';
	this.paneName='';
	this.decisionExpression='';
	this.decisionType='';
	this.nodeDesc='';
	this.authType='';
	this.specName='';
				
 
 
 this.x = 0;
 this.y = 0;
	
}

var newPoint = new function(){
  this.x = 0;
  this.y = 0;
  
  this.getPoint = function(event){

  	  	  this.x = event.pageX;
					this.y = event.pageY;
					if( $.browser.msie ){
						this.x = this.x ;	
						this.y = this.y ;	
					 }else{
					 	this.x = this.x - 10;	
					 	this.y = this.y - 75;	
					}
					return this;
  }
}
	
	

	
$(function() {
		
		
	$('#svgbasics').svg( {
    loadURL: 'flowtemplate.svg',
     onLoad: function(){
     	     	  
     	   var svg = $('#svgbasics').svg('get');
     	   svgDoc = svg.root().ownerDocument;
/////////////////////////////////////////////////////////////////////////////////////
//////////////////////node mother event/////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////
				 $("image[type|=mother]",svg.root()).bind('click',
					  function(event){
					  			selectedMother = this;///选中模板
									unSelectNode();///节点设为没有选中状态

					  			/////下面是加选中边框
							  	try{
							  	 	 var t = event.target;// can replace "this" pointer
							  	 	 if( $(t).attr('type').indexOf('mother')!=0 ){////only for ie
							  	 	 		return;
							  	 	 	}
							  	 	 	if( $(t).attr('type') =='mother'){//如果点击其他模板，让其不可以划线
								  	 	 	canDrawLine = false;
							  	 	  }
										 $('#toolrange',svg.root()).attr('x',$(t).attr('x')-1)
										 .attr('y',$(t).attr('y')-1)
										 .attr('visibility','visible');
									}catch(e){
									}
					  	}
					).bind('mouseout',function(){
								// $('#toolrange',svg.root()).attr('visibility','hidden')
						}
					).bind('mousedown',function(){
								// selectedMother = this;
								// selectedMother.setAttributeNS(null, 'pointer-events', 'none');
						}
					);
////////////////////////////////////////////////////////////////////////////////////
//////////////////////////workbench event//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////					
					
				 $("#workflow",svg.root()).bind("mouseup",function(evt){
						
							try{
								if(isWorkSpace(evt)){

									///处理选中模板，创建新节点
								   doMouseUpMother(evt,svg); 
								   
								   ///处理选中节点  让节点停止移动
								   ///非划线模式
								   if(!canDrawLine){
								 		 unSelectNode();
								   }
								 
		   				 	  
							  }
							}catch(e){
							}
						}
					).bind("mousemove",function(evt){
								doMouseMove(evt,svg);
					});
					
					////开始划线
					$("#drawLine",svg.root()).bind("click",function(evt){
							beginDrawLine();											
					});
					
					
					////删除按钮
					$("#delete",svg.root()).bind("click",function(evt){
							deleteObject();											
					});
					
					//保存按钮
					
					$("#save",svg.root()).bind("click",function(evt){
							save();											
					});
					
					////加载xml
					$("#property",svg.root()).bind("click",function(evt){
						  window.showModalDialog('<%=request.getContextPath()%>/pane_wf_propertydetails.pml?isApp=true" +  "',window,'scroll:0;status:0;resizable:1;dialogWidth:680px;dialogHeight:520px');
										
					});
					///加载该模板的流程图
					loadWfXml();
					
					
     	}
    }
    );
});



function doMouseUpMother(evt,svg){
	
		 if ( selectedMother )
		 {

			if ($(selectedMother).attr('type')=='mother')//如果是节点模板
			{
					if (isWorkSpace(evt))//如果up在工作区
					{
						var np = newPoint.getPoint(evt);
										
						var nodeType = $(selectedMother).attr('nodetype');
						
						var aNode = new  FlowNode();
						aNode.nodeType = nodeType;
						aNode.x = np.x;
						aNode.y = np.y;
					  addNewNode(aNode);
					}
					  ///取消边框 
	    	  $('#toolrange',svg.root()).attr('visibility','hidden');
	  	      selectedMother = null;
	    	  
			}
			else if ($(selectedMother).attr('type')=='mother-flow')//如果是流元素
			{
			}
			
			////把选择的模板置为null,重新选择
	 
	    
		 }	
}

function mLineTxt(befLine,aLineId,newX,newY,beyond){


		var xm = parseInt((parseInt(befLine.getAttribute('x1')) + newX)/2);  
  	var ym = parseInt((parseInt(befLine.getAttribute('y1')) + newY + 20)/2);
  	
  	if(beyond){
  		 xm = parseInt((parseInt(befLine.getAttribute('x2')) + newX)/2);  
	     ym = parseInt((parseInt(befLine.getAttribute('y2')) + newY + 20)/2);
    }
 	
		var theLineTxt = svgDoc.getElementById("txt_" + aLineId);
					
	
						
		if(theLineTxt){
			if(xm >0 && ym>0){
				theLineTxt.setAttribute("x", xm);
				theLineTxt.setAttribute("y", ym);
			}
		}

}


function doMouseMove(evt,svg){

	var o = evt.target;
	window.status= evt.pageX + "," + evt.pageY +"==" + selectedNode;
	if(selectedNode  && !canDrawLine){
		////当鼠标move的时候才不能相应任何事件
			    
			    selectedNode.setAttributeNS(null, 'pointer-events', 'none');
				  var np = newPoint.getPoint(evt);

					$(selectedNode).attr('x',np.x);
					$(selectedNode).attr('y',np.y);


					
					
				  $('#toolrange',svg.root()).attr('x',$(selectedNode).attr('x')-1)
							 .attr('y',$(selectedNode).attr('y')-1)
			       .attr('visibility','visible');

					
					
					////改变移动节点txt的x y坐标	
				  var selectedId = $(selectedNode).attr("id");

    			   $("#txt_" + selectedId,svg.root()).attr("x",parseInt(np.x)+15).attr("y",parseInt(np.y)+50);
     			
					var sBefore = $(selectedNode).attr('before');
					
					

					var newX = np.x;
					var newY = np.y;
					

					if (sBefore!=null && sBefore!='none' && sBefore!='')//如果有before
					{
						if(sBefore.indexOf(",")==-1){
							var befLine = svg.root().ownerDocument.getElementById(sBefore+selectedId);
							befLine.setAttribute('x2',newX);
							befLine.setAttribute('y2',newY+20);
							changeLinePosition(befLine);
							
							var aLineId =  sBefore + selectedId ;
							mLineTxt(befLine,aLineId,newX,newY);
						}else{
							var arrayBefors = sBefore.split(",");
							for(var i = 0; i < arrayBefors.length; i++){
								var aBefor = arrayBefors[i];
								var befLine = svg.root().ownerDocument.getElementById(aBefor+selectedId);
								if(befLine){
									befLine.setAttribute('x2',newX);
									befLine.setAttribute('y2',newY+20);
									changeLinePosition(befLine);
	                				var aLineId =  aBefor + selectedId;
									mLineTxt(befLine,aLineId,newX,newY);
								}
							}
						
						}
					}
					var sBeyond = $(selectedNode).attr('beyond');
					if (sBeyond!=null && sBeyond!='none' && sBeyond!='')//如果有beyond
					{
						if(sBeyond.indexOf(",")==-1){
							var beyLine = svg.root().ownerDocument.getElementById(selectedId+sBeyond);
							beyLine.setAttribute('x1',newX+40);
							beyLine.setAttribute('y1',newY+20);
							changeLinePosition(beyLine);
							var aLineId =  selectedId + sBeyond;
							mLineTxt(beyLine,aLineId,newX,newY,true);
							
						}else{
							var arrayBeyonds = sBeyond.split(",");
							for(var i = 0; i < arrayBeyonds.length; i++){
								var aBeyond = arrayBeyonds[i];
								var beyLine = svg.root().ownerDocument.getElementById(selectedId+aBeyond);
								if(beyLine){
									beyLine.setAttribute('x1',newX+40);
									beyLine.setAttribute('y1',newY+20);
									changeLinePosition(beyLine);
									var aLineId =  selectedId + aBeyond;
								  	mLineTxt(beyLine,aLineId,newX,newY,true);
								}
							}
						}
				}	
		}
}


function changeLinePosition(line){

	var svg = $('#svgbasics').svg('get');
	var object1 = svg.root().ownerDocument.getElementById(line.getAttribute("start"));
	var object2 = svg.root().ownerDocument.getElementById(line.getAttribute("end"));

 	var x1 = parseInt($(object1).attr('x'))+40;
	var y1 = parseInt($(object1).attr('y'))+20;
	
	var x2 = parseInt($(object2).attr('x'));
	var y2 = parseInt($(object2).attr('y'))+20;

	///两个点位置感应
	if(x2 < x1){
	    if(y2 < (y1-35*2)){  ///左上方
			x2 = x2 + 35;
			y2 = y2 + 18;
			x1 = x1 - 18;
			y1 = y1 - 18;
	    }else if(y2 > (y1 + 35*2)){                ////左下方
		    x1 = x1 - 18;
		    y1 = y1 + 18;

		    x2 = x2 + 22;
			y2 = y2 - 18;
		}else {
            x1 = x1 - 35;
            x2 = x2 + 35;
		}
    }else {
	    if(y2 < (y1-35)){      /////右上方
			x1 = x1 - 18;
			y1 = y1 - 18;
			y2 = y2 + 18;
	    }
     }

	line.setAttribute("x1", x1);
	line.setAttribute("y1", y1);
	line.setAttribute("x2", x2);
	line.setAttribute("y2", y2);
	
}



function doClick(evt){


	var o = evt.target;

	if(evt.detail==2){
	  var vid = $(o).attr('id');
		  
	  window.showModalDialog('<%=request.getContextPath()%>/pane_wf_propertydetails.pml?isApp=true&vid=" + vid +"',window,'scroll:0;status:0;resizable:1;dialogWidth:680px;dialogHeight:520px');
	 //  popupDialog("aaa","属性编辑器",'propertyDetails.html?vid=' + vid );
		//browserEval(openUrl);
		
		return;
	}
	
  lineUnvisible();
  var svg = $('#svgbasics').svg('get');
  $('#toolrange',svg.root()).attr('x',$(o).attr('x')-1)
							 .attr('y',$(o).attr('y')-1)
			 .attr('visibility','visible');
  
  
	//var s1 = tAtt(object,'type');
//	if (s1 != 'flow') return;//如果不是流节点则退出
    	selectedNodeBak = selectedNode;
    	selectedNode = o;
     if(selectedMother!=null && selectedMother.getAttribute('type')=='mother-flow'){

		//drawLine(selectedNode,o);
		drawLine(selectedNodeBak,selectedNode);
     }

	
}



function doClickSelectLine(evt)//选中某line元素，stroke变为blue
{
	
	var object = evt.target;
	var s1 = object.getAttribute('type');
	if (s1 != 'line') return;
	lineUnvisible();
	object.setAttribute('stroke','red');
	selectedLine = object;
}


function doClickLineTxt(evt){

	var object = evt.target;
	selectedLineTxt = object;		
	if(evt.detail==2){
	  var vid = $(object).attr('id');
	  window.showModalDialog('<%=request.getContextPath()%>/pane_wf_propertydetailsline.pml?isApp=true?vid=" + vid +"',window,'scroll:0;status:0;resizable:1;dialogWidth:400px;dialogHeight:200px');
	 //  popupDialog("aaa","属性编辑器",'propertyDetails.html?vid=' + vid );
		//browserEval(openUrl);
		return;
	}
	
	
	
}

function lineUnvisible()//使line恢复原样，成为不选中状态
{
	if (selectedLine == null) return;	
	selectedLine.setAttribute('stroke','black');
	selectedLine = null;
}

//------------------------------

function unSelectNode(){

	   if(selectedNode){
				   	   		  ///开启选中节点的事件	
					 selectedNode.setAttributeNS(null, 'pointer-events', 'all');
					 selectedNodeBak = selectedNode;
					 selectedNode = null;
	   }						
}

//------------------------------
function isWorkSpace(evt)//是否在工作区
{
	var nowX = evt.pageX;
	var nowY = evt.pageY;
	if ( (nowX>100 && nowX<globalX)
	  && (nowY>0   && nowY<globalY)  ){
 		return true;
	}
	else {
		 return false;
	}
}



//========================================================================================================================================
//画线
function beginDrawLine()//开始画线
{
	canDrawLine = true;
}
function drawLine(object1,object2,condition,showValue)//画线函数
{
	
	
  var svg = $('#svgbasics').svg('get');


	if (object1==null) return;//不符合条件的情况
	if (object2==null) return;
	if (object1==object2) return;
	if (canDrawLine == false) return;
	
	//判断是否可以划线
	//先判断是否可以画出
	var node1Type = 	$(object1).attr('nodetype');
	
	//判断是否可以画入
	var node2Type = 	$(object2).attr('nodetype');
	


	//开始节点判断
	if(node2Type=='start'){
		return;
	}
	
	//结束节点判断
	if(node1Type=='end'){
		return;
	}

  if ($(object2).attr('before')!='none' && $(object2).attr('before')!=null && $(object2).attr('before')!=''){
	 		  var sBefore = $(object2).attr('before');
				var arrayBefors = sBefore.split(",");
				for(var i = 0; i < arrayBefors.length; i++){
					  var aBefore = arrayBefors[i];
					  if($(object1).attr('id')==aBefore){
					  	   return;
				  	}
			  }
	     // if(node2Type=='andDecision' || node2Type=='xorDecision' || node2Type=='activity' || node2Type=='auto' || node2Type=='end')  return;
	 }   

   if ($(object1).attr('beyond')!='none' && $(object1).attr('beyond')!=null && $(object1).attr('beyond')!=''){
	  if(node1Type=='andConjuction'|| node1Type=='activity' || node1Type=='auto' || node1Type=='start' || node1Type=='subFlow')  return;   
   }
   
	

	var selector = "#" + $(object2).attr('id')+$(object1).attr('id');
	var tmpLine = $(selector,svg.root());
	

	if (tmpLine[0]!=null) return;//如果想反序画则退出

	////判断连接点的位置
//  var dStr = "M" + x1 +" " + y1 + " L" + xm + " " + ym + " L" + x2 +" " + y2 + " Z";
//  alert("dStr::" + dStr);
	var shape  = svg.root().ownerDocument.createElementNS("http://www.w3.org/2000/svg","line");
  //var shape  = svg.root().ownerDocument.createElementNS("http://www.w3.org/2000/svg","path");
	
	var strId = $(object1).attr('id')+$(object2).attr('id');
//	shape.setAttribute("d", dStr);
	shape.setAttribute("id", strId);
	shape.setAttribute("start",$(object1).attr('id'));
	shape.setAttribute("end",$(object2).attr('id'));
	changeLinePosition(shape);

	var x1 = parseInt(shape.getAttribute("x1"));
	var y1 = parseInt(shape.getAttribute("y1"));
	var x2 = parseInt(shape.getAttribute("x2"));
	var y2 = parseInt(shape.getAttribute("y2"));

	
	shape.setAttribute("condition", "");
	if(condition){
		shape.setAttribute("condition", condition);
	}
	shape.setAttribute("showvalue", "");
	if(showValue){
		shape.setAttribute("showvalue", showValue);
	}
	
	shape.setAttribute("before",$(object1).attr('id'));
	shape.setAttribute("beyond",$(object2).attr('id'));
	
	shape.setAttribute("stroke","black");

	shape.setAttribute("marker-mid", "url(#sanjiao)");
	shape.setAttribute("marker-end", "url(#arrow)");
	shape.setAttribute("type", "line");
	//shape.setAttribute("onmouseover", "showAttribute2(evt)");
	shape.setAttribute("onclick", "doClickSelectLine(evt)");




	$("#workflow",svg.root()).append(shape);
		
		///划线结束，去掉选中
  canDrawLine = false;
  $('#toolrange',svg.root()).attr('visibility','hidden')
	
	
	var strBefors = '';
	if($(object2).attr('before')!='none' && $(object2).attr('before')!=null && $(object2).attr('before')!=''){
		strBefors = $(object2).attr('before') + "," +  $(object1).attr('id');	
	}else{
		strBefors = $(object1).attr('id');
	}
	
	var strbeyonds = '';
	if($(object1).attr('beyond')!='none' && $(object1).attr('beyond')!=null  && $(object1).attr('beyond')!=''){
		strbeyonds = $(object1).attr('beyond') + "," + $(object2).attr('id');	
	}else{
		strbeyonds = $(object2).attr('id');	
	}	
	
	object2.setAttribute('before',strBefors);
	
	object1.setAttribute('beyond',strbeyonds);
	
	
	
	//////////////当条件分支时，需要显示转向条件
	if($(object1).attr('nodetype')=='xorDecision'){

	
	  		var xm = parseInt((x1 + x2)/2);  
  			var ym = parseInt((y1 + y2)/2);
				var txt = svgDoc.createElementNS("http://www.w3.org/2000/svg","text");

				txt.setAttribute("id", "txt_" + strId);
			  txt.setAttribute("onclick", "doClickLineTxt(evt)");
				
				txt.setAttribute("x", xm);
				txt.setAttribute("y", ym);
				txt.setAttribute("stroke", "red");
				txt.setAttribute("stroke-width", 0.15);
				txt.setAttribute("style", "fill:black;text-anchor: middle");
				var strCondition = "转向条件";
				if(showValue){
					strCondition = showValue;
				}
 				var textNode = svg.root().ownerDocument.createTextNode(strCondition);
				txt.appendChild(textNode);
				$("#workflow",svg.root()).append(txt);
				
				
	}		
	//////////////End 当条件分支时，需要显示转向条件
	
	unSelectNode();


}



function addNewNode(aNode)//增加新节点,user流默认property为none
{
	
	var svg = $('#svgbasics').svg('get');;
  try{	
				var shape = svg.root().ownerDocument.createElementNS("http://www.w3.org/2000/svg","image");
				iIndex++;
				if(aNode.id){
					shape.setAttribute("id", aNode.id);
				}else{
					shape.setAttribute("id", tmplName +  "_n"+iIndex);
				}
				//alert('flow'+iIndex);
				shape.setAttribute("x", aNode.x);
				shape.setAttribute("y", aNode.y);
				shape.setAttribute("width", 40);
				shape.setAttribute("height", 40);
				var xlinkns = "http://www.w3.org/1999/xlink";

				shape.setAttribute("before", "none");
				shape.setAttribute("beyond", 'none');
				
				////绑定mousedown 选中元素
				//shape.setAttribute("onmousedown", 'doMouseDown(evt)');
				///绑定单击事件				
				shape.setAttribute("onclick", 'doClick(evt)');
				
				selectedNode = shape;
			
			if(aNode.nodeName){
				shape.setAttribute("nodename", aNode.nodeName); 
			}else{
		  	shape.setAttribute("nodename", "new node" + iIndex); 
		  }
		  
			if(aNode.nodeStateShow){
				shape.setAttribute("nodestateshow", aNode.nodeStateShow); 
			}else{
				shape.setAttribute("nodestateshow", ""); 
			}
			if(aNode.nodeStateShowBack){
							shape.setAttribute("nodestateshowback", aNode.nodeStateShowBack); 
			}else{
							shape.setAttribute("nodestateshowback", ""); 
			}
			if(aNode.accessClass){
							shape.setAttribute("accessclass", 	aNode.accessClass);
			}else{
							shape.setAttribute("accessclass", 	"");
			}
			if(aNode.passTxt){
							shape.setAttribute("passtxt", aNode.passTxt);
			}else{
							shape.setAttribute("passtxt", "");
			}
			if(aNode.rejectTxt){
							shape.setAttribute("rejecttxt", aNode.rejectTxt);
			}else{
 							shape.setAttribute("rejecttxt", "");
			}
			if(aNode.autoService){
							shape.setAttribute("autoservice", aNode.autoService);
			}else{
							shape.setAttribute("autoservice", "");
			}

			if(aNode.paneName){
				shape.setAttribute("panename", aNode.paneName);
			}else{
				shape.setAttribute("panename", "");
			}
			if(aNode.decisionExpression){
							shape.setAttribute("decisionexpression", aNode.decisionExpression); 
			}else{
							shape.setAttribute("decisionexpression", ""); 
			}
			if(aNode.decisionType){
							shape.setAttribute("decisiontype", aNode.decisionType); 
			}else{
							shape.setAttribute("decisiontype", ""); 
			}
			if(aNode.nodeDesc){
							shape.setAttribute("nodedesc", aNode.nodeDesc);
			}else{
							shape.setAttribute("nodedesc", "");
			}
			if(aNode.authType){
							shape.setAttribute("authtype", aNode.authType); 

			}else{
							shape.setAttribute("authtype", ""); 
			}
			if(aNode.specName){
							shape.setAttribute("specname", aNode.specName); 
			}else{
							shape.setAttribute("specname", ""); 
			}
				
		  

			
			nodeType = aNode.nodeType;



				if(nodeType=='start'){
					if(startNode!=null){
					    alert("流程中只能有一个开始节点!");
						return;
					}
			    	shape.setAttributeNS(xlinkns,'xlink:href','images/start.png');
			    	shape.setAttribute("nodetype", 'start');
			    	if(aNode.nodeName){
				    }else{
			    		shape.setAttribute("nodename", "开始节点");
				    } 
			    	startNode = shape;
				}else if (nodeType=='end'){
				    shape.setAttributeNS(xlinkns,'xlink:href','images/end.gif');
			    	shape.setAttribute("nodetype", 'end');
			    	if(aNode.nodeName){
				    }else{
			    		shape.setAttribute("nodename", "结束节点");
				    } 
				}else if (nodeType=='activity'){
					shape.setAttributeNS(xlinkns,'xlink:href','images/task.png');
					shape.setAttribute("nodetype", 'activity');
					
				}else if (nodeType=='xorDecision'){
					shape.setAttributeNS(xlinkns,'xlink:href','images/iffork.gif');
					shape.setAttribute("nodetype", 'xorDecision');
				}else if (nodeType=='andDecision'){
					shape.setAttributeNS(xlinkns,'xlink:href','images/fork.png');
					shape.setAttribute("nodetype", 'andDecision');
				}else if (nodeType=='andConjuction'){
					shape.setAttributeNS(xlinkns,'xlink:href','images/route.gif');
					shape.setAttribute("nodetype", 'andConjuction');
				}else if (nodeType=='subFlow'){
					shape.setAttributeNS(xlinkns,'xlink:href','images/models.png');
					shape.setAttribute("nodetype", 'subFlow');

				}else if (nodeType=='auto'){
					shape.setAttributeNS(xlinkns,'xlink:href','images/auto.gif');
					shape.setAttribute("nodetype", 'auto');
			
				}
			
			
				$("#workflow",svg.root()).append(shape);
			
			///////////////////创建下面的文字元素
	
			
				var txt = svg.root().ownerDocument.createElementNS("http://www.w3.org/2000/svg","text");
				if(aNode.id){
					txt.setAttribute("id", "txt_" + aNode.id);
				}else{		
					txt.setAttribute("id", "txt_" + tmplName + "_n" + iIndex);
				}	
				//alert('flow'+iIndex);
				txt.setAttribute("x", parseInt(aNode.x)+15);
				txt.setAttribute("y", parseInt(aNode.y)+50);
				txt.setAttribute("stroke", "black");
				txt.setAttribute("stroke-width", 0.2);
				txt.setAttribute("style", "fill:black;text-anchor: middle");
				


				var textNode = null;
  			if(aNode.nodeName){
  				textNode = svg.root().ownerDocument.createTextNode(aNode.nodeName);
  			}else{
						if(nodeType=='start'){
							textNode = svg.root().ownerDocument.createTextNode("开始节点");
						}else if(nodeType=='end'){
							textNode = svg.root().ownerDocument.createTextNode("结束节点");
						}
						else{
							textNode = svg.root().ownerDocument.createTextNode("new node" + iIndex);
						}
			  }
			
				txt.appendChild(textNode);
				$("#workflow",svg.root()).append(txt);
	}catch(e){
		//alert(e);
	}
	

}



function deleteObject()//删除所选节点
{
	

	var object = selectedNodeBak;//节点
	var object2 = selectedLine;//线
	



	  var svg = $('#svgbasics').svg('get');
	
	//删除节点下面的文字
	if(object && object2==null){
  	var txtId = 'txt_' + object.getAttribute('id');
    $("#" + txtId,svg.root()).remove();
	}	
		
	
	if ((object==null)&&(object2 == null))
	{
		alert('未选中任何元素');
		return;
	}
	///如果选择两个，则只删除线
	if ((object!=null)&&(object2 != null))
	{
		object = null;
	}

	if (object!=null)//删除流节点
	{
		unSelectNode();//有selectedNode = null;
   $('#toolrange',svg.root()).attr('visibility','hidden')

		//取消其前驱和后继的beyond和before属性，变为none
		var sBefore = object.getAttribute('before');
		if (sBefore!='none' && sBefore!=null && sBefore!='')
		{
			var arrayBefors = sBefore.split(",");
			for(var i = 0; i < arrayBefors.length; i++){
				  var aBefore = arrayBefors[i];
					var befObject = svg.root().ownerDocument.getElementById(aBefore);//取消节点的beyond属性
					if(befObject){
						befObject.setAttribute('beyond',befObject.getAttribute('beyond').replace(object.getAttribute('id'),''));
						$("#" + aBefore+object.getAttribute('id')).remove();//删除连线
	 				  	$("#txt_" + aBefore+object.getAttribute('id')).remove();//删除连线上的txt节点
					} 	
			 }	
		}
		var sBeyond = object.getAttribute('beyond');
		if (sBeyond!='none' &&  sBeyond!=null && sBeyond!='')
		{
			 var arrayBeyonds = sBeyond.split(",");
			for(var i = 0; i < arrayBeyonds.length; i++){
					var aBeyond = arrayBeyonds[i];
					var beyObject = svg.root().ownerDocument.getElementById(aBeyond);
					if(beyObject){
						beyObject.setAttribute('before',beyObject.getAttribute('before').replace(object.getAttribute('id'),''));
						$("#" + object.getAttribute('id')+aBeyond).remove();//删除连线
						$("#txt_" + object.getAttribute('id')+aBeyond).remove();//删除连线上的txt节点
					}
				 
			}	
		}
	  $("#" + object.getAttribute('id')).remove();
		return;
	}
	if (object2!=null)//删除线
	{
		var sBefore = object2.getAttribute('before');
		var befObject = svg.root().ownerDocument.getElementById(sBefore);//获得before object
		
		
  		var sBeyond = object2.getAttribute('beyond');
		var beyObject = svg.root().ownerDocument.getElementById(sBeyond);//获得beyond object
		
		var befores = befObject.getAttribute('beyond').replace(sBeyond,'');
		befObject.setAttribute('beyond',befores);

		var beyonds = beyObject.getAttribute('before').replace(sBefore,'');
		beyObject.setAttribute('before',beyonds);
		
		
		
  	$("#" + object2.getAttribute('id')).remove();	
  	$("#txt_" + object2.getAttribute('id')).remove();	///删除联系上的txt节点
		selectedLine = null;
		return;
	}	

}

function save()//生成流程串
{
	if (!isGoodFlow())
	{
		alert("流程有问题！");
		return 'Error';
	}
	
	///要遍历全部节点 
	var strNodeList = '<nodes>';
	var strFlowList = '<transitions>';
	
	for (var i=1;i<iIndex+1;i++)
	{
		var strId = tmplName + '_n'+i;
		o = svgDoc.getElementById(strId);
		if (o!=null)
		{ 
			var aNodeStr = "<node id='"  +  strId  
				+"' nodeType='" + o.getAttribute("nodetype")
				+"' nodeName='" + o.getAttribute("nodename")

				+"' nodeStateShow='" + o.getAttribute("nodestateshow")
				+"' nodeStateShowBack='" + o.getAttribute("nodestateshowback")
				+"' accessClass='" + o.getAttribute("accessclass")
				+"' passTxt='" + o.getAttribute("passtxt")
				+"' rejectTxt='" + o.getAttribute("rejecttxt")
				+"' paneName='" + o.getAttribute("panename")
				+"' authType='" + o.getAttribute("authtype")
				+"' specName='" + o.getAttribute("specname")
				
				+"' autoService='" + o.getAttribute("autoservice");

			   if(o.getAttribute("decisionexpression")!=null){
				  aNodeStr = aNodeStr +"' decisionExpression='" + o.getAttribute("decisionexpression").replace(/&/g,'&amp;').replace(/>/g,'&gt;').replace(/</g,'&lt;')
				   .replace(/'/g,'&apos;').replace(/"/g,'&quot;');
			    }

			    aNodeStr = aNodeStr + "' decisionType='" + o.getAttribute("decisiontype")
				+"' nodeDesc='" + o.getAttribute("nodedesc")
				
				+"' x='" + o.getAttribute("x")
				+"' y='" + o.getAttribute("y")
				+"'/>\n";
			strNodeList += aNodeStr;
	


			
			var sBeyond = o.getAttribute('beyond');
			if(sBeyond!=null && sBeyond!="none"){
					var arrayBeyonds = sBeyond.split(",");
					for(var ii = 0; ii < arrayBeyonds.length; ii++){
							var aBeyond = arrayBeyonds[ii];
							var theLineId = strId + aBeyond;
							var theLine = svgDoc.getElementById(theLineId);
							if(sBeyond!=null && sBeyond!=""){
										var aLineStr = "<transition id='" + strId + aBeyond
										+ "' from='" + strId
										+ "' to='" + aBeyond;
										if(theLine.getAttribute('condition')!=null && theLine.getAttribute('condition')!=''){
									   aLineStr = aLineStr 	+ "' condition='" + theLine.getAttribute('condition')
									   .replace(/&/g,'&amp;').replace(/>/g,'&gt;').replace(/</g,'&lt;')
									   .replace(/'/g,'&apos;').replace(/"/g,'&quot;');
										}

										if(theLine.getAttribute('showvalue')!=null && theLine.getAttribute('showvalue')!=''){
										 aLineStr = aLineStr + "' showvalue='" + theLine.getAttribute('showvalue');
										}
									
										aLineStr = aLineStr +"'/>\n";
										strFlowList += aLineStr;
							
								}
					}
		  }

			
		}
	}
	
	strNodeList +='</nodes>';
	strFlowList +='</transitions>';
	
	
//	$("#outFlow").text(strNodeList 
	
//	+ "\n" +strFlowList );
	
	var xmlWf = "<wf>" + strNodeList + "\n" + strFlowList  + "</wf>";

	if(xmlWf.indexOf('nodeType')==-1){

		alert("保存失败：无法正确生成XML，请检查模型是否正确或者关闭后重新打开！")
		return;
	}

	try{
		callAction({'actionName':"com.exedosoft.plat.action.wf.DOPTStore",
	   			   'paras':'ptXml=' +  encodeURIComponent(xmlWf)});
		alert("保存成功!");
	}catch(e){
	   alert("保存失败：服务器端错误!");
	}	   

		   
	
	
	
}


/*   
    function loadXML(xmlFile){   
        var xmlDoc;   
        if(!window.ActiveXObject){   
            var parser = new DOMParser();   
            xmlDoc = parser.parseFromString(xmlFile,"text/xml");   
        }else{   
            xmlDoc = new ActiveXObject("Microsoft.XMLDOM");   
            xmlDoc.async="false";   
            xmlDoc.load(xmlFile);   
        }   
        return xmlDoc;   
    }   
*/   
    //针对两种浏览器，分别获取xmlDocument对象   
    function   loadXML(xmlFile)    
    {    

        	var xmlDoc;   
            if(window.ActiveXObject) {    
                xmlDoc=new ActiveXObject("Microsoft.XMLDOM");    
                xmlDoc.async=false;   
                xmlDoc.load(xmlFile);   
            }   
            else if(document.implementation&&document.implementation.createDocument){    
                xmlDoc=document.implementation.createDocument( "", "",null);    
                xmlDoc.async=false;   
                xmlDoc.load(xmlFile);   
            } else{   
                alert('Your   browser   cannot   handle   this   script');    
            }   
        	return xmlDoc;
    }   


function cbXml(data){
	var parser = new DOMParser();
    var  xmlDoc = parser.parseFromString(data.xmlstr,"text/xml");   

	loadWfXmlHelper(xmlDoc);
	
}


function loadWfXml(){
	//首先清空界面
	  clearAllNodes();

	//var xmlDoc = loadXML("1.xml");
	//loadWfXmlHelper(xmlDoc);
     callAction({'actionName':"com.exedosoft.plat.action.wf.DOPTRead",
 		'callback':cbXml});
	
}

function loadWfXmlHelper(xmlDoc){
	
	

   	var root = xmlDoc.documentElement;

		var cs = root.childNodes;
		var len = cs.length;
		

		for (var i = 0; i < len; i++) {
					if( cs[i].tagName  == 'processtemplate' ){////模板层
					     var  ptchildren =  cs[i].childNodes;
					     var maxNodeIndex = ptchildren.length;
					     for(var ptc = 0; ptc < ptchildren.length; ptc++){
					     	   var aPtc = ptchildren[ptc];

					     	   if(aPtc.tagName=='nodes'){
		
					     	   	    var nodeList = aPtc.childNodes;
											    for(var nodei = 0; nodei < nodeList.length; nodei++){
										    	  var aNode = nodeList[nodei];
										    	 	var domAttrs = aNode.attributes;
										    	 	if(domAttrs){
										    	 			var fNode = new FlowNode();
																for (var ii = 0; ii < domAttrs.length; ii++) {
																	if (domAttrs[ii] == null) {
																		continue;
																	}
																	fNode[domAttrs[ii].nodeName] = domAttrs[ii].nodeValue;
																}
																addNewNode(fNode);
																if(fNode.id){
																   var tempNodeIndex = fNode.id.substring(fNode.id.indexOf("_n") +2);
																   if(parseInt(tempNodeIndex) > maxNodeIndex){
																   	   maxNodeIndex = parseInt(tempNodeIndex);
																   	}
																}
													  }
										    }
					     	   	}else  if(aPtc.tagName=='transitions'){
					     	   	    var trans = aPtc.childNodes;
										    for(var trani = 0; trani < trans.length; trani++){
										    	  var aTran = trans[trani];
										    	 	var domAttrs = aTran.attributes;
										    	 	if(domAttrs){
										    	 		  var strFrom = "";
										    	 		  var strTo  = "";
										    	 		  var strCondtion = null;
										    	 		  var strShowValue= null;
																for (var ii = 0; ii < domAttrs.length; ii++) {
																	if (domAttrs[ii] == null) {
																		continue;
																	}
																	if(domAttrs[ii].nodeName=='from'){
																		strFrom = domAttrs[ii].nodeValue;
																	}
																	if(domAttrs[ii].nodeName=='to'){
																		strTo = domAttrs[ii].nodeValue;
																	}
																	if(domAttrs[ii].nodeName=='condition'){
																		strCondtion = domAttrs[ii].nodeValue;
																	}
																	if(domAttrs[ii].nodeName=='showvalue'){
																		strShowValue = domAttrs[ii].nodeValue;
																	}
															  }

															  if(strFrom!="" && strTo!=""){
															  		var from = svgDoc.getElementById(strFrom);
															  		var to = svgDoc.getElementById(strTo);
															  		if(from!=null && to!=null){
																			canDrawLine = true;										  			
															  			drawLine(from,to,strCondtion,strShowValue);
															  		}
															  }
													  }
										    }
					     	   	}
					     	}
					     iIndex =	maxNodeIndex;
					}
		}
		
		selectedNode = null;
		selectedLine = null;
}

function clearAllNodes(){
	
	for (var i=1;i<iIndex+1;i++)
	{
		var strId = tmplName + '_n'+i;
		o = svgDoc.getElementById(strId);
		if (o!=null)
		{
			selectedNodeBak  = o;
			deleteObject();
		}
	}
	selectedNodeBak = null;
	startNode = null;
	
}





function isGoodFlow()//检测是否是合理的流程串
{
///没有节点	
	if (iIndex==0){
		return false;
    }
	
	//没有开始节点
	if(startNode==null){
		alert("没有开始节点");
		return false;
	}

	///只能有一个开始节点
	if (getFCount()>1)
	return false;

	return true;
}


////开始节点 只能有一个  结束节点可以有多个个
function getFCount(svg)//获得before为none的节点数
{


	  
	var iCount = 0;
	var svg = $('#svgbasics').svg('get');
	for (var i=1;i<iIndex+1;i++)
	{
		if($("#" +tmplName + "_n" +i,svg.root()).attr('before')=='none'){
			iCount++;
			if(iCount > 1){
	          alert(tmplName + "_n" +i + "::Error Before is none!");
			}
		}
	}
	return iCount;
}



</script>
</head>
<body  >
<h2>易元工作流建模工具</h2>

<div id="svgbasics"></div>

<div id="outFlow"></div>

</body>
</html>
