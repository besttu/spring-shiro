package com.shiro.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.anno.Log;
import com.shiro.common.shiro.PasswordHash;
import com.shiro.controller.BaseController;
import com.shiro.entity.SysUser;
import com.shiro.pojo.DataTable;
import com.shiro.service.DeptService;
import com.shiro.service.RoleService;
import com.shiro.service.UserRoleService;
import com.shiro.service.UserService;
import com.shiro.util.ResponseCode;
import com.shiro.util.ServerResponse;
import com.shiro.util.ShiroUtil;

@Controller
@RequestMapping("/admin/user/")
public class UserController extends BaseController {
	@Autowired
	UserService userService;
	@Autowired
	DeptService deptService;
	@Autowired
	RoleService roleService;
	@Autowired
	UserRoleService userRoleService;

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

	@Log("删除用户")
	@RequiresPermissions("deleteUser")
	@RequestMapping("del/{id}")
	@ResponseBody
	public ServerResponse<String> del(@PathVariable String id) {
		userService.deleteUser(id);
		return ServerResponse.createBySuccess();
	}

	@RequiresPermissions("editUser")
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable String id, HttpServletRequest req) {
		SysUser userById = userService.getUserById(id);
		// SysDept deptById = deptService.getDeptById(userById.getDeptid());
		req.setAttribute("u", userById);
		// userById.setPassword(deptById.getDeptname());
		req.setAttribute("roles", roleService.getAll());// 获取所有用户
		req.setAttribute("deptList", deptService.getAll());//
		req.setAttribute("roleIds", userRoleService.getRoleIdByUid(userById.getId()));
		return "admin/user/edit";
	}

	@Log("编辑用户")
	@RequiresPermissions("editUser")
	@RequestMapping("doEdit")
	@ResponseBody
	public ServerResponse<String> doEdit(String[] roleIds, SysUser user) {
		userService.editUser(user, roleIds);
		return ServerResponse.createBySuccess();
	}

	@RequiresPermissions("addUser")
	@RequestMapping("add")
	public String add(HttpServletRequest req) {
		req.setAttribute("roles", roleService.getAll());// 获取所有用户
		req.setAttribute("deptList", deptService.getAll());//
		return "admin/user/add";
	}

	@Log("添加用户")
	@RequiresPermissions("addUser")
	@RequestMapping("doAdd")
	@ResponseBody
	public ServerResponse<String> doAdd(String[] roleIds, SysUser user) {
		userService.saveUser(user, roleIds);
		return ServerResponse.createBySuccess();
	}

}
