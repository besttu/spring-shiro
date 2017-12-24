package com.shiro.service;

import java.util.List;

import com.shiro.entity.SysMenu;
import com.shiro.pojo.TreeAll;
import com.shiro.pojo.TreeMenu;

public interface MenuService {

	List<TreeMenu> getRootMenu();

	public List<TreeAll> getTreeMenu();

	/**
	 * 获取用户所拥有的资源
	 * 
	 * @return
	 */
	public List<SysMenu> getAllTree();
}
