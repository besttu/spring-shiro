<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<jsp:include page="../common/head.jsp" />
<body>
	<form id="form1" role="form" class="layui-form layui-form-pane"
		method="GET" action="admin/role/doAuth">
		<div class="box-body">
			<div class="form-group">
				<ul>
					<c:forEach items="${treeMenu }" var="t">
						<li>${t.tree.menuname }</li>
						<ul>
							<c:forEach items="${t.children }" var="t1">
								<li>${t1.tree.menuname}</li>
								<ul>
									<c:forEach items="${t1.children } " var="t2"></c:forEach>
									<li>${t2.tree.menuname}</li>
								</ul>

							</c:forEach>
						</ul>

					</c:forEach>
				</ul>
			</div>
			<div class="form-group">
				<label for="password">密码</label> <input type="text" id="password"
					name="password" value="" class="form-control" lay-verify="pass"
					laceholder="不填写则为默认密码">
			</div>
			<div class="form-group">
				<label for="username">确认密码</label> <input type="text" name="null"
					value="" class="form-control" laceholder="不填写则为默认密码"
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
			<button class="layui-btn layui-btn-primary" lay-filter="cancle">取消</button>
		</div>
		</div>
		<!-- /.box-body -->
	</form>
</body>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>

<script src="static/plugins/layui/layui.all.js">
	
</script>
</html>