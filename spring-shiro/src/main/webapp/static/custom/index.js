$(function() {
	index.init()
})

var index = {
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
							callback()

						})
	},
	getUser : function() {
		$.get("admin/user/getUser", function(data) {
			$(".u_name").each(function(d) {
				$(this).text(data.username)
			})
			$(".u_img").each(function(d) {
				// alert(data.userimg)
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
			var url=self.attr("layer-url")
			var title = self.attr("layer-title")
			if(url){
				self.click(function(){
					index.editLayer(url,title)
				})
			}
		})
	}

}
