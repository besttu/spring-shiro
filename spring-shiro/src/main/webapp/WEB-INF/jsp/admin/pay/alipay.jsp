<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- Content Header (Page header) -->
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
					<h3 class="box-title">支付宝管理</h3>
				</div>
				<form id="form1" role="form" class="layui-form layui-form-pane"
					method="post" action="admin/pay/doAliPay" target="_blank">
					<!-- /.box-header -->
					<div id="m1"></div>
					<br>
					<div class="alert alert-info" role="alert">
						<strong>注意!</strong>支付宝配置可以不写 . 也可以修改为自己的配置
					</div>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">支付宝配置</h3>
						</div>
						<div class="panel-body">
							<div class="box-body">
								<div class="form-group">
									<label class="sr-only" for="appId"></label>
									<div class="input-group" style="width: 500px">
										<div class="input-group-addon" style="width: 100px">APPID</div>
										<input type="text" name="appId" class="form-control config1"
											id="appId" placeholder="">
									</div>
								</div>
								<div class="form-group">
									<label class="sr-only" for="gatewayUrl">Amount (in
										dollars)</label>
									<div class="input-group" style="width: 500px">
										<div class="input-group-addon" style="width: 100px">网关</div>
										<input type="text" class="form-control config1"
											id="gatewayUrl" name="gatewayUrl" placeholder="">
									</div>
								</div>
								<div class="form-group">
									<label class="sr-only" for="notifyUrl">Amount (in
										dollars)</label>
									<div class="input-group" style="width: 500px">
										<div class="input-group-addon" style="width: 100px">回调地址</div>
										<input type="text" class="form-control config1" id="notifyUrl"
											name="notifyUrl" placeholder="">
									</div>
								</div>
								<div class="form-group">
									<label class="sr-only" for="returnUrl">Amount (in
										dollars)</label>
									<div class="input-group" style="width: 500px">
										<div class="input-group-addon" style="width: 100px">同步地址</div>
										<input type="text" class="form-control config1" id="returnUrl"
											name="returnUrl" placeholder="">
									</div>
								</div>
								<div class="form-group">
									<label class="sr-only" for="publicKey">Amount (in
										dollars)</label>
									<div class="input-group" style="width: 500px">
										<div class="input-group-addon" style="width: 100px">公钥</div>
										<input type="hidden" class="form-control "
											id="exampleInputAmount" placeholder="">
									</div>
									<textarea class="form-control config1" id="publicKey"
										name="publicKey" style="width: 500px" rows="3"></textarea>
								</div>
								<div class="form-group">
									<label class="sr-only" for="privateKey">Amount (in
										dollars)</label>
									<div class="input-group" style="width: 500px">
										<div class="input-group-addon" style="width: 100px">秘钥</div>
										<input type="hidden" class="form-control "
											id="exampleInputAmount" placeholder="">
									</div>

									<textarea class="form-control config1" name="privateKey"
										id="privateKey" style="width: 500px" rows="6"></textarea>
								</div>
								<div class="form-group">
									<a class="layui-btn" onclick="edit1()">修改</a> <a
										class="layui-btn" onclick="save1()">保存</a> <a
										class="layui-btn" onclick="cancle1()">取消修改</a>
								</div>
							</div>
						</div>
					</div>
					<div id="order1" class="alert alert-success" role="alert">
						恭喜！！！<span id="ali_status"></span>支付成功
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">订单配置</h3>
						</div>
						<div class="panel-body ">
							<div class="box-body">
								<div class="form-group">
									<label class="sr-only" for="number">Amount (in dollars)</label>
									<div class="input-group" style="width: 500px">
										<div class="input-group-addon" style="width: 100px">订单号码</div>
										<input type="text" name="number" class="form-control"
											id="number" placeholder="" lay-verify="required">
									</div>
								</div>
								<div class="form-group">
									<label class="sr-only" for="price">Amount (in dollars)</label>
									<div class="input-group" style="width: 500px">
										<div class="input-group-addon" style="width: 100px">订单价格</div>
										<input type="text" class="form-control" name="price"
											id="price" placeholder="" lay-verify="required">
									</div>
								</div>
								<div class="form-group">
									<label class="sr-only" for="title">Amount (in dollars)</label>
									<div class="input-group" style="width: 500px">
										<div class="input-group-addon" style="width: 100px">订单名称</div>
										<input type="text" class="form-control" name="title"
											id="title" placeholder="" lay-verify="required">
									</div>
								</div>
								<div class="form-group">
									<label class="sr-only" for="desc">Amount (in dollars)</label>
									<div class="input-group" style="width: 500px">
										<div class="input-group-addon" style="width: 100px">订单描述</div>
										<input type="hidden" class="form-control"
											id="exampleInputAmount" placeholder="">
									</div>
									<textarea class="form-control" id="desc" name="desc"
										style="width: 500px" rows="3" placeholder="可以为空"></textarea>
								</div>
								<div class="form-group">
									<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
									<a class="btn btn-default"
										href="javascript:parent.layer.closeAll('iframe');"><i
										class="fa fa-angle-left"></i> 返回</a>
								</div>
							</div>

						</div>
					</div>
				</form>
			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->
	</div>
</section>
<script type="text/javascript">
	$(function() {
		$("#m1").html("<input name='m' type='hidden' value='0'></input>")
		$("#order1").hide()
	})
	function cancle1() {
		$("#m1").html("<input name='m' type='hidden' value='0'></input>")
		$(".config1").attr("readonly", " ").removeAttr("lay-verify").val("")
	}
	function save1() {
		$("#m1").html("<input name='m' type='hidden' value='1'></input>")

		$(".config1").attr("readonly", " ").attr("lay-verify", "required")
	}
	function edit1() {
		$(".config1").removeAttr("readonly").attr("lay-verify", "required")
	}
	$(".config1").attr("readonly", " ")
	var form = layui.form
	//监听提交  cancle1
	form.on('submit(demo1)', function(data) {
		var interval = setInterval(function() {
			$.get("admin/pay/getAlipayStatus/" + $("#number").val(),
					function(d) {
						console.log(d)
						if (d.status == 0) {
							$("#order1").show()
							$("#ali_status").text(d.msg)
							clearInterval(interval);
						}
					})
		}, 1000);// 注意函数名没有引号和括弧！ 

		return true;
	});
</script>
<!-- /.content -->