var isStart=true;
var nn;
var tt;
function setFocus(i)
{
 if(tt) clearTimeout(tt);
 nn = i;
 selectLayer1(i);
 tt=setTimeout('change_img()',4000);
}
function selectLayer1(i)
{
 switch(i)
 {
 case 1:
document.getElementById("bbs_s1").style.display="block";
document.getElementById("bbs_s2").style.display="none";
 break;
case 2:
document.getElementById("bbs_s1").style.display="none";
document.getElementById("bbs_s2").style.display="block";
 break;
 }
}
