package com.shiro.service;

import java.util.List;

import com.shiro.entity.SysMenu;
import com.shiro.pojo.DataTable;
import com.shiro.pojo.TreeAll;
import com.shiro.pojo.TreeMenu;

public interface MenuService {

	public List<TreeMenu> getRootMenu();

	public List<TreeAll> getTreeMenu();

	/**
	 * 获取用户所拥有的资源
	 * 
	 * @return
	 */
	public List<SysMenu> getAllTree();


	public DataTable<SysMenu> getAll(int draw, int start, int length, String search);

	public SysMenu getMenuById(String id);

	public void saveMenu(SysMenu m);

	public void delete(String id);

	public List<SysMenu> getMenuByDeep(int i);

	public List<SysMenu> getMenuByPid(String pid);

	public void save(SysMenu m);
}
