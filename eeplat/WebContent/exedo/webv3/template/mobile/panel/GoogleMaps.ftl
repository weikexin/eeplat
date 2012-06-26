<div data-role="page"  id="${model.name}" name="${model.name}">
	<div data-role="header" data-theme="b">
		<a href="#" data-icon="arrow-l" data-rel="back"  class="ui-btn-left">Back</a>
		<h1>Find the Place</h1>
		<a href="javascript:window.location='${app_index}'" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">Home</a>
	</div>
  <div data-role="content">
 	  	<div class="ui-bar-c ui-corner-all ui-shadow" style="padding:1em;">
					<div id="map_canvas" style="height:350px;"></div>
		</div>
 	</div> 
 	<div  data-role="footer" data-theme="b">
 	    <a  href="javascript:window.location='http://192.168.2.74/eeplat/PM_GoogleMaps.pml'" >Reload manually</a>
 	</div>  
</div>

<script type="text/javascript">

		
         
			var mobileDemo = { 'center': '39.9016, 116.3517', 'zoom': 10 };
			$('#map_canvas').gmap({'center': mobileDemo.center, 
			'zoom': mobileDemo.zoom, 'disableDefaultUI':true, 'callback': function(map) {
			}});
					
</script>