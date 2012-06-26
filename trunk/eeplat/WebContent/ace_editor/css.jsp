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
    
    #csseditor { 
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

<pre id="csseditor"></pre>
    
<script src="src/ace.js" type="text/javascript" charset="utf-8"></script>
<script src="src/mode-javascript.js" type="text/javascript" charset="utf-8"></script>
<script src="src/mode-html.js" type="text/javascript" charset="utf-8"></script>
<script>



var theHiddenValue = parent.document.getElementById("<%=hiddenid%>").value;

window.onload = function() {
	if(parent.cssEditor!=null){
		parent.cssEditor.destroy();
	}
		parent.cssEditor = ace.edit("csseditor");
	    
		document.getElementById('csseditor').style.fontSize='16px';
	
	    var cssMode = require("ace/mode/css").Mode;
	    parent.cssEditor.getSession().setMode(new cssMode());
	    parent.cssEditor.getSession().setValue(theHiddenValue);
		
	    parent.cssEditor.commands.addCommand({
			name: "save",
			bindKey: {
				win: "Ctrl-s", // Shift-Right
				mac: "Command-s" // Shift-Right
			},
			exec: function(editor) {	
				 parent.insertAceCode();
			}
		});
	

};
</script>

</body>
</html>
