<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		<small id="heaader1">123</small>
	</h1>
</section>
<section class="content">
	<!-- Your Page Content Here -->
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">其它</h3>
				</div>
				<form action="/admin/other/upload" target="_blank" method="post"
					class="layui-form layui-form-pane" enctype="multipart/form-data">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">图片上传</h3>
						</div>
						<div class="panel-body">
							<div class="box-body">
								<div class="form-group">
									选择文件:<input type="file" name="file"> <input
										type="submit" value="上传">
								</div>
							</div>
						</div>
					</div>
				</form>

			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->
	</div>
</section>
<script type="text/javascript">
	
</script>
<!-- /.content -->