$(function() {
	index.init()
})

var index = {
	
data: {"clickme":""},
	refresh:function(){
		 if( index.data.clickme){
			 $('.treeview').remove(); 
		 var url= index.data.clickme.attr("url")
		 this.getAllTree(function(){
		var self=	 $("[url='"+url+"']").css({
				 "color":"white"
			 })
			self.parent().parent().parent().addClass("active")
			index.bindMenu()
		 })}
	},
	init : function() {
		f = this.bindMenu;
		// 获取用户的菜单结构
		this.getAllTree(f);
		// 获取用户信息
		this.getUser();
		// 格式化当前日期
	},
	getAllTree : function(callback) {
		var leftText = "";
		$
				.get(
						"admin/getTree",
						function(data) {
							$
									.each(
											data,
											function(index, data) {
												var childrenText = ""
												if (data.children) {
													childrenText = "<ul class='treeview-menu'>"
													$
															.each(
																	data.children,
																	function(i,
																			d) {
																		childrenText += ("<li>"
																				+ "<a class = 'menutree' href='javascript:void(0)' url='"
																				+ d.url
																				+ "'><i class='"
																				+ d.icon
																				+ "'></i>"
																				+ d.menuname
																				+ "</a>" + "</li>")
																	})
													childrenText += "</ul>"
												}
												var active = ""

												leftText += ("<li class='"
														+ active
														+ " treeview'><a href='"
														+ data.sysMenu.url
														+ "'>"
														+ " <i class='"
														+ data.sysMenu.icon
														+ "''></i><span>"
														+ data.sysMenu.menuname
														+ "</span>"
														+ " <span class='pull-right-container'> <i"
														+ "class='fa fa-angle-left pull-right'></i></span></a>"
														+ childrenText + "</li>")
											})
											
							$("#header1").after(leftText)
							if(callback){
								callback()
							}
						})
	},
	getUser : function() {
		$.get("admin/user/getUser", function(data) {
			$(".u_name").each(function(d) {
				$(this).text(data.username)
			})
			$(".u_img").each(function(d) {
				$(this).attr("src", data.userimg)
			})

		})
	},
	getStatic : function() {
		$("#sidebar").append('')
	},
	bindMenu : function() {
		$(".menutree").each(
				function() {
					$(this).click(
							function() {
								var self = $(this)
								$(".menutree").each(function(d) {
									$(this).removeAttr("style")
								})
								self.css("color", "white")
								var url = (self.attr("url"))
								var p1 = ($(this).parent().parent().parent()
										.find("span").html())
								var p2 = ($(this).text())
								index.data.clickme = self
								$("#content1").load(url, function() {
									index.initFrameValue(p1, p2)
								})
								// console.log("p1:" + p1 + "p2:" + p2)

							})
				})
	},
	initFrameValue : function(p1, p2) {
		($("#heaader1").text(p1 + ">" + p2))
	},
	editLayer : function(url,title="hello") {
		layer.open({
			title:title,
			 shadeClose: true,
		      shade: false,
		      maxmin: true, // 开启最大化最小化按钮
		      area: ['893px', '600px'],
			  type: 2, 
			  content: url
			}); 
	},bindLayer:function(){// 绑定所有.layer1元素
		$(".layer1").each(function(){
			var self=$(this);
			self.unbind()
			var url=self.attr("layer-url")
			var title = self.attr("layer-title")
			if(url){
				self.click(function(){
					if(title=="删除"){
						layer.confirm('确认要删除？', {
							  btn: ['确认','取消'] // 按钮
							}, function(){
								$.get(url,function(data,status){
									if(data.status==0){
										reload("删除成功");
									}else{
										layer.msg('删除失败', {icon: 1});
									}
									 
								})
							 
							}, function(){
							  
							});
						return;
					}
					index.editLayer(url,title)
				})
			}
		})
	},
	chinese:{
		"sProcessing" : "处理中...",
		"sLengthMenu" : "显示 _MENU_ 项结果",

		"sZeroRecords" : "没有匹配结果",

		"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",

		"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",

		"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",

		"sInfoPostFix" : "",

		"sSearch" : "搜索:",

		"sUrl" : "",

		"sEmptyTable" : "表中数据为空",

		"sLoadingRecords" : "载入中...",

		"sInfoThousands" : ",",

		"oPaginate" : {

			"sFirst" : "首页",

			"sPrevious" : "上页",

			"sNext" : "下页",

			"sLast" : "末页"

		},

		"oAria" : {

			"sSortAscending" : ": 以升序排列此列",

			"sSortDescending" : ": 以降序排列此列"

		}

	}
	
}
