package com.shiro.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.entity.SysMenu;
import com.shiro.pojo.TreeMenu;
import com.shiro.service.MenuService;
import com.shiro.util.ShiroUtil;

@Controller
@RequestMapping("/admin")
public class IndexController {
	@Autowired
	MenuService menuService;

	@RequestMapping("/")
	public String index(String path) {
		System.out.println("path:" + path);
		return "admin/index";
	}

	/**
	 * 通过userId查找对应的树结构
	 * 
	 * @param pid
	 */
	@RequestMapping("/getTree")
	@ResponseBody
	public List<TreeMenu> getTree(@RequestParam(defaultValue = "0") Integer pid) {
		List<TreeMenu> allTree = menuService.getRootMenu();
		return allTree;
	}

}
