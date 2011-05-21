function pauseAutoSwitch() {
	clearTimeout(AutoPicSwitchTabTimer) ;
}
function goonAutoSwitch(ident,index,count,TimeLength) {
	clearTimeout(AutoPicSwitchTabTimer) ;
	AutoPicSwitchTabTimer = setTimeout("autoSwitchTab('"+ident+"',"+index+","+count+","+TimeLength+");", TimeLength);
}
function autoSwitchTab(ident,index,count,TimeLength) {
	if (index == count || index > count) {
		index = 0 ;
	}
	switchTab(ident,index,count) ;
	index = index + 1 ;
	AutoPicSwitchTabTimer = setTimeout("autoSwitchTab('"+ident+"',"+index+","+count+","+TimeLength+");", TimeLength);
}

function switchTab(identify,index,count) {
	for(i=0;i<count;i++) {
		var CurTabObj = document.getElementById("Tab_"+identify+"_"+i) ;
		var CurListObj = document.getElementById("List_"+identify+"_"+i) ;
		if (i != index) {
			fRemoveClass(CurTabObj , "upH3") ;
			fRemoveClass(CurListObj , "upBox") ;
		}
	}
	fAddClass(document.getElementById("Tab_"+identify+"_"+index),"upH3") ;
	fAddClass(document.getElementById("List_"+identify+"_"+index),"upBox") ;
}

function switchSideTab(identify,index,count) {
	for(i=0;i<count;i++) {
		var CurTabObj = document.getElementById("Tab_"+identify+"_"+i) ;
		var CurListObj = document.getElementById("List_"+identify+"_"+i) ;
		if (i != index) {
			fRemoveClass(CurTabObj , "upH3") ;
			fRemoveClass(CurListObj , "upUL") ;
		}
	}
	fAddClass(document.getElementById("Tab_"+identify+"_"+index),"upH3") ;
	fAddClass(document.getElementById("List_"+identify+"_"+index),"upUL") ;
}

function fAddClass(XEle, XClass)
{/* shawl.qiu code, void return */
  if(!XClass) throw new Error("XClass 不能为空┖!");
  if(XEle.className!="") 
  {
    var Re = new RegExp("\\b"+XClass+"\\b\\s*", "");
    XEle.className = XEle.className.replace(Re, "");
	var OldClassName = XEle.className.replace(/^\s+|\s+$/g,"") ;
	if (OldClassName == "" ) {
		 XEle.className = XClass;
	}
	else {
		XEle.className = OldClassName + " " + XClass;
	}
   
  }
  else XEle.className = XClass;
}/* end function fAddClass(XEle, XClass) */

function switchPic(screen) {
	if (screen > MaxScreen) {
		screen = 1 ;
	}
	
	for (i=1;i<=MaxScreen;i++) {
		document.getElementById("Switch_"+i).style.display = "none" ;
	}
	document.getElementById("Switch_"+screen).style.display = "block" ;
	showSwitchNav(screen);
	showSwitchTitle(screen);
	//Effect.Appear("Switch_"+screen);
			
	//switchLittlePic(screen);
	//showSwitchTitles(screen);
	CurScreen = screen  ;
}
function showSwitchNav(screen) {
	var NavStr = "" ;
	for (i=1;i<=MaxScreen;i++) {
		if (i == screen) {
			NavStr += '<li style="float:left;width:15px;height:18px;line-height:18px;text-align:center;margin-left:1px;background:#000000;margin:0;padding:0;" onmouseover="pauseSwitch();" onmouseout="goonSwitch();"><a href="javascript://" target="_self" class="sel" style="text-decoration:none;display:block;">'+i+'</a></li>' ;
		}
		else {
			NavStr += '<li style="float:left;width:15px;height:18px;line-height:18px;text-align:center;margin-left:1px;background:#000000;margin:0;padding:0;" onmouseover="pauseSwitch();" onmouseout="goonSwitch();" onclick="goManSwitch('+i+');"><a href="javascript://" target="_self" style="text-decoration:none;display:block;">'+i+'</a></li>' ;
		}
		
	}
	document.getElementById("SwitchNav").innerHTML = NavStr ;
}
function showSwitchTitle(screen) {
	var titlestr = "" ;
	titlestr = '<h3><a href="'+Switcher[screen]['link']+'" target="_blank" style="text-decoration:none;color:#FFFFF;">'+Switcher[screen]['title']+'</a></h3><p><a href="'+Switcher[screen]['link']+'" target="_blank" style="text-decoration:none;color:#FFFFFF;">'+Switcher[screen]['stitle']+'</a></p>' ;
	document.getElementById("SwitchTitle").innerHTML = titlestr ;
}
function reSwitchPic() {
	refreshSwitchTimer = null;
	switchPic(CurScreen+1);
	refreshSwitchTimer = setTimeout('reSwitchPic();', 3000);
}
function pauseSwitch() {
	clearTimeout(refreshSwitchTimer);
}
function goonSwitch() {
	clearTimeout(refreshSwitchTimer);
	refreshSwitchTimer = setTimeout('reSwitchPic();', 3000);
}
function goManSwitch(index) {
	clearTimeout(refreshSwitchTimer);
	
	CurScreen = index - 1 ;
	reSwitchPic();
}
