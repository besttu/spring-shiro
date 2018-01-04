package com.shiro.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shiro.controller.BaseController;

/**
 * 监控
 * 
 * @author Gaojun.Zhou
 * @date 2017年2月5日 下午3:38:19
 */
@Controller
@RequestMapping("/admin/monitor")
public class MonitorController extends BaseController {

	/**
	 * 系统监控列表
	 */
	@RequiresPermissions("monitorList")
	@RequestMapping("/list")
	public String list(Model model) {
		return "admin/monitor/list";
	}
}
