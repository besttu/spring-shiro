<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<section class="content-header">
	<h1>
		<small class="header1" id="heaader1">123</small>
	</h1>
</section>

<!-- Main content -->
<section class="content" id="content">
	<!-- Your Page Content Here -->
	<div class="row">
		<div class="col-xs-12" style="height: 800px">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">角色管理</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<shiro:hasPermission name="addRole">
						<div class="input-group">
							<a class="btn btn-primary layer1" href="javascript:;"
								layer-url="admin/role/add" layer-url><i class="fa fa-plus"></i>
								创建新角色</a>
						</div>
					</shiro:hasPermission>
					<table id="example1" action=""
						class="table table-bordered table-hover dataTable">
						<thead>
							<tr>
								<th><input type="checkbox" class="checkall" /></th>
								<th>角色名称</th>
								<th>描述</th>
								<th>创建时间</th>
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
		</div>
	</div>
</section>
<script type="text/javascript">
var table
	$(function() {
		table=$("#example1")
				.DataTable(
						{
							"drawCallback": function( settings ) {
								index.bindLayer()
						    },
							"lengthChange" : false,
							"info" : true,
							 "cache": false,
							"autoWidth" : false,
							"processing" : true,
							"ordering" : false,
							"serverSide" : true,
							"ajax" : {
								"url" : "admin/role/getAll",
								"type" : "get"
							},
							"columns" : [ {
								"data" : null
							}, {
								"data" : "rolename"
							}, {
								"data" : "roledesc"
							}, {
								"data" : "createtime"
							}, {
								"data" : "rolestate"
							}, {
								"data" : null
							} ],
							"columnDefs" : [
									{
										"sClass" : "text-center",
										"render" : function(data, type, row) {
											return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
										},
										"targets" : 0
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
										"targets" : 4
									}, {
										"sClass" : "text-center",
										"render" : function(data, type, row) {
											var o1 = <shiro:hasPermission name="authRole">
											'<a  class="label label-info layer1" layer-url="admin/role/auth/'+data.id+'" layer-title="授权" href = "javascript:void(0)" >授权</a>' +
											</shiro:hasPermission>""
											o1+=<shiro:hasPermission name="editRole">
											'<a  class="label label-primary layer1" layer-url="admin/role/edit/'+data.id+'" layer-title="编辑"  href = "javascript:void(0)" >编辑</a>' +
											</shiro:hasPermission>""
											o1+=<shiro:hasPermission name="deleteRole">
											'<a  class="label label-success layer1" layer-url="admin/role/delete/'+data.id+'" layer-title="删除"    href = "javascript:void(0)" >删除</a>'+
											</shiro:hasPermission>""
											return o1;
										},
										"targets" : 5
									},

							]

						});
		

	})
	
	function reload(msg){
	//当调用table.draw(false)只是实现了表格的局部刷新(局部刷新并不会更新list页面中的 o1变量，因此只能采用重新加载页面的方式)，只能采取list页面刷新的方式进行刷新
	$("#content").load("admin/role/list",function(){
		$(".header1").each(function(){
			if($(this).html()==123){
				$(this).parent().parent().remove()
			}
		})
	  })
	if(msg){
		layer.msg(msg)
	}
}
</script>

<!-- /.content -->