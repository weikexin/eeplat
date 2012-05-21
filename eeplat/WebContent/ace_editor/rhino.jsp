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
    var editor;


	var autocompleting=false;
	
	var js_tags=[
	["addBatch()","- DOService"],
	["begin()","- DOService"],
	["beginBatch()","- DOService"],
	["currentTransaction()","- DOService"],
	["DOBO.getDOBOByName('')", "- DOBO"],
	["DOService.getService('')", "- DOService"],
	["delete()","- DOService"],
	["end()","- DOService"],
	["endBatch()","- DOService"],
	["getAValue()", "- DOService"],
	["getValue()","- BOInstance"],
	["getValue4Null()","- BOInstance"],
	["getValueArray()","- BOInstance"],
	["getCollection()","- BOInstance"],
	["getObjectValue()","- BOInstance"],
	["getIntValue()","- BOInstance"],
	["getLongValue()","- BOInstance"],
	["getDateValue()","- BOInstance"],
	["getDataBase()","- BOInstance"],
	["getTransaction()","- DOBO"],
	["new BOInstance()","- BOInstance"],
	["getInstance()","- DOService"],
	["invokeAll()","- DOService"],
	["putValue()","- BOInstance"],
	["rollback()","- DOService"],
	["select()","- DOService"],
	["store()","- DOService"], 
	["storeByForm()","- DOService"], 
	["setTempParaMap()", "- DOService"],
	["setTempParaList()", "- DOService"],
	["setParaStrValues()","- DOService"]
	];
	
		
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
					ac.style.width='240px';
					ac.size=10;
				}

				var sel = editor.getSelection();
				
				var session = editor.getSession();

				var lead = sel.getSelectionLead();

				var pos = editor.renderer.textToScreenCoordinates(lead.row, lead.column);
				
				//calulate the container offset
				var obj=editor.container;
				
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
				
				var sel=editor.selection.getRange();

				//var tag;
				for(i in js_tags){
					if(!js_tags.hasOwnProperty(i) ){
						continue;
					}
					
					//tag=js_tags[i][0];					
				
					//if( text ){
					//	if( text!=tag.substr(0,text.length) ){
						
					//		console.log("text::" + text);
							
					//		console.log("tag.substr(0,text.length)::" + tag.substr(0,text.length));
					//		continue;
					//	}
					//}
				
					var option = document.createElement('option');
					option.text =js_tags[i][0] + "   " + js_tags[i][1] + " ";
					option.value = js_tags[i][0];
					
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
		
				editor.container.appendChild(ac);
				
				autocompleting=true;

	}
	
	window.onload = function() {
	
		editor = ace.edit("javascripteditor");
		
		document.getElementById('javascripteditor').style.fontSize='16px';
		//editor.getSession().setUseWrapMode(true);
		
		var Mode = require("ace/mode/javascript").Mode;
		editor.getSession().setMode(new Mode());
		
		editor.setBehavioursEnabled(false);
		
		editor.getSession().setValue(theHiddenValue);
		
		//editor.commands.removeCommand("gotowordright");///Ctril + right use myself defined
		
	
		
		editor.commands.addCommand({
			name: "langle2",
			bindKey: {
				win: "Ctrl-.", // Shift-Right
				mac: "Command-." // Shift-Right
			},
			exec: function(editor) {	
               
				//editor.autocomplete();
				
				autocomplete(editor);
		
			}
		});
		
		editor.commands.addCommand({
			name: "save",
			bindKey: {
				win: "Ctrl-s", // Shift-Right
				mac: "Command-s" // Shift-Right
			},
			exec: function(editor) {	
				 parent.insertAceCode();
			}
		});
		
		
		
		editor.commands.addCommand({
			name: "finish",
			bindKey: {
				win: ".", // >
				mac: ".", // >
				sender: "javascripteditor"
			},
			exec: function(editor) {					
				
				editor.insert('.');
				if( document.getElementById('ac') ){
					var ac=document.getElementById('ac');
					ac.parentNode.removeChild(ac);
					autocompleting=false;
				}
	
			}
		});
		
		editor.commands.addCommand({
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
					editor.navigateUp(args.times);
				}
			}
		});
		
		editor.commands.addCommand({
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
					editor.navigateDown(args.times);
				}
			}
		});
		
		editor.commands.addCommand({
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
		
		editor.commands.addCommand({
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
					
					var sel=editor.selection.getRange();

					var line=editor.getSession().getLine(sel.start.row);	
					if(line.toLowerCase().charAt(line.length-1)==tag.toLowerCase().charAt(0)){
					  tag = tag.substr(1);
					}	
					editor.insert(tag);
					var ac=document.getElementById('ac');
					ac.parentNode.removeChild(ac);
					autocompleting=false;
				}else{
					editor.insert('\n');
				}
			}
		});
		
	
//		editor.getSession().selection.on('changeCursor',function(oldRange, newRange, newText) {			
//			if( document.getElementById('ac') ){
//				var ac=document.getElementById('ac');
//				
//				ac.parentNode.removeChild(ac);
//			}					
//		});
		
//		editor.getSession().on('change', function(e) {   
			
//			var sel=editor.selection.getRange();

//			var line=editor.getSession().getLine(sel.start.row);						

			//console.log("last::" + line.charAt(sel.start.column));
	  
	  
//	  });
		

		var event = require("ace/lib/event");
		

		
		event.addListener(editor.textInput.getElement(), "keydown", 
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
							editor.navigateUp(args.times);
						}
				}
			}
		);
		

		
		
	};
</script>

</body>
</html>
