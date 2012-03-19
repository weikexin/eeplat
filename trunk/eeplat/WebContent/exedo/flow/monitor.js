var canvas;
var iIndex = 0;//全局元素个数
var startNode = null;

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
		this.status = 'init';
					
	 
	 
	 this.x = 0;
	 this.y = 0;
		
	}

function loadWfMonitor(paper){
	//首先清空界面
	 canvas = paper;
	 if(canvas==null){
		 return;
	 }
	 canvas.clear();

     callAction({'actionName':"com.exedosoft.plat.action.wf.DOWfMonitor",
 		'callback':loadWfMonitorHelper});
	
}



function loadWfMonitorHelper(data){

	if(canvas==null){
		 return;
	 }

     var xmlDoc = $.parseXML(data.xmlstr); 

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
																addNewWfNode(fNode);
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
															  		var from = canvas.getById( $.data(document.body,strFrom) );
															  		var to = canvas.getById($.data(document.body,strTo) );
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
	 
	    //fixed safari bug 
		canvas.safari();
  }
  
  
function addNewWfNode(aNode)//增加新节点,user流默认property为none
{

  try{	
	  nodeType = aNode.nodeType;
	  nodeImg = "task.png";
	  var status = aNode.status;
	  var nodeName = "";
	  if(aNode.nodeName){
		  nodeName = aNode.nodeName;
	  }
				if(nodeType=='start'){
					 nodeImg = "start.png";
					 nodeName = "开始节点";
				}else if (nodeType=='end'){
					 nodeImg = "end.gif";	
					 if(status == 'init'){
						 nodeImg = "end_g.gif";	
					 }

					 nodeName = "结束节点";
				}else if (nodeType=='activity'){
					 if(status == 'init'){
						 nodeImg = "task_g.png";	
					 }else if(status == 'run'){
						 nodeImg = "task_r.png";
					 }
				}else if (nodeType=='xorDecision'){
					 nodeImg = "iffork.gif";
					 if(status == 'init'){
						 nodeImg = "iffork_g.gif";	
					 }
				}else if (nodeType=='andDecision'){
					 nodeImg = "fork.png";	
					 if(status == 'init'){
						 nodeImg = "fork_g.png";	
					 }
				}else if (nodeType=='andConjuction'){
					 nodeImg = "route.gif";	
					 if(status == 'init'){
						 nodeImg = "route_g.gif";	
					 }
				}else if (nodeType=='subFlow'){
					 nodeImg = "models.png";	
					 if(status == 'init'){
						 nodeImg = "models_g.png";	
					 }
				}else if (nodeType=='auto'){
					 nodeImg = "auto.gif";
					 if(status == 'init'){
						 nodeImg = "auto_g.gif";	
					 }
				}
//				console.log("node.x::" + aNode.x);
//				console.log("node.x::---" + (aNode.x-120));
//				console.log("node.y:::" + aNode.y);
//				console.log("node.y:::-----" + (aNode.y-50));
				
				var theImg = canvas.image("images/" + nodeImg,(aNode.x-120),(aNode.y-50),35,35);
				if(aNode.id){
					$.data(document.body,aNode.id,theImg.id);
				}
				var attr = {font: "12px 宋体"};

	
				var txtItem = canvas.text((aNode.x-105),(aNode.y-5),nodeName).attr( {font: "12px 宋体"});
				
				if(status == 'init'){
					txtItem.attr({fill:'gray'});
				}else if(status == 'run'){
					txtItem.attr({fill:'red'});
				}
				

			
	}catch(e){
		//alert(e);
	}
}

function drawLine(object1,object2,condition,showValue)//画线函数
{
    if(canvas==null){
		 return;
	 }
    
    
	var x1 = parseInt(object1.attr('x'))+40;
	var y1 = parseInt(object1.attr('y'))+20;
	
	var x2 = parseInt(object2.attr('x'));
	var y2 = parseInt(object2.attr('y'))+20;

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
     
     var pathstr = "M" + x1 + ","
                       + y1 + " "
                       + x2 + ","
                       + y2  ;
     
     pathstr = cal(x1,y1,x2,y2);

     canvas.path(pathstr).attr({fill: "#000", stroke: "none"});//
     

}

function pointer(){
	
	this.x = 0;
	this.y = 0;
}

function cal(x1,y1,x2,y2){


	 if(x2==x1){
		 x2 = x1 + 1;
	 }
	
	 var vtan = (y2-y1)/(x2-x1);
	 var r = Math.atan( vtan );   
     var vsin = Math.sin(r);
     var vcos = Math.cos(r);
     
     var absin = Math.abs(vsin);
     var abcos = Math.abs(vcos);

     
     var c1 = new pointer();
     
     var topDown = (y2 > y1);
     var leftRight = (x2 > x1);
     
     
     
     if(topDown){
    	 c1.x = x1 + 1 * absin;
     }else{
    	 c1.x = x1 - 1 * absin;
     }
       
     if(leftRight){
    	  c1.y = y1 - 1 * abcos;
     }else{
    	 c1.y = y1 + 1 * abcos;
     }
   
     
     var c2 = new pointer();
     if(leftRight){
    	 c2.x = x2 - 5 * abcos + 1 * absin;
     }else{
    	 c2.x = x2 + 5 * abcos + 1 * absin;
     }
     if(topDown){
    	 c2.y = y2 - 5 * absin - 1 * abcos;
     }else{
    	 c2.y = y2 + 5 * absin - 1 * abcos;
     }
     
     var c3 = new pointer();
     if(topDown){
         c3.x = c2.x + 2 * absin;
     }else{
         c3.x = c2.x - 2 * absin;
     }
     if(leftRight){
    	 c3.y = c2.y - 2 * abcos;
     }else{
    	 c3.y = c2.y + 2 * abcos;
     }
     
     
     var c4 = new pointer();
     c4.x = x2;
     c4.y = y2;
     
     var c5 = new pointer();
     if(topDown){
     	c5.x = c3.x - 6 * absin;
	 }else{
    	c5.x = c3.x + 6 * absin;
	 }
     if(leftRight){
    	 c5.y = c3.y + 6 * abcos;
     }else{
    	 c5.y = c3.y - 6 * abcos;
     }
     
     var c6 = new pointer();
     if(topDown){
    	 c6.x = c3.x - 4 * absin;
     }else{
    	 c6.x = c3.x + 4 * absin;
     }
     if(leftRight){
    	 c6.y = c3.y + 4 * abcos;
     }else{
    	 c6.y = c3.y - 4 * abcos;
     }
     
     var c7 = new pointer();
     
     if(topDown){
    	 c7.x = x1 - 1 * absin;
     }else{
    	 c7.x = x1 + 1 * absin;
     }
     if(leftRight){
    	 c7.y = y1 + 1 * vcos;
     }else{
    	 c7.y = y1 - 1 * abcos;
     }
     
     var pathstr = "M" + x1 + "," + y1 
             + "L" + c1.x + "," + c1.y
             + "L" + c2.x + "," + c2.y
             + "L" + c3.x + "," + c3.y
             + "L" + c4.x + "," + c4.y
             + "L" + c5.x + "," + c5.y
             + "L" + c6.x + "," + c6.y
             + "L" + c7.x + "," + c7.y
     		 + "L" + x1 + "," + y1
     		 + "Z";
     return pathstr;
	
}



