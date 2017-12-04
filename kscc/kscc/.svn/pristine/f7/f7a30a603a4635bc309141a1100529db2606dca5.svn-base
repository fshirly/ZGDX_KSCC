if(typeof($) != 'undefined' && typeof($.fn.dataTable) != 'undefined'){
	$.fn.dataTable.ext.errMode = 'throw';
}

var GlobalAjax = new globalAjax();

function globalAjax(){
	
}

globalAjax.prototype.checkLogin = function(){
	/**
	 * 设置未来(全局)的AJAX请求默认选项
	 * 主要设置了AJAX请求遇到Session过期的情况
	 */
	if(typeof($)!="undefined"){
		$.ajaxSetup({
			complete: function(xhr, status) {
				var sessionStatus = xhr.getResponseHeader('sessionStatus');
				if(sessionStatus == 'timeOut') {
					getTopWinow().location.replace(xhr.getResponseHeader("redirectUrl"));
				}
			}
		});
	}
	
	/**
	 * 在页面中任何嵌套层次的窗口中获取顶层窗口
	 * @return 当前页面的顶层窗口对象
	 */
	function getTopWinow(){
		var p = window;
		while(p != p.parent){
			p = p.parent;
		}
		return p;
	}
};

GlobalAjax.checkLogin();