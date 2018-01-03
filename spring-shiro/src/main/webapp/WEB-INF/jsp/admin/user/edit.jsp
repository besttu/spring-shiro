<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<jsp:include page="../common/head.jsp" />
<body>
	<form id="form1" role="form" class="layui-form layui-form-pane"
		method="GET" action="admin/user/doEdit">
		<input type="hidden" id="userId" name="id" value="${u.id }">
		<div class="box-body">

			<div class="form-group">
				<label for="username">用户名</label> <input type="text" id="username"
					name="username" class="form-control" value="${u.username }"
					placeholder="请输入用户名长度大于等于2" lay-verify="title">
			</div>
			<div class="form-group">
				<label for="password">密码</label> <input type="text" id="password"
					name="password" value="" class="form-control" p
					laceholder="不填写则为默认密码">
			</div>
			<div class="form-group">
				<label for="username">确认密码</label> <input type="text" name="null"
					value="" class="form-control" laceholder="不填写则为默认密码"
					lay-verify="validateP">
			</div>
			<div class="form-group">
				<div class="">
					<input type="radio" name="userstate" value="1"
						${(u.userstate==1)?'checked':'' } title="启用"> <input
						${(u.userstate!=1)?'checked':'' } type="radio" name="userstate"
						value="-1" title="未启用">
				</div>
			</div>


			<div class="form-group">
				<select name="deptid">
					<option value="">请选择部门</option>
					<c:forEach var="d" items="${deptList}">
						<option ${(d.id==u.deptid)?'selected':'' } checked
							value="${d.id }">${d.deptname }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<textarea name="userdesc" placeholder="请输入内容" class="layui-textarea">${u.userdesc }</textarea>
		</div>
		<div class="form-group">
			<c:forEach items="${roles }" var="r">
				<input type="checkbox" name="roleIds" class="role1" name="role"
					value=${r.id } title="${r.rolename }" />
			</c:forEach>
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
			if (value.length < 2) {
				return '标题至少得2个字符';
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
			console.log(d)
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