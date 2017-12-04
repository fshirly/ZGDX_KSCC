(function(){
	var  basePath="";
	require.config({
		baseUrl:(function() {
		    var curWwwPath = window.document.location.href;
		    var pathName = window.document.location.pathname;
		    var pos = curWwwPath.indexOf(pathName);
		    var localhostPath = curWwwPath.substring(0,pos);
		    var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		    basePath=localhostPath+projectName;
		    return(localhostPath+projectName);
		})() + "/js",
		baseUrl:basePath+"/js",
		shim: {
			bootstrap:["jquery"],
			datetimepicker:["jquery"],
			datetimepickerCN:['datetimepicker'],
			'template' : {
				exports:"template"
			},
			commonUtil:["jquery"],
			'commonUtil' : {
				exports:"commonUtil"
			},
			syqUpload:["jquery"],
			'syqUpload':{
				exports:"syqUpload"
			},
			flow:["jquery"],
			'flow' : {
				exports:"flow"
			},
			form:["jquery"],
			'jquery.form' : {
				exports:"jquery.form"
			},
			treeTable:['jquery'],
			"BaseApp":{
				exports:"BaseApp"
			},
			"GlobalAjax":["jquery"],
			"select2":["jquery"],
			ztreeCore:['jquery'],
			ztreeExcheck:['ztreeCore'],
			ztreeExcheckMin:['ztreeCore'],
			ztreeExedit:['ztreeCore'],
			form:["jquery"],
			fileInput:["jquery"],
			fileInputMin:["jquery"],
			compare:["template"],
			bootstrapSelect:["jquery"],
			grid:['jquery'],
			bsgrid:['jquery'],
			enclosure:['jquery'],
			jqScrollbar:['jquery','jqMousewheel'],
			jqMousewheel:["jquery"],
			jscroll:['jquery'],
			calendar:['jqueryUI'],
	   		calendarLocale:["calendar"],
	   		jquery:["moment"]
		},
		
		paths:{
			"jquery" : "lib/jquery-1.9.1.min",
			"GlobalAjax":"lib/globalAjax",
			"bootstrap":"lib/bootstrap.min",
			"template":"lib/handlebars-v4.0.4",
			"base":"lib/base.min",
			"baseApp":"lib/baseApp",
			"engineFactory":"lib/engineFactory",
			"fableFactory":"lib/fableFactory",
			"datetimepicker":"lib/bootstrap-datetimepicker.min",
			"datetimepickerCN":"lib/bootstrap-datetimepicker.zh-CN",
			"datatables.net":"lib/jquery.dataTables",
			"echarts":"lib/echarts.min",
			"swiper":"lib/swiper.min",
			"idangerousSwiper":"lib/idangerous.swiper2.7.6",
			"resDatatables":"lib/dataTables.responsive.min",
			"bsDatatables":"lib/dataTables.bootstrap",
			"jqueryUI":"lib/jquery-ui.min",
			"flow":"lib/flow",
			"commonUtilOld":"lib/commonUtilOld",
			"commonUtil":"lib/commonUtil",
			"fileUpload":"lib/jquery.form",
			"BaseApp":"lib/baseAppModal",
			"commonFun":"lib/commonFunList",
			"select2-min":"lib/select2.full.min",
			"select2":"lib/select2",
			"ztreeAll":"lib/ztree/jquery.ztree.all.min",
			"ztreeCore":"lib/jquery.ztree.core-3.5",
			"ztreeExcheck":"lib/jquery.ztree.excheck-3.5",
			"ztreeExcheckMin":"lib/ztree/jquery.ztree.excheck.min",
			"ztreeExedit":"lib/jquery.ztree.exedit-3.5.min",
			"fileInput":"lib/fileinput/fileinput",
			"fileInputMin":"lib/fileinput/fileinput.min",
			"form":"lib/jquery.form",
			"treeTable":"lib/jquery.treetable",
			"calendar":"lib/fullcalendar.min",
			"calendarLocale":"lib/locale-all",
			"moment":"lib/moment.min",
			"compare":"lib/templateCompare",
			"treeTable":"lib/jquery.treetable",
			"bootstrapSelect":"lib/bootstrap-select",
			"syqUpload":"lib/upload",
			//"layDate":"lib/laydate",
			//"laydate":"lib/laydate.dev",
			"grid":"lib/grid.zh-CN.min",
			"bsgrid":"lib/bsgrid.all.min",
			"enclosure":"enclosure",
			"jqScrollbar":"lib/jquery.mCustomScrollbar.min",
		    "jqMousewheel":"lib/jquery.mousewheel.min",
			"fileUpload":"lib/ajaxfileupload",
			"date5.0":"lib/laydate5.0.min",
			"autoSelect":"lib/jquery.autoSelect",
			"areaSelect":"lib/areaSelect.min"
		},
		map: {
	        '*': {
	            'jquery': 'jquery-config',
	        },
	        'jquery-config': {
	            'jquery': 'jquery'
	        }
	   }
	});

	define('jquery-config', ['jquery'], function(){
		var curWwwPath = window.document.location.href;
	    var pathName = window.document.location.pathname;
	    var pos = curWwwPath.indexOf(pathName);
	    var localhostPath = curWwwPath.substring(0,pos);
	    var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	    $.base = localhostPath+projectName;
	    return $;
	});	
})()

