function flow(container,flowData){
	this.step = 20;
	this.intervalObj = null;
	this.container = container;
	this.flowData = flowData;
	var that = this;
	this.lineAnimate = function(obj){
		var pw = $(obj).parent().width();
      	var i=0;
      	that.intervalObj = window.setInterval(function(){
      		i+=2;
      		if(i>pw){
      			i=0;
      		}
      		$(obj).css("width",i);
      	},that.step);
	};
	
	this.flowAction = function(){
	   $(".active").removeClass("active");
	   $(".animate-line").removeAttr("style");
	   $(".animate-line").removeAttr("class");
	   if(intervalObj){
		   window.clearInterval(intervalObj);
	   }
	   $(active).addClass("active");
	   if($(active).next()){
		   var amimateObj = $(active).next().find("span");
		   amimateObj.addClass("animate-line");
		   lineAnimate(amimateObj);
	   }
	   that.getflowContent(active);
	};
	
	this.getflowContent= function(active){
		if($(".flowContent")){
			$(".flowContent div").hide();
	   		$(".flowContent div:eq("+(Number($(active).attr("att"))-1)+")").show();
		}
	};
	
	this.init = function(){
		this.create();
		var active = $(that.container).find(".active");
		$(active).click(function(){
			// alert("这是第"+$(this).attr("att")+"步流程！");
		});
      	$(that.container).find("div").each(function(i,o){
      		if($(o).attr("att")==$(active).attr("att")){
      			return false;
      		}else{
      			if($(o).hasClass("flowline")){
      				$(o).find("span").addClass("green");
      			}
      			if($(o).hasClass("item")){
      				$(o).addClass("ready");
      			}
      		}
      	});
      	var animateObj = null;
      	if($(active).next()){
      		animateObj = $(active).next().find("span");
      		that.lineAnimate(animateObj);
      	}
	};
	this.create = function(){
		if(that.flowData&&that.flowData.length>0){
			var flowData = that.flowData;
			var s = "<div class='flow'>";
            
            var active = " ready";
			$(flowData).each(function(i,o){
				if(o.isActive){
					active = " active";
				}
				if(i==(flowData.length-1)){
					s+="<div class='item"+active+"' sid='"+o.sid+"' att='"+(i+1)+"'>"+
				       		"<div>"+
				       			"<span class='icon' style='background:url("+$.base+"/images/process/center/flow/"+o.name+".png) center no-repeat;'></span>"+
				       			"<span class='text'>"+(i+1)+"."+o.text+"</span>"+
				       		"</div>"+
		      			"</div>";
				}else{
					s+="<div class='item"+active+"' sid='"+o.sid+"' att='"+(i+1)+"'>"+
				       		"<div>"+
				       			"<span class='icon' style='background:url("+$.base+"/images/process/center/flow/"+o.name+".png) center no-repeat;'></span>"+
				       			"<span class='text'>"+(i+1)+"."+o.text+"</span>"+
				       		"</div>"+
		      			"</div>"+
				       "<div class='flowline'>"+
				       		"<span></span>"+
				       "</div>";
				}
				if(o.isActive){
					active = " ";
				}
			});
			s+="</div>";
			$(that.container).html(s);
		}
	};
};
