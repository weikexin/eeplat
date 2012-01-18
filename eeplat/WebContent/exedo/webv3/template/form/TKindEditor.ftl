<textarea id="${model.fullColID}" name="${model.colName}"  style="height: 300px; width: 100%;">${model.value?if_exists}
</textarea>

<script>
        if(kindEditor){
        	kindEditor.remove();
        }
		kindEditor = KindEditor.create('#${model.fullColID}');
</script>
