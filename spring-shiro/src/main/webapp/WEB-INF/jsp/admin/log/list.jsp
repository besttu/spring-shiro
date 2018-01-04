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
					<h3 class="box-title">用户日志</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="example1" action=""
						class="table table-bordered table-hover dataTable">
						<thead>
							<tr>
								<th>行号</th>
								<th>日志操作</th>
								<th>用户</th>
								<th>地址</th>
								<th>时间</th>
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
			language : index.chinese,

			"drawCallback" : function(settings) {
				index.bindLayer()
			},
			"info" : true,
			"cache" : false,
			"autoWidth" : false,
			"processing" : true,
			"ordering" : false,
			"serverSide" : true,
			"ajax" : {
				"url" : "admin/log/getAll",
				"type" : "get"
			},
			"columns" : [ {
				"data" : null
			}, {
				"data" : "title"
			}, {
				"data" : "username"
			}, {
				"data" : "url"
			}, {
				"data" : "createtime"
			} ],
			"columnDefs" : [ {
				"sClass" : "text-center",
				"render" : function(data, type, full, meta) {
					return meta.row + 1 + meta.settings._iDisplayStart;
				},
				"targets" : 0
			}, ]
		});

	})

	function reload(msg) {
		//当调用table.draw(false)只是实现了表格的局部刷新(局部刷新并不会更新list页面中的 o1变量，因此只能采用重新加载页面的方式)，只能采取list页面刷新的方式进行刷新
		$("#content").load("admin/log/list", function() {
			$(".header1").each(function() {
				if ($(this).html() == 123) {
					$(this).parent().parent().remove()
				}
			})
		})
		if (msg) {
			layer.msg(msg)
		}
	}
</script>

<!-- /.content -->