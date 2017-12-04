define(["base","bootstrap"],function(base,bootstrap){

	function setBanner(){
    	var itemStrs="";
    	var olStrs="";
    	$.ajax({
            type:"post",
            async: false,
            url:$.base+"/liveBroadCastController/selectHomePage",
            success:function (e) {
                if(e.status==='1'){
                    var pitureList=e.data
                    $(".carousel-indicators").empty();
                    $(".carousel-inner").empty();
                    $.each(pitureList,function(i,v){
                        if(v.networkUrl){
                            if(v.networkUrl.indexOf("http")!==-1){
                                itemStrs+="<div class='item'>"+
                                    "<a target='_blank'  href='"+v.networkUrl+"'><img src='"+$.base+"/loginController/showPic?name="+v.imageUrl+"'></a>"+
                                    "</div>";
                            }
                            else{
                                itemStrs+="<div class='item'>"+
                                    "<a target='_blank' href='https://"+v.networkUrl+"'><img src='"+$.base+"/loginController/showPic?name="+v.imageUrl+"'></a>"+
                                    "</div>";
                            }
                        }
                        else{
                            itemStrs+="<div class='item'>"+
                                "<img src='"+$.base+"/loginController/showPic?name="+v.imageUrl+"'>"+
                                "</div>";
                        }
                        olStrs+="<li data-target='#myCarousel' data-slide-to='"+i+"'></li>";
                    });
                    $(".carousel-indicators").append(olStrs);
                    $(".carousel-inner").append(itemStrs);
                    $(".item:first").addClass("active");
                    $(".carousel-indicators").children(":first").addClass("active");
                }
                else{

                }
            },
            error:function(){}
    	});
    }
	
	return {
		run:function(){
			setBanner();
		}
	};
});
