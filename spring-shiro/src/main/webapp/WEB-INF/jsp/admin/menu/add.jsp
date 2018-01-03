<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<jsp:include page="../common/head.jsp" />
<body>
	<div class="box-body">

		<div class="form-group">
			<div class="layui-tab layui-tab-brief" lay-filter="demo">
				<ul class="layui-tab-title">
					<li class="layui-this">添加目录</li>
					<li>添加菜单</li>
					<li>添加功能</li>
				</ul>
				<div class="layui-tab-content" style="height: 100px;">
					<div class="layui-tab-item layui-show">
						<form id="form1" role="form" class="layui-form layui-form-pane"
							method="GET">
							<input type="hidden" name="pid" value="0" />
							<div class="form-group">
								<label for="password">编码</label> <input value="" type="text"
									id="code" name="code" lay-verify="required|number"
									class="form-control" placeholder="">
							</div>
							<div class="form-group">
								<label for="username">目录名称</label> <input type="text"
									id="username" name="menuname" class="form-control"
									placeholder="请输入用户名长度大于等于2" lay-verify=required>
							</div>
							<div class="form-group">
								<label for="username">排序</label> <input type="text" name="sort"
									value="" class="form-control" laceholder=""
									lay-verify="validateP">
							</div>
							<div class="form-group">
								<label for="username">图标</label> <input type="text" name="icon"
									value="fa-folder" class="form-control" laceholder=""
									lay-verify="required">
							</div>
							<div class="form-group">
								<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
								<a class="btn btn-default"
									href="javascript:parent.layer.closeAll('iframe');"><i
									class="fa fa-angle-left"></i> 返回</a>
							</div>
						</form>
					</div>
					<div class="layui-tab-item">
						<form id="form2" role="form" class="layui-form layui-form-pane"
							method="GET">
							<div class="form-group">
								<select name="pid">
									<c:forEach items="${menu }" var="m">
										<option value="${m.id }">${m.menuname }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="password">编码</label> <input value="" type="text"
									id="code" name="code" lay-verify="required|number"
									class="form-control" placeholder="">
							</div>
							<div class="form-group">
								<label for="username">菜单名称</label> <input type="text"
									id="username" name="menuname" class="form-control" value=""
									placeholder="请输入用户名长度大于等于2" lay-verify=required>
							</div>
							<div class="form-group">
								<label for="username">排序</label> <input type="text" name="sort"
									value="" class="form-control" laceholder=""
									lay-verify="validateP">
							</div>
							<div class="form-group">
								<label for="username">图标</label> <input type="text" name="icon"
									value="fa-folder" class="form-control" laceholder=""
									lay-verify="required">
							</div>
							<div class="form-group">
								<button class="layui-btn" lay-submit="" lay-filter="demo2">立即提交</button>
								<a class="btn btn-default"
									href="javascript:parent.layer.closeAll('iframe');"><i
									class="fa fa-angle-left"></i> 返回</a>
							</div>
						</form>
					</div>
					<div class="layui-tab-item">
						<form id="form3" role="form" class="layui-form layui-form-pane"
							method="GET">
							<div class="form-group">
								<select name="fid" id="select1" lay-filter="select1">
									<option value="">请选择功能</option>
									<c:forEach var="m" items="${menu}">
										<option class="department1" value="${m.id }">${m.menuname }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<select name="pid" id="pid" lay-verify="pid1">
									<option value="">请选择菜单</option>
								</select>
							</div>
							<div class="form-group">
								<label for="password">编码</label> <input value="" type="text"
									id="code" name="code" lay-verify="required|number"
									class="form-control" placeholder="">
							</div>
							<div class="form-group">
								<label for="username">功能名称</label> <input type="text"
									id="username" name="menuname" class="form-control" value=""
									placeholder="请输入用户名长度大于等于2" lay-verify=required>
							</div>
							<div class="form-group">
								<label for="username">资源名称</label> <input type="text"
									name="resource" value="" class="form-control" laceholder=""
									lay-verify="required">
							</div>
							<div class="form-group">
								<label for="username">排序</label> <input type="text" name="sort"
									value="" class="form-control" laceholder=""
									lay-verify="validateP">
							</div>
							<div class="form-group">
								<button class="layui-btn" lay-submit="" lay-filter="demo3">立即提交</button>
								<a class="btn btn-default"
									href="javascript:parent.layer.closeAll('iframe');"><i
									class="fa fa-angle-left"></i> 返回</a>
							</div>
						</form>
					</div>


				</div>
			</div>
		</div>
	</div>



	<!-- /.box-body -->
</body>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="static/plugins/layui/layui.all.js">
	
</script>
<script type="text/javascript">
	var form = layui.form
	//日期
	/* 	layui.use('laydate', function(){
	 var laydate = layui.laydate;
	
	 //执行一个laydate实例
	 laydate.render({
	 elem: '#date1' //指定元素
	 });
	 }); */
	//自定义验证规则

	//监听提交  
	form.on('submit(demo1)', function(data) {
		$.post("admin/menu/doAdd", $("#form1").serialize(), function(d, s) {
			if (d.status == 0) {
				parent.layer.closeAll()
				parent.reload("编辑成功0")
			} else {
				layer.msg("修改失败")
			}
		})
		return false;
	});
	//监听提交  
	form.on('submit(demo2)', function(data) {
		$.post("admin/menu/doAddMenu", $("#form2").serialize(), function(d, s) {
			if (d.status == 0) {
				parent.layer.closeAll()
				parent.reload("编辑成功0")
			} else {
				layer.msg("修改失败")
			}
		})
		return false;
	}); //监听提交  
	form.on('submit(demo3)', function(data) {
		$.post("admin/menu/doAddContent", $("#form3").serialize(), function(d,
				s) {
			if (d.status == 0) {
				parent.layer.closeAll()
				parent.reload("编辑成功")
			} else {
				layer.msg("修改失败")
			}
		})
		return false;
	});
	form.on('select(select1)', function(d) {
		var pid = d.value;
		$.get("admin/menu/getMenuByPid/" + pid, function(data) {
			$("#pid").html("")
			var h = "";
			$.each(data, function(d, s) {
				h += "<option value='"+s.id+"'>" + s.menuname + "</option>"
			})
			$("#pid").html(h)
			form.render('select');
		})
	})
</script>
</html>