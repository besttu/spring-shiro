package com.shiro.controller.system;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.controller.BaseController;
import com.shiro.entity.SysRole;
import com.shiro.entity.SysRoleMenu;
import com.shiro.pojo.DataTable;
import com.shiro.pojo.TreeAll;
import com.shiro.service.MenuService;
import com.shiro.service.RoleService;

@Controller
@RequestMapping("/admin/role/")
public class RoleController extends BaseController {
	@Autowired
	RoleService roleService;
	@Autowired
	MenuService menuService;

	@RequestMapping("list")
	public String index() {
		return "admin/role/list";
	}

	@RequiresPermissions("listRole")
	@RequestMapping("getAll")
	@ResponseBody
	public DataTable<SysRole> getAll(int draw, @RequestParam(defaultValue = "0") int start, int length,
			HttpServletRequest req) {
		String search = req.getParameter("search[value]");
		System.out.println(roleService.getAll(draw, start, length, search));
		return roleService.getAll(draw, start, length, search);

	}

	@RequiresPermissions("authRole")
	public String auth(@PathVariable String id, ServletRequest req) {
		// req.setAttribute("", o);
		return "admin/role/auth";
	}

	@RequiresPermissions("authRole")
	@GetMapping("auth/{roleId}")
	public String getTreeAll(@PathVariable String roleId, ServletRequest req) {
		List<SysRoleMenu> allTree = roleService.getAll(roleId);

		Set<String> menuIds = new HashSet<String>();
		for (SysRoleMenu s : allTree) {
			menuIds.add(s.getMenuid());
		}
		req.setAttribute("menuIds", menuIds);
		List<TreeAll> treeMenu = menuService.getTreeMenu();
		req.setAttribute("roleId", roleId);
		req.setAttribute("treeMenu", treeMenu);
		return "admin/role/auth";
	}

	@RequiresPermissions("authRole")
	@RequestMapping("doAuth")
	@ResponseBody
	public void doAuth(String roleId, HttpServletRequest req, String[] mid) {
		roleService.addAuth(roleId, mid);
	}

	@RequiresPermissions("editRole")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable String id, ServletRequest req) {
		SysRole roleById = roleService.getRoleById(id);
		req.setAttribute("role", roleById);
		return "admin/role/edit";
	}

	@RequiresPermissions("deleteRole")
	@GetMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable String id) {
		roleService.deleteById(id);
	}

}
