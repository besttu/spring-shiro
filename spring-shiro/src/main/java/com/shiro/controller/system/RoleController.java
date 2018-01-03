package com.shiro.controller.system;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.anno.Log;
import com.shiro.controller.BaseController;
import com.shiro.entity.SysRole;
import com.shiro.entity.SysRoleMenu;
import com.shiro.pojo.DataTable;
import com.shiro.pojo.TreeAll;
import com.shiro.service.MenuService;
import com.shiro.service.RoleService;
import com.shiro.util.ServerResponse;

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
		roleService.getAll(draw, start, length, search);
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

	@Log("授权认证")
	@RequiresPermissions("authRole")
	@PostMapping("doAuth")
	@ResponseBody
	public ServerResponse<String> doAuth(String roleId, HttpServletRequest req, String[] mid) {
		roleService.addAuth(roleId, mid);
		return ServerResponse.createBySuccess();
	}

	@RequiresPermissions("editRole")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable String id, ServletRequest req) {
		SysRole roleById = roleService.getRoleById(id);
		req.setAttribute("role", roleById);
		return "admin/role/edit";
	}

	@Log("编辑角色")
	@RequiresPermissions("editRole")
	@PostMapping("/doEdit")
	@ResponseBody
	public ServerResponse<String> doEdit(SysRole role) {
		roleService.editRole(role);
		return ServerResponse.createBySuccess();
	}

	@RequiresPermissions("addRole")
	@GetMapping("/add")
	public String add(String id, Model model) {
		SysRole role = roleService.getRole(id);
		model.addAttribute("role", role);
		return "admin/role/add";

	}

	@Log("添加角色")
	@RequiresPermissions("addRole")
	@PostMapping("/doAdd")
	@ResponseBody
	public ServerResponse<String> doAdd(SysRole role) {
		role.setId(UUID.randomUUID().toString());
		roleService.addRole(role);
		return ServerResponse.createBySuccess();
	}

	@Log("删除角色")
	@RequiresPermissions("deleteRole")
	@GetMapping("/delete/{id}")
	@ResponseBody
	public ServerResponse<String> delete(@PathVariable String id) {
		roleService.deleteById(id);
		return ServerResponse.createBySuccess();
	}

}
