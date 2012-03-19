<#include "TFormBase.ftl">

 <input type="text"  id='${model.objUid}'   />

 <script>

 			var url = globalURL + "servicecontroller?contextServiceName=${model.linkService.name}&callType=ss";

			$.post(url,function(result){
				   var ret = result;

				   if(ret!=null && ret.items!=null && ret.items.length>0){
				   
					     $("#${model.objUid}").autocomplete(ret.items, {
								minChars: 0,
								max: 12,
								autoFill: true,
								mustMatch: true,
								matchContains: false,
								scrollHeight: 220,
								formatItem: function(row, i, max) {
									return i + "/" + max + ": \"" + row.name + "\" [" + row.objuid + "]";
								},
								formatMatch: function(row, i, max) {
									return row.name + " " + row.objuid;
								},
								formatResult: function(row) {
									return row.name;
								}
						});
					}	
			   });
 
 


 
 </script>
