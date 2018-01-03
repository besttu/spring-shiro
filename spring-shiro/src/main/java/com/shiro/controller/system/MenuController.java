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

import com.shiro.controller.BaseController;
import com.shiro.entity.SysMenu;
import com.shiro.pojo.DataTable;
import com.shiro.service.MenuService;

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

	@RequiresPermissions("editMenu")
	@PostMapping("doEdit")
	@ResponseBody
	public void doEdit(SysMenu m) {
		System.out.println("m:" + m.toString());
		menuService.saveMenu(m);
	}

	@RequiresPermissions("addMenu")
	@GetMapping("add")
	public String addMenu(Model model) {
		List<SysMenu> menuByPid = menuService.getMenuByPid("0");
		model.addAttribute("menu", menuByPid);
		return "admin/menu/add";
	}

	@RequiresPermissions("addMenu")
	@PostMapping("doAdd")
	@ResponseBody
	public void save(SysMenu m) {
		m.setDeep(1);
		m.setId(UUID.randomUUID().toString());
		menuService.save(m);
	}

	@RequiresPermissions("addMenu")
	@PostMapping("doAddMenu")
	@ResponseBody
	public void saveMenu(SysMenu m) {
		m.setDeep(2);
		m.setId(UUID.randomUUID().toString());
		menuService.save(m);
	}

	@RequiresPermissions("addMenu")
	@PostMapping("doAddContent")
	@ResponseBody
	public void saveContent(SysMenu m) {
		m.setDeep(3);
		if (m.getPid() == null||m.getPid().equals("")) {
				m.setPid("0");
		}
		m.setId(UUID.randomUUID().toString());
		menuService.save(m);
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

	@RequiresPermissions("deleteMenu")
	@GetMapping("del/{id}")
	@ResponseBody
	public void delete(@PathVariable String id) {
		menuService.delete(id);
	}

	@RequiresPermissions("editMenu")
	@GetMapping("getMenuByPid/{id}")
	@ResponseBody
	public List<SysMenu> getMenuByPid(@PathVariable String id) {
		List<SysMenu> contents = menuService.getMenuByPid(id);
		return contents;
	}

}
