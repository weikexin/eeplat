    
var $jx = jQuery.noConflict(); 
	$jx(function() {
        $jx("#A").lavaLamp({
        fx: "backout", 
        speed: 700,
        click: function(event, menuItem) {
        return true;
            }
           });
        });
