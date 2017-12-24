<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<section class="content-header">
	<h1>
		<small id="heaader1">123</small>
	</h1>
</section>

<!-- Main content -->
<section class="content">
	<!-- Your Page Content Here -->
	<div class="row">
		<div class="col-xs-12" style="height: 800px">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Data Table With Full Features</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<div class="input-group">
						<a class="btn btn-primary dialog" id="createUser"
							href="javascript:;" data-url="admin/user/add" data-width="850"
							data-height="550"><i class="fa fa-plus"></i> 创建新用户</a>
					</div>
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

	$(function() {
		$("#example1")
				.DataTable(
						{
							"fnInitComplete":function(){
								index.bindLayer()
							},
							"lengthChange" : false,
							"info" : true,
							"autoWidth" : false,
							"processing" : true,
							"ordering" : false,
							"serverSide" : true,
							"ajax" : {
								"url" : "admin/role/getAll",
								"type" : "POST"
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
											'<a  class="label label-primary "  href = "javascript:void(0)" >编辑</a>' +
											</shiro:hasPermission>""
											o1+=<shiro:hasPermission name="deleteRole">
											'<a  class="label label-success"  href = "javascript:void(0)" >删除</a>'+
											</shiro:hasPermission>""
											return o1;
										},
										"targets" : 5
									},

							]

						});
		

	})
</script>

<!-- /.content -->