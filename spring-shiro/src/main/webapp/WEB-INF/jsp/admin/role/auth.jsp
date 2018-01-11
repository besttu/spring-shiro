<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<jsp:include page="../common/head.jsp" />
<body>
	<form id="form1" method="return false"
		class="layui-form layui-form-pane">
		<table class="table table-hover">
			<input type="hidden" value="${(roleId)}" name="roleId" />
			<c:forEach items="${treeMenu }" var="t1">
				<tr>
					<td width="150px"><label><input name="mid"
							type="checkbox" value="${t1.tree.id}"
							class="minimal checkbox-tr menu1" lay-ignore> <i
							class="fa ${t1.tree.icon}"></i> ${t1.tree.menuname} ></label></td>
					<td><c:forEach items="${t1.children }" var="t2">
							<label><input name="mid" type="checkbox"
								value="${t2.tree.id}" class="minimal checkbox-td menu1"
								lay-ignore> <i class="fa ${(t2.tree.icon)}"></i>
								${(t2.tree.menuname)}</label>&nbsp; > &nbsp;
		           		<c:forEach items="${t2.children }" var="t3">
								<label style="font-weight: normal;"><input name="mid"
									type="checkbox" value="${(t3.tree.id)}"
									class="minimal checkbox-td menu1" lay-ignore>
									${(t3.tree.menuname)}</label>&nbsp;
						</c:forEach>
							<br>
						</c:forEach></td>
				</tr>
			</c:forEach>
		</table>
		<div class="box-footer">
			<button class="layui-btn" lay-submit="" lay-filter="demo1">确认授权</button>
			<a class="btn btn-default"
				href="javascript:parent.layer.closeAll('iframe');"><i
				class="fa fa-angle-left"></i> 返回</a>
		</div>
	</form>
	<c:forEach items="${menuIds }" var="menuId">
		<input class="menu2" type="hidden" value="${menuId }" />
	</c:forEach>

</body>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>

<script src="static/plugins/layui/layui.all.js">
	
</script>
<script type="text/javascript">
	$(".menu2").each(function() {
		var self = $(this)
		$(".menu1").each(function() {
			if (self.val() == $(this).val()) {
				$(this).attr("checked", "checked")
				return;
			}
		})
	})
	var form = layui.form
	//监听提交  
	form.on('submit(demo1)', function(data) {
		$.post("admin/role/doAuth", $("#form1").serialize(), function(d, s) {
			if (d.status == 0) {
				parent.layer.closeAll()
				parent.$("#refresh1").trigger("click");
				parent.reload("授权成功");
			} else {
				layer.msg("修改失败")
			}
		})
		return false;
	});
</script>
</html>