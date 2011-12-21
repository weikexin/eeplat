<style type="text/css"> 
<!-- 
td, a { font-size:12px; color:#000000 } 
#Layer1 { position:absolute; z-index:100; top: 75px; } 
#Layer2 { position:absolute; z-index:1;} 
--> 
</style> 
<div id="Layer1"> 
<table border="0" cellspacing="2" cellpadding="0"> 
<tr> 
<td></td> 
<td><img src="/${webmodule}/exedo/webv3/images/jsImageEnlargerNarrowRotation/up.gif" width="20" height="20" style="cursor:hand" onClick="clickMove('up')" title="向上"></td> 
<td></td> 
</tr> 
<tr> 
<td><img src="/${webmodule}/exedo/webv3/images/jsImageEnlargerNarrowRotation/left.gif" width="20" height="20" style="cursor:hand" onClick="clickMove('left')" title="向左"></td> 
<td><img src="/${webmodule}/exedo/webv3/images/jsImageEnlargerNarrowRotation/zoom.gif" width="20" height="20" style="cursor:hand" onClick="realsize()" title="还原"></td>
<td><img src="/${webmodule}/exedo/webv3/images/jsImageEnlargerNarrowRotation/right.gif" width="20" height="20" style="cursor:hand" onClick="clickMove('right')" title="向右"></td> 
</tr> 
<tr> 
<td></td> 
<td><img src="/${webmodule}/exedo/webv3/images/jsImageEnlargerNarrowRotation/down.gif" width="20" height="20" style="cursor:hand" onClick="clickMove('down')" title="向下"></td> 
<td></td> 
</tr> 
<tr> 
<td></td> 
<td><img src="/${webmodule}/exedo/webv3/images/jsImageEnlargerNarrowRotation/zoom_in.gif" width="20" height="20" style="cursor:hand" onClick="bigit();" title="放大"></td> 
<td></td> 
</tr> 
<tr> 
<td></td> 
<td><img src="/${webmodule}/exedo/webv3/images/jsImageEnlargerNarrowRotation/zoom_out.gif" width="20" height="20" style="cursor:hand" onClick="smallit();" title="缩小"></td> 
<td></td> 
</tr> 
<tr> 
<td></td> 
<td><img src="/${webmodule}/exedo/webv3/images/jsImageEnlargerNarrowRotation/turn.gif" width="20" height="20" style="cursor:hand" onClick="playImg();" title="旋转"></td> 
<td></td> 
</tr> 
</table> 
</div> 

<div algin="center" id='hiddenPic' style='position:absolute; left:0px; top:70px; width:0px; height:0px; z-index:1; visibility: hidden;'><img name='images222' height="560" src='${absPath}' border='0'></div> 
<div algin="center" id='block1' onmouseout='drag=0' onmouseover='dragObj=block1; drag=1;' style='z-index:10; height: 0; left: 0px; position: absolute; top: 70px; width: 0' class="dragAble"> <img name='images111' height="560" src='${absPath}' border='0'></div> 

