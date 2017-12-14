<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h3>后台登陆界面</h3>
	<form method="post" action="/admin/doLogin">
		<table>
			<tr>
				<td>用户名:</td>
				<td><input name="username" type="text" /></td>
			</tr>
			<tr>
				<td>密 码:</td>
				<td><input name="password" type="password" /></td>
			</tr>
			<tr>
				<td><a
					href="javascript:$('.img').attr('src','/admin/captcha');">点击刷新</a></td>
				<td><img alt="captcha" src="/admin/captcha"></td>
			</tr>
			<tr>
				<td>验证码:</td>
				<td><input name="captcha" type="text" onClick="" /></td>
			</tr>
			<tr>
				<td><input type="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>