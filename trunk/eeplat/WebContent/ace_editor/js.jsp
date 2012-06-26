<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Editor</title>
  <style type="text/css" media="screen">
    body {
        overflow: hidden;
    }
    
    #javascripteditor { 
        margin: 0;
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
    }
  </style>
</head>
<body>

<%

  String hiddenid = request.getParameter("hiddenid");

%>

<pre id="javascripteditor"></pre>
    
<script src="src/ace.js" type="text/javascript" charset="utf-8"></script>
<script src="src/mode-javascript.js" type="text/javascript" charset="utf-8"></script>

<script>
	
	
	var theHiddenValue = parent.document.getElementById("<%=hiddenid%>").value;

	var autocompleting=false;
	
	var js_tags=["callService",
	"callService({'btn':this,'serviceName':'','callback':,'paras':'','formName':'' ,'pml':'','pmlWidth':'','pmlHeight':'','target':'','echoJs':''});",
	"callAction",
	"callAction({'btn':this,'actionName':'','callback':,'paras': ''});", 
	"loadPml", 
	"loadPml({'pml':'','pmlWidth':'','pmlHeight':'', 'title':'','formName':'','target':''});", 
	"var dealBus = 'dataBus=setContext&contextKey=&contextValue='", 
	"callPlatBus({'paras':dealBus});"];
	
		
	function  autocomplete(){
	
				var ac;
				if( document.getElementById('ac') ){
					ac=document.getElementById('ac');
					if(select.options.length>1){
						return;
					}
				}else{
					ac = document.createElement('select');
					ac.id = 'ac';
					ac.namme = 'ac';
					ac.style.position='absolute';
					ac.style.zIndex=100;;
					ac.style.width='140px';
					ac.size=10;
				}

				var sel = parent.jsEditor.getSelection();
				
				var session = parent.jsEditor.getSession();

				var lead = sel.getSelectionLead();

				var pos = parent.jsEditor.renderer.textToScreenCoordinates(lead.row, lead.column);
				
				//calulate the container offset
				var obj=parent.jsEditor.container;
				
				var curleft = 0;
				var curtop = 0;
				if (obj.offsetParent) {
					do {
						curleft += obj.offsetLeft;
						curtop += obj.offsetTop;
					} while (obj = obj.offsetParent);
				}						

				//position autocomplete
				ac.style.top=pos.pageY - curtop + 20 + "px";
				ac.style.left=pos.pageX - curleft + "px";
				ac.style.display='block';
				ac.style.background='white';
				
				var sel=parent.jsEditor.selection.getRange();

				var tag;
				for(i in js_tags){
					if(!js_tags.hasOwnProperty(i) ){
						continue;
					}
					
					tag=js_tags[i];					
				
					//if( text ){
					//	if( text!=tag.substr(0,text.length) ){
						
					//		console.log("text::" + text);
							
					//		console.log("tag.substr(0,text.length)::" + tag.substr(0,text.length));
					//		continue;
					//	}
					//}
				
					var option = document.createElement('option');
					option.text = tag;
					option.value = tag;
					
					try {
						ac.add(option, null); // standards compliant; doesn't work in IE
					}
					catch(ex) {
						ac.add(option); // IE only
					}
				};
				
				if( ac.length==0 ){
					autocompleting=false;
					
					return false;
				}
				
				ac.selectedIndex=0;						
		
				parent.jsEditor.container.appendChild(ac);
				
				autocompleting=true;

	}
	
	window.onload = function() {
		
		if(parent.jsEditor!=null){
			parent.jsEditor.destroy();
		}
	
		parent.jsEditor = ace.edit("javascripteditor");
		
		document.getElementById('javascripteditor').style.fontSize='16px';
		//parent.jsEditor.getSession().setUseWrapMode(true);
			
		var Mode = require("ace/mode/javascript").Mode;
		parent.jsEditor.getSession().setMode(new Mode());
		
		
		
		parent.jsEditor.setBehavioursEnabled(false);
		
		parent.jsEditor.getSession().setValue(theHiddenValue);

		//parent.jsEditor.commands.removeCommand("gotowordright");///Ctril + right use myself defined
		
	
		
		parent.jsEditor.commands.addCommand({
			name: "langle2",
			bindKey: {
				win: "Ctrl-.", // Shift-Right
				mac: "Command-." // Shift-Right
			},
			exec: function(editor) {	
               
				//parent.jsEditor.autocomplete();
				
				autocomplete(editor);
		
			}
		});
		
		parent.jsEditor.commands.addCommand({
			name: "save",
			bindKey: {
				win: "Ctrl-s", // Shift-Right
				mac: "Command-s" // Shift-Right
			},
			exec: function(editor) {	
				 parent.insertAceCode();
			}
		});
		
		
		parent.jsEditor.commands.addCommand({
			name: "finish",
			bindKey: {
				win: ".", // >
				mac: ".", // >
				sender: "javascripteditor"
			},
			exec: function(editor) {					
				
				parent.jsEditor.insert('.');
				if( document.getElementById('ac') ){
					var ac=document.getElementById('ac');
					ac.parentNode.removeChild(ac);
					autocompleting=false;
				}
	
			}
		});
		
		parent.jsEditor.commands.addCommand({
			name: "up",
			bindKey: {
				win: "Up",
				mac: "Up",
				sender: "javascripteditor"
			},
			exec: function(editor,args) {
				if( document.getElementById('ac') ){
					var select=document.getElementById('ac');
					
					if( select.selectedIndex==0 ){
						select.selectedIndex=select.options.length-1;
					}else{
						select.selectedIndex=select.selectedIndex-1;
					}
				}else{
					parent.jsEditor.navigateUp(args.times);
				}
			}
		});
		
		parent.jsEditor.commands.addCommand({
			name: "down",
			bindKey: {
				win: "Down",
				mac: "Down",
				sender: "javascripteditor"
			},
			exec: function(editor,args) {
				if( document.getElementById('ac') ){
					var select=document.getElementById('ac');
					
					if( select.selectedIndex==select.options.length-1 ){
						select.selectedIndex=0;
					}else{
						select.selectedIndex=select.selectedIndex+1;
					}
				}else{
					parent.jsEditor.navigateDown(args.times);
				}
			}
		});
		
		parent.jsEditor.commands.addCommand({
			name: "escape",
			bindKey: {
				win: "Esc",
				mac: "Esc",
				sender: "javascripteditor"
			},
			exec: function(editor) {
				if( document.getElementById('ac') ){							
					var ac=document.getElementById('ac');
					
					ac.parentNode.removeChild(ac);
					
					autocompleting=false;
				}
			}
		});
		
		parent.jsEditor.commands.addCommand({
			name: "enter",
			bindKey: {
				win: "Return",
				mac: "Return",
				sender: "javascripteditor"
			},
			exec: function(editor) {
				if( document.getElementById('ac') ){
					var select=document.getElementById('ac');
					var tag=select.options[select.selectedIndex].value;
					
					
					var sel=parent.jsEditor.selection.getRange();
					var line=parent.jsEditor.getSession().getLine(sel.start.row);	
					if(line.toLowerCase().charAt(line.length-1)==tag.toLowerCase().charAt(0)){
					  tag = tag.substr(1);
					}					

					parent.jsEditor.insert(tag);
					
					var ac=document.getElementById('ac');
					ac.parentNode.removeChild(ac);
					autocompleting=false;
				}else{
					parent.jsEditor.insert('\n');
				}
			}
		});
		
	
//		parent.jsEditor.getSession().selection.on('changeCursor',function(oldRange, newRange, newText) {			
//			if( document.getElementById('ac') ){
//				var ac=document.getElementById('ac');
//				
//				ac.parentNode.removeChild(ac);
//			}					
//		});
		
//		parent.jsEditor.getSession().on('change', function(e) {   
			
//			var sel=parent.jsEditor.selection.getRange();

//			var line=parent.jsEditor.getSession().getLine(sel.start.row);						

			//console.log("last::" + line.charAt(sel.start.column));
	  
	  
//	  });
		

		var event = require("ace/lib/event");
		

		
		event.addListener(parent.jsEditor.textInput.getElement(), "keydown", 
			function(e){
				if(autocompleting && ( (e.keyCode >=65 && e.keyCode <=90)  )){//|| e.keyCode == 51
						
						if( document.getElementById('ac') ){
							var select=document.getElementById('ac');
							
							for(var i = 0; i < select.options.length; i++ ){
								  if( (select.options[i].value.toUpperCase().charCodeAt(0)==e.keyCode) ){
									select.selectedIndex=i;
									break;
								  }
							}
						}else{
							parent.jsEditor.navigateUp(args.times);
						}
				}
			}
		);
		

		
		
	};
</script>

</body>
</html>
