<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		<small id="heaader1">123</small>
	</h1>
</section>

<!-- Main content -->
<section class="content">
	<!-- Your Page Content Here -->
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Data Table With Full Features</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<shiro:hasPermission name="addUser">
						<div class="input-group">
							<a class="btn btn-primary dialog" id="createUser"
								href="javascript:;" data-url="admin/user/add" data-width="850"
								data-height="550"><i class="fa fa-plus"></i> 创建新用户</a>
						</div>
					</shiro:hasPermission>
					<table id="example1" action="/system/log/list/1"
						class="table table-bordered table-hover dataTable">
						<thead>
							<tr>
								<th><input type="checkbox" class="checkall" /></th>
								<th>用户名</th>
								<th>描述</th>
								<th>创建时间</th>
								<th>部门</th>
								<th>状态</th>
								<th>操作</th>

							</tr>
						</thead>
						<tfoot>

						</tfoot>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
	</div>
</section>
<script type="text/javascript">
var table;
	$(function() {
		table=$('#example1')
				.DataTable(
						{	
							"lengthChange" : false,
							"info" : true,
							"autoWidth" : false,
							"processing" : true,
							"ordering" : false,
							"serverSide" : true,
							"ajax" : {
								"url":"admin/user/getAll",
								"type":"POST"
							},
							  
							"columns" : [ {
								"data" : "id"
							}, {
								"data" : "username"
							}, {
								"data" : "userdesc"
							}, {
								"data" : "createtime"
							}, {
								"data" : "deptid"
							}, {
								"data" : "userstate"
							}, {
								"data" : null
							} ],
							"columnDefs" : [
									{
										"sClass" : "text-center",
										"render" : function(data, type, row) {
											if (data) {
												return data
											} else {
												return "";
											}
										},
										"targets" : 2
									},

									{
										"sClass" : "text-center",
										"render" : function(data, type, row) {
											if (data == 1) {
												return "<span class='label label-success'>激活</span>"
											} else {
												return "<span class='label label-warning'>锁定</span>"
											}
										},
										"targets" : 5
									},
									{
										"targets" : -1,
										"data" : null,
										"render" : function(data, type, row) {
												var id = data.id
												var operation = <shiro:hasPermission name="deleteUser">'<span onclick=edit("'+id+'")  class="label label-info">编辑</span>'</shiro:hasPermission>
												operation+=""+<shiro:hasPermission name="deleteUser">'<span class="label label-danger" onclick=del("'+id+'") label-danger">删除</span>'</shiro:hasPermission>
												return operation
										},
									
									},
									{
										"sClass" : "text-center",
										"render" : function(data, type, row) {
											return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
										},
										"targets" : 0
									} ],

						})
						
						//添加用户操作
						$("#createUser").click(function(){
							
							layer.open({
								 shadeClose: true,
							      shade: false,
							      maxmin: true, //开启最大化最小化按钮
							      area: ['893px', '600px'],
								  type: 2, 
								  content: "admin/user/add"
								}); 
							
							
						})
						
	})
		function del(id){
		layer.confirm('确认要删除？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
				$.get("admin/user/del/"+id,function(data,status){
					if(status=="success"){
						layer.msg('删除成功', {icon: 1});
						table.ajax.reload();
					}else{
						layer.msg('删除失败', {icon: 1});
					}
					 
				})
			 
			}, function(){
			  
			});
			}
	 
	function edit(id){
	layer.open({
			 shadeClose: true,
		      shade: false,
		      maxmin: true, //开启最大化最小化按钮
		      area: ['893px', '600px'],
			  type: 2, 
			  content: "admin/user/edit/"+id 
			}); 
			}	
	
	function editSuccess(){
		layer.msg("修改成功")
		table.ajax.reload();
	}
	
	
			
</script>
<!-- /.content -->