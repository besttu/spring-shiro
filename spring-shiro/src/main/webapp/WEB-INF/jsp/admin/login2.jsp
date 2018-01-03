<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>AdminLTE 2 | Starter</title>
<base href="${path }" />
<link rel="stylesheet"
	href="static/plugins/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="static/plugins/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="static/plugins/Ionicons/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="static/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="static/dist/css/skins/skin-blue.min.css">
<style type="text/css">
.has-feedback .form-control {
	height: 50px;
}

.form-control-feedback {
	top: 8px;
	important
	!
}
</style>
</head>
<body class="hold-transition login-page">
	<h3>${url }</h3>
	<div class="login-box">
		<div class="login-logo"></div>
		<!-- /.login-logo -->

		<form id="form1" method="return false">
			<div class="login-box-body">
				<p class="login-box-msg">请输入用户名和密码登录</p>
				<div class="form-group has-feedback mg">
					<input type="text" class="form-control" id="username"
						name="username" placeholder="用户名"
						data-rule="用户名:required;username;"> <span
						class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback mg">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="密码" data-rule="密码:required;password;">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<div class="row">
						<div class="col-lg-4">
							<div class="form-group has-feedback mg">
								<input type="text" class="form-control" name="captcha"
									placeholder="验证码" data-rule="验证码:;" size="5"> <span
									class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
							</div>
						</div>
						<div class="col-lg-8">
							<div class="form-group has-feedback">
								<img alt="如果看不清楚，请单击图片刷新！" class="pointer img"
									src="admin/captcha"> <a
									href="javascript:$('.img').attr('src','admin/captcha');">点击刷新</a>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck"></div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<span onclick="submitform()"
							class="btn btn-primary btn-block btn-flat"> <i
							class="fa fa-sign-in">登录</i>
						</span>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.social-auth-links -->
			</div>
		</form>
		<!-- /.login-box-body -->
	</div>
	<!-- REQUIRED JS SCRIPTS -->

	<!-- jQuery 3 -->
	<script src="static/plugins/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<!-- iCheck 1.0.1 -->
	<!-- 	<script src="/plugins/iCheck/icheck.min.js"></script>
nice-validator-1.0.8 -->
	<script
		src="static/plugins/nice-validator-1.0.8/jquery.validator.js?local=zh-CN"></script>
	<script type="text/javascript">
		function submitform() {
			$.post("admin/doLogin", $("#form1").serialize(), function(d) {
				if (d.status == 0) {
					alert("successed")
				}
			})
		}
	</script>
</body>
</html>
