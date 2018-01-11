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
				<div class=" form-inline" style="text-align: center">
					<div class="form-group">
						<label for="exampleInputName2">开始日期</label> <input type="text"
							name="start_date" id="start_date" placeholder="yyyy-MM-dd"
							autocomplete="off" class="layui-input"
							style="display: inline; width: 120px" />
					</div>
					<div class="form-group">
						<label for="exampleInputEmail2">结束日期</label> <input type="text"
							class="form-control" id="end_date" name="end_date"
							placeholder="yyyy-MM-dd" style="display: inline; width: 120px">
					</div>
					<a type="submit" id="btn_search" class="btn btn-default">搜索</a>
				</div>
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
				"type" : "get",
				"data" : function(d) {
					d.start_date = $("#start_date").val()
					d.end_date = $("#end_date").val()
				}
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

		$("#btn_search").click(function() {
			table.draw();
		})
	})
	function reload(msg) {

	}
	laydate = layui.laydate
	laydate.render({
		elem : '#start_date'
	});
	laydate.render({
		elem : '#end_date'
	});
</script>

<!-- /.content -->