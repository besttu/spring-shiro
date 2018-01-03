<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<jsp:include page="../common/head.jsp" />
<body>
	<form id="form1" role="form" class="layui-form layui-form-pane"
		method="GET" action="admin/user/doEdit">
		<input type="hidden" id="userId" name="id" value="${role.id }">
		<div class="box-body">

			<div class="form-group">
				<label for="username">角色名</label> <input type="text" id="username"
					name="rolename" class="form-control" value="${role.rolename }"
					placeholder="请输入角色名长度大于等于3" lay-verify="title">
			</div>
			<div class="form-group">
				<label for="password">描述</label> <input type="text" id="password"
					name="roledesc" value="${role.roledesc }" class="form-control" p
					laceholder="">
			</div>
			<div class="form-group">
				<div class="">
					<input type="radio" name="rolestate" value="1"
						${(role.rolestate==1)?'checked':'' } title="启用"> <input
						${(role.rolestate!=1)?'checked':'' } type="radio" name="rolestate"
						value="-1" title="未启用">
				</div>
			</div>
		</div>
		<div class="form-group">
			<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
			<a class="btn btn-default"
				href="javascript:parent.layer.closeAll('iframe');"><i
				class="fa fa-angle-left"></i> 返回</a>
		</div>
		</div>
		<!-- /.box-body -->
	</form>
	<c:forEach var="r" items="${roleIds }">
		<input type="hidden" class="role2" value="${r}" />
	</c:forEach>
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
	form.verify({
		title : function(value) {
			if (value.length < 5) {
				return '角色名至少得3个字符';
			}
		}
	});
	//监听提交  
	form.on('submit(demo1)', function(data) {
		var index = parent.layer.getFrameIndex(window.name);
		$.post("admin/role/doEdit", $("#form1").serialize(), function(d, s) {
			if (d.status == 0) {
				parent.layer.closeAll()
				parent.reload("修改成功")
			} else {
				layer.msg("修改失败")
			}
		})
		return false;
	});
</script>
</html>