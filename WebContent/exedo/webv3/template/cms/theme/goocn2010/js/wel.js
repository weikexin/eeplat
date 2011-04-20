var sweetTitles = {
	x : 10,								
	y : 20,								
	tipElements : "a",	    			
	init : function() {
		$(this.tipElements).mouseover(function(e){
			this.myTitle = this.title;
			this.myHref = this.href;
			this.myHref = (this.myHref.length > 30 ? this.myHref.toString().substring(0,30)+"..." : this.myHref);       // url 超过 30 个字符的部分用 ... 代替
			this.title = "";
			var tooltip = "<div id='tooltip'><p>"+this.myTitle+"<em>"+this.myHref+"</em>"+"</p></div>";
			$('body').append(tooltip);
			$('#tooltip')
				.css({
					"opacity":"0.8",                   // 0.8 为透明度可自行根据喜好调整数字
					"top":(e.pageY+20)+"px",
					"left":(e.pageX+10)+"px"
				}).show('fast');	
		}).mouseout(function(){
			this.title = this.myTitle;
			$('#tooltip').remove();
		}).mousemove(function(e){
			$('#tooltip')
			.css({
				"top":(e.pageY+20)+"px",
				"left":(e.pageX+10)+"px"
			});
		});
	}
};
$(function(){
	sweetTitles.init();
});