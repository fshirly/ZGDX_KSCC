<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<%
    Object id = request.getAttribute("id");
%>
<!-- 医院管理员列表-->
<div class="hospitalListDiv">
    <input type="hidden" id="idstr" value="<%=id %>"/>
    <input type="hidden" id="exloginName" name="exloginName"/>
    <%--<div class="clearfix">
        <button class="btn btn-primary hospitalAdd">新增</button>
    </div>--%>

    <div id="tAdminUser" class="tAdminUser">
        <table id="tblAdminHospital" class="table table-striped kscc-grid"></table>
    </div>
</div>

<!-- 新增医院管理员-->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:100px!important;">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">新增管理员</h4>
            </div>
            <div class="modal-body pl-p15">
                <form id="adminFormAdd" method="post" action="">
                    <div class="clearfix mt-10">
                        <label class="text-right">登录名称：</label>
                        <input class="form-control form-width" id="loginNameAdd" role="{required:true,length:20}"><dfn>*</dfn>
                    </div>
                    <div class="clearfix mt-10">
                        <label class="text-right">登录密码：</label>
                        <input class="form-control form-width" type="password" id="passwordAdd" role="{required:true,password:true}"><dfn>*</dfn>
                        <div style="padding-left:124px;font-size:10px;color:#c0c9d8;">字母和数字组合的6-18位字符</div>
                    </div>
                    <div class="clearfix mt-10">
                        <label class="text-right">确认密码：</label>
                        <input class="form-control form-width" type="password" id="confirmPasswordAdd" role="{required:true,password:true}"><dfn>*</dfn>
                        <div style="padding-left:124px;font-size:10px;color:#c0c9d8;">字母和数字组合的6-18位字符</div>
                    </div>
                    <div class="clearfix mt-10">
                        <label class="text-right">姓名：</label>
                        <input class="form-control form-width" id="userNameHosAdd" role="{filterCN:true}">
                    </div>
                    <div class="clearfix mt-10">
                        <label class="text-right">联系电话：</label>
                        <input class="form-control form-width" id="telephoneAdd" >
                    </div>
                    <div class="clearfix mt-10">
                        <label class="text-right">邮箱：</label>
                        <input class="form-control form-width" id="emailAdd" role="{email:true}">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary saveAddHospital">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 编辑医院管理员-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:100px!important;">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">编辑管理员</h4>
            </div>
            <div class="modal-body pl-p15">
                <form id="adminFormEdit" method="post" action="">
                    <div class="clearfix mt-10">
                        <label class="text-right">登录名：</label>
                        <input type="text" class="form-control form-width" name="loginName" id="loginName" role="{required:true,length:20}"><dfn>*</dfn>
                    </div>
                    <div class="adminDiv" style="display:none;">
                        <div class="clearfix mt-10">
	                        <label class="text-right">原密码：</label>
	                        <input type="password" class="form-control form-width" name="password" id="password" role="{password:true}">
	                        <div style="padding-left:124px;font-size:10px;color:#c0c9d8;">字母和数字组合的6-18位字符</div>
	                    </div>
	                    <div class="clearfix mt-10">
	                        <label class="text-right">新密码：</label>
	                        <input type="password" class="form-control form-width" name="newpassword" id="newpassword" role="{password:true}">
	                        <div style="padding-left:124px;font-size:10px;color:#c0c9d8;">字母和数字组合的6-18位字符</div>
	                    </div>
	                    <div class="clearfix mt-10">
	                        <label class="text-right">确认新密码：</label>
	                        <input type="password" class="form-control form-width" name="confirmpassword" id="confirmpassword" role="{password:true}">
	                        <div style="padding-left:124px;font-size:10px;color:#c0c9d8;">字母和数字组合的6-18位字符</div>
	                    </div>
                    </div>
                    <div class="clearfix mt-10">
                        <label class="text-right">姓名：</label>
                        <input type="text" class="form-control form-width" name="userNameHos" id="userNameHos" role="{filterCN:true}">
                    </div>
                    <div class="clearfix mt-10">
                        <label class="text-right">联系电话：</label>
                        <input type="text" class="form-control form-width" name="mobilePhone" id="mobilePhone" >
                    </div>
                    <div class="clearfix mt-10">
                        <label class="text-right">邮箱：</label>
                        <input type="text" class="form-control form-width" name="email" id="email" role="{email:true}">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary saveEdit">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script>
    require(["app/accountManage/hospitalAdminList"],function(page){
        page.run();
    })
</script>
