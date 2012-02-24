<iframe  id="if${model.name}"  frameborder='0' src="${resPath}" style="width:100%;"/>
<script>

	var	height = $(window).height();
	var top = $(".gMain").offset().top;
	$("#if${model.name}").css("height",height-top);
</script>

