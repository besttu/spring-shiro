<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<h3 class="box-title">系统配置</h3>
				</div>
				<div class="box-body" style="width: 600px">
					<div id="info1" style="display: none"
						class="alert alert-success alert-dismissible">
						<h4 style="margin-bottom: 0px;">
							<i class="fa fa-check-circle"></i> 修改成功
						</h4>
					</div>
					<form id="form1" onsubmit="return false">
						<c:forEach items="${lists }" var="st">
							<div class="form-group">
								<label for="${(st.syskey)}">${(st.sysname)}</label> <input
									type="hidden" name="id" value="${(st.id)}" /> <input
									type="text" id="${(st.syskey) }" name="sysvalue"
									value="${(st.sysvalue)}" class="form-control"
									data-rule="${(st.sysname)}:required;">
								<p class="help-block" style="color: #dd4b39;">${(st.sysdesc)}</p>
							</div>
						</c:forEach>
						<button type="submit" onclick="submit_form()"
							class="btn btn-default">提交</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	function submit_form() {
		$.post("admin/Setting/save", $("#form1").serialize(), function(d) {
			if (d.status == 0) {
				$("[url='admin/Setting/list']").trigger("click");//模拟点击事件，更新配置页面
			}
		})
	}
</script>
