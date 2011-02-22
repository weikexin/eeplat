$(function() {
	$('.related_thumbnail img').animate({"opacity": .5 });

	$('.related_thumbnail img').hover(function() {
		$(this).stop().animate({ "opacity": 1 });
	}, function() {
		$(this).stop().animate({ "opacity": .5 });
	});
});
