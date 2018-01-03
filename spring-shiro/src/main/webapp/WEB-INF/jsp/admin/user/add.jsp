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
				<label for="username">用户名</label> <input type="text" id="username"
					name="username" class="form-control" value="${u.username }"
					placeholder="请输入用户名至少得2个字符" lay-verify="title">
			</div>
			<div class="form-group">
				<label for="password">密码</label> <input type="password"
					id="password" name="password" value="" class="form-control"
					lay-verify="pass" laceholder="不填写则为默认密码">
			</div>
			<div class="form-group">
				<label for="username">确认密码</label> <input type="password"
					name="null" value="" class="form-control" laceholder="不填写则为默认密码"
					lay-verify="validateP">
			</div>
			<div class="form-group">
				<div class="">
					<input type="radio" name="userstate" value="1" checked title="启用">
					<input type="radio" name="userstate" value="-1" title="未启用">
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
				<input type="checkbox" name="roleIds" lay-verify="role1"
					class="role1" name="role" value=${r.id } title="${r.rolename }" />
			</c:forEach>
		</div>

		<div class="form-group">
			<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
			<button class="layui-btn layui-btn-primary">
				<a href="javascript:parent.layer.closeAll('iframe');">取消</a>
			</button>
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
		},

	});

	//监听提交  
	form.on('submit(demo1)', function(data) {
		$.post("admin/user/doAdd/", $("#form1").serialize(), function(d, s) {
			console.log(d)
			console.log(s)
			if (d.status == 0) {
				parent.layer.closeAll()
				parent.reload("添加成功")
			} else {
				layer.msg("修改失败")
			}
		})
		return false;
	});
</script>
</html>