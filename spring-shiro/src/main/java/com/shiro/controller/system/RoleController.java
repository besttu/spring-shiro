package com.shiro.controller.system;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.controller.BaseController;
import com.shiro.entity.SysMenu;
import com.shiro.entity.SysRole;
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
		return roleService.getAll(draw, start, length, search);

	}

	@RequestMapping("auth/{id}")
	public String auth(@PathVariable String id, ServletRequest req) {
		// req.setAttribute("", o);
		return "admin/role/auth";
	}

	@RequestMapping("auth/list")
	public String getTreeAll(ServletRequest req) {
		List<SysMenu> allTree = menuService.getAllTree();
		Set<String> menuIds = new HashSet<String>();
		for (SysMenu s : allTree) {
			menuIds.add(s.getId());
		}
		req.setAttribute("menuIds", menuIds);
		List<TreeAll> treeMenu = menuService.getTreeMenu();
		req.setAttribute("treeMenu", treeMenu);
		return "admin/role/auth";
	}

}
