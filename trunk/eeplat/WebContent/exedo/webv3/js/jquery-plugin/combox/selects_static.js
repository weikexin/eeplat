function createStaticDmLayer(obj,dataStr,configClearOtherUid){


    objGlobals = obj;
	// sc_page_size(每页多少条),sc_page_no(第几页)。

	/////联动菜单清楚其它的

	if(configClearOtherUid!=null){
	
	  var clears = configClearOtherUid.split(",");
	  for(i = 0; i < clears.length; i++){
	      var clearOtherUid = clears[i];
	
	      if(clearOtherUid==""){
	      	continue;
	      }
	      
		  if( $(clearOtherUid)!=null){
	  	  	$(clearOtherUid).value = "";
	  	  }
	  	  if($(clearOtherUid)!=null && $(clearOtherUid).previousSibling!=null){
		  	$(clearOtherUid).previousSibling.value = "";
		  }
	  }
	}


	   	eval("var ret =" + dataStr);
		if(ret!=null && ret.items!=null && ret.items.length>0){
		
			
		    var popHeight = 250;
		    var height = 20*(ret.items.length+2) + 15;
		    if(popHeight > height){
		    	popHeight = height;
		    }
		    
		    $("#dmLayer").css("height",popHeight);
		    setTip(obj,popHeight);

  			var dmLayer = "";
			dmLayer = dmLayer + '<table class="dmBody" style="margin-left:5px;margin-top:5px;width:95%;font-size:9pt;cursor:pointer"   border="0" cellpadding="0" cellspacing="0" >';
			
			//输出一个空行
			dmLayer = dmLayer + '<tr height="20px"  codeID="" ><td style="padding: 1px;" ></td></tr>';
			//输出具体数据
			for(var i = 0 ;  i < ret.items.length; i++){
	 		    var content = ret.items[i];
	 		   	dmLayer = dmLayer + '<tr height="20px" codeID="'
	 		   	+ content.objuid +
	 		   	'">'
				+' <td style="padding: 1px;" title="' 
				  + content.name   +  '">'+
				content.name 
				+'</td> </tr> ';
			}
			dmLayer = dmLayer + '</table>';

			$("#dmLayer").empty().append(dmLayer);
			$(".dmBody tr").bind('click',selInputValue)
 		                 .bind('mouseover',function(){$(this).find("td").addClass("dmLayerMouseOverCss")})
 		                 .bind('mouseout',function(){$(this).find("td").removeClass("dmLayerMouseOverCss")});
 		    $(".dmBody tr:even").addClass("dmLayerEven"); 

//			mOver($("#dmBody")[0].firstChild.firstChild);
		
	   }else{
			$("#dmLayer").empty().append("&nbsp;&nbsp;&nbsp;&nbsp;没有记录!").show();
	   }
	   
	   $(".dmBody tr:even").css("background","#e6EEEE"); 
//		$('.tablesorter tbody  tr').bind('mouseover',function(){
//			$(this).addClass("mover");
//		}).bind('mouseout',function(){
//			$('.tablesorter tbody  tr').removeClass("mover");
//		});

}



function invokeStaticPopup(obj,dataStr,clearOtherUid){
   
   
	if($("#dmLayer").css("display")=="none"){
		  if(obj!=null){  
		  	obj = obj.previousSibling;
		  }
		  var t = $(obj);
		  $("#dmLayer").css("top", t.offset().top + t.height()+1).css("left",t.offset().left+1);
 	      $("#dmLayer").empty().append("<font color='red'>正在加载.............</font>").show();
		  createStaticDmLayer(obj,dataStr,clearOtherUid);
		  $(document).bind("mouseover",popupHide);
	}else{
		$("#dmLayer").hide(); 	
	}

  
}
















