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
@RequestMapping("/admin")
public class UserController extends BaseController {
	@Autowired
	UserService userService;

	@RequestMapping("getUser")
	@ResponseBody
	public SysUser getUser() {
		SysUser sessionUser = ShiroUtil.getSessionUser();
		sessionUser.setPassword(null);
		return sessionUser;
	}
}
