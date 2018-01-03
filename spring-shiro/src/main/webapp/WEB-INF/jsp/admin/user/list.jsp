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
					<h3 class="box-title">用户管理</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<shiro:hasPermission name="addUser">
						<div class="input-group">
							<a class="btn btn-primary dialog layer1" id="createUser"
								href="javascript:void(0);" layer-url="admin/user/add"
								layer-title="创建新用户"><i class="fa fa-plus"></i> 创建新用户</a>
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
							"drawCallback": function( settings ) {
								index.bindLayer()
						    },
							"lengthChange" : false,
							"info" : true,
							"autoWidth" : false,
							"processing" : true,
							"ordering" : false,
							"serverSide" : true,
							 "cache": false,
							"ajax" : {
								"url":"admin/user/getAll",
								"type":"POST",
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
												var operation = <shiro:hasPermission name="editUser">'<a href="javascript:void(0)" layer-url="admin/user/edit/'+id+'" layer-title="编辑"  class="label label-info layer1">编辑</a>'+</shiro:hasPermission>""
												operation+=""+<shiro:hasPermission name="deleteUser">'<a href="javascript:void(0)" layer-url="admin/user/del/'+id+'" layer-title="删除"   class="label label-danger label-danger layer1">删除</a>'</shiro:hasPermission>
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
						
	})
 
	function reload(msg){
		if(msg){
			layer.msg(msg)
		}
		table.draw(false)
	}
</script>
<!-- /.content -->