<iframe  id="if${model.name}"  frameborder='0' src="/${webmodule}/nkoa/openawordedit.jsp?docName=111.doc" style="width:100%;"/>
<script>

	var	height = $(window).height();
	var top = $(".gMain").offset().top;
	$("#if${model.name}").css("height",height-top);
</script>

