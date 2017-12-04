var BaseApp = new baseApp();

function baseApp(){
	this.params={};
	this.VERSION = "20150928";
	this.jsonpOption = {
		url:null,
		successFunc:null,
		errorFunc:null,
		data:this.params,
		timeout:2000
	};
	//this.ip = "http://192.168.0.142:9090";
	this.ip = "http://192.168.0.187:8089";
	this.authData = null;
	this.chartColors = ["#fa7906","#3a86d3","#40c916","#cec713","#39d2e0","#966bd1","#bfe03b","#ef396d","#6590c5","#81c5b6","#9fc581","#c5ab81","#ae6c2c","#16b8ab","#355fd8","#d530b0"];
    this.COOKIE_TOKEN_NAME = "accessToken";
    this.mediaFontColor = "#eee";
    this.textAnimateMap = [];
    this.mapCharts = {};
    this.msgTimer =  null;
    this.paramsMsgTimer = null;
    this.mainPointerName = "江苏省公安厅";
    this.GLCS = 0;
    this.longConnectionObj = {};
};
/*
	判断是否是ie8以下浏览器
*/
baseApp.prototype.checkBrowser = function(){
	if (!document.getElementsByClassName) {
		return false;
	}
    return true;
};

baseApp.prototype.getBaseUrl = function(){
	var urlSplit = window.location.href.split("/");
	var urlStr =  "";
	
	for(var i=0,j=urlSplit.length;i<j;i++){
		if(i<j-1){
			urlStr += urlSplit[i]+"/";
		}
	}
	return urlStr;
};

/*
	校验用户身份，如果合法则进入index.html，否则跳入login页面
*/
baseApp.prototype.authValidate = function(){
	var token = BaseApp.getToken();
	if(!token){
		window.location = "login.html";
		return;
	}
   
	$.ajax({
             type: "GET",
             async: false,
             data:  {"accessToken":token},
             url:   BaseApp.ip+"/frsp/rest/login/check",
             dataType: "jsonp",
             jsonpCallback:"callback"+BaseApp.getRad(),
             jsonp: "callback",
             success:function(data){
                if(data.code==200){
                    BaseApp.authData = data.data;
                    $("#authTip").html("您好："+BaseApp.authData.userName+"，欢迎登录！");
                }else{
                    window.location = "login.html";
                   
                }
             },
             error:function(XMLHttpRequest, textStatus, errorThrown){
                 
                   window.location = "login.html";
             },

             timeout:5000
             
     });
	
};


/*
	获取jsonp数据
*/
baseApp.prototype.getJsonp = function(option,params){
	
	$.ajax({
             type: "GET",
             async: option.async?option.async:true,
             data:	option.data?option.data:"",
             url:	option.url,
             dataType: "jsonp",
             jsonpCallback:option.jsonpCallback,
             jsonp: "callback",
             success:option.success?option.success:function(data,params){
                 
             },
             error:option.error?option.error:function(XMLHttpRequest, textStatus, errorThrown){
                    if(textStatus=="timeout"){  
                        alert("加载超时，请重试");  
                     }else{   
                         alert(textStatus);   
                    }
             },

             timeout:option.timeout?option.timeout:2000
             
     });
     
};
/*
	获取json数据
*/
baseApp.prototype.getJson = function(){
	$.ajax({
             type: "get",
             async: option.async?option.async:true,
             data:	option.params?option.params:"",
             url:	option.url,
             dataType:"json",
             success: function(data){
                 alert('您查询到航班信息：票价： ' + data.price + ' 元，余票： ' + data.tickets + ' 张。');
             },
             error: function(XMLHttpRequest, textStatus, errorThrown){
                 if(textStatus=="timeout"){  
                     alert("加载超时，请重试");  
                 }else{   
                     alert("网络出错");   
                }
             }
     });
};

/*
	获取url参数
*/
baseApp.prototype.getUrlParamByName = function(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
};

/*
	获取url的token值
*/
baseApp.prototype.getToken = function(){
	return this.getUrlParamByName("token");
};


/*
	页面随机数
*/
baseApp.prototype.getRad = function(){
  //return Math.random(100000,999999);
    var Max = 999999;
    var Min = 100000;
    var Range = Max - Min;
    var Rand = Math.random();   
    var num = Min + Math.round(Rand * Range);
    return num;
};

/*
    获取查询参数  option属性如下
    container: 容器
    type : 1.obj  返回{}对象 (默认)
           2.不填或其他  返回字符串的url参数
    isFullParams: 1.true  如果控件的值是空也会跟随参数返回
                  2.false 如果控件的值是空也不会跟随参数返回  （默认）
    encode:1.true 2.false
*/

baseApp.prototype.getFormParams = function(option){
    //container,type,isFullParams
    //alert($(option.isFullParams).length)
	if(!option.container){
		option = $("body");
	}
    
    if(!option.type){
        option.type = "obj";
    }
    var tmp = {};
    var params = {};
    var inputname = [];
    $(option.container).find("input,select,textarea").each(function(){

        var paramObj = {};
        var name = $(this).attr("name");
        if($(this).is("input")){
            if($(this).attr("type")!="radio"&&$(this).attr("type")!="checkbox"){
                var input =  $(option.container).find("input[name="+name+"]");
               
                if(option.isFullParams){
                	
                	if(option.encode){
                		
                		params[name] = encodeURI(input.val());
                	}else{
                		params[name] = input.val();
                	}
                     
                }else{
                	
                    if(input.val()){
                        if(option.encode){
                        	
                			params[name] = encodeURI(input.val());
	                	}else{
	                		params[name] = input.val();
	                	}
                    }
                }
            }else{
               
                var s = "";
                    if(!tmp[name]){
                        var hav = false;
                       $(inputname).each(function(i3,o3){
                           if(name==o3){
                               hav = true;
                               return false;
                           }
                       });
                       if(hav){return true;}
                       var inputs =  $(option.container).find("input[name="+name+"]:checked");
                       var n =  inputs.size();
                       if(option.isFullParams){
                           inputs.each(function(i,obj){
                               if(i==n-1){
                               	 if(option.encode){
		                			s+= encodeURI($(obj).val());
			                	 }else{
			                		s+= $(obj).val();
			                	 }
                                   
                               }else{
                               	 if(option.encode){
		                			s+= encodeURI($(obj).val()+",");
			                	 }else{
			                		s+= $(obj).val()+",";
			                	 }
                                   
                               }
                            });
                        params[name] = s;
                       }else{
                           if(n==0){
                                return true;
                           }else{
                               inputs.each(function(i,obj){
                                   if(i==n-1){
                                   	 if(option.encode){
			                			s+= encodeURI($(obj).val());
				                	 }else{
				                		s+= $(obj).val();
				                	 }
                                       
                                   }else{
                                   	 if(option.encode){
			                			s+= encodeURI($(obj).val())+",";
				                	 }else{
				                		s+= $(obj).val()+",";
				                	 }
                                      
                                   }
                                });
                                params[name] = s;
                           }
                       }
                       
                        tmp[name] = true;
                    }
                
            }
        }
        
        if($(this).is("select")){
        	if($(this).attr("name")){
        		 if(option.encode){
        			params[name] = encodeURI($(option.container).find("select[name="+name+"]").val());
            	 }else{
            		params[name] = $(option.container).find("select[name="+name+"]").val();
            	 }
        		
        	}
            
        }
        if($(this).is("textarea")){
        	if($(this).attr("name")){
        		var textarea = $(option.container).find("textarea[name="+name+"]");
	            if(option.isFullParams){
	            	 if(option.encode){
	        			params[name] = encodeURI(textarea.val());
	            	 }else{
	            		params[name] = textarea.val();
	            	 }
	                
	            }else{
	                if(textarea.val()){
	                	if(option.encode){
		        			params[name] = encodeURI(textarea.val());
		            	 }else{
		            		params[name] = textarea.val();
		            	 }
	                    
	                }
	            }
        	}
          
        }
    });
    tmp = {};

    if(option.type && option.type.toLowerCase()=="obj"){
        return params;
    }else{
        pstr =  "";
        var num = 0;
        
        for(var key in params){
            if(option.isFirst&&num==0){
                pstr += "?"+key+"="+params[key];
            }else{
                pstr += "&"+key+"="+params[key];
            }
            
            num++;
        }
        return pstr;
    }
};
/*
	显示错误信息
*/
baseApp.prototype.showError = function(containerId,errorMsg){
    $("#"+containerId).html("<i class=''></i>"+errorMsg);
};

/*判断对象是不是空*/
baseApp.prototype.isEmptyObject = function(obj){
    for(var n in obj){return false;}
    return true; 
};

/*
	下载
*/
baseApp.prototype.download = function(url,params,method){
 
   var inputs ="";
   for(var key in params){
        inputs+='<input type="hidden" name="'+ key +'" value="'+ params[key] +'" />'; 
   }
       //alert(inputs);
   jQuery("<iframe id='ifm' src='html/download.html' style='display:block'></iframe>")
       //.appendTo('body').submit().remove();
       .appendTo('body');
   $("#ifm").contents().find("body").html("<form action='"+url+"' method='"+method+"'>"+inputs+"</form>");
    
};

/*分页*/
baseApp.prototype.Pagination = function(){
    
};
/*表格
 
        OPTION各类选项
 isCustom: boolean   自定义表头   false or true
 url：String  ajax地址
 cols:Array[]  thead的列数据
 colDef:Array[] 单元格重新定义内容
 pageSize:Number      每页显示条目，默认是10
 currentPage:Number   当前页 ，默认是1
 inputType: String  checkbox（全选），radio(单选)，false(无)
 editor: Object    是否需要编辑   1.false 无编辑  2.Array[buttons]
 pagination: boolean   是否需要分页    false or true
 * 
 * */

baseApp.prototype.Grid = function(options){
    this.options = {
        isCustom:options.isCustom?options.isCustom:false,
        url:options.url?options.url:"",
        cols:options.cols?options.cols:[],
        pageSize:options.pageSize?options.pageSize:10,
        currentPage:options.currentPage?options.currentPage:1,
        hasCheckbox:options.hasCheckbox?options.hasCheckbox:false,
        editor:options.editor?options.editor:false
    };
    
    this.data = null;
    this.cols = [];
    this.gridContainer = $("#"+options.containerId);
    this.grid = null;
    
    var that = this;
    this.init = function(){
      
       this.gridContainer.html("<table class='table table-striped'><thead></thead><tbody></tbody><tfoot></tfoot></table>");
       this.grid = this.gridContainer.find("table:eq(0)");
       
       if(options.isCustom){
           $.ajax({
             type: "get",
             async: true,
             url:   option.url,
             dataType:"json",
             data: this.getPageParams(),
             //data : {"accessToken":$(".token-input").val()},
             //jsonpCallback:"callback"+BaseApp.getRad(),
             //dataType: "jsonp",
             success: function(data){
                 this.setThead(data.columns);
                 this.setTbody(data.list);
                 this.setTfoot();
             },
             error: function(XMLHttpRequest, textStatus, errorThrown){
                 if(textStatus=="timeout"){  
                     alert("加载超时，请重试");  
                 }else{   
                     alert("网络出错");   
                }
             }
           });
       }else{
            this.setThead();
            this.setTbody();
            this.setTfoot();
       }
    };

    this.setThead = function(columns){
        if(columns){ 
            
        }else{
            var tr = $(document.createElement("tr"));
           
            if(this.options.hasCheckbox){
                tr.append("<th sign='checkbox'><input type='checkbox' name='cball' class='cball'/></th");
                that.cols.push("checkbox");
            }
            $(this.options.cols).each(function(i,o){
                tr.append("<th sign='"+o.id+"'>"+o.title+"</th");
                that.cols.push(o.name);
            });
            if(this.options.editor){
                tr.append("<th sign='editor'>操作</th");
                that.cols.push("editor");
            }
            this.grid.find("thead").append(tr);
        }
    };
    this.setTbody = function(list){
        if(list){ 
            
        }else{
             $.ajax({
                 type: "get",
                 async: true,
                 url:   options.url,
                 data: this.getPageParams(),
                 dataType:"json",
                 success: function(data){
                     that.drawGrid(data.items);
                 },
                 error: function(XMLHttpRequest, textStatus, errorThrown){
                     if(textStatus=="timeout"){  
                         alert("加载超时，请重试");  
                     }else{   
                         alert("网络出错");   
                    }
                 }
             });
        }
    };
    this.setTfoot = function(){
        
    };

    this.setPageParams = function(params){
        for(var key in params){
             this.options[key] = params[key];
        }
    };
    this.getPageParams = function(){
      var params = [];
      params.pageSize = this.options.pageSize;
      params.currentPage = this.options.currentPage;
      params.accessToken = this.options.accessToken;
      return params;  
    };
    this.drawGrid = function(items){
        var tbody = this.grid.find("tbody")[0];
         $(items).each(function(m,n){
            var tr = $(document.createElement("tr"));
            $(that.cols).each(function(i,o){
                switch(o){
                    case "checkbox":
                        tr.append("<th sign='"+o+"'><input type='"+o+"' name='cb' class='cb'/></th");
                    break;
                    
                   
                    case "editor":
                        $(that.options.editor).each(function(i1,o1){
                            
                            var btn = "<button sid='"+n.id+"' class='"+o1.cls+" "+o1.name+"'>";
                            if(o1.icon){
                                btn+="<i class='"+o1.icon+"'></i>";
                            }
                            btn+=o1.title+"</button>";
                            
                            tr.append("<th sign='"+o+"'>"+btn+"</th");
                        });
                    break;
                    
                    default:
                        for(var key in n){
                            if(key==o){
                                 tr.append("<th sign='"+key+"'>"+n[key]+"</th");
                            }
                        }
                       
                    break;
                    
                }
                    
             });
           $(tbody).append(tr);
           
        });
        $(that.options.editor).each(function(i,o){
            $("."+o.name).click(function(){
               o.func($(this)); 
            });
        });
        BaseApp.checkAll(this.gridContainer);
    };
    this.init();
};

baseApp.prototype.checkAll = function(container){
    var cbs= $(container).find("input[name='cb']");
    var cball = $(container).find("input[name='cball']");
    cball.click(function(){
        
        if($(this).is(':checked')){
            cbs.prop("checked",true);
        }else{
            cbs.prop("checked",false);
        }
        
    });
   
    cbs.click(function(){
           var isAll = true;
           cbs.each(function(){
              if(!$(this).is(':checked')) {
                  isAll = false;
                  return false;
              }
           });
           if(isAll){
               cball.prop("checked",true);
           }else{
               cball.prop("checked",false);
           }
           
       });
   
};

baseApp.prototype.getRadio = function(name){
    return $("input[name='"+name+"']:checked").val();
};

baseApp.prototype.getCheckbox = function(name){
	var cbs = [];
	var cbs = $("input[name='"+name+"']:checked");
    return cbs;
};

baseApp.prototype.getToken = function(){
   return  $.cookie('accessToken');
};

baseApp.prototype.setToken = function(v){
    var date = new Date();  
    date.setTime(date.getTime() + (7 * 24 * 60 * 60 * 1000)); //三天后的这个时候过期  
    $.cookie(this.COOKIE_TOKEN_NAME, v, { path: '/', expires: date });  
};
baseApp.prototype.setSideMenu = function(container,data,hasActive){
	$(container).html("");
	var s = "<ul class='sidebar'>";
	$(data).each(function(i,o){
		var hasChild = false;
		if(o.child){
			hasChild = true;
		}
		var active="";
		if(hasActive&&i==0){
			active = " active";
		}
		if(sideMenuIcons){
			
			s +=  "<li class='treeview"+active+"' type='"+o.type+"' page='"+o.page+"' hasChild="+hasChild+">"+
					"<i class='"+sideMenuIcons[i]+" menuIcon' ></i><span >"+o.name+"</span>";
                    if(hasChild){
                    	s+="<i class='fa fa-angle-left pull-right'></i>";
                    }
			
			
		}else{
			s +=  "<li class='treeview"+active+"' type='"+o.type+"' page='"+o.page+"' hasChild="+hasChild+">"+
					"<i class='fa fa-folder menuIcon'></i><span >"+o.name+"</span>";
                    if(hasChild){
                    	s+="<i class='fa fa-angle-left pull-right'></i>";
                    }
			
		}
		
                 
        if(o.child){
        	s+="<ul class='treeview-menu' style='display: none;'>";
        	$(o.child).each(function(i1,o1){
        		 s+="<li class='active'><a href='javascript:void(0)' page='"+o1.page+"'><i class='fa fa-file-o'></i> "+o1.name+"</a></li>";
        	});
        	s+="</ul>";
        }
		s+="</li>";
	});
	s+="</ul>";
	$(container).html(s);
	/*
	$(".showSideMenu").click(function(){
			if($(this).attr("show")=="1"){
				$(".sideMenuText").hide();
				$(".main-sidebar").css("width",50);
				$(".content-wrapper").css("margin-left",50);
				
				$(this).attr("show","0");
			}else{
				$(".main-sidebar").css("width",110);
				$(".content-wrapper").css("margin-left",110);
				window.setTimeout(function(){$(".sideMenuText").show();},300);
				
				$(this).attr("show","1");
			}
		});*/
	
	
};


baseApp.prototype.loading = function(container){
    var s = "<div class=\"col-xs-12 load\" style=\"text-align: center;padding:20%;color:#fff\">"+
             "<i class=\"fa fa-spinner fa-spin fa-2x fa-fw margin-bottom\" style=\"font-size:16px\"></i>读取中...</div>";
    $(container).html(s);
};
baseApp.prototype.getGeoCoord = function(){
	var geoCoords = {
		
		"jiangsu":{
                '上海': [121.4648,31.2891],
                '东莞': [113.8953,22.901],
                '东营': [118.7073,37.5513],
                '中山': [113.4229,22.478],
                '临汾': [111.4783,36.1615],
                '临沂': [118.3118,35.2936],
                '丹东': [124.541,40.4242],
                '丽水': [119.5642,28.1854],
                '乌鲁木齐': [87.9236,43.5883],
                '佛山': [112.8955,23.1097],
                '保定': [115.0488,39.0948],
                '兰州': [103.5901,36.3043],
                '包头': [110.3467,41.4899],
                '北京': [116.4551,40.2539],
                '北海': [109.314,21.6211],
                '江苏省公安厅': [117.0208,32.8162],
                '南京': [118.8062,31.9208],
                '南宁': [108.479,23.1152],
                '南昌': [116.0046,28.6633],
                '南通': [121.1023,32.1625],
                '厦门': [118.1689,24.6478],
                '台州': [121.1353,28.6688],
                '合肥': [117.29,32.0581],
                '呼和浩特': [111.4124,40.4901],
                '咸阳': [108.4131,34.8706],
                '哈尔滨': [127.9688,45.368],
                '唐山': [118.4766,39.6826],
                '嘉兴': [120.9155,30.6354],
                '大同': [113.7854,39.8035],
                '大连': [122.2229,39.4409],
                '天津': [117.4219,39.4189],
                '太原': [112.3352,37.9413],
                '威海': [121.9482,37.1393],
                '宁波': [121.5967,29.6466],
                '宝鸡': [107.1826,34.3433],
                '宿迁': [118.5535,33.7775],
                '常州': [119.4543,31.5582],
                '广州': [113.5107,23.2196],
                '廊坊': [116.521,39.0509],
                '延安': [109.1052,36.4252],
                '张家口': [115.1477,40.8527],
                '徐州': [117.5208,34.3268],
                '德州': [116.6858,37.2107],
                '惠州': [114.6204,23.1647],
                '成都': [103.9526,30.7617],
                '扬州': [119.4653,32.8162],
                '承德': [117.5757,41.4075],
                '拉萨': [91.1865,30.1465],
                '无锡': [120.3442,31.5527],
                '日照': [119.2786,35.5023],
                '昆明': [102.9199,25.4663],
                '杭州': [119.5313,29.8773],
                '枣庄': [117.323,34.8926],
                '柳州': [109.3799,24.9774],
                '株洲': [113.5327,27.0319],
                '武汉': [114.3896,30.6628],
                '汕头': [117.1692,23.3405],
                '江门': [112.6318,22.1484],
                '沈阳': [123.1238,42.1216],
                '沧州': [116.8286,38.2104],
                '河源': [114.917,23.9722],
                '泉州': [118.3228,25.1147],
                '泰安': [117.0264,36.0516],
                '泰州': [120.0586,32.5525],
                '济南': [117.1582,36.8701],
                '济宁': [116.8286,35.3375],
                '海口': [110.3893,19.8516],
                '淄博': [118.0371,36.6064],
                '淮安': [118.927,33.4039],
                '深圳': [114.5435,22.5439],
                '清远': [112.9175,24.3292],
                '温州': [120.498,27.8119],
                '渭南': [109.7864,35.0299],
                '湖州': [119.8608,30.7782],
                '湘潭': [112.5439,27.7075],
                '滨州': [117.8174,37.4963],
                '潍坊': [119.0918,36.524],
                '烟台': [120.7397,37.5128],
                '玉溪': [101.9312,23.8898],
                '珠海': [113.7305,22.1155],
                '盐城': [120.2234,33.5577],
                '盘锦': [121.9482,41.0449],
                '石家庄': [114.4995,38.1006],
                '福州': [119.4543,25.9222],
                '秦皇岛': [119.2126,40.0232],
                '绍兴': [120.564,29.7565],
                '聊城': [115.9167,36.4032],
                '肇庆': [112.1265,23.5822],
                '舟山': [122.2559,30.2234],
                '苏州': [120.6519,31.3989],
                '莱芜': [117.6526,36.2714],
                '菏泽': [115.6201,35.2057],
                '营口': [122.4316,40.4297],
                '葫芦岛': [120.1575,40.578],
                '衡水': [115.8838,37.7161],
                '衢州': [118.6853,28.8666],
                '西宁': [101.4038,36.8207],
                '西安': [109.1162,34.2004],
                '贵阳': [106.6992,26.7682],
                '连云港': [119.1248,34.552],
                '邢台': [114.8071,37.2821],
                '邯郸': [114.4775,36.535],
                '郑州': [113.4668,34.6234],
                '鄂尔多斯': [108.9734,39.2487],
                '重庆': [107.7539,30.1904],
                '金华': [120.0037,29.1028],
                '铜川': [109.0393,35.1947],
                '银川': [106.3586,38.1775],
                '镇江': [119.4763,31.9702],
                '长春': [125.8154,44.2584],
                '长沙': [113.0823,28.2568],
                '长治': [112.8625,36.4746],
                '阳泉': [113.4778,38.0951],
                '青岛': [120.4651,36.3373],
                '韶关': [113.7964,24.7028],
                '泰州市公安厅': [119.620187,32.488406],
                '兴化市': [119.840162,32.938065],
                '姜堰区': [120.148208,32.508483],
                '泰兴市': [120.020228,32.168784],
                '高港区': [119.88166,32.315701],
                '靖江市': [120.26825,32.018168],
                '海陵区': [119.920187,32.488406],
            }
		
	};
	return geoCoords;
};
baseApp.prototype.getMediaLayout = function(){
	var w = $(".content-wrapper").innerWidth()-$(".slider-tj").outerWidth()-30-($(".carousel-control").outerWidth()+20)*2;
	var h = $(".content-wrapper").innerHeight()-$(".core-slider").outerHeight();
	return {"w":w,"h":h};
};


baseApp.prototype.textAnimate = function(containerId,step){
	var objAry = $(containerId+" span[type='val']");
	if(!step){
		step = 4;
	}
	var cache = this.textAnimateMap[containerId];
	if(cache){
		
		$(cache).each(function(i,o){
			window.clearInterval(o);
		});
		
		delete cache;
	}
	var tempAry = [];
	$(objAry).each(function(i,o){
		
		var v = Number($(o).attr("v"));
		if(!v){
			return true;
		}
		var isFloat = false;
		if(parseInt(v)!=v){
			isFloat = true;
		}
		
		var temp = 0;
		
		var lis = window.setInterval(function(){
			if(temp>Number(v)){
				if(isFloat){
					$(o).html(v+"%");
				}else{
					$(o).html(v);
				}
				
				window.clearInterval(lis);
				return;
			}
			if(isFloat){
				$(o).html(temp+"%");
			}else{
				$(o).html(temp);
			}
			
			temp+=step;
			
		},50);
		tempAry.push(lis);
		
		
	});
	BaseApp.textAnimateMap[containerId] = tempAry;
};

baseApp.prototype.clearMapChart = function(){
	for(var key in BaseApp.mapCharts){
		BaseApp.mapCharts[key].dispose();
		delete BaseApp.mapCharts[key];
	}
};

baseApp.prototype.msg = function(type,title,text){
  
  var icon = "";
  switch(type){
  	case "success":
  	case "info":
  		type = "success";
  		icon = "ion-checkmark-circled";
  	break;
  	
  	case "error":
  	    type = "danger";
  		icon = "ion-android-cancel";
  	break;
  	
  	case "params":
  		icon = "ion-android-cancel";
  	break;
  }

 
  if(type=="params"){
  	 $(".paramsMsg").remove();
	  	var pmBox =  "<div style='font-size:13px;' class='alert alert-info alert-dismissable paramsMsg'>"+
		   "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>"+
		   "<i style='float:left;' class='ion-chatbubble-working'></i>"+
		   "<span style='float:left;line-height:30px;'>"+title+"</span>";
		   if(text){
		   	pmBox+="<span style='float:left;clear:both'>"+text+"</span>";
		   }
		   pmBox+"</div>";
		$("body").append(pmBox);
	   $(".paramsMsg").css("bottom",-($(".paramsMsg").height()));
	   $(".paramsMsg").show();
	   
	   $(".paramsMsg").animate({
	         	bottom:0,
	         	opacity:1
	         },1000);
	   if(BaseApp.paramsMsgTimer){
	      window.clearTimeout(BaseApp.paramsMsgTimer);
	   }
  }else{
  	  $(".coreMsg-"+type).remove();
	  var s = "<div style='font-size:13px;' class='alert alert-"+type+" alert-dismissable coreMsg-"+type+"'>"+
		   "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>"+
		   "<i style='float:left;' class='"+icon+"'></i>"+
		   "<span style='float:left;line-height:30px;'>"+title+"</span>";
		   if(text){
		   	s+="<span style='float:left;clear:both'>"+text+"</span>";
		   }
		   s+"</div>";
		$("body").append(s);
	
	   $(".coreMsg-"+type).css("bottom",-($(".coreMsg-"+type).height()));
	   $(".coreMsg-"+type).show();
	   $(".coreMsg-"+type).animate({
	         	bottom:0,
	         	opacity:1
	         },1000);
	   if(BaseApp.msgTimer){
	      window.clearTimeout(BaseApp.msgTimer);
	   }
	 
	  
/*
	   BaseApp.msgTimer =  window.setTimeout(function(){
	                $(".coreMsg-"+type).animate({
	                    bottom:-($(".coreMsg-"+type).height()),
	                    opacity:0
	                },1000,function(){
	                    $(".coreMsg-"+type).remove();
	                });
	       },5000);*/

	  
   }
};

baseApp.prototype.setTimePickerDate = function(){
	if($('.form_datetime').length>0){
		$('.form_datetime').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	        showMeridian: 1
	    });
	}
};

baseApp.prototype.setTimePicker = function(){
	if($('.form_date').length>0){
		$('.form_date').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0,
		});
	}
};

baseApp.prototype.icheckChange = function(){
	$('input').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
    	radioClass: 'iradio_minimal-blue',
    	increaseArea: '20%' 

    });
};

baseApp.prototype.selectChange = function(){
	$('select').comboSelect();
};
/**长连接*/
/*
 options 
 1.url(地址) String
 2.callback(回调方法) function
 3.callbackParams(回调方法参数) object
 4.params(url参数) object
 5.timeout(超时时间) int（单位：秒）
 6.timer(时间片) int（单位：秒）
 * */
baseApp.prototype.longConnection = function(option){
	
	if(!option.timeout){
			option.timeout = 0;//超时默认10秒
	}
	
	this.status = 0;

	BaseApp.longConnectionObj[option.type]  = this;
	this.create = function(){
		if(this.status==0){
			 $.ajax({  
	            type:"POST",  
	            url:option.url,  
	            dataType : "json",  
	            timeout : option.timeout*1000,  
	            data : option.params,  
	            success:function(data,textStatus){

	                option.callback(data,option.callbackParams);
	                if(option.timer<=0){
	                	BaseApp.longConnectionObj[option.type].create(option);
	                }else{
	                	window.setTimeout(function(){BaseApp.longConnectionObj[option.type].create(option);},option.timer*1000);
	                }
	            },  
	            complete:function(XMLHttpRequest,textStatus){  
	                if(XMLHttpRequest.readyState=="4"){  
	                    //BaseApp.msg("success",XMLHttpRequest.responseText);  
	                }  
	            },  
	            error: function(XMLHttpRequest,textStatus,errorThrown){
	                if(textStatus=="timeout"){
	                	BaseApp.longConnection(option);
	                };
	            }
	        }); 
       }
	};
	
	this.stop = function(){
		this.status = 1;
	};
	this.start = function(){
		this.status = 0;
		this.create();
	};
	this.create();
};

baseApp.prototype.clearForm = function(container){
	$(container).find("input[type='checkbox']").prop("checked",false);
	$(container).find("input[type='text']").val("");
	$(container).find("select").val("");
};
baseApp.prototype.showParams = function(params){
	var s = "";
	for(var key in params){
		s+=key+"："+params[key]+"</br>";
	}
	BaseApp.msg("params","提交参数",s);
};

baseApp.prototype.modalEffect = function(){
	 $(".modal[drag='true']").draggable({   
        handle: ".modal-header",   
        cursor: 'move',   
        refreshPositions: false  
    });  
};
/**
 *表单校验
 * validates是表单控件校验规则（必须）
  		属性
  		1.必填项 required(boolean) (例如：required:true)
  		2.整数  number(boolean或整数组) (例如: number:true   number[200,500]{200-500之间})
  		3.数字  floating(boolean或数组) (例如: floating:true   floating[0.3,1]{0.3-1之间})
  		4.金额格式 isMoney (boolean)
  		5.邮件格式 isEmail (boolean)
  		6.手机格式 isMobile (boolean)
  		7.固定电话 isTelephone (boolean)
  		8.数据长度 length (int) 不超过n个字符
  		9.过滤非法字符，只允许数字、字母、下划线  isFliter (boolean)
  		//10.ajax校验  ajaxUrl(url)
 * 
 * container（可选），表单容器，不填默认是整个document
 * **/
baseApp.prototype.validate = function(validates,container){
	var isPass = true;
	var errorObj = null;
	if(!container){
		container = $("body");
	}
	$(".errorContainer").removeClass("errorContainer");
	$(".errorTip").remove();
	if(validates){
		for(var validate in validates){
			var obj = $(container).find("[name='"+validate+"']");
			if($(obj).length>0){
				switch($(obj)[0].tagName.toLowerCase()){
					case "input":
						var type = "text";
						if($(obj).attr("type")){
							type = $(obj).attr("type").toLowerCase();
						}
						
						switch(type){
							case "text":
							 var rules = validates[validate];
								for(var rule in rules){
									switch(rule){
										case "required"://是否必填
											if(!$(obj).val()){
												$(obj).addClass("errorContainer");
												//alert($(obj)[0].tagName);
												$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>必填项</div>");
												isPass = false;
												return false;	
											}
										break;
										
										case "length"://字符串长度
											if($(obj).val().length>rules[rule]){
												$(obj).addClass("errorContainer");
												$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>不能超过"+rules[rule]+"个字符</div>");
												isPass = false;
												return false;
											}
										break;
										
										case "number"://1.boolean(非0整数) 2.整数组 int[min,max],分别是数字的上下限
											var n = rules[rule];
											var ex = /^\d+$/;
											if($.isArray(n)){
												if (!ex.test($(obj).val())){
													$(obj).addClass("errorContainer");
													$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>非整数</div>");
													isPass = false;
													return false;
												}else{
													if($(obj).val()<=0){
														$(obj).addClass("errorContainer");
														$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>不能为0或负数</div>");
														isPass = false;
														return false;
													}
													if($(obj).val()<n[0]){
														$(obj).addClass("errorContainer");
														$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>数字过小</div>");
														isPass = false;
														return false;
													}
													if($(obj).val()>n[1]){
														$(obj).addClass("errorContainer");
														$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>数字过大</div>");
														isPass = false;
														return false;
													}
												}
											}else{
												if (n){
													if (!ex.test($(obj).val())){
														$(obj).addClass("errorContainer");
														$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>非整数</div>");
														isPass = false;
														return false;
													}
													if($(obj).val()<=0){
														$(obj).addClass("errorContainer");
														$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>不能为0或负数</div>");
														isPass = false;
														return false;
													}
												}
												
											}
											
											
										break;
										
										case "floating"://浮点型
										    var isFloat = /^\d+(\.\d+)?$/;
										    var n = rules[rule];
										    if($.isArray(n)){
												if (!isFloat.test($(obj).val())){
													$(obj).addClass("errorContainer");
													$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>非数字</div>");
													isPass = false;
													return false;
												}else{
													if($(obj).val()<=0){
														$(obj).addClass("errorContainer");
														$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>不能为0或负数</div>");
														isPass = false;
														return false;
													}
													if($(obj).val()<n[0]){
														$(obj).addClass("errorContainer");
														$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>数字过小</div>");
														isPass = false;
														return false;
													}
													if($(obj).val()>n[1]){
														$(obj).addClass("errorContainer");
														$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>数字过大</div>");
														isPass = false;
														return false;
													}
												}
											}else{
												if (n){
													if (!isFloat.test($(obj).val())){
														$(obj).addClass("errorContainer");
														$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>非数字</div>");
														isPass = false;
														return false;
													}
													if($(obj).val()<=0){
														$(obj).addClass("errorContainer");
														$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>不能为0或负数</div>");
														isPass = false;
														return false;
													}
												}
												
											}
										break;
										
										case "isMoney"://金额
											var isMoney = /^\d+\.?\d{1,2}$/;
											var money = $(obj).val();
											if(rules[rule]){
												if(!isMoney.test(money)){
													$(obj).addClass("errorContainer");
													$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>金额格式不符</div>");
													isPass = false;
													return false;
												}
											}
											
										break;
										
										case "isMobile"://手机号
											var mobile = $(obj).val();
											var isMobile = /(1[3-9]\d{9}$)/;
											if(rules[rule]){
												if (!isMobile.test(mobile)) {
													$(obj).addClass("errorContainer");
													$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>请填写正确的手机号码</div>");
													isPass = false;
													return false;
												}
											}
										break;
										
										case "isTelephone"://电话
											var phone = $(obj).val();
											var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
											if(rules[rule]){
												if (!isPhone.test(phone)) { 
													$(obj).addClass("errorContainer");
													$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>请填写正确的电话号码</div>");
													isPass = false;
													return false;
												}
											}
										break;
										
										case "isEmail"://邮箱
											var isEmail = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
											var email = $(obj).val();
											if(rules[rule]){
												if(!isEmail.test(email)){
													$(obj).addClass("errorContainer");
													$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>请填写正确的邮箱地址</div>");
													isPass = false;
													return false;
												}
											}
										break;
										
										case "isFliter"://只允许字母、数字、下划线
											var isFliter = /^\w+$/;
											if(rules[rule]){
												if(!isFliter.test($(obj).val())){
													$(obj).addClass("errorContainer");
													$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>只允许字母、数字、下划线</div>");
													isPass = false;
													return false;
												}
											}
										break;
										
									}
								}
							break;
							
							case "radio":
							case "checkbox":
								if(BaseApp.getCheckbox(validate).length==0){
									if($(obj).parent().hasClass("inputGroup")){
										$(obj).parent().addClass("errorContainer");
										$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>必选项</div>");
									}
									if($(obj).parent().parent().hasClass("inputGroup")){
										$(obj).parent().parent().addClass("errorContainer");
										$(obj).parent().parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>必选项</div>");
									}
									isPass = false;
									return false;
								}
							break;
						}
					break;
					
					case "select":
						if($(obj).val()=="-1"){
							$(obj).addClass("errorContainer");
							$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>必选项</div>");
							isPass = false;
							return false;
						}
					break;
					
					case "textarea":
					
						if(!$(obj).val()){
							$(obj).addClass("errorContainer");
							$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>必填项</div>");
							isPass = false;
							return false;
						}
						var rules = validates[validate];
						
						for(var rule in rules){
							switch(rule){
								case "isFliter":
									var isFliter = /^\w+$/;
									if(rules[rule]){
										if(!isFliter.test($(obj).val())){
											$(obj).addClass("errorContainer");
											$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>只允许字母、数字、下划线</div>");
											isPass = false;
											return false;
										}
									}
								break;
								
								case "length":
									if($(obj).val().length>rules[rule]){
										$(obj).addClass("errorContainer");
										$(obj).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>不能超过"+rules[rule]+"个字符</div>");
										isPass = false;
										return false;
									}
								break;
							}
						}
						
					break;
				}
			}
		}
	}
	
	return isPass;
	
};
/**
 *option
 * modalId: String(必须)
 * title : String(必须)
 * size :1.lg 2.normal 3.sm (可选) String
 * buttons:[] Array  按钮
    *name : String 按钮的name 
    *handle: function() //按钮的click事件
    *handlen: 1.function() 2.close String(默认)//按钮点击后的回调方法
    *title:String 按钮的title
 *  **/
baseApp.prototype.createModal = function(option){
	var returnObj = {};
	$("#"+option.modalId).remove();
	option.size?option.size:"lg";
	
	var s = "<div class='modal fade' drag='true' id='"+option.modalId+"' tabindex='-1' role='dialog' aria-labelledby='"+option.modalId+"Label' aria-hidden='true' data-backdrop='true'>"+
			   "<div class='modal-dialog modal-"+option.size+"'>"+
				  "<div class='modal-content'>"+
					 "<div class='modal-header' style='background:#428bca;'>"+
						"<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>"+
						"<h4 class='modal-title' id='"+option.modalId+"Label'>"+"<span style='font-size:16px;color:#FFF'>"+option.title+"</span>"+
						"</h4>"+
					 "</div>"+
					 "<div class='modal-body "+option.modalId+"-body'></div>"+
					 "<div class='modal-footer'>"+
						// "<button class='btn btn-default cancel' data-dismiss='modal'>取消</button>"+
						// "<button class='btn btn-primary accept'>确定</button>"+			 "</div>"+
				  "</div>"+
			   "</div>"+
			"</div>";
	$("body").append(s);
	if(option.hasScroll){
		$("#"+option.modalId).find(".modal-body").css("height",$(document).height()-220);
	}
	BaseApp.loading($("#"+option.modalId).find(".modal-body"));
	 
	var modal = $("#"+option.modalId).modal();
	var buttons = {};
	if(option.buttons&&option.buttons.length>0){
		$(option.buttons).each(function(i,o){
			var button = document.createElement("button");
			$(button).attr("class","btn btn-default"+" "+o.name);
			$(button).css({"background": "#428bca","border": "1px solid #428bca","color":"#FFF"});
			$(button).html(o.title);
			buttons[o.name] = button;
			if(o.hide){
				$(button).css("display","none");
			}
			switch(o.name){
				case "cancel":
					//$(button).prepend("<i class='ion-close'> </i>");
					$(button).attr("class","btn btn-default"+" "+o.name);
					$(button).attr("data-dismiss","modal");
				break;
				
				case "closePage":
					$(button).attr("onclick","window.close()");
					$(button).attr("class","btn btn-default"+" "+o.name);
					$(button).attr("data-dismiss","modal");
				break;
				
				default:
					$(button).prepend("<i class='ion-checkmark'> </i>");
					$(button).attr("class","btn btn-primary"+" "+o.name);
					$(button).click(function(){
						var pass = o.handle(returnObj);
						if(pass!="error"){
							if(o.handlen){
								switch(o.handlen){
									case "close":
										$("#"+option.modalId).modal("hide");
									break;
									default:
										o.handlen();
									break;
								}
							}
						}
						
					});
				break;
				
			}
			$("#"+option.modalId).find(".modal-footer").append(button);
		});
	}
	returnObj.modalContainer = $("#"+option.modalId).find(".modal-body");
	returnObj.buttons = buttons;
	returnObj.modal = modal;
	return returnObj;
};
/**
 *	option
 * 		1.container:obj
 * 		2.dataRangeData:Obj
 * 		2.callback:function()
 *  **/
baseApp.prototype.setMapDataRange = function(option){
	var container = option.container?option.container:$(document);
	option.dataRangeData = option.dataRangeData?option.dataRangeData:null;
	if(!option.dataRangeData||option.dataRangeData.length==0){return;}
	var callback = option.callback?option.callback:null;
	var ul = document.createElement("ul");
	$(dataRangeData).each(function(i,o){
		var li = document.createElement("li");
		var colorSpan = document.createElement("span");
		$(colorSpan).attr("color",o.color);
		var textSpan = document.createElement("span");
		$(colorSpan).addClass("colorSpan");
		if(o.disabled){
			$(li).addClass("disabled");
			$(textSpan).attr("color","#666");
		}else{
			$(colorSpan).css("background",o.color);
			$(textSpan).attr("color",o.color);
		}
		
		$(li).attr("status",o.status);
		$(colorSpan).click(function(){
			if($(this).parent().hasClass("disabled")){
				$(this).parent().removeClass("disabled");
				$(this).css("background",$(this).attr("color"));
				$(dataRangeData).each(function(i1,o1){
					if(Number($(this).attr("status"))==o1.status){
						dataRangeData[i1].disabled = false;
						return false;
					}
				});
			}else{
				$(this).parent().addClass("disabled");
				$(this).css("background","#666");
				var index = 0;
				$(dataRangeData).each(function(i1,o1){
					if(Number($(this).attr("status"))==o1.status){
						dataRangeData[i1].disabled = true;
						index = i1;
						return false;
					}
				});
				
			}
			if(callback){
				callback();
			}
		});
		
		$(textSpan).addClass("textSpan");
		$(textSpan).html(o.title);
		$(li).append(colorSpan);$(li).append(textSpan);
		$(ul).append(li);
	});
	$(container).append(ul);
	
};
/**
 *	option
 * 		map:obj
 * 		mapOption:mapOption
 * 		mapChartData:obj
 * 		mapArea:Obj
 * 		dataRangeContainer:obj
 *  **/
baseApp.prototype.updateMapData = function(option){
	
	var mapChartData = option.mapChartData?option.mapChartData:null;
	var map = option.map;
	map.refresh();
	var mapContainer = option.mapContainer;
	var mapOption = option.mapOption;
	var mapArea = option.mapArea;
	var dataRangeContainer = option.dataRangeContainer?option.dataRangeContainer:null;
	var data = [];var data2 = [];
	var mapAreaObj = {"name":mapArea};
	if(mapChartData&&mapChartData.length>0){
		
		var activeLiAry = $(dataRangeContainer).find("li");
		$(activeLiAry).each(function(index,obj){
			if(!$(obj).hasClass("disabled")){
				
				var status = Number($(obj).attr("status"));
				$(mapChartData).each(function(i,o){
					if(status==o.value){
						var tmpAry = [];
						tmpAry.push(mapAreaObj);
						var ls = {};
						ls.name = o.name;
						ls.value = o.value;
						tmpAry.push(ls);
						data.push(tmpAry);
						data2.push(ls);
					}
					
				});
			}
		});
		mapOption.series[0].markLine.data = data;
		mapOption.series[0].markPoint.data = data2;
		map.setOption(mapOption,true);
		//
	}
};

baseApp.prototype.tableStriped = function(containerId){
	$("#"+containerId).find('tr.odd').css('background-color','#e9eaed');
	$("#"+containerId).find('tr.even').css('background-color','#fff');
	$("#"+containerId).find('tbody').find('tr').hover(function () {
	    $(this).addClass('trHover');
	}, function () {
	    $(this).removeClass("trHover");
	});
};








