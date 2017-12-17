package com.shiro.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.controller.BaseController;
import com.shiro.entity.SysUser;
import com.shiro.service.UserService;
import com.shiro.util.ShiroUtil;

@Controller
@RequestMapping("/admin/role/")
public class RoleController extends BaseController {
	
	@RequestMapping("list")
	public String index() {
		return "admin/role/list";
	}
}
