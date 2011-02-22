// 滑动特效
$(function() {
	// featured window effect
	$("#featured .item").hover(function(){
		$(this).find(".boxCaption").stop().animate({
			top:0
		}, 150);
		}, function(){
		$(this).find(".boxCaption").stop().animate({
			top:160
		}, 600);
	});
	
	// tab
	var tabContainers = $('div.tabs').find('div.tabitem');
	var tabnavs = $('div.tabs').find('ul.tabnav li');
	tabContainers.css('display','block').fadeOut(0);
	tabContainers.filter(':first').css('display','block');
	tabnavs.filter(':first').find('.arrow-down').css('display','block');
                        
	$('.tabs ul.tabnav a').click(function () {
		if($(this).attr('class') != 'selected') {
			tabContainers.hide();
			tabnavs.find('.arrow-down').hide();
			$(this).parent().find('span').show();
			tabContainers.filter(this.hash).show();
			$('.tabs ul.tabnav a').removeClass('selected');
			$(this).addClass('selected');
		}
		return false;
	});
});