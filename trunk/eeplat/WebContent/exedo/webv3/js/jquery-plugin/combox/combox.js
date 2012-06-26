(function( $ ) {
		$.widget( "ui.combobox", {
			_create: function() {
				var input,
					self = this,
					select = this.element,
					serviceName = select.attr("serviceName"),
					value = select.val(),
					theLabel = select.attr('tlabel'),
					wrapper = $( "<span>" )
						.addClass( "ui-combobox" )
						.insertAfter( select );
					
				console.log("serviceName:::" + serviceName);
				if(theLabel==null){
					theLabel = "";
				}



				input = $( "<input>" )
					.appendTo( wrapper )
					.val( theLabel )
					.addClass( "ui-state-default" )
					.autocomplete({
						delay: 0,
						minLength: 0,
						source: function( request, response ) {
							$.ajax({
								url: globalURL + "servicecontroller?contextServiceName=" + serviceName +"&callType=ss",// can pinyin
								success: function( data ) {
									
									var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
									
									response( $.map( data.items, function( item ) {
										
										if ( ( !request.term || matcher.test(item.name) ) )
											return {
												label: item.name.replace(
													new RegExp(
														"(?![^&;]+;)(?!<[^<>]*)(" +
														$.ui.autocomplete.escapeRegex(request.term) +
														")(?![^<>]*>)(?![^&;]+;)", "gi"
													), "<strong>$1</strong>" ),
												value: item.name,
												tId: item.objuid
											};
//										return {
//											label: item.name,
//											value: item.objuid
//										}
									}
									));
								}
							});
						},
						select: function( event, ui ) {
//							console.log( ui.item ?
//								"Selected: " + ui.item.label :
//								"Nothing selected, input was " + this.value);
							//$(this).val(ui.item.label);
							console.log("The ID::::" + ui.item.tId);
							
							select.val(ui.item.tId);
							//return false;
						}
//						,
//						search: function( event, ui ) {
////							console.log( ui.item ?
////								"Selected: " + ui.item.label :
////								"Nothing selected, input was " + this.value);
//							//$(this).val(ui.item.label);
//							console.log("The label::::" + ui.item);
//						}

						
						
					})
					.addClass( "ui-widget ui-widget-content ui-corner-left" );

				input.data( "autocomplete" )._renderItem = function( ul, item ) {
					return $( "<li></li>" )
						.data( "item.autocomplete", item )
						.append( "<a>" + item.label + "</a>" )
						.appendTo( ul );
				};

				$( "<a>" )
					.attr( "tabIndex", -1 )
					.attr( "title", "Show All Items" )
					.appendTo( wrapper )
					.button({
						icons: {
							primary: "ui-icon-triangle-1-s"
						},
						text: false
					})
					.removeClass( "ui-corner-all" )
					.addClass( "ui-corner-right ui-button-icon" )
					.click(function() {
						// close if already visible
						if ( input.autocomplete( "widget" ).is( ":visible" ) ) {
							input.autocomplete( "close" );
							return;
						}

						// work around a bug (likely same cause as #5265)
						$( this ).blur();

						// pass empty string as value to search for, displaying all results
						input.autocomplete( "search", "" );
						input.focus();
					});
			},

			destroy: function() {
				this.wrapper.remove();
				this.element.show();
				$.Widget.prototype.destroy.call( this );
			}
		});
	})( jQuery );
