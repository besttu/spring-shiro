package com.shiro.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.controller.BaseController;
import com.shiro.entity.SysUser;
import com.shiro.pojo.DataTable;
import com.shiro.service.UserService;
import com.shiro.util.ShiroUtil;

@Controller
@RequestMapping("/admin/user/")
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

	@RequestMapping("getAll")
	@ResponseBody
	public DataTable<SysUser> getAll(int draw, @RequestParam(defaultValue = "0") int start, int length,
			HttpServletRequest req) {
		String search = req.getParameter("search[value]");
		return userService.startPage(draw, start, length, search);
	}

	@RequiresPermissions("listUser")
	@RequestMapping("list")
	public String index() {
		return "admin/user/list";
	}

	@RequiresPermissions("deleteUser")
	@RequestMapping("del/{id}")
	@ResponseBody
	public void del(@PathVariable String id) {
		System.out.println("id:" + id);
		userService.deleteUser(id);
	}

	@RequiresPermissions("editUser")
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable String id, HttpServletRequest req) {
		SysUser userById = userService.getUserById(id);
		req.setAttribute("u", userById);
		return "admin/user/edit";
	}

}
