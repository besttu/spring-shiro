<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<jsp:include page="../common/head.jsp" />
<body>
	<h3>${u.username }</h3>
	<form id="form1" role="form" class="layui-form layui-form-pane"
		method="GET" action="admin/user/doEdit">
		<input type="hidden" id="userId" name="id" value="${u.id }">
		<div class="box-body">

			<div class="form-group">
				<label for="username">角色名</label> <input type="text" id="username"
					name="username" class="form-control" value="${u.username }"
					placeholder="请输入用户名长度大于等于5" lay-verify="title">
			</div>
			<div class="form-group">
				<label for="password">描述</label> <input type="text" id="password"
					name="password" value="" class="form-control" p
					laceholder="不填写则为默认密码">
			</div>
			<div class="form-group">
				<div class="">
					<input type="radio" name="userstate" value="1"
						${(u.userstate==1)?'checked':'' } title="启用"> <input
						${(u.userstate!=1)?'checked':'' } type="radio" name="userstate"
						value="-1" title="未启用">
				</div>
			</div>
		</div>
		<div class="form-group">
			<textarea name="userdesc" placeholder="请输入内容" class="layui-textarea">${u.userdesc }</textarea>
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
				return '标题至少得5个字符';
			}
		},
		pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
		validateP : function(value) {
			if ($("#password").val() != value) {
				return '密码不一致'
			}
		}
	});
	//监听提交  
	form.on('submit(demo1)', function(data) {
		var index = parent.layer.getFrameIndex(window.name);
		$.post("admin/user/doEdit/", $("#form1").serialize(), function(d, s) {
			if (s = "success") {
				parent.layer.closeAll()
				parent.loade("修改成功哦")
			} else {
				layer.msg("修改失败")
			}
		})
		return false;
	});
</script>
</html>