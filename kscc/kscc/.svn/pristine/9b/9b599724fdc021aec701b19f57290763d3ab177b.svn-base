//获取上传的列表
	function viewFileUpload(fileIdArr){
		if(fileIdArr.length>0){
			//查询上传文件的详情
		var fileList="";
		$.each(fileIdArr,function(index,fileItem){
			$.ajax({
				async: false, 
				type:"get",
				url:$.base+"/tag/filemanager/fileinfo/"+fileItem,
				success:function(data){
					fileList +="<tr class='basicFiles'><td class='fileContent' fileId='"+data.id+"' filePath='"+data.filePath+"'>"+data.fileName+
					"</td><td style='width:100px;padding-left:10px'><a class='downloadIcon' href='"+$.base+"/tag/filemanager/download/"+data.id+"'>下载</a></tr>";
				}
			});
		});
		$(".basicSourceFile").html(fileList);
		}
	}
	function viewAttachment(fileAttachment){
		if(fileAttachment.length>0){
			//查询上传文件的详情
		var fileList="";
		$.each(fileAttachment,function(index,fileItem){
			$.ajax({
				async: false, 
				type:"get",
				url:$.base+"/tag/filemanager/fileinfo/"+fileItem,
				success:function(data){
					fileList +="<tr class='filesList'><td class='fileContent' fileId='"+data.id+"' filePath='"+data.filePath+"'>"+data.fileName+
					"</td><td><a class='downloadIcon' href='"+$.base+"/tag/filemanager/download/"+data.id+"'>下载</a></tr>";
				}
			});
		});
		$(".planFile").html(fileList);
		}
	}
	
	//插播一个一键关闭
	function oneAllOpen(){
		var aa=true;
		$(".oneAllOpen").click(function(){
			if(aa==true){
				//说明需要关闭所有
				$(".panel-collapse").each(function(index,item){
					if($(item).hasClass("in")){
						//$(item).removeClass("in");
                        $(item).collapse("hide");
						$(".oneAllOpen").find("span").html("&#xe724;");
						$(item).parent().find(".panel-title a span").html("&#xe724;");
					}
					aa=false;
				});
			}else{
				//说明需要展开所有
				$(".panel-collapse").each(function(index,item){
					if(!$(item).hasClass("in")){
						//$(item).addClass("in");
                        $(item).collapse("show");
						$(".oneAllOpen").find("span").html("&#xe69e;");
						$(item).parent().find(".panel-title a span").html("&#xe69e;");
					}
					aa=true;
				});
			}
		});
	}