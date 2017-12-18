<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<jsp:include page="../common/head.jsp" />
<body>
	<form role="form" class="layui-form layui-form-pane" method="post"
		action="/system/menu/doEdit">
		<input type="hidden" name="id"
			value="0818e1c76bbd44eba3a698547ec4e637">
		<div class="box-body">
			<div class="form-group">
				<label>选择目录/菜单</label> <select id="dir"
					class="form-control select2 select2-hidden-accessible"
					style="width: 100%; display: inline-block;" lay-verify="required"
					lay-ignore="" tabindex="-1" aria-hidden="true">
					<option value="" selected="selected">--请选择--</option>
					<option value="1" selected="selected">01-系统管理</option>
					<option value="661a1f4a6ec94fa89ec6f90ce77a8eb3">02-商品管理</option>
					<option value="b1487b113b324e79b7fbadbe28660349">03-采购管理</option>
					<option value="e0ff3505df7c4aff87c60bb510c6d274">04-仓库管理</option>
					<option value="cbaa1282de1c460db6e8fd7beffcd412">安卓管理-管理目录</option>
					<option value="5a839d7ebb8c41d3b877418d44ce832c">测试-哈哈</option>
				</select><span class="select2 select2-container select2-container--default"
					dir="ltr" style="width: 100%;"><span class="selection"><span
						class="select2-selection select2-selection--single"
						role="combobox" aria-autocomplete="list" aria-haspopup="true"
						aria-expanded="false" tabindex="0"
						aria-labelledby="select2-dir-container"><span
							class="select2-selection__rendered" id="select2-dir-container"
							title="01-系统管理">01-系统管理</span><span
							class="select2-selection__arrow" role="presentation"><b
								role="presentation"></b></span></span></span><span class="dropdown-wrapper"
					aria-hidden="true"></span></span> <select id="pid" name="pid"
					class="form-control select2 select2-hidden-accessible"
					style="width: 100%; display: inline-block;" lay-verify="required"
					lay-ignore="" tabindex="-1" aria-hidden="true">
					<option value="10" selected="selected">0106-系统配置</option>
				</select><span class="select2 select2-container select2-container--default"
					dir="ltr" style="width: 100%;"><span class="selection"><span
						class="select2-selection select2-selection--single"
						role="combobox" aria-autocomplete="list" aria-haspopup="true"
						aria-expanded="false" tabindex="0"
						aria-labelledby="select2-pid-container"><span
							class="select2-selection__rendered" id="select2-pid-container"
							title="0106-系统配置">0106-系统配置</span><span
							class="select2-selection__arrow" role="presentation"><b
								role="presentation"></b></span></span></span><span class="dropdown-wrapper"
					aria-hidden="true"></span></span>
			</div>
			<div class="form-group">
				<label for="uname">编码</label> <input type="text" name="code"
					class="form-control" value="010600" placeholder="请输入编码,如050101"
					lay-verify="required">
			</div>
			<div class="form-group">
				<label for="uname">功能名称</label> <input type="text" name="menuName"
					value="查询系统设置" class="form-control" placeholder="请输入目录名称"
					lay-verify="required">
			</div>
			<div class="form-group">
				<label for="resource">权限资源</label> <input type="text" id="resource"
					name="resource" value="listSetting" class="form-control"
					placeholder="请输入权限资源名称" lay-verify="required">
			</div>
			<div class="form-group">
				<label for="uname">排序</label> <input type="text" name="sort"
					value="0" class="form-control" placeholder="请输入排序"
					lay-verify="required|number">
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success" lay-submit=""
					lay-filter="submit">
					<i class="fa fa-save"></i> 提 交
				</button>
				<a class="btn btn-default"
					href="javascript:parent.layer.closeAll('iframe');"><i
					class="fa fa-close"></i> 取消</a>
			</div>
		</div>
		<!-- /.box-body -->
	</form>

</body>
</html>