//休假或出差地点变更信息新增调用
function callServicModifycTemp(p) {
	if (p == null) {
		return;
	}
	if (p.btn) {
		if (p.btn.nodeName == 'A') {
			if (p.btn.flag) {
				alert("请不要重复点击！");
				return;
			}
			p.btn.flag = true;
		} else {
			p.btn.disabled = true;
		}
	}
	if (p.serviceUid == null && p.serviceName == null) {
		if (p.btn) {
			if (p.btn.nodeName == 'A') {
				p.btn.flag = false;
			} else {
				p.btn.disabled = false;
			}
		}
		return;
	}
	// 表单验证
	if (!validateModifycTemp(p.formName)) {
		if (p.btn) {
			if (p.btn.nodeName == 'A') {
				p.btn.flag = false;
			} else {
				p.btn.disabled = false;
			}
		}
		return;
	}

	// /支持pml的两种形式
	var pmlName = "";
	if (p.pml != null && p.pml.indexOf('mvccontroller') == -1
			&& p.pml.indexOf('.pml') == -1) {
		pmlName = p.pml;
		p.pml = globalURL + p.pml + ".pml?1=1";
	}

	// ///提示性问题
	if (p.echoJs != null && !eval(unescape(p.echoJs))) {
		if (p.btn) {
			if (p.btn.nodeName == 'A') {
				p.btn.flag = false;
			} else {
				p.btn.disabled = false;
			}
		}
		return;
	}

	// 初始化FckEditor值
	updateEditorFormValue();
	// 只要设置了formName，就从表单中获取

	var paras = "";
	if (p.paras) {
		paras = p.paras;
		if (p.formName != null && $.trim(p.formName) != "") {
			paras = paras + "&" + getParasOfForms(p.formName);
		}
	} else if (p.paras == null && p.formName != null
			&& $.trim(p.formName) != "") {
		paras = getParasOfForms(p.formName);
	}
	var callType = "us";
	if (p.callType) {
		callType = p.callType;
	}

	var callServStr = "";
	if (p.serviceUid) {
		callServStr = "contextServiceUid=" + p.serviceUid;
	} else {
		callServStr = "contextServiceName=" + p.serviceName;
	}

	paras = callServStr + "&callType=" + callType + "&" + urlCodeDeal(paras);

	$
			.post(
					globalService,
					paras,
					function(data, textStatus) {

						if (data != null && data.echo_msg != null
								&& $.trim(data.echo_msg) != '') {
							var errmsg = unescape(data.echo_msg);
							if (errmsg != 'success' && errmsg != 'null'
									&& errmsg != 'undefined') {
								alert(errmsg);
							}
						}

						if (p.pml != null) {

							if (p.target && $.trim(p.target) != "") {
								if (p.target == '_opener_window') {
									window
											.open(
													title,
													p.pml
															+ "&"
															+ urlCodeDeal(paras),
													'height=760,width=1012,left=0,top=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
								} else if (p.target == '_opener_location') {
									window.location = p.pml + "&"
											+ urlCodeDeal(paras)
											+ "&isApp=true";
								} else if (p.target == '_opener_tab') {
									createNewTab(pmlName, title, p.pml);
								} else if (p.target == '_opener') {
									popupDialog(pmlName, title, p.pml,
											p.pmlWidth, p.pmlHeight);
								} else {
									$("#" + p.target).empty().load(p.pml);
								}
							} else if (pmlName != "") {
								$("#" + pmlName).empty().load(p.pml);
							} else {
								alert("没有定义目标面板,请检查相关配置!");
							}
						}
						if (p.callback) {
							p.callback(data);
						}
						if (p.btn) {
							if (p.btn.nodeName == 'A') {
								p.btn.flag = false;
							} else {
								p.btn.disabled = false;
							}
						}
					}, "json");

}

// 休假或出差地点变更信息 更改调用
function callServicModifyCurrent(p) {
	if (p == null) {
		return;
	}
	if (p.btn) {
		if (p.btn.nodeName == 'A') {
			if (p.btn.flag) {
				alert("请不要重复点击！");
				return;
			}
			p.btn.flag = true;
		} else {
			p.btn.disabled = true;
		}
	}
	if (p.serviceUid == null && p.serviceName == null) {
		if (p.btn) {
			if (p.btn.nodeName == 'A') {
				p.btn.flag = false;
			} else {
				p.btn.disabled = false;
			}
		}
		return;
	}
	// 表单验证
	if (!validateModifyCurrent(p.formName)) {
		if (p.btn) {
			if (p.btn.nodeName == 'A') {
				p.btn.flag = false;
			} else {
				p.btn.disabled = false;
			}
		}
		return;
	}

	// /支持pml的两种形式
	var pmlName = "";
	if (p.pml != null && p.pml.indexOf('mvccontroller') == -1
			&& p.pml.indexOf('.pml') == -1) {
		pmlName = p.pml;
		p.pml = globalURL + p.pml + ".pml?1=1";
	}

	// ///提示性问题
	if (p.echoJs != null && !eval(unescape(p.echoJs))) {
		if (p.btn) {
			if (p.btn.nodeName == 'A') {
				p.btn.flag = false;
			} else {
				p.btn.disabled = false;
			}
		}
		return;
	}

	// 初始化FckEditor值
	updateEditorFormValue();
	// 只要设置了formName，就从表单中获取

	var paras = "";
	if (p.paras) {
		paras = p.paras;
		if (p.formName != null && $.trim(p.formName) != "") {
			paras = paras + "&" + getParasOfForms(p.formName);
		}
	} else if (p.paras == null && p.formName != null
			&& $.trim(p.formName) != "") {
		paras = getParasOfForms(p.formName);
	}
	var callType = "us";
	if (p.callType) {
		callType = p.callType;
	}

	var callServStr = "";
	if (p.serviceUid) {
		callServStr = "contextServiceUid=" + p.serviceUid;
	} else {
		callServStr = "contextServiceName=" + p.serviceName;
	}

	paras = callServStr + "&callType=" + callType + "&" + urlCodeDeal(paras);

	$
			.post(
					globalService,
					paras,
					function(data, textStatus) {

						if (data != null && data.echo_msg != null
								&& $.trim(data.echo_msg) != '') {
							var errmsg = unescape(data.echo_msg);
							if (errmsg != 'success' && errmsg != 'null'
									&& errmsg != 'undefined') {
								alert(errmsg);
							}
						}

						if (p.pml != null) {

							if (p.target && $.trim(p.target) != "") {
								if (p.target == '_opener_window') {
									window
											.open(
													title,
													p.pml
															+ "&"
															+ urlCodeDeal(paras),
													'height=760,width=1012,left=0,top=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
								} else if (p.target == '_opener_location') {
									window.location = p.pml + "&"
											+ urlCodeDeal(paras)
											+ "&isApp=true";
								} else if (p.target == '_opener_tab') {
									createNewTab(pmlName, title, p.pml);
								} else if (p.target == '_opener') {
									popupDialog(pmlName, title, p.pml,
											p.pmlWidth, p.pmlHeight);
								} else {
									$("#" + p.target).empty().load(p.pml);
								}
							} else if (pmlName != "") {
								$("#" + pmlName).empty().load(p.pml);
							} else {
								alert("没有定义目标面板,请检查相关配置!");
							}
						}
						if (p.callback) {
							p.callback(data);
						}
						if (p.btn) {
							if (p.btn.nodeName == 'A') {
								p.btn.flag = false;
							} else {
								p.btn.disabled = false;
							}
						}
					}, "json");

}

//新增验证
function validateModifycTemp(formName) {

	//上一次的类型值和到达时间值
	var lasttype = $("#lasttype").val();
	var lastendtime = $("#lastendtime").val();
	

	// 需要验证所有字段
	var modifytype = $("#leixingtype").val();
	var vacationtype = $("#vacationtype").val();
	var begincitytype = $("#beginaddress").val();
	var begincity = $("#begincity").val();
	var endcitytype = $("#endaddress").val();
	var endcity = $("#endcity").val();
	
	// 非当天的新增
	var modifytime = $("#gm_cw_modifycity_insert_tianxie_modifytime").val();
	var modifytime2 = $("#gm_cw_modifycity_insert_tianxie_modifytime2").val();
	
	// 当天的新增
	if (typeof modifytime == "undefined") {
		modifytime = $("#gm_cw_modifycity_insert_tianxie_today_modifytime")
				.val();
		modifytime2 = $("#gm_cw_modifycity_insert_tianxie_today_modifytime2")
				.val();
	}
	// 修改之新增
	if (typeof modifytime == "undefined") {
		modifytime = $("#gm_cw_modifycity_insert_xiugai_modifytime")
				.val();
		modifytime2 = $("#gm_cw_modifycity_insert_xiugai_modifytime2")
				.val();
	}
	
	/**
	 * 先验证选择类型是否正确:
	 * 
	 * 最先：上一次记录endtime不能为空
	 * 
	 * 上条记录为出差或中转:本次记录可能为中转(modifytype==2)、返回(modifytype==3)、休假出(modifytype==4)
	 * 上条记录为返回：这次则可能为出差(modifytype==1)、休假出(modifytype==4)
	 * 上条记录为休假出:本次记录可能为出差(modifytype==1)、中转(modifytype==2)、返回(modifytype==3)、休假归(modifytype==5)
	 * 上条记录为休假归:本次记录可能为出差(modifytype==1)、中转(modifytype==2)、返回(modifytype==3)、休假出(modifytype==4)
	 */
	
	if(lasttype != "" && lastendtime == "") {
		alert("上一条记录到达时间不能为空，请重新填写后再添加新的记录。");
		return false;
	}
	if(lasttype == 1 || lasttype == 2) {
		//本次记录可能为中转(modifytype==2)、返回(modifytype==3)、休假出(modifytype==4)
		if(modifytype == 1 || modifytype == 5) {
			alert("上一条记录类型为出差或中转，这次记录的类型不能为出差或者休假归，请重新选择。");
			return false;
		} 
	}
	
	if(lasttype == 3) {
		//这次则可能为出差(modifytype==1)、休假出(modifytype==4)
		if(modifytype == 2 || modifytype == 3 || modifytype == 5) {
			alert("上一条记录类型为返回，这次记录的类型不能为中转、返回或者是休假归，请重新选择。");
			return false;
		} 
	}
	
	if(lasttype == 4) {
		//本次记录可能为出差(modifytype==1)、中转(modifytype==2)、返回(modifytype==3)、休假归(modifytype==5)
		if(modifytype == 4) {
			alert("上一条记录类型为休假出，这次记录的类型不能为休假出，请重新选择。");
			return false;
		} 
	}
	
	if(lasttype == 5) {
		//本次记录可能为出差(modifytype==1)、中转(modifytype==2)、返回(modifytype==3)、休假出(modifytype==4)
		if(modifytype == 5) {
			alert("上一条记录类型为休假归，这次记录的类型不能为休假归，请重新选择。");
			return false;
		} 
	}
	
	/**
	 * 验证规则 
	 * 除休假外：两地点不能一样
	 */
	if(modifytype != 4 && modifytype != 5) {
		if(begincitytype == endcitytype && begincity == endcity) {
			alert("开始地点和到达地点相同，请重新填写");
			return false;
		}
	}
	
	
	/**
	 * 验证规则 
	 * 出差(modifytype=1)：到达地点不能为公司或本地(endcitytype != 1)
	 * 中转(modifytype=2)：开始地点和到达地点不能为公司或本地(endcitytype != 1)
	 * 返回(modifytype=4)：开始地点不能为公司或本地(begincitytype != 1)
	 */
	if (modifytype == 1) {
		if (endcitytype == 1) {
			alert("到达地点选择不正确，请重新选择");
			return false;
		}
	} else if(modifytype == 2) {
		if (begincitytype == 1) {
			alert("开始地点选择不正确，请重新选择");
			return false;
		} else if(endcitytype == 1) {
			alert("到达地点选择不正确，请重新选择");
			return false;
		} 
	} else if(modifytype == 3) {
		if (begincitytype == 1) {
			alert("开始地点选择不正确，请重新选择");
			return false;
		}
	}
	
	
	
	/**
	 * 
	 * 选择出差地时，选填写城市名称
	 * 
	 * 
	 */
	if (begincitytype != 1) {
		if ($.trim(begincity) == "") {
			alert("开始地点不能为空，请填写。");
			return false;
		}
	}
	if (endcitytype != 1) {
		if ($.trim(endcity) == "") {
			alert("到达地点不能为空，请填写。");
			return false;
		}
	}
	



	
	
	/**
	 * 
	 * 
	 * 时间填写验证：出发时间和到达时间不能同时为空； 出发时间不能小于上一次的到达时间，到达时间不能小于出发时间;
	 * 				到达时间不能大于下一条记录的开始时间
	 * 休假出：开始时间不能为空；休假归：到达时间不能为空
	 * 
	 */
	
	if($.trim(modifytime) == "" && $.trim(modifytime2) == "") {
		alert("出发时间和到达时间不能同时为空，请重新填写");
		return false;
	}
	
	if($.trim(lastendtime) != "") {
		if($.trim(modifytime) == "") {
			alert("开始时间不能为空，请重新填写");
			return false;
		}
		//出发时间不能小于上一次的到达时间
		if($.trim(modifytime) != "") {
			//1:01;01:1
			if($.trim(modifytime).length == 4) {
				if($.trim(modifytime).indexOf(":") == 1) {
					modifytime = "0"+modifytime;
				} else if($.trim(modifytime).indexOf(":") == 2) {
					var timestr = $.trim(modifytime).split(":");
					modifytime = timestr[0]+":0"+timestr[1];
				}
			} 
			//1:1
			else if($.trim(modifytime).length == 3) {
				if($.trim(modifytime).indexOf(":") == 1) {
					var timestr = $.trim(modifytime).split(":");
					modifytime = "0" + timestr[0]+":0"+timestr[1];
				}
			}
			
			if($.trim(lastendtime) > $.trim(modifytime)) {
				alert("出发时间不能小于上一次的到达时间，请重新填写");
				return false;
			}
		}
	}
	
	if($.trim(modifytime) != "" && $.trim(modifytime2) != "") {
		if($.trim(modifytime).length == 4) {
			if($.trim(modifytime).indexOf(":") == 1) {
				modifytime = "0"+modifytime;
			} else if($.trim(modifytime).indexOf(":") == 2) {
				var timestr = $.trim(modifytime).split(":");
				modifytime = timestr[0]+":0"+timestr[1];
			}
		} 
		//1:1
		else if($.trim(modifytime).length == 3) {
			if($.trim(modifytime).indexOf(":") == 1) {
				var timestr = $.trim(modifytime).split(":");
				modifytime = "0" + timestr[0]+":0"+timestr[1];
			}
		}
		
		if($.trim(modifytime2).length == 4) {
			if($.trim(modifytime2).indexOf(":") == 1) {
				modifytime2 = "0"+modifytime2;
			} else if($.trim(modifytime2).indexOf(":") == 2) {
				var timestr = $.trim(modifytime2).split(":");
				modifytime2 = timestr[0]+":0"+timestr[1];
			}
		} 
		//1:1
		else if($.trim(modifytime2).length == 3) {
			if($.trim(modifytime2).indexOf(":") == 1) {
				var timestr = $.trim(modifytime2).split(":");
				modifytime2 = "0" + timestr[0]+":0"+timestr[1];
			}
		}
		if($.trim(modifytime) >= $.trim(modifytime2)) {
			alert("到达时间须大于出发时间，请重新填写");
			return false;
		}
	}
	
	
	if(modifytype == 4 || modifytype == 5) {
		if(modifytype == 4 && $.trim(vacationtype) == "") {
			alert("休假类型不能为空，请填写");
			return false;
		} else if(modifytype == 4 && $.trim(modifytime) == "") {
			alert("休假出，开始时间不能为空，请填写");
			return false;
		} else if(modifytype == 5 && $.trim(modifytime2) == "") {
			alert("休假归，到达时间不能为空，请填写");
			return false;
		}
	}
	
	return true;
}


//修改验证
function validateModifyCurrent(formName) {

	//上一次的类型值和到达时间值
	var lasttype = $("#lasttype").val();
	var lastendtime = $("#lastendtime").val();
	//下一次的类型值和到达时间值
	var nexttype = $("#nexttype").val();
	var nextstarttime = $("#nextstarttime").val();
	

	//本次
	// 需要验证所有字段
	var modifytype = $("#leixingtype").val();
	var vacationtype = $("#vacationtype").val();
	var begincitytype = $("#beginaddress").val();
	var begincity = $("#begincity").val();
	var endcitytype = $("#endaddress").val();
	var endcity = $("#endcity").val();
	
	// 修改的修改
	var modifytime = $("#gm_cw_modifycity_update_tianxie_xiugai_modifytime").val();
	var modifytime2 = $("#gm_cw_modifycity_update_tianxie_xiugai_modifytime2").val();
	// 非当天的修改
	if (typeof modifytime == "undefined") {
		modifytime = $("#gm_cw_modifycity_update_tianxie_modifytime").val();
		modifytime2 = $("#gm_cw_modifycity_update_tianxie_modifytime2").val();
	}
	
	// 当天的修改
	if (typeof modifytime == "undefined") {
		modifytime = $("#gm_cw_modifycity_update_tianxie_today_modifytime")
				.val();
		modifytime2 = $("#gm_cw_modifycity_update_tianxie_today_modifytime2")
				.val();
	}
	
	/**
	 * 先验证选择类型是否正确:
	 * 
	 * 最先：上一次记录endtime不能为空
	 * 
	 * 上条记录为出差或中转:本次记录可能为中转(modifytype==2)、返回(modifytype==3)、休假出(modifytype==4)
	 * 上条记录为返回：这次则可能为出差(modifytype==1)、休假出(modifytype==4)
	 * 上条记录为休假出:本次记录可能为出差(modifytype==1)、中转(modifytype==2)、返回(modifytype==3)、休假归(modifytype==5)
	 * 上条记录为休假归:本次记录可能为出差(modifytype==1)、中转(modifytype==2)、返回(modifytype==3)、休假出(modifytype==4)
	 * 
	 * 下条记录为出差:本次记录可能为返回(modifytype==3)、休假出(modifytype==4)、休假归(modifytype==5)
	 * 下条记录为中转:本次记录可能为出差(modifytype==1)、中转(modifytype==2)、休假出(modifytype==4)、休假归(modifytype==5)
	 * 下条记录为返回：这次则可能为出差(modifytype==1)、中转(modifytype==2)、休假出(modifytype==4)、休假归(modifytype==5)
	 * 下条记录为休假出:本次记录可能为出差(modifytype==1)、中转(modifytype==2)、返回(modifytype==3)、休假归(modifytype==5)
	 * 下条记录为休假归:本次记录可能为休假出(modifytype==4)
	 */
	if(lasttype != "" && lastendtime == "") {
		alert("上一条记录到达时间不能为空，请重新填写后再添加新的记录。");
		return false;
	}
	if(nexttype != "" && modifytime2 == "") {
		alert("存在下一条记录，该次记录到达时间不能为空，请重新填写。");
		return false;
	}
	
	//上一条记录
	if(lasttype == 1 || lasttype == 2) {
		//本次记录可能为中转(modifytype==2)、返回(modifytype==3)、休假出(modifytype==4)
		if(modifytype == 1 || modifytype == 5) {
			alert("上一条记录类型为出差或中转，这次记录的类型不能为出差或者休假归，请重新选择。");
			return false;
		} 
	} else if(lasttype == 3) {
		//这次则可能为出差(modifytype==1)、休假出(modifytype==4)
		if(modifytype == 2 || modifytype == 3 || modifytype == 5) {
			alert("上一条记录类型为返回，这次记录的类型不能为中转、返回或者是休假归，请重新选择。");
			return false;
		} 
	} else if(lasttype == 4) {
		//本次记录可能为出差(modifytype==1)、中转(modifytype==2)、返回(modifytype==3)、休假归(modifytype==5)
		if(modifytype == 4) {
			alert("上一条记录类型为休假出，这次记录的类型不能为休假出，请重新选择。");
			return false;
		} 
	} else if(lasttype == 5) {
		//本次记录可能为出差(modifytype==1)、中转(modifytype==2)、返回(modifytype==3)、休假出(modifytype==4)
		if(modifytype == 5) {
			alert("上一条记录类型为休假归，这次记录的类型不能为休假归，请重新选择。");
			return false;
		} 
	}

	//下一条记录
	if(nexttype == 1) {
		//本次记录可能为返回(modifytype==2)、休假出(modifytype==4)、休假归(modifytype==5)
		if(modifytype == 1 || modifytype == 2) {
			alert("下一条记录类型为出差，这次记录的类型不能为出差或者中转，请重新选择。");
			return false;
		} 
	} else if(nexttype == 2) {
		//本次记录可能为出差(modifytype==1)、中转(modifytype==2)、休假出(modifytype==4)、休假归(modifytype==5)
		if(modifytype == 3) {
			alert("下一条记录类型为中转，这次记录的类型不能为返回，请重新选择。");
			return false;
		} 
	} else if(nexttype == 3) {
		//这次则可能为出差(modifytype==1)、中转(modifytype==2)、休假出(modifytype==4)、休假归(modifytype==5)
		if(modifytype == 3 ) {
			alert("下一条记录类型为返回，这次记录的类型不能为返回，请重新选择。");
			return false;
		} 
	} else if(nexttype == 4) {
		//本次记录可能为出差(modifytype==1)、中转(modifytype==2)、返回(modifytype==3)、休假归(modifytype==5)
		if(modifytype == 4) {
			alert("下一条记录类型为休假出，这次记录的类型不能为休假出，请重新选择。");
			return false;
		} 
	} else if(nexttype == 5) {
		//本次记录可能为出差(modifytype==1)、中转(modifytype==2)、返回(modifytype==3)、休假出(modifytype==4)
		if(modifytype == 5) {
			alert("下一条记录类型为休假归，这次记录的类型不能为休假归，请重新选择。");
			return false;
		} 
	}
	
	
	/**
	 * 验证规则 
	 * 除休假外：两地点不能一样
	 */
	if(modifytype != 4 && modifytype != 5) {
		if(begincitytype == endcitytype && begincity == endcity) {
			alert("开始地点和到达地点相同，请重新填写");
			return false;
		}
	}
	
	
	/**
	 * 验证规则 
	 * 出差(modifytype=1)：到达地点不能为公司或本地(endcitytype != 1)
	 * 中转(modifytype=2)：开始地点和到达地点不能为公司或本地(endcitytype != 1)
	 * 返回(modifytype=4)：开始地点不能为公司或本地(begincitytype != 1)
	 */
	if (modifytype == 1) {
		if (endcitytype == 1) {
			alert("到达地点选择不正确，请重新选择");
			return false;
		}
	} else if(modifytype == 2) {
		if (begincitytype == 1) {
			alert("开始地点选择不正确，请重新选择");
			return false;
		} else if(endcitytype == 1) {
			alert("到达地点选择不正确，请重新选择");
			return false;
		} 
	} else if(modifytype == 3) {
		if (begincitytype == 1) {
			alert("开始地点选择不正确，请重新选择");
			return false;
		}
	}
	
	
	
	/**
	 * 
	 * 选择出差地时，选填写城市名称
	 * 
	 * 
	 */
	if (begincitytype != 1) {
		if ($.trim(begincity) == "") {
			alert("开始地点不能为空，请填写。");
			return false;
		}
	}
	if (endcitytype != 1) {
		if ($.trim(endcity) == "") {
			alert("到达地点不能为空，请填写。");
			return false;
		}
	}
	



	
	
	/**
	 * 
	 * 时间填写验证：出发时间和到达时间不能同时为空；出发时间不能小于上一次的到达时间，到达时间不能小于出发时间
	 * 				到达时间不能大于下一条记录的开始时间
	 * 
	 *注意：本条记录不是第一条记录，否则不合适，故需排除：限制到达时间不等于开始时间即可，服务不用更改
	 * 
	 */
	
	if($.trim(modifytime) == "" && $.trim(modifytime2) == "") {
		alert("出发时间和到达时间不能同时为空，请重新填写");
		return false;
	}
	
	if($.trim(lastendtime) != "") {
		if($.trim(modifytime) == "") {
			alert("开始时间不能为空，请重新填写");
			return false;
		}
		//出发时间不能小于上一次的到达时间
		if($.trim(modifytime) != "") {
			//1:01;01:1
			if($.trim(modifytime).length == 4) {
				if($.trim(modifytime).indexOf(":") == 1) {
					modifytime = "0"+modifytime;
				} else if($.trim(modifytime).indexOf(":") == 2) {
					var timestr = $.trim(modifytime).split(":");
					modifytime = timestr[0]+":0"+timestr[1];
				}
			} 
			//1:1
			else if($.trim(modifytime).length == 3) {
				if($.trim(modifytime).indexOf(":") == 1) {
					var timestr = $.trim(modifytime).split(":");
					modifytime = "0" + timestr[0]+":0"+timestr[1];
				}
			}
			
			if($.trim(lastendtime) > $.trim(modifytime)) {
				alert("出发时间不能小于上一次的到达时间，请重新填写");
				return false;
			}
		}
	}
	
	if($.trim(modifytime) != "" && $.trim(modifytime2) != "") {
		if($.trim(modifytime).length == 4) {
			if($.trim(modifytime).indexOf(":") == 1) {
				modifytime = "0"+modifytime;
			} else if($.trim(modifytime).indexOf(":") == 2) {
				var timestr = $.trim(modifytime).split(":");
				modifytime = timestr[0]+":0"+timestr[1];
			}
		} 
		//1:1
		else if($.trim(modifytime).length == 3) {
			if($.trim(modifytime).indexOf(":") == 1) {
				var timestr = $.trim(modifytime).split(":");
				modifytime = "0" + timestr[0]+":0"+timestr[1];
			}
		}
		
		if($.trim(modifytime2).length == 4) {
			if($.trim(modifytime2).indexOf(":") == 1) {
				modifytime2 = "0"+modifytime2;
			} else if($.trim(modifytime2).indexOf(":") == 2) {
				var timestr = $.trim(modifytime2).split(":");
				modifytime2 = timestr[0]+":0"+timestr[1];
			}
		} 
		//1:1
		else if($.trim(modifytime2).length == 3) {
			if($.trim(modifytime2).indexOf(":") == 1) {
				var timestr = $.trim(modifytime2).split(":");
				modifytime2 = "0" + timestr[0]+":0"+timestr[1];
			}
		}
		if($.trim(modifytime) >= $.trim(modifytime2)) {
			alert("到达时间须大于出发时间，请重新填写");
			return false;
		}
		
	}
	
	
	if($.trim(nextstarttime) != "") {
		if($.trim(modifytime2) == "") {
			alert("存在下一条记录，该次记录到达时间不能为空，请重新填写");
			return false;
		}
		//出发时间不能小于上一次的到达时间
		if($.trim(modifytime2) != "") {
			//1:01;01:1
			if($.trim(modifytime2).length == 4) {
				if($.trim(modifytime2).indexOf(":") == 1) {
					modifytime2 = "0"+modifytime2;
				} else if($.trim(modifytime2).indexOf(":") == 2) {
					var timestr = $.trim(modifytime2).split(":");
					modifytime2 = timestr[0]+":0"+timestr[1];
				}
			} 
			//1:1
			else if($.trim(modifytime2).length == 3) {
				if($.trim(modifytime2).indexOf(":") == 1) {
					var timestr = $.trim(modifytime2).split(":");
					modifytime2 = "0" + timestr[0]+":0"+timestr[1];
				}
			}
			if($.trim(nextstarttime) < $.trim(modifytime2)) {
				alert("到达时间不能大于下一条记录的开始时间，请重新填写");
				return false;
			}
		}
	}
	
	if(modifytype == 4 || modifytype == 5) {
		if(modifytype == 4 && $.trim(vacationtype) == "") {
			alert("休假类型不能为空，请填写");
			return false;
		} else if(modifytype == 4 && $.trim(modifytime) == "") {
			alert("休假出，开始时间不能为空，请填写");
			return false;
		} else if(modifytype == 5 && $.trim(modifytime2) == "") {
			alert("休假归，到达时间不能为空，请填写");
			return false;
		}
	}
	
	return true;
}
