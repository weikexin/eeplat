jQuery(document).ready(function($){
$('#roll_top').click(function(){$('html,body').animate({scrollTop: '0px'}, 800);}); 
$('#ct').click(function(){$('html,body').animate({scrollTop:$('#comments').offset().top}, 800);});
$('#fall').click(function(){$('html,body').animate({scrollTop:$('.footer_bottom').offset().top}, 800);});
});
(function() {

function $(id) {
	return document.getElementById(id);
}

function setStyleDisplay(id, status) {
	$(id).style.display = status;
}

function goTop(acceleration, time) {

	acceleration = acceleration || 0.1;
	time = time || 16;

	var dx = 0;
	var dy = 0;
	var bx = 0;
	var by = 0;
	var wx = 0;
	var wy = 0;

	if (document.documentElement) {
		dx = document.documentElement.scrollLeft || 0;
		dy = document.documentElement.scrollTop || 0;
	}
	if (document.body) {
		bx = document.body.scrollLeft || 0;
		by = document.body.scrollTop || 0;
	}
	var wx = window.scrollX || 0;
	var wy = window.scrollY || 0;

	var x = Math.max(wx, Math.max(bx, dx));
	var y = Math.max(wy, Math.max(by, dy));

	var speed = 1 + acceleration;
	window.scrollTo(Math.floor(x / speed), Math.floor(y / speed));
	if(x > 0 || y > 0) {
		var invokeFunction = "ZMJS.goTop(" + acceleration + ", " + time + ")"
		window.setTimeout(invokeFunction, time);
	}
}

function switchTab(showPanel, hidePanel, activeTab, activeClass, fadeTab, fadeClass) {
	$(activeTab).className = activeClass;
	$(fadeTab).className = fadeClass;
	setStyleDisplay(showPanel, 'block');
	setStyleDisplay(hidePanel, 'none');
}

window['ZMJS'] = {};
window['ZMJS']['$'] = $;
window['ZMJS']['setStyleDisplay'] = setStyleDisplay;
window['ZMJS']['goTop'] = goTop;
window['ZMJS']['switchTab'] = switchTab;

})();

function switchImage(imageId, imageUrl, linkId, linkUrl, preview, title, alt) {
	if(imageId && imageUrl) {
		var image = $(imageId);
		image.src = imageUrl;

		if(title) {
			image.title = title;
		}
		if(alt) {
			image.alt = alt;
		}
	}

	if(linkId && linkUrl) {
		var link = $(linkId);
		link.href = linkUrl;
	}
}