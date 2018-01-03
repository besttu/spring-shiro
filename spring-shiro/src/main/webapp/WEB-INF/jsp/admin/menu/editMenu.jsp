<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<jsp:include page="../common/head.jsp" />
<body>

	<form id="form1" role="form" class="layui-form layui-form-pane"
		method="GET">
		<input type="hidden" id="id" name="id" value="${menu.id }">
		<div class="box-body">
			<div class="form-group">
				<select name="pid">
					<option value="">请选择部门</option>
					<c:forEach var="m" items="${menus}">
						<option value="${m.id }" ${m.id==menu.pid?'selected':'' }>${m.menuname }</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<label for="password">编码</label> <input value=${menu.code }
					type="text" id="code" name="code" lay-verify="required|number"
					class="form-control" placeholder="">
			</div>
			<div class="form-group">
				<label for="username">目录名称</label> <input type="text" id="username"
					name="menuname" class="form-control" value="${menu.menuname }"
					placeholder="请输入用户名长度大于等于2" lay-verify=required>
			</div>

			<div class="form-group">
				<label for="username">排序</label> <input type="text" name="sort"
					value="${menu.sort }" class="form-control" laceholder=""
					lay-verify="validateP">
			</div>
			<div class="form-group">
				<label for="username">图标</label> <input type="text" name="icon"
					value="${menu.icon }" class="form-control" laceholder=""
					lay-verify="required">
			</div>
		</div>
		<div class="form-group">
			<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
			<a class="btn btn-default"
				href="javascript:parent.layer.closeAll('iframe');"><i
				class="fa fa-angle-left"></i> 返回</a>
		</div>
		<c:forEach items="${menus }" var="m">
			<input type="hidden" class="menu1" value="${m.id }"
				data-name="${m.menuname }" />
		</c:forEach>

		</div>
		<!-- /.box-body -->

	</form>
</body>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script type="text/javascript">
	$(".role2").each(function() {
		var self = $(this)
		$(".role1").each(function() {
			if (self.val() == $(this).val()) {
				$(this).attr("checked", "checked")
			}
		})
	})
</script>
<script src="static/plugins/layui/layui.all.js">
	
</script>
<script type="text/javascript">
	var form = layui.form
	//监听提交  
	form.on('submit(demo1)', function(data) {
		$.post("admin/menu/doEdit/", $("#form1").serialize(), function(d, s) {
			if (d.status == 0) {
				parent.layer.closeAll()
				parent.reload("编辑成功")
			} else {
				layer.msg("修改失败")
			}
		})
		return false;
	});
</script>
</html>