<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
 request.setCharacterEncoding("UTF-8");
%>
	<style>
	input[type=file]{
	display:inline-block;
	}
	</style>
	      <div class="middleContent heightFull">

                  <div class="uploadPictures clearfix mb-5">
                      <div class="col-xs-6 col-sm-6 col-md-6 uploadDiv clearfix" style="float:left;">
                         <form role="form" id="uploadPicFormFirst" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/liveBroadCastController/addHomePicture">
                             <input type="hidden" name="createId"/>
                             <div class="imgUpDiv">
                                 <div class="set-middle-box">
                                     <div class="imgPreview">
                                         <img src="${pageContext.request.contextPath}/images/no-image.png" id="imgPreviewFirst" flag="0">
                                         <div class="btn-box">
                                             <label class="btn btn-primary btnUpload"  id="imgBtnFirst" for="pictureInputFirst" box="imgPreviewFirst">上传图片</label>
                                             <input type="file" class="form-control form-width" name="file" id ="pictureInputFirst" value="" accept="image/jpg,image/png" />
                                         </div>
                                     </div>
                                     <div class="row-fluid tip-style">* 建议上传宽度为1140px且高度为380px的图片</div>
                                 </div>
                             </div>
                             <div class="row-fluid clearfix forms-box">
                                   <div class="left-input-box">
                                       <div class="form-inline">
                                           <label class="text-center">直播选择:</label>
                                           <%--<a type="button" class="selectBtnFirst" num="1">直播选择</a>--%>
                                           <select class="form-control" name="liveChoose" placeholder="请选择"></select>
                                           <input type="hidden"  name="liveId"/>
                                       </div>
                                       <div class="form-inline">
                                           <label class="text-center" for="networkUrl">URL链接:</label>
                                           <input type="text" class="form-control" name="networkUrl" id ="networkUrlFirst" value="" />
                                       </div>
                                   </div>
                                   <div class="right-btn-box text-center">
                                       <button type="button" class="btn btn-primary imgSubmit" id="imgSubmitFirst" num="0">提交</button>
                                       <button type="button" class="btn btn-default imgCancel imgCancel_1" pos="1" style="margin-left:5px;">取消</button>
                                   </div>
                             </div>
                         </form>
                         <div class="num-box">
                             <span>1</span>
                         </div>
                      </div>
                      <div class="col-xs-6 col-sm-6 col-md-6 uploadDiv" style="float:right;">
                         <form role="form" id="uploadPicFormSecond" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/liveBroadCastController/addHomePicture">
                             <div class="imgUpDiv">
                                <div class="set-middle-box">
                                    <div class="imgPreview">
                                        <img id="imgPreviewSecond" src="${pageContext.request.contextPath}/images/no-image.png"  flag="0" />
                                        <div class="btn-box">
                                            <label class="btn btn-primary btnUpload"  id="imgBtnSecond" for="pictureInputSecond" box="imgPreviewSecond">上传图片</label>
                                            <input type="file" class="form-control form-width" name="file" id ="pictureInputSecond" value="" accept="image/jpg,image/png" />
                                        </div>
                                    </div>
                                    <div class="row-fluid tip-style">* 建议上传宽度为1140px且高度为380px的图片</div>
                                </div>
                             </div>
                             <div class="row-fluid clearfix forms-box">
                                 <div class="left-input-box">
                                     <div class="form-inline">
                                         <label class="text-center">直播选择:</label>
                                         <%--<a type="button" class="selectBtnSecond" num="2">直播选择</a>--%>
                                         <select class="form-control" name="liveChoose" placeholder="请选择"></select>
                                         <input type="hidden"  name="liveId"/>
                                     </div>
                                     <div class="form-inline">
                                         <label class="text-center" for="urlLinkSecond">URL链接:</label>
                                         <input type="text" class="form-control" name="networkUrl" id ="networkUrlSecond" value="" />
                                     </div>
                                 </div>
                                 <div class="right-btn-box text-center">
                                     <button type="button" class="btn btn-primary imgSubmit" id="imgSubmitSecond" num="1">提交</button>
                                     <button type="button" class="btn btn-default imgCancel imgCancel_2" pos="2" style="margin-left:5px;">取消</button>
                                 </div>
                             </div>
                         </form>
                          <div class="num-box">
                              <span>2</span>
                          </div>
                      </div>
                  </div>
                  <div class="uploadPictures">
                      <div class="col-xs-6 col-sm-6 col-md-6 uploadDiv" style="float:left;">
                            <form role="form" id="uploadPicFormThree" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/liveBroadCastController/addHomePicture">
                                 <div class="imgUpDiv">
                                     <div class="set-middle-box">
                                         <div class="imgPreview">
                                             <img id="imgPreviewThree" src="${pageContext.request.contextPath}/images/no-image.png" flag="0" />
                                             <div class="btn-box">
                                                 <label class="btn btn-primary btnUpload"  id="imgBtnThree"  for="pictureInputThree" box="imgPreviewThree">上传图片</label>
                                                 <input type="file" class="form-control" name="file" id ="pictureInputThree" value="" accept="image/jpg,image/png" />
                                             </div>
                                         </div>
                                         <div class="row-fluid tip-style">* 建议上传宽度为1140px且高度为380px的图片</div>
                                     </div>
                                 </div>
                                 <div class="row-fluid clearfix forms-box">
                                     <div class="left-input-box">
                                         <div class="form-inline">
                                             <label class="text-center">直播选择:</label>
                                             <%--<a type="button" class="selectBtnThree" num="3">直播选择</a>--%>
                                             <select class="form-control" name="liveChoose" placeholder="请选择"></select>
                                             <input type="hidden" name="liveId"/>
                                         </div>
                                         <div class="form-inline">
                                             <label class="text-center" for="urlLinkThree">URL链接:</label>
                                             <input type="text" class="form-control" name="networkUrl" id ="networkUrlThree" value="" />
                                         </div>
                                     </div>
                                     <div class="right-btn-box text-center">
                                         <button type="button" class="btn btn-primary imgSubmit" id="imgSubmitThree" num="2">提交</button>
                                         <button type="button" class="btn btn-default imgCancel imgCancel_3" pos="3" style="margin-left:5px;">取消</button>
                                     </div>
                                 </div>
                         </form>
                          <div class="num-box">
                              <span>3</span>
                          </div>
                      </div>
                      <div class="col-xs-6 col-sm-6 col-md-6 uploadDiv" style="float:right;">
                         <form role="form" id="uploadPicFormForth" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/liveBroadCastController/addHomePicture">
                             <div class="imgUpDiv">
                                 <div class="set-middle-box">
                                     <div class="imgPreview">
                                         <img id="imgPreviewForth" src="${pageContext.request.contextPath}/images/no-image.png"  flag="0" />
                                         <div class="btn-box">
                                             <label class="btn btn-primary btnUpload"  id="imgBtnForth" for="pictureInputForth" box="imgPreviewForth">上传图片</label>
                                             <input type="file" class="form-control" name="file" id ="pictureInputForth" value="" accept="image/jpg,image/png" />
                                         </div>
                                     </div>
                                     <div class="row-fluid tip-style">* 建议上传宽度为1140px且高度为380px的图片</div>
                                 </div>
                             </div>

                             <div class="row-fluid clearfix forms-box">
                                 <div class="left-input-box">
                                     <div class="form-inline">
                                         <label class="text-center">直播选择:</label>
                                         <%--<a type="button" class="selectBtnThird" num="4">直播选择</a>--%>
                                         <select class="form-control" name="liveChoose" placeholder="请选择"></select>
                                         <input type="hidden"  name="liveId"/>
                                     </div>
                                     <div class="form-inline">
                                         <label class="text-center" for="networkUrlForth">URL链接:</label>
                                         <input type="text" class="form-control" name="networkUrl" id ="networkUrlForth" value="" />
                                     </div>
                                 </div>
                                 <div class="right-btn-box text-center">
                                     <button type="button" class="btn btn-primary imgSubmit" id="imgSubmitForth" num="3">提交</button>
                                     <button type="button" class="btn btn-default imgCancel imgCancel_4" pos="4" style="margin-left:5px;">取消</button>
                                 </div>
                             </div>
                         </form>
                          <div class="num-box">
                              <span>4</span>
                          </div>
                      </div>
                  </div>

		</div>
<!--选择直播弹框-->
<div class="modal fade" id="imgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<%--<div class="modal-header">--%>
				<%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
				<%--<h4 class="modal-title" id="myModalLabel">Modal title</h4>--%>
			<%--</div>--%>
			<div class="modal-body">
				<img class="amplify-img" src="" alt="" />
			</div>
		</div>
	</div>
</div>



<%--<div class="modal fade" id="selectLiveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">--%>
    <%--<div class="modal-dialog modal-md">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                <%--<h4 class="modal-title">选择要宣传的直播</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
		        <%--<div >--%>
                     <%--<input type="text" class="form-control" name="searchLive" id="searchLive" placeholder="直播名称" style="width:250px;">--%>
                     <%--<span class="iconfont fuzzySearchBtn" style="top:15px;left:235px;">&#xe60a;</span>--%>
				   	 <%--<div class="mt-10" id="tLiveSelect">--%>
				    	<%--<!-- <table id="tblLiveSelect" class="table table-striped kscc-grid"></table> -->--%>
				    <%--</div>--%>
				<%--</div>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                <%--<button type="button" class="btn btn-primary saveSelect">确定</button>--%>
            <%--</div>--%>
        <%--</div><!-- /.modal-content -->--%>
    <%--</div><!-- /.modal -->--%>
<%--</div>--%>

<script>
require(["app/homeConfig/homeConfigApp"],function(page){
    page.run();
})
</script>