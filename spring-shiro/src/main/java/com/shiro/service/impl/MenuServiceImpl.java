package com.shiro.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Collections2;
import com.shiro.dao.SysMenuMapper;
import com.shiro.entity.SysMenu;
import com.shiro.pojo.TreeMenu;
import com.shiro.service.MenuService;
import com.shiro.util.ShiroUtil;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	SysMenuMapper menuMapper;

	// 需要注意的是当一个支持缓存的方法在对象内部被调用时是不会触发缓存功能的
	// @Cacheable(value = "permissionCache", key = "#uid")
	private List<SysMenu> getAllTree() {
		String sessionUid = ShiroUtil.getSessionUid();
		List<SysMenu> tree = menuMapper.getTree(sessionUid);
		return tree;
	}

	@Cacheable(value = "menuCache")
	public List<TreeMenu> getRootMenu() {
		List<SysMenu> allTree = this.getAllTree();
		List<TreeMenu> trees = new ArrayList<TreeMenu>();
		for (SysMenu m : allTree) {
			// 获取所有pid为0的资源
			if (m.getDeep() == 1) {
				TreeMenu t = new TreeMenu();
				t.setSysMenu(m);
				this.getMenu(m.getId(), t.getChildren());
				trees.add(t);
			}
		}
		return trees;

	}

	private void getMenu(String pid, List<SysMenu> menus) {
		List<SysMenu> allTree = this.getAllTree();
		for (SysMenu m : allTree) {
			if (m.getPid().equals(pid) && m.getDeep() != 3) {
				this.getMenu(m.getId(), menus);
				menus.add(m);
			}
		}
	}
}
