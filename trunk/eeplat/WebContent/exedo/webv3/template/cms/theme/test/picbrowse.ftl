<!--
  播放图片插件 需要 模板中加入jquery-1.4.2a.min.js 和jquery.KinSlideshow-1.2.1.min.js
可根据实际需要修改img标签的高度和宽度
-->
<script type="text/javascript">
	$(function(){
		$("#KinSlideshow").KinSlideshow();
	})
</script>


<div id="KinSlideshow" >
    <a href="http://www.qq1.com" target="_blank"><img src="${bloginfo('current_path')}images/1.jpg" alt="这是标题一" width="446" height="257" /></a>
    <a href="http://www.qq2.com" target="_blank"><img src="${bloginfo('current_path')}images/2.jpg" alt="这是标题二" width="446" height="257" /></a>
    <a href="http://www.qq3.com" target="_blank"><img src="${bloginfo('current_path')}images/3.jpg" alt="这是标题三" width="446" height="257" /></a>
    <a href="http://www.qq4.com" target="_blank"><img src="${bloginfo('current_path')}images/4.jpg" alt="这是标题四" width="446" height="257" /></a>
    <a href="http://www.qq5.com" target="_blank"><img src="${bloginfo('current_path')}images/5.jpg" alt="这是标题五" width="446" height="257" /></a>
    <a href="http://www.qq6.com" target="_blank"><img src="${bloginfo('current_path')}images/6.jpg" alt="这是标题六" width="446" height="257" /></a>
    <a href="http://www.qq6.com" target="_blank"><img src="${bloginfo('current_path')}images/zephyr.png" alt="这是标题六" width="446" height="257" /></a>
</div>