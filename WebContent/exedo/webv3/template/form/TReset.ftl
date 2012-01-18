<input type="button"  style="${model.style?if_exists}"  id='${model.objUid}' value="&nbsp;${model.l10n}&nbsp;" class='ctlBtn'>

<script>
$('#${model.objUid}').bind('click',function(){
    $('#${model.targetForms}')[0].reset();
  }
);
</script>