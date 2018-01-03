package com.shiro.controller.system;

import java.util.List;
import java.util.UUID;

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
import com.shiro.entity.SysMenu;
import com.shiro.pojo.DataTable;
import com.shiro.service.MenuService;
import com.shiro.util.ServerResponse;

@Controller
@RequestMapping("admin/menu/")
public class MenuController extends BaseController {
	@Autowired
	MenuService menuService;

	@RequiresPermissions("listMenu")
	@GetMapping("list")
	public String index() {
		return "admin/menu/list";
	}

	@RequiresPermissions("listMenu")
	@GetMapping("getAll")
	@ResponseBody
	public DataTable<SysMenu> getAll(int draw, @RequestParam(defaultValue = "0") int start, int length,
			HttpServletRequest req) {
		String search = req.getParameter("search[value]");
		return menuService.getAll(draw, start, length, search);
	}

	@RequiresPermissions("editMenu")
	@GetMapping("edit/{id}")
	public String edit(@PathVariable String id, Model m) {
		SysMenu memu = menuService.getMenuById(id);
		m.addAttribute("menu", memu);
		return "admin/menu/edit";
	}

	@Log("编辑菜单")
	@RequiresPermissions("editMenu")
	@PostMapping("doEdit")
	@ResponseBody
	public ServerResponse<String> doEdit(SysMenu m) {
		menuService.saveMenu(m);
		return ServerResponse.createBySuccess();
	}

	@RequiresPermissions("addMenu")
	@GetMapping("add")
	public String addMenu(Model model) {
		List<SysMenu> menuByPid = menuService.getMenuByPid("0");
		model.addAttribute("menu", menuByPid);
		return "admin/menu/add";
	}

	@Log("添加目录菜单")
	@RequiresPermissions("addMenu")
	@PostMapping("doAdd")
	@ResponseBody
	public ServerResponse<String> save(SysMenu m) {
		m.setDeep(1);
		m.setId(UUID.randomUUID().toString());
		menuService.save(m);
		return ServerResponse.createBySuccess();
	}

	@Log("添加菜单")
	@RequiresPermissions("addMenu")
	@PostMapping("doAddMenu")
	@ResponseBody
	public ServerResponse<String> saveMenu(SysMenu m) {
		m.setDeep(2);
		m.setId(UUID.randomUUID().toString());
		menuService.save(m);
		return ServerResponse.createBySuccess();
	}

	@Log("添加功能菜单")
	@RequiresPermissions("addMenu")
	@PostMapping("doAddContent")
	@ResponseBody
	public ServerResponse<String> saveContent(SysMenu m) {
		m.setDeep(3);
		if (m.getPid() == null || m.getPid().equals("")) {
			m.setPid("0");
		}
		m.setId(UUID.randomUUID().toString());
		menuService.save(m);
		return ServerResponse.createBySuccess();
	}

	@RequiresPermissions("editMenu")
	@GetMapping("editMenu/{id}")
	public String editMenu(@PathVariable String id, Model m) {
		SysMenu memu = menuService.getMenuById(id);
		List<SysMenu> menus = menuService.getMenuByDeep(1);
		m.addAttribute("menus", menus);
		m.addAttribute("menu", memu);
		return "admin/menu/editMenu";
	}

	@RequiresPermissions("editMenu")
	@GetMapping("editContent/{id}")
	public String editContent(@PathVariable String id, Model m) {
		SysMenu memu = menuService.getMenuById(id);
		List<SysMenu> menus = menuService.getMenuByDeep(1);
		SysMenu content = menuService.getMenuById(memu.getPid());
		m.addAttribute("menus", menus);
		m.addAttribute("content", content);
		m.addAttribute("menu", memu);
		return "admin/menu/editContent";
	}

	@Log("刪除菜单")
	@RequiresPermissions("deleteMenu")
	@GetMapping("del/{id}")
	@ResponseBody
	public ServerResponse<String> delete(@PathVariable String id) {
		menuService.delete(id);
		return ServerResponse.createBySuccess();
	}

	@RequiresPermissions("editMenu")
	@GetMapping("getMenuByPid/{id}")
	@ResponseBody
	public List<SysMenu> getMenuByPid(@PathVariable String id) {
		List<SysMenu> contents = menuService.getMenuByPid(id);
		return contents;
	}

}
