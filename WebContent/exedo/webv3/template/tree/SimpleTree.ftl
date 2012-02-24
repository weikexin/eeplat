<div class=tree id='${model.nodeName}'></div>
<script language="javascript">
	webFXTreeHandler.resetContext();
	var tree = new WebFXLoadTree('${model.l10n}','${treeModelUrl}');
	$("#${model.nodeName}").append(tree.toHtml());
	resscrEvt();
 //  $(".mRight:eq(0)").css("overflow-x","hidden");
  // $(".mRight:eq(0)").css("overflow-y","hidden");
</script>
 

	
	
