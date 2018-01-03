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
					<h3 class="box-title">菜单管理</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<shiro:hasPermission name="addMenu">
						<a class="btn btn-primary layer1" href="javascript:;"
							layer-url="admin/menu/add" layer-url><i class="fa fa-plus"></i>
							创建新菜单</a>
					</shiro:hasPermission>
					<table id="example1" action=""
						class="table table-bordered table-hover dataTable">
						<thead>
							<tr>
								<th><input type="checkbox" class="checkall" /></th>
								<th>菜单名称</th>
								<th>编码</th>
								<th>访问地址</th>
								<th>图标</th>
								<th>深度</th>
								<th>资源</th>
								<th>排序</th>
								<th>类型</th>
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
		table = $("#example1").DataTable({
			"drawCallback" : function(settings) {
				index.bindLayer()
			},
			"lengthChange" : false,
			"info" : true,
			"cache" : false,
			"processing" : true,
			"ordering" : false,
			"serverSide" : true,
			"ajax" : {
				"url" : "admin/menu/getAll",
				"type" : "get"
			},
			"columns" : [ {
				"data" : null
			}, {
				"data" : "menuname"
			}, {
				"data" : "code"
			}, {
				"data" : "url"
			}, {
				"data" : "icon"
			}, {
				"data" : "deep"
			}, {
				"data" : "resource"
			}, {
				"data" : "sort"
			}, {
				"data" : null
			}, {
				"data" : null
			} ],
			"columnDefs" : [ {
				"sClass" : "text-center",
				"render" : function(data, type, row) {
					return 1;
				},
				"targets" : 0
			}, {
				"sClass" : "text-center",
				"render" : function(data, type, row) {
						if(data.deep==1){
							return "目录";
						}
						if(data.deep==2){
							return "菜单";
						}
						if(data.deep==3){
							return "功能";
						}
					return "2"
				},
				"targets" : 8
			}, {
				"sClass" : "text-center",
				"render" : function(data, type, row) {
					var o = ""
					if(data.deep==1){
						o+=<shiro:hasPermission name="editMenu">'<a  class="label label-info layer1" layer-url="admin/menu/edit/'+data.id+'" layer-title="编辑" href = "javascript:void(0)" >编辑</a> '+</shiro:hasPermission>""
						o+=<shiro:hasPermission name="deleteMenu">'<a  class="label label-danger layer1" layer-url="admin/menu/del/'+data.id+'" layer-title="删除" href = "javascript:void(0)" >删除</a> '+</shiro:hasPermission>""
					}
					if(data.deep==2){
						o+=<shiro:hasPermission name="editMenu">'<a  class="label label-info layer1" layer-url="admin/menu/editMenu/'+data.id+'" layer-title="编辑" href = "javascript:void(0)" >编辑</a> '+</shiro:hasPermission>""
						o+=<shiro:hasPermission name="deleteMenu">'<a  class="label label-danger layer1" layer-url="admin/menu/del/'+data.id+'" layer-title="删除" href = "javascript:void(0)" >删除</a> '+</shiro:hasPermission>""
					}
					if(data.deep==3){
						o+=<shiro:hasPermission name="editMenu">'<a  class="label label-info layer1" layer-url="admin/menu/editContent/'+data.id+'" layer-title="编辑" href = "javascript:void(0)" >编辑</a> '+</shiro:hasPermission>""
						o+=<shiro:hasPermission name="deleteMenu">'<a  class="label label-danger layer1" layer-url="admin/menu/del/'+data.id+'" layer-title="删除" href = "javascript:void(0)" >删除</a> '+</shiro:hasPermission>""
					}
					return o
				},
				"targets" : 9
			} ]

		});

	})

	function reload(msg) {
		table.draw(false)
		if (msg) {
			layer.msg(msg)
		}
	}
</script>

<!-- /.content -->