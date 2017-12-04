/**
 * util.js created by wu.lin 2013-11-20
 */
//var _projectRoot = "fable-itsm-bpmconsole-war";
//var _productName = "fable-itsm-bpmconsole-war";
//var _dTreeImgPath = "fable-itsm-bpmconsole-war/js/permission/dTree/";
var file_Server_Path = "";

/**
 * 动态获取文件服务器地址
 * @author zhengz
 */
function getFileServerPath(){
	var path = getRootName();
	var uri = path + "/commonFileUpload/getFileServerPath";
	if("" === file_Server_Path){
		var ajax_param={
			url : uri,
			type : "post",
			datdType : "json",
			async : true,
			error : function() {
				$.messager.alert("错误", "ajax_error", "error");
			},
			success : function(data) {
				file_Server_Path = data;
			}
		};
		ajax_(ajax_param);
	}
}
/**
 * IE8下String 转 Date
 * @author liy
 */
function parseISO8601(dateStringInRange) {
    var isoExp = /^\s*(\d{4})-(\d\d)-(\d\d)\s*$/,
        date = new Date(NaN), month,
        parts = isoExp.exec(dateStringInRange);

    if(parts) {
      month = +parts[2];
      date.setFullYear(parts[1], month - 1, parts[3]);
      if(month != date.getMonth() + 1) {
        date.setTime(NaN);
      }
    }
    return date;
  }
/**
 * 产生随机数
 * 
 * @author wul
 * @param number
 *            是定数目以内产生随机数
 */
function rand(number) {
	rnd.today = new Date();
	rnd.seed = rnd.today.getTime();
	function rnd() {
		rnd.seed = (rnd.seed * 9301 + 49297) % 233280;
		return rnd.seed / (233280.0);
	}
	;
	var s = Math.ceil(rnd() * number);
	return s;
};

function updateFile(url, eleId, fileDir, callback, previousFileName) {
	var uploadfile = $("#" + eleId).val();
	if (uploadfile != '') {
		url = url + "?fileDir=" + fileDir + "";
		$.ajaxFileUpload({
			url : url,
			secureuri : false,
			fileElementId : eleId,
			dataType : 'json',
			success : function(data, status) {
				var dataJson = data.msg;
				dataJson = eval("(" + dataJson + ")");
				// var filepath = dataJson.filePath;
				var _fileName = fileDir + '/' + dataJson.fileName;
				callback(_fileName);
			},
			error : function(data, status, e) {
				$.messager.alert("错误", "上传失败!", "error");
			}
		});
	} else {
		callback($("#" + previousFileName).val());
	}
}

/**
 * 图片上传预览
 * 
 * @author wul
 * @param imgFile
 *            上传的图片
 */
var _fileType = "";
function PreviewImage(imgFile,imgPath,imgDiv) {
	if(imgFile.value) {
		var filextension = imgFile.value.substring(imgFile.value.lastIndexOf("."),
			imgFile.value.length);
		_fileType = filextension;
		filextension = filextension.toLowerCase();
		if ((filextension != '.jpg') && (filextension != '.gif')
				&& (filextension != '.jpeg') && (filextension != '.png')
				&& (filextension != '.bmp')) {
			$.messager.alert("提示", "对不起，系统仅支持标准格式的照片，请您调整格式后重新上传，谢谢 !", "info");
			imgFile.focus();
		} else {
			var path;
			if (document.all)// IE
			{
				imgFile.select();
				path = document.selection.createRange().text;
				document.getElementById(imgDiv).innerHTML = "";
			} else// FF
			{
				path = window.URL.createObjectURL(imgFile.files[0]);// FF 7.0以上
				// path = imgFile.files[0].getAsDataURL();// FF 3.0
				// document.getElementById("img1").src = path;
			}
		}
	}
	
	
	if(document.all) {
		// IE
		if(!imgFile.value && imgPath) {
			document.getElementById(imgDiv).style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
					+ imgPath + "\")";// 使用滤镜效果
		}
		else {
			document.getElementById(imgDiv).style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
					+ path + "\")";// 使用滤镜效果
		}
	}
	else {
		// FF
		if(!imgFile.value && imgPath) {
			document.getElementById(imgDiv).innerHTML = "<img class='img1' width='180px' height='90px' src='"
					+ imgPath + "'/>";
		}
		else {
			document.getElementById(imgDiv).innerHTML = "<img class='img1' width='180px' height='90px' src='"
					+ path + "'/>";
		}
	}
	
}

/**
 * 验证带小数点的数字
 * 
 * @author wul
 * @param num
 *            验证的数字
 */
function numberCheck(num, message) {
	var re = /^\d+(\.\d+)?$/;
	var chkInfo = re.exec(num) != null;
	if (!chkInfo) {
		$.messager.alert("提示", message, "error");
	}
	return chkInfo;
}

/**
 * 验证输入是否为中文
 * 
 * @author zyp
 * @param wordStr
 *            输入的字符串
 * 
 * 
 * function isChn(wordStr){ var reg = /^[u4E00-u9FA5]+$/; if(reg.test(wordStr)){
 * $.messager.alert("提示","只能输入汉字！！","error"); } return true; }
 * 
 * /** 验证输入是否只能为英文和数字
 * 
 * @author zyp
 * @param str
 *            输入的字符串
 */

/**
 * function isEnAndNum(str){ var re; re = /[a-zA-Z0-9]{6,16}/; if (re.test(str)) {
 * return true; //匹配 }else { $.messager.alert("提示","只能输入英文或者数字！！","error");
 * //不匹配 } }
 * 
 * /** 检查输入的起止日期是否正确，规则为两个日期的格式正确，且结束如期>=起始日期
 * 
 * @author zyp
 * @param startDate：起始日期，字符串
 *            endDate：结束如期，字符串
 */

/**
 * function checkTwoDate( startDate,endDate ) { if( !isDate(startDate) ) {
 * $.messager.alert("提示","起始日期不正确!","error"); return false; } else if(
 * !isDate(endDate) ) { $.messager.alert("提示","终止日期不正确!","error"); return false; }
 * else if( startDate > endDate ) {
 * $.messager.alert("提示","起始日期不能大于终止日期!","error"); return false; } return true; }
 */

/**
 * function checkValues(value){ var checkValue = value; var email =
 * $('email').val(); var phoneNum = $('phoneNum').val(); var wStr =
 * $('wStr').val(); var emailRe =
 * /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
 * var phoneNumRe = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/; var
 * wStrRe = /^[u4E00-u9FA5]+$/; var nikeName =
 * /^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]{6,18}$/; switch(checkValue){ case email:
 * if(!emailRe.test(email)){ $.messager.alert("提示信息","请输入正确的邮箱格式!","error");
 * return; }break; case phoneNum: if(!phoneNumRe.exec(phoneNum)){
 * $.messager.alert("提示信息","请输入正确的手机号码!","error"); return; }break; case wStr:
 * if(!wStrRe.test(wStr)){ $.messager.alert("提示信息","只能输入汉字!","error"); return;
 * }break; } }
 */

/**
 * 公用验证表单方法
 * 
 * @author wul
 * @param checkControlAttr
 *            控件数组
 * @param checkControlAttr
 *            提示信息数组
 */
function checkFormCommon(checkControlAttr, checkMessagerAttr) {
	for (var i = 0; i < checkControlAttr.length; i++) {
		var ctlVal = $("#" + checkControlAttr[i]).val();
		ctlVal = ctlVal.Trim();
		if ('' == ctlVal || ctlVal.length <= 0) {
			$.messager.alert("提示", checkMessagerAttr[i], "info", function(e) {
				$("#" + checkControlAttr[i]).focus();
			});
			return false;
		}
	}
	return true;
}

/**
 * 重新加载表格
 * 
 * @author wul
 * @param dataGridId
 */
function reloadTableCommon(dataGridId) {
	$('#' + dataGridId).datagrid('load');
	$('#' + dataGridId).datagrid('clearSelections');
}

/**
 * 去除用户选择的lable和img
 * 
 * @author wul
 * @param obj
 *            控件obj
 * @param prefixname
 *            控件id
 */
function removebnlables(obj, controlId) {
	// 去除控件
	$("[name='" + obj.name + "']").remove();
	_u_sel_auto_val = _u_sel_auto_val.replace(obj.name + ",", "");
}

/**
 * 模糊匹配单选效果
 * 
 * @author wul
 * @param uSelValTemp
 *            用户选择的数值
 * @param $input
 *            控件
 */
function autoEventClickCommonRadio(uSelValTemp, $input) {
	var uSelValArr = uSelValTemp.split(":");
	var uSelVal = uSelValArr[0];
	var uSelId = uSelValArr[1];
	$input.val(uSelVal);
	_u_sel_auto_val = uSelId;
}

/**
 * 模糊匹配多选效果
 * 
 * @author wul
 * @param uSelValTemp
 *            用户选择的数值
 * @param $input
 *            控件
 */
function autoEventClickCommonMultiple(uSelValTemp, controlId, $input) {
	$input.val('');
	var path = getRootPatch();
	var uSelValArr = uSelValTemp.split(":");
	var uSelVal = uSelValArr[0];
	var uSelId = uSelValArr[1];
	if (_u_sel_auto_val.indexOf(uSelId) == -1) {
		var lbl_bn = $("<label name='" + uSelId + "'>  " + uSelVal
				+ "  </label>");

		var _imgclose = $("<img></img>");
		$(_imgclose).attr({
			"title" : uSelVal,
			"name" : uSelId,
			"src" : "" + path + "/style/images/close.gif"
		});

		$(_imgclose).click(function() {
			removebnlables(this);
		});
		$("#" + controlId).append(lbl_bn);
		$("#" + controlId).append(_imgclose);
		_u_sel_auto_val += uSelId + ",";
	}
}

/**
 * 模糊匹配点击事件
 * 
 * @author wul
 * @param uSelValTemp
 *            用户选择的数值
 * @param controlId
 *            控件ID
 */
function autoEventClickCommon(uSelValTemp, controlId) {
	var path = getRootPatch();
	var uSelValArr = uSelValTemp.split(":");
	var uSelVal = uSelValArr[0];
	var uSelId = uSelValArr[1];
	if (_u_sel_auto_val.indexOf(uSelId) == -1) {
		var lbl_bn = $("<label name='" + uSelId + "'>  " + uSelVal
				+ "  </label>");

		var _imgclose = $("<img></img>");
		$(_imgclose).attr({
			"title" : uSelVal,
			"name" : uSelId,
			"src" : "" + path + "/style/images/close.gif"
		});

		$(_imgclose).click(function() {
			removebnlables(this);
		});
		$("#" + controlId).append(lbl_bn);
		$("#" + controlId).append(_imgclose);
		_u_sel_auto_val += uSelId + ",";
	}
}

function doEditChkbox(control) {
	var controlChk = $(control).is(':checked');
	var controlId = $(control).attr('id');
	var controlAlt = $(control).attr('alt');
	$("[alt=" + controlId + "]").click();
	$("[alt=" + controlId + "]").attr("checked", controlChk);
	if (controlChk) {
		$("#" + controlAlt + "").attr("checked", controlChk);
	} else {
		var controlStatus = false;
		$("[alt=" + controlAlt + "]").each(function(i, val) {
			var controlChkTemp = $(val).is(':checked');
			if (controlChkTemp) {
				controlStatus = true;
			}
		});
		$("#" + controlAlt + "").attr("checked", controlStatus);
	}
}

function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {
		XMLHttpR = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {
			XMLHttpR = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpR = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}
function sendRequest(url) {
	window.location.href = url;
}
function processResponse() {
	if (XMLHttpR.readyState == 4 && XMLHttpR.status == 200) {
		document.write(XMLHttpR.responseText);
	}
}
var _imageRoot = "/CBA_V2/images/";
var _IEVersion = "";
function cgds(val) {
	var s = val + "";
	if (10 != s.length) {
		if (null != val && "" != val) {
			var year = parseInt(val.year) + 1900;
			var month = (parseInt(val.month) + 1);
			month = month > 9 ? month : ("0" + month);
			var date = parseInt(val.date);
			date = date > 9 ? date : ("0" + date);
			var time = year + "-" + month + "-" + date;
			return time;
		}
	} else {
		return val;
	}
}

/**
 * 重置表单
 * 
 * @author wul
 */
function resetForm(pcId) {
	var iptLst = $("#" + pcId).find("input[id!='uploadBtn']");
	$.each(iptLst, function(i, val) {
		$(val).val('');
		$(val).attr('alt', '');
	});

	var txtareaLst = $("#" + pcId).find("textarea");
	$.each(txtareaLst, function(i, val) {
		$(val).val('');
	});

	var selLst = $("#" + pcId).find("select");
	var optLst;
	$.each(selLst, function(i, val) {
		optLst = $(val).find('option');
		$.each(optLst, function(i, v) {
			if ($(v).attr('value') == -1) {
				$(v).attr("selected", "selected");
			} else {
				$(v).removeAttr("selected");
			}
		});
	});
	$('#' + pcId).find("select").prop('selectedIndex', 0);
}

/**
 * 重置表单
 * 
 * @author wul
 */
function resetFormCommon(pcId) {
	var iptLst = $("#" + pcId).find("input[id!='uploadBtn']");
	$.each(iptLst, function(i, val) {
		$(val).val('');
		$(val).attr('alt', '');
	});

	var txtareaLst = $("#" + pcId).find("textarea");
	$.each(txtareaLst, function(i, val) {
		$(val).val('');
	});

	var selLst = $("#" + pcId).find("select");
	var optLst;
	$.each(selLst, function(i, val) {
		optLst = $(val).find('option');
		$.each(optLst, function(i, v) {
			if ($(v).attr('value') == -1) {
				$(v).attr("selected", "selected");
			} else {
				$(v).removeAttr("selected");
			}
		});
	});
	$('#' + pcId).find("select").prop('selectedIndex', 0);
}

/**
 * 判断IE版本
 * 
 * @author wul
 */
function chkIENumber() {
	var isIE = !!window.ActiveXObject;
	var isIE6 = isIE && !window.XMLHttpRequest;
	var isIE8 = isIE && !!document.documentMode;
	var isIE7 = isIE && !isIE6 && !isIE8;
	if (isIE) {
		if (isIE6) {
			_IEVersion = "IE6";
		} else {
			if (isIE7) {
				_IEVersion = "IE7";
			} else {
				if (isIE8) {
					_IEVersion = "IE8";
				}
			}
		}
	}
}

/**
 * 格式化CST日期的字串
 * 
 * @author maowei
 */
function formatCSTDate(strDate, format) {
	return formatDate(new Date(strDate), format);
}

/**
 * 格式化日期
 * 
 * @author maowei
 */
function formatDate(date, format) {

	var paddNum = function(num) {
		num += "";
		return num.replace(/^(\d)$/, "0$1");
	};

	// 指定格式字符
	var cfg = {
		yyyy : date.getFullYear(), // 年 : 4位
		yy : date.getFullYear().toString().substring(2), // 年 : 2位
		M : date.getMonth() + 1, // 月 : 如果1位的时候不补0
		MM : paddNum(date.getMonth() + 1), // 月 : 如果1位的时候补0
		d : date.getDate(), // 日 : 如果1位的时候不补0
		dd : paddNum(date.getDate()), // 日 : 如果1位的时候补0
		hh : paddNum(date.getHours() + 0), // 时
		mm : paddNum(date.getMinutes() + 0), // 分
		ss : paddNum(date.getSeconds() + 0)
	// 秒
	};

	format || (format = "yyyy-MM-dd hh:mm:ss");

	return format.replace(/([a-z])(\1)*/ig, function(m) {
		return cfg[m];
	});
}

function formatDateText(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	var h = date.getHours();
	var M = date.getMinutes();
	var s = date.getSeconds();
	return y + "-" + m + "-" + d + " " + h + ":" + M + ":" + s;
}

function formatDateText2(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + "-" + m + "-" + d;
}

/**
 * 构建生成select
 * 
 * @author wul
 * @param obj
 * @param prefixname
 */
function buildselect(selectid, text, value) {
	var id = "#" + selectid;
	$(id)[0].options.add(new Option(text, value));
}
/**
 * 判断一个字符是否存在一个数组里面
 * 
 * @author wul
 * @param arrStrTemp
 *            数组参数
 */
String.prototype.wlIn = function(arrStrTemp) {
	var arr = arrStrTemp.split(",");
	var strTemp = this + "";
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == strTemp) {
			return true;
		}
	}
	return false;
};
/**
 * 去空格 L,R,ALL
 * 
 * @author wul
 * @param obj
 */
String.prototype.Trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.LTrim = function() {
	return this.replace(/(^\s*)/g, "");
};
String.prototype.RTrim = function() {
	return this.replace(/(\s*$)/g, "");
};

/**
 * 将数据填充到控件
 * 
 * @author wul
 * @param obj
 *            集合
 * @param prefixname
 *            判断类型
 */
function iterObj(obj, prefixname) {

	var patrnheadertime = /^.+(_time)$/;
	var patrnheader = /^.+(Time)$/;
	var patrnheaderdate = /^.+(_date)$/;
	for ( var key in obj) {
		var compentVal = obj[key];
		if ("object" != typeof (compentVal)) {
			if ("lbl" == prefixname) {
				$("#" + prefixname + "_" + key + "").html(compentVal);
			}
			if ("lbl2" == prefixname) {
				$("#" + prefixname + "_" + key + "").html(compentVal);
			}
			if ("txt" == prefixname || "ipt" == prefixname) {
			}
			$("#" + prefixname + "_" + key + "").val(compentVal);
		}
		if ("object" == typeof (compentVal)) {
			var tt = "";
			if ((patrnheadertime.test(key + "") || patrnheader.test(key + "") || patrnheaderdate
					.test(key + ""))
					&& null != compentVal) {
				compentVal = formatetime(compentVal, 0);
				if ("lbl" == prefixname) {
					$("#" + prefixname + "_" + key + "").html(compentVal);
				}
				if ("lbl2" == prefixname) {
					$("#" + prefixname + "_" + key + "").html(compentVal);
				}
				if ("txt" == prefixname || "ipt" == prefixname) {
					$("#" + prefixname + "_" + key + "").val(compentVal);
				}
			} else {
				for ( var key2 in compentVal) {
					tt = compentVal[key2];
					if ("lbl" == prefixname) {
						$("#" + prefixname + "_" + key + "_" + key2 + "").html(
								tt);
					}
					if ("txt" == prefixname || "ipt" == prefixname) {
						$("#" + prefixname + "_" + key + "_" + key2 + "").val(
								tt);
					}
				}
			}
		}
	}
}

function showWaiting(){  
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",'z-index':10000,width:"100%",height:$(window).height()}).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({'z-index':10000,display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});  
 }  
 function hideWaiting(){  
     $(".datagrid-mask").remove();  
     $(".datagrid-mask-msg").remove();              
}

/**
 * 执行aAjax统一方法
 * 
 * @author wul
 * @param ajax_params
 *            ajax参数格式
 */
//var ajax_sync = 0;
function ajax_(ajax_params) {
//	if (ajax_sync == 0) {
//		ajax_sync = 1;
		$.ajax({
			type : ajax_params.type,
			dataType : ajax_params.dataType,
			ifModified : true,
			url : ajax_params.url,
			async : ajax_params.async==undefined?true:false,
			data : ajax_params.data,
//			beforeSend:ajax_params.showWaiting ? showWaiting : function(){
//			    $('.conditionsBtn').hide();
//				$('.conditionsBtn').append('<div class="conditionsBtnDis"></div>'); 
//				},
			beforeSend : ajax_params.showWaiting ? showWaiting : ajax_params.beforeSendHandler,
			error : function() {
				//if (ajax_sync != 0) {
				//	ajax_sync = 0;
				//}
				ajax_params.error;
			},
			complete : function(){
//				$('.conditionsBtn').show();
//				$('.conditionsBtnDis').remove();
			},
			success : function(data) {
				if (ajax_params.showWaiting){
					hideWaiting();
				}
				//if (ajax_sync != 0) {
				//	ajax_sync = 0;
				//}
				
				ajax_params.success(data);
			}
		});
	//}
}
/**
 * 取得项目名称
 * 
 * @author wul
 */
function getRootName() {
	// 取得当前URL
	var path = window.document.location.href;
	// 取得主机地址后的目录
	var pathName = window.document.location.pathname;
	var post = path.indexOf(pathName);
	// 取得主机地址
	var hostPath = path.substring(0, post);
	// 取得项目名
	var name = pathName.substring(0, pathName.substr(1).indexOf("/") + 1);
	return name;
}
/**
 * 取得项目路径
 * 
 * @author wul
 */
function getRootPatch() {
	// 取得当前URL
	var path = window.document.location.href;
	// 取得主机地址后的目录
	var pathName = window.document.location.pathname;
	var post = path.indexOf(pathName);
	// 取得主机地址
	var hostPath = path.substring(0, post);
	// 取得项目名
	var name = pathName.substring(0, pathName.substr(1).indexOf("/") + 1);
	return hostPath + name;
}
String.prototype.wlInArr = function(arr) {
	var strTemp = this + "";
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == strTemp) {
			return true;
		}
	}
	return false;
};

Array.prototype.remove = function(strTemp) {
	var arr = this;
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == strTemp) {
			arr.splice(i, 1);
		}
	}
}
function formatDateCommon(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + "-" + m + "-" + d;
}
/**
 * 判断字符的长度 返回true str 检验的字符串 count 最大长度
 */
function widthCheck(str, count) {
	var w = 0;
	for (var i = 0; i < str.length; i++) {
		var c = str.charCodeAt(i);
		// 单字节加1
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
			w++;
		} else {
			w += 2;
		}
	}
	if (w > count) {
		return false;
	}
	return true;
}

// 拦截特殊字符
function CheckCode(t) {
	if (/[\':;*?~`!【】￥@#$%^&+={}\[\]\<\>\(\),\.]/.test(t)) {
		return true;// 含有特殊字符!
	}
	return false;// 没含有特殊字符!
}

function s4() {
    return Math.floor((1 + Math.random()) * 0x10000)
               .toString(16)
               .substring(1);
};
function JsGuid() {
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
           s4() + '-' + s4() + s4() + s4();
}

function openwindow(url, name, iWidth, iHeight) {
	var url; // 转向网页的地址;
	var name; // 网页名称，可为空;
	var iWidth; // 弹出窗口的宽度;
	var iHeight; // 弹出窗口的高度;
	var iTop = (window.screen.availHeight - 30 - iHeight) / 2; // 获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; // 获得窗口的水平位置;

	window
			.open(
					url,
					name+JsGuid(),
					'height='
							+ iHeight
							+ ',,innerHeight='
							+ iHeight
							+ ',width='
							+ iWidth
							+ ',innerWidth='
							+ iWidth
							+ ',top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=yes,resizeable=no,location=no,status=no');
}

/**
 * 非表单提交校验 作者：zyp
 */



function checkInfo(divId){
	var datatype = {
			//不能为空 正则表达式
			"*":/[\w\W]+/, 
			//只输入非负数字、带小数点的数字正则表达式
			"num":/^\+?(:?(:?\d+\.\d+)|(:?\d+))$/, 
			//正负数、小数点均可输入
			"pnNum":/^[-+]?[0-9]+(\.[0-9]+)?$/,
			//字符串类型
			"str":/^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]+$/,
			//字符串长度
			"s6-18":/^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]{6,18}$/,
			//邮政编码长度正则表达式
			"postNum":/^[0-9]{6}$/,
			//手机号码长度
			"phoneNum":/^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/,
			//手机和电话号码长度
			"phoneAndTelNum":/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/,
			//校验是否含有特殊字符
			"specialCharacters":/^[\u4E00-\u9FA5a-zA-Z0-9_]{1,100}$/,
			//邮箱格式
			"email":/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
			//url格式校验
			"url":/^(\w+:\/\/)?\w+(\.\w+)+.*$/,
			//域名格式校验
            "domain":/^[0-9a-zA-Z]+[0-9a-zA-Z\.-]*\.[a-zA-Z]{2,4}$/,
			//只能输入汉字正则表达式
			"chnStr":/[\u4e00-\u9fa5]/,  
			//只能输入字母和数字
			"enAndNum":/^[0-9a-zA-Z_]+$/,
			//只能输入字母
			"en":/^[a-zA-Z_]+$/,
			//金额格式正则表达式
			"money":/^\+?(:?(:?\d+\.\d+)|(:?\d+))$/,
			//IP地址格式与长度正则表达式
			"ipAddr":/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/,
			//密码长度判断
			"pwd":/^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]{6,18}$/,
			//时间格式校验正则
			"time":/^(\d{4})(-|\/)(\d{2})\2(\d{2}) (\d{2}):(\d{2}):(\d{2})$/,
			
			"date" : /^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}$/,
			//匹配中文字符
			"chnreg":/[^\u4E00-\u9FA5\uf900-\ufa2d]/g,
			//身份证号码验证
			"IDCard" : /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X)$)/,
			//匹配正整数
			"ptInteger":/^[+]?[1-9]+\d*$/i,
			//非负整数
			"noInteger":/^\d+$/,
			"fax":/^((\+?[0-9]{2,4}\-[0-9]{3,4}\-)|([0-9]{3,4}\-))?([0-9]{7,8})(\-[0-9]+)?$/
			
	};
    var errorMsg = {
	       "*":"输入信息不得为空。",
	       "num":"输入内容必须为正数。",
	       "pnNum":"请输入数字，例如1、1.2、-1、……！",
		   "postNum":"邮编号码不正确！",
		   "email":"请输入正确的邮箱格式，如examplename@example.com",
		   "phoneNum":"请输入正确的手机格式11位数，如18788888888",
		   "phoneAndTelNum":"请输入正确的手机或固话号码，如18788888888，02588888888，0522-88888888！",
		   "money":"金额只能输入数字！",
		   "ipAddr":"请输入正确的Ip地址错误，如192.168.1.1",
		   "specialCharacters":"输入只能是下划线、汉字、字母、数字\n不能含有特殊字符！",
		   "pwd":"密码长度6-18位！",
		   "enAndNum":"输入信息必须为字母或数字。",
		   "en":"输入信息必须为英文字符，如“InsightView”。",
		   "chnStr":"输入内容必须为汉字，如“飞搏运维”",
		   "url":"请输入正确的网址链接，如http://wenku.baidu.com/portal/",
		   "domain":"请输入正确的域名，如www.google.com",
		   "time":"时间格式错误",
		   "ptInteger":"请输入正整数,例如1、2、3……。",
		   "fax":"请填写正确的传真号，例如：+086-0731-88888888、020-88888888、025-88888888",
		   "noInteger":"请输入非负整数,例如0、1、2……。",
		   "IDCard" : "身份证号码只可以为15位或者18位，15位时全为数字，18位时前17位为数字，最后一位为数字或者大写字母X"
	};
	var flag = true;
	$(divId+" [validator]").each(function() {
		var inputValue = "";
		var type ="";
		var validator = $(this).attr('validator');
		var valid = eval('('+ validator +')');
		var msg = $(this).attr('msg');
		var errormsg = eval('('+ msg +')');
        if(valid &&(valid.type=="combobox" || valid.type=="timespinner" || valid.type=="datetimebox" || valid.type=="datebox" || valid.type=="select2")){//针对于特殊控件的取值方式不同特殊处理
			if(valid.type=="combobox"){//判断控件类型为combobox的取值方式
				type = "combobox";
			    inputValue = $(this).combobox("getValue");
			}else if(valid.type=="datetimebox"){//判断控件类型为datetimebox的取值方式
				type = "datetimebox";
				inputValue = $(this).datetimebox("getText");
			}else if(valid.type=="datebox"){//判断控件类型为datetimebox的取值方式
                type = "datebox";
                inputValue = $(this).datebox("getText");
            }else if(valid.type == "select2"){//判断控件类型为select2的取值方式
				type = "select2";
				inputValue = $(this).select2("val");
			}else if(valid.type == "timespinner"){//判断控件类型为select2的取值方式
				type = "timespinner";
				inputValue = $(this).timespinner("getValue");
			}
			if(inputValue=="" && valid.type != "select2" && valid['default'] == "*"){//特殊控件取值后如果所取的值为空，并且此控件类型为非select2
				flag=false;
				var that;
				if(valid.type == "timespinner"){
					that = $(this).parent(".spinner");
			    } else {
			    	that = $(this).next(".combo");
			    }
				var ErrorMsg = errorMsg['*']; 
				onShow(that,ErrorMsg,type);
			}else if(inputValue=="-1" && valid.type == "select2" && valid['default'] == "*"){
				flag=false;
				var that = $(this).prev(".select2-container").children(".select2-choice");
				var ErrorMsg = errorMsg['*']; 
				onShow(that,ErrorMsg,type);
		    }else if(inputValue!="" && valid.type =="datebox"){
		    	if(!datatype[valid["default"]].test(inputValue)){
		    		flag = false;
		    		var that = $(this).next(".combo.datebox");
		    		var ErrorMsg = errorMsg["time"];
		    		onShow(that,ErrorMsg);
		    	}
		    }
		}else if(valid){
				inputValue = $(this).val();
				if(/^\s*$/.test(inputValue) && valid['default']=="*"){
			        if(valid['length'] && valid['length'].split("-")[0]==0){
						//alert(flag) 
						}else{
					        flag=false;
					        var that = $(this);
					        var ErrorMsg = errorMsg['*'];
					        onShow(that,ErrorMsg);
						}
					}else if(valid['default'] && (valid['default']).indexOf('checkEmpty_') == 0){//空的输入框进行校验 例<input id="email" validator="{'default':'checkEmpty_email'}"/>
						if(valid['default'].substr(11) && ((valid['default'].substr(11)) in datatype) && !datatype[(valid['default'].substr(11))].test(inputValue)) {
							flag = false;
							var that = $(this);
							var ErrorMsg = errorMsg[valid['default'].substr(11)];
							onShow(that,ErrorMsg);
							return;
						}
				   }else if(valid['default'] && (valid['default'] in datatype) && inputValue!='' && !datatype[valid['default']].test(inputValue)){
						flag = false;
						var that = $(this);
						var ErrorMsg = errorMsg[valid['default']];
						onShow(that,ErrorMsg);
				   }else if(valid['reg'] && inputValue!=""){
					   var regex;
					   try {
						  regex = eval('('+ valid['reg'] +')');
						  if (regex && !regex.test(inputValue)){
							 flag = false;
							 var that = $(this);
							 var ErrorMsg = errormsg['reg'];
							 onShow(that,ErrorMsg);
						 }else if(valid['illegalCharacter'] && inputValue !="" && string_compare(valid['illegalCharacter'],inputValue) == true ){
					          flag = false;
					          var that = $(this);
					          var ErrorMsg ="输入信息不得包含非法字符，如"+ valid['illegalCharacter'] +"";
					          onShow(that,ErrorMsg);
					     }else if(valid['min-max']){
							   if(!datatype['pnNum'].test(inputValue)){
									flag = false;
									var that =$(this);
									var ErrorMsg = errorMsg['pnNum'];
									onShow(that,ErrorMsg); 
							   }else if(inputValue.indexOf(".")!=-1 && inputValue.split(".")[1].length > 2 ){
									flag = false;
									var that =$(this);
									var ErrorMsg = "输入数值小数点后不得超过2位";
									onShow(that,ErrorMsg);  
							   }else if ((parseFloat(inputValue) < parseFloat(valid['min-max'].split("~")[0]))||(parseFloat(inputValue) > parseFloat(valid['min-max'].split("~")[1]))){
						    	    flag = false;
								    var that =$(this);
								    var ErrorMsg = "输入信息不得超出"+valid['min-max']+"范围！";
								    onShow(that,ErrorMsg);
								 }
						  }else if(valid['length']){
							  //var chn = inputValue.replace(datatype['chnreg'], "");
							  var inputStrLength = inputValue.length //+ chn.length;
					    	  if((valid['length'].indexOf("-") != -1)&&(inputStrLength < valid['length'].split("-")[0] || inputStrLength > valid['length'].split("-")[1])){
						         flag = false;
						         var that = $(this);
						         if(typeof(msg) !="undefined"){
						        	 var ErrorMsg = errormsg['reg']; 
						         }else{
						        	 var ErrorMsg = "输入信息范围："+valid['length']+"位字符。"; 
						         }
						         onShow(that,ErrorMsg);
							  }else if((valid['length'].indexOf("-") == -1)&&(inputStrLength < valid['length'] || inputStrLength > valid['length'])){
						    		
					    			flag = false;
					    			var that = $(this);
									var ErrorMsg = "请输入正确的手机格式" + valid['length'] + "位，如18788888888";
									onShow(that,ErrorMsg);

					    	}
				         }
					} catch(err){
						var regParts = valid['reg'].match(/^\/(.*?)\/([gim]*)$/);
						if (regParts) {
						    // the parsed pattern had delimiters and modifiers. handle them. 
						    regex = new RegExp(regParts[1], regParts[2]);
						} else {
						    // we got pattern string without delimiters
						    regex = new RegExp(valid['reg']);
						}
						if (regex && !regex.test(inputValue)){
							flag = false;
							var that = $(this);
							var ErrorMsg = errormsg['reg'];
							onShow(that,ErrorMsg);
						}
			   }
		    }else if(valid['illegalCharacter'] && inputValue !="" && string_compare(valid['illegalCharacter'],inputValue) == true ){
		          flag = false;
		          var that = $(this);
		          var ErrorMsg ="输入信息不得包含非法字符，如"+ valid['illegalCharacter'] +"";
		          onShow(that,ErrorMsg);
		     }else if(valid['min-max']){
		    	if(!datatype['pnNum'].test(inputValue)){
					flag = false;
					var that =$(this);
					var ErrorMsg = errorMsg['pnNum'];
					onShow(that,ErrorMsg); 
			   }else if(inputValue.indexOf(".")!=-1 && inputValue.split(".")[1].length > 2 ){
					flag = false;
					var that =$(this);
					var ErrorMsg = "输入数值小数点后不得超过2位";
					onShow(that,ErrorMsg);  
				 }else if ((parseFloat(inputValue) < parseFloat(valid['min-max'].split("~")[0]))||(parseFloat(inputValue) > parseFloat(valid['min-max'].split("~")[1]))){
		    	    flag = false;
				    var that =$(this);
				    var ErrorMsg = "输入信息不得超出"+valid['min-max']+"范围！";
				    onShow(that,ErrorMsg);
				 }
		      }
		    else if(valid['length']){
				 // var chn = inputValue.replace(datatype['chnreg'], "");
				  var inputStrLength = inputValue.length //+ chn.length;
		    	  if((valid['length'].indexOf("-") != -1)&&(inputStrLength < valid['length'].split("-")[0] || inputStrLength > valid['length'].split("-")[1])){
					  flag = false;
					  var that = $(this);
					  if(typeof(msg) !="undefined"){
				        	 var ErrorMsg = errormsg['reg']; 
				         }else{
				        	 var ErrorMsg = "输入信息范围："+valid['length']+"位字符。"; 
				         }
					  onShow(that,ErrorMsg);
		    	}else if((valid['length'].indexOf("-") == -1)&&(inputStrLength < valid['length'] || inputStrLength > valid['length'])){
		    		
		    			flag = false;
		    			var that = $(this);
						var ErrorMsg = "请输入正确的手机格式" + valid['length'] + "位，如18788888888";
						onShow(that,ErrorMsg);

		    	}

			    }
	    }
    });
function string_compare(str1,str2){
		var mark = false;
		for(var i = 0; i < str2.length ;i++){
			var character = str2.substring(i,i+1);
			if(str1.indexOf(character) != -1){
				mark = true;
			}
		}
		return mark;
	}
function onShow(that,ErrorMsg,type){
	that.tooltip({
        position: 'right',
        content: '<span style="color:#990000">'+ErrorMsg+'</span>',
        onShow: function(){
            $(this).tooltip('tip').css({
                 backgroundColor: '#ffffcc',
                 borderColor: '#e54d00'
            });
        }
    });
    that.css("border-color","#e56000");
        if(type =="select2"){
		    that.bind("mouseleave mousedown",function(){
			    that.tooltip({
                    onShow: function(){
                        $(this).tooltip('tip').css({
                             display:'none'
                        });
                    }
				});
	           that.css("border-color","");
		   });
       }else{
		   that.bind("keyup change click",function(){
			    that.tooltip({
                     onShow: function(){
                          $(this).tooltip('tip').css({
                               display:'none'
                          });
                     }
				});
		        that.css("border-color","");
		   });
     }
	}
	return flag;
}
//特殊输入框输入红框消失方式
function cancelRedBox(inputId){
	  $('#'+inputId).tooltip({
          onShow: function(){
                      $(this).tooltip('tip').css({
                           display:'none'
                      });
                  }
					});
	  $('#'+inputId).css("border-color","");
}

/**
 * 居中弹出窗口
 * 
 * @author Maowei
 * @param url
 * @param name
 * @param iWidth
 * @param iHeight
 * @param iscrollbars
 * @param iresizable
 */
function openWindowInC(url, name, iWidth, iHeight, iscrollbars, iresizable) {
	var url; // 转向网页的地址;
	var name; // 网页名称，可为空;
	var iWidth; // 弹出窗口的宽度;
	var iHeight; // 弹出窗口的高度;
	var iscrollbars; // 弹出窗口的是否有滚动条(auto/yes/no);
	var iresizable; // 弹出窗口是否可调整大小(yes/no);
	var iTop = (window.screen.availHeight - 30 - iHeight) / 2; // 获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; // 获得窗口的水平位置;
	window.open(url, name, 'height=' + iHeight + ',,innerHeight=' + iHeight
			+ ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop
			+ ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars='
			+ iscrollbars + ',resizable=' + iresizable
			+ ',location=no,status=no');
}

/**
 * 加载字典
 */
function doChoseConstantByTypeId(selectId, typeId) {
	$("#" + selectId).combobox({
		panelHeight : '120',
		url : '../../dict/readItems?id=' + typeId,
		valueField : 'key',
		textField : 'val',
		editable : false,
		required : true
	});
}

function doChoseConstantByTypeName(selectId, typeName) {
	 
	var path = getRootName();
	$("#" + selectId).combobox({
		panelHeight : '120',
		url : path+'/dict/readItemsByName?typeName=' + typeName,
		valueField : 'key',
		textField : 'val',
		editable : false,
		required : true,
		onLoadSuccess : function(data) {
			if (data) {
				$("#" + selectId).combobox('setValue', data[0].key);
			}

		}
	});
}

/**
 * 居中弹出窗口（从父页面）
 * 
 * @author Maowei
 * @param url
 * @param name
 * @param iWidth
 * @param iHeight
 * @param iscrollbars
 * @param iresizable
 */
function openWindowFromFather(url, name, iWidth, iHeight, iscrollbars,
		iresizable) {
	var url; // 转向网页的地址;
	var name; // 网页名称，可为空;
	var iWidth; // 弹出窗口的宽度;
	var iHeight; // 弹出窗口的高度;
	var iscrollbars; // 弹出窗口的是否有滚动条(auto/yes/no);
	var iresizable; // 弹出窗口是否可调整大小(yes/no);
	var iTop = (window.opener.screen.availHeight - 30 - iHeight) / 2; // 获得窗口的垂直位置;
	var iLeft = (window.opener.screen.availWidth - 10 - iWidth) / 2; // 获得窗口的水平位置;
	window.opener.open(url, name, 'height=' + iHeight + ',,innerHeight='
			+ iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top='
			+ iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars='
			+ iscrollbars + ',resizable=' + iresizable
			+ ',location=no,status=no');
}

/**
 * 
 * @param uri 后台请求rul
 * @param paramKey 请求参数的key
 * @param paramValue 请求参数value
 * @param idAttr 页面上的id组合，用逗号隔开，最后拼装一个txtFilterDefName
 * @param name 查出集合的name
 * @param id 查出集合的id
 */
function createSelect2(uri,paramKey,paramValue, idAttr,name,id) {
	var path = getRootPatch();
	var ajax_param = {
		url : path + uri,
		type : "post",
		datdType : "json",
		data : {
			param : paramValue,
			"t" : Math.random()
		},
		error : function() {
			$.messager.alert("错误", "ajax_error", "error");
		},
		success : function(data) {
			if (data != "") {
				doGetUserByName('', 'txtFilterHandleMan,ipt_handleMan');

				var resIdArr = idAttr.split(',');
				var resAssetList = eval('(' + data.resAssetLstJson + ')');
				
				for(var i=0;i<resAssetList.length;i++)
				{
					for(var j =0;j<resIdArr.length;j++){
					
						buildselect(resIdArr[j],resAssetList[i][name],resAssetList[i][id]);
					}
				}
				for(var j =0;j<resIdArr.length;j++){
					
					 $("#"+resIdArr[j]).select2();
				}
				
			}
		}
	};
	ajax_(ajax_param);
}



/**
* JQuery扩展方法，用户对JQuery EasyUI的DataGrid控件进行操作。
* 
* @author zhaoyp
* 修改DataGrid对象的默认大小，以适应页面宽度。
* @param heightMargin
* 高度对页内边距的距离。
* @param widthMargin
* 宽度对页内边距的距离。
* @param minHeight
* 最小高度。
* @param minWidth
* 最小宽度。
*
*/
$.fn.extend({
    resizeDataGrid : function(heightMargin, widthMargin, minHeight, minWidth) {
        var height = $(document.body).height() - heightMargin;
        var width = $(document.body).width() - widthMargin;
        height = height < minHeight ? minHeight : height;
        width = width < minWidth ? minWidth : width;
        $(this).datagrid('resize', {
            height : height,
            width : width
         });
      }
}); 

//展开树
function openTree(treedata){
	treedata.openAll();
}
//收缩树
function closeTree(treedata){
	treedata.closeAll();
}

//显示行
function setShowRow(oTable,iRow){
    oTable.rows[iRow].style.display = oTable.rows[iRow].style.display = "block";
}
//隐藏行
function setHiddenRow(oTable,iRow){
    oTable.rows[iRow].style.display = oTable.rows[iRow].style.display = "none";
}

/**
 * 弹出编辑页面
 * @param pageInfo
 * @return
 */
var pageInfo=new Object();
pageInfo.title='编辑页面';
pageInfo.width=600;
pageInfo.height=400;
pageInfo.uri='';
function toOpenPage(pageInfo){
	parent.$('#popWin').window({
    	title:pageInfo.title,
        width:pageInfo.width,
        height:pageInfo.height,
        minimizable:false,
        maximizable:false,
        collapsible:false,
        modal:true,
        href: getRootName() + uri
    });
}

/**
* 
*Creat By Zhaoyp
*/
//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
function banBackSpace(e){   
         var ev = e || window.event;//获取event对象   
         var obj = ev.target || ev.srcElement;//获取事件源   
         var t = obj.type || obj.getAttribute('type');//获取事件源类型  
         //获取作为判断条件的事件类型
         var vReadOnly = obj.getAttribute('readonly');
        //处理null值情况
        vReadOnly = (vReadOnly == "") ? false : vReadOnly;
        //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
         //并且readonly属性为true或enabled属性为false的，则退格键失效
         var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") 
                     && vReadOnly=="readonly")?true:false;
         //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
        var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")?true:false;        
         
         //判断
         if(flag2){
            return false;
         }
        if(flag1){   
            return false;   
        }   
}

window.onload = function(){
	//禁止后退键 作用于Firefox、Opera
     document.onkeypress=banBackSpace;
     //禁止后退键  作用于IE、Chrome
     document.onkeydown=banBackSpace;
 }


;(function(){
    $("input").each(function(){
        if(!$(this).attr("type")){
        	$(this).attr("type","text");
        }
    });
})();

function timeFormatter(value, row, index){
	  if(!value){
		  return '';
	  }
	  
	  return formatDate(new Date(value), 'yyyy-MM-dd hh:mm:ss');
}
