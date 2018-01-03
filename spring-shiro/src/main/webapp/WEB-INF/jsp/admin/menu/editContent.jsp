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
				<select name="fid" id="select1" lay-filter="select1">
					<option value="">请选择功能</option>
					<c:forEach var="m" items="${menus}">
						<option class="department1" value="${m.id }">${m.menuname }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<select name="pid" id="pid">
					<option value="">请选择菜单</option>
					<option ${menu.pid==content.id?'selected':'' }
						pid="${content.pid }" value="${content.id }">${content.menuname}</option>
				</select>
			</div>
			<div class="form-group">
				<label for="password">编码</label> <input value=${menu.code }
					type="text" id="code" name="code" lay-verify="required|number"
					class="form-control" placeholder="">
			</div>
			<div class="form-group">
				<label for="username">功能名称</label> <input type="text" id="username"
					name="menuname" class="form-control" value="${menu.menuname }"
					placeholder="请输入用户名长度大于等于2" lay-verify=required>
			</div>
			<div class="form-group">
				<label for="username">资源名称</label> <input type="text" name="resource"
					value="${menu.resource }" class="form-control" laceholder=""
					lay-verify="required">
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
	<input type="hidden" id="pid1" value="${menu.pid }">
</body>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<!--一定要在插件的下面，并且，不能$(function())里面执行  -->
<script type="text/javascript">
	
</script>
<script src="static/plugins/layui/layui.all.js">
	
</script>
<script type="text/javascript">
	var form = layui.form
	//监听提交  
	form.on('submit(demo1)', function(data) {
		$.post("admin/menu/doEdit/", $("#form1").serialize(), function(d, s) {
			if (s = "success") {
				parent.layer.closeAll()
				parent.reload("编辑成功0")
			} else {
				layer.msg("修改失败")
			}
		})
		return false;
	});
	var v1 = ($("#pid option:selected").attr("pid"))
	$(".department1").each(function() {
		if ($(this).val() == v1) {
			$(this).attr("selected", true);
		}
	})
	form.render('select');
	form.on('select(select1)', function(d) {
		var pid = d.value;
		$.get("admin/menu/getMenuByPid/" + pid, function(data) {
			$("#pid").html("")
			var h = "";
			$.each(data, function(d, s) {
				if (s.id == $("#pid1").val()) {
					h += "<option selected value='"+s.id+"'>" + s.menuname
							+ "</option>"
				} else {
					h += "<option value='"+s.id+"'>" + s.menuname + "</option>"
				}
			})
			$("#pid").html(h)
			form.render('select');
		})
	})
</script>
</html>