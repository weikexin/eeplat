<div class="ui-widget">
	<input id="${model.fullColID}" type="hidden" name="${model.colName}" serviceName="${model.linkService.name}"  value="${model.value?if_exists}" tlabel="${label?if_exists}"/>
	
</div>
<script>
$( "#${model.fullColID}" ).combobox();

</script>
