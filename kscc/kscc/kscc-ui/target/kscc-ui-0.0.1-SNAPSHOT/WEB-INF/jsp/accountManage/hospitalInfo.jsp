<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<%
    Object id = request.getAttribute("id");
%>
<style>
#hospitalInfoForm input[disabled],#hospitalInfoForm textarea[disabled]{
    cursor:inherit;
    box-shadow:none;
    background-color:#fff;
    border:none;
}
.imgPreviewLogo{
    float:left;
    margin-left:5px;
    border: 1px solid #c0c9d7;
    width: 202px;
    height: 134px;
}
.imgPreviewLogo img{
    width:200px;
    height:90px;
}
.imgPreviewLogo .btn-box {
    line-height: 40px;
    padding: 5px 12px;
    border-top: 1px solid #c0c9d7;
    text-align: center;
}
.imgPreviewLogo input[type="file"] {
    display: none;
}
.imgPreviewLogo .btn-box .btnUpload {
    width: 180px !important;
    background: #01489d;
    border: 1px solid #d7d7d7;
    text-align: center;
    height: 32px;
}
</style>
<div class="hospitalInfoDiv">
    <form class="form-inline" id="hospitalInfoForm" method="post" action="">
        <input type="hidden" id="idstr" value="<%=id %>"/>
        <%--<div class="clearfix">
            <button class="btn btn-primary hospitalAdd">新增</button>
        </div>--%>
        <div class="clearfix mt-10">
            <label class="text-right">医院名称：</label>
            <input class="form-control form-width" name="hospitalName" id="hospitalName" style="width:40%;" role="{required:true,length:60}"><dfn class="hosNameEdit disNone">*</dfn>
        </div>
        <div class="clearfix mt-10">
            <label class="text-right">网站链接：</label>
            <input class="form-control form-width" name="hospitalUrl" id="hospitalUrl" style="width:40%;" role="{length:100}">
        </div>
        <div class="clearfix mt-10" id="hosLocation">
            <label class="text-right belongCity">医院地址：</label>
            <input class="form-control form-width" name="locationDetail" id="locationDetail" style="width:40%;margin-left:5px;">
        </div>
        <div class="clearfix mt-10 hospitalLogo">
            <label class="text-right" style="float:left;">医院头像：</label>
            <div class="imgPreviewLogo">
               <img src="/kscc-ui/images/no-image.png" id="pictureView" flag="0" class="mCS_img_loaded">
               <div class="btn-box">
                   <label class="btn btn-primary btnUpload" id="imgBtnLogo" for="pictureInputLogo">上传图片</label>
                   <input type="file" class="form-control" name="file" id="pictureInputLogo" value="" accept="image/jpg,image/png">
               </div>
            </div>
        </div>
        <!-- <div style="padding-left:120px;font-size:10px;" class="picNameDiv"></div> -->
		<div style="padding-left:120px;font-size:10px;color:#c0c9d8;">系统仅支持标准格式(jpg、png)的照片</div>
        <div class="clearfix mt-10">
            <label class="text-right" style="float:left;">医院简介：</label>
            <textarea class="form-control" name="hospitalContent" id="hospitalContent" role="{length:200}"></textarea>
        </div>
    </form>
    <div class="setBtns"></div>
</div>
<script>
    require(["app/accountManage/hospitalInfo"],function(page){
        page.run();
    })
</script>


