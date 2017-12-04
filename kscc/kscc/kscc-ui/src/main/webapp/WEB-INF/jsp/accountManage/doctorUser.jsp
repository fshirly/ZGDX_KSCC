<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
 request.setCharacterEncoding("UTF-8");
%>
<%
	Object iddoctor = request.getAttribute("id");
%>
<style>
.dataTables_wrapper .dataTables_paginate .paginate_button{
    padding:0px!important;
}
#tblRecord_paginate{
   font-size:10px!important;
}
</style>

<div>
    <div id="doctorSearchDiv">
		<input type="hidden" id="iddoctorstr" value="<%=iddoctor %>"/>
	     <form class="form-inline" id="doctorSearchForm">
       		<div class="row" style="margin:0 auto;">
	            <div class="form-group col-sm-6 col-md-6">
	                <label class="text-right" for="doctorName">医生姓名：</label>
	                <input id="doctorName" name="doctorName" class="form-control form-width" style="width:50%;">
	            </div>
	            <div class="form-group col-sm-6 col-md-6">
                    <label class="text-center" for="belongDepartment" >所属科室：</label>
                    <select class="form-control form-width" name="belongDepartment" id ="belongDepartment" />

	            </div>
	    	</div>
            <div class="row clearfix" style="margin:0 auto;margin-top:10px;">
	           <div class="form-group col-sm-6 col-md-6">
	                <label class="text-right" for="rank">职级：</label>
	                <select id="rank" name="rank" class="form-control form-width" style="width:50%;">
						<option value=""></option>
						<option value="主任">主任</option>
						<option value="副主任">副主任</option>
						<option value="医生">医生</option>
	                </select>
	           </div>            
	           <div class="col-sm-6 col-md-6 docBtn">
	                <button id="searchBtn1" type="button" class="btn btn-primary btn-search">搜索</button>
	            	<button id="resetBtn1" type="button"  class="btn btn-primary btn-warning" style="margin-left:20px;">重置</button>
	           </div>
            </div>				
	    </form>
	</div>
	<div class="clearfix">
        <button class="btn btn-primary doctorAdd">新增</button>
        <button class="btn btn-primary doctorBatchDel">删除</button>
    </div>
    <div id="tDoctor" class="tDoctor">
	    <table id="tblDoctor" class="table table-striped kscc-grid"></table>
	</div>
</div>    

<!--新增弹框-->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:100px!important;">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
               <h4 class="modal-title">新增</h4>
            </div>
            <div class="modal-body pl-p15">
                <form id="doctorFormAdd" method="post" action="">
            		<div class="clearfix mt-10">
			            <label class="text-right">医生姓名：</label>
			            <input class="form-control form-width" id="doctorNameAdd" role="{required:true,length:20}"><dfn>*</dfn>
			        </div>
			        <div class="clearfix mt-10">
			            <label class="text-right">身份证号：</label>
			            <input class="form-control form-width" id="IDNumberAdd" role="{required:true,identityCard:true}"><dfn>*</dfn>
			        </div>
		            <div class="clearfix mt-10">
		                <label class="text-right">联系电话：</label>
		                <input class="form-control form-width" id="phoneAdd">
		            </div>
		            <div class="clearfix mt-10">
						<label class="text-right" for="belongDepartmentAdd" >所属科室：</label>
						<select class="form-control form-width" name="belongDepartmentAdd" id ="belongDepartmentAdd"/>
		            </div>
		            <div class="clearfix mt-10">
		                <label class="text-right">职级：</label>
		                <select id="rankAdd" name="rankAdd" class="form-control form-width">
							<option value=""></option>
							<option value="主任">主任</option>
							<option value="副主任">副主任</option>
							<option value="医生">医生</option>
						</select>
		            </div>
		        </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary saveAdd">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!--编辑弹框-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:100px!important;">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
               <h4 class="modal-title">编辑</h4>
            </div>
            <div class="modal-body pl-p15">
                <form id="doctorFormEdit" method="post" action="">
            		<div class="clearfix mt-10">
			            <label class="text-right">医生姓名：</label>
			            <input class="form-control form-width" id="doctorNameEdit" role="{required:true,length:20}"><dfn>*</dfn>
			        </div>
			        <div class="clearfix mt-10">
			            <label class="text-right">身份证号：</label>
			            <input class="form-control form-width" id="IDNumberEdit" role="{required:true,identityCard:true}"><dfn>*</dfn>
			        </div>
		            <div class="clearfix mt-10">
		                <label class="text-right">联系电话：</label>
		                <input class="form-control form-width" id="phoneEdit" >
		            </div>
		            <div class="clearfix mt-10">
						<label class="text-right" for="belongDepartmentEdit" >所属科室：</label>
						<select class="form-control form-width" name="belongDepartmentEdit" id ="belongDepartmentEdit" />

		            </div>
		            <div class="clearfix mt-10">
		                <label class="text-right">职级：</label>
		                <select id="rankEdit" name="rankEdit" class="form-control form-width" >
							<option value=""></option>
							<option value="主任">主任</option>
							<option value="副主任">副主任</option>
							<option value="医生">医生</option>
						</select>
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
	require(["app/accountManage/doctorUserApp"],function(page){
	    page.run();
	})
</script>
