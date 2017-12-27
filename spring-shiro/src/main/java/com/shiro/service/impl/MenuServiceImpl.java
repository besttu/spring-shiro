package com.shiro.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.shiro.dao.SysMenuMapper;
import com.shiro.dao.SysRoleMenuMapper;
import com.shiro.entity.SysMenu;
import com.shiro.entity.SysMenuExample;
import com.shiro.entity.SysMenuExample.Criteria;
import com.shiro.entity.SysRoleMenu;
import com.shiro.entity.SysRoleMenuExample;
import com.shiro.pojo.TreeAll;
import com.shiro.pojo.TreeMenu;
import com.shiro.service.MenuService;
import com.shiro.util.ShiroUtil;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	SysMenuMapper menuMapper;
	@Autowired
	SysRoleMenuMapper roleMenuMapper;

	// 需要注意的是当一个支持缓存的方法在对象内部被调用时是不会触发缓存功能的
	// @Cacheable(value = "permissionCache", key = "#uid")
	public List<SysMenu> getAllTree() {
		String sessionUid = ShiroUtil.getSessionUid();
		List<SysMenu> tree = menuMapper.getTree(sessionUid);
		return tree;
	}

	/**
	 * 菜单栏缓存
	 */
	// @Cacheable(value = "menuCache", key = "")
	public List<TreeMenu> getRootMenu() {
		List<SysMenu> allTree = this.getAllTree();
		List<TreeMenu> trees = new ArrayList<TreeMenu>();
		for (SysMenu m : allTree) {
			// 获取所有pid为0的资源
			if (m.getDeep() == 1) {
				TreeMenu t = new TreeMenu();
				t.setSysMenu(m);
				this.getMenu(m.getId(), t.getChildren(), allTree);
				trees.add(t);
			}
		}
		return trees;

	}

	private void getMenu(String pid, List<SysMenu> menus, List<SysMenu> allTree) {
		for (SysMenu m : allTree) {
			if (m.getPid().equals(pid) && m.getDeep() != 3) {
				System.out.println("name:" + m.getResource());
				this.getMenu(m.getId(), menus, allTree);
				menus.add(m);
			}
		}
	}

	/**
	 * 该角色所拥有的资源
	 * 
	 * @param id
	 * @return
	 */
	public Set<String> getAllByRoleId(String id) {
		SysRoleMenuExample example = new SysRoleMenuExample();
		com.shiro.entity.SysRoleMenuExample.Criteria c = example.createCriteria();
		c.andRoleidEqualTo(id);
		List<SysRoleMenu> selectByExample = roleMenuMapper.selectByExample(example);
		Set<String> menuIds = new HashSet<String>();
		if (selectByExample != null) {
			for (SysRoleMenu rm : selectByExample) {
				String menuId = rm.getMenuid();
				menuIds.add(menuId);
			}
		}
		return menuIds;
	}

	/**
	 * 获取所有资源
	 * 
	 * @return
	 */
	private List<SysMenu> getAll() {
		SysMenuExample example = new SysMenuExample();
		example.setOrderByClause("sort");
		return menuMapper.selectByExample(null);
	}

	/**
	 * 把所有menu装载到TreeAll
	 * 
	 * @param lists
	 * @return
	 */
	public List<TreeAll> getTreeMenu() {
		List<SysMenu> lists = this.getAll();
		List<TreeAll> alls = new ArrayList<TreeAll>();
		for (SysMenu m : lists) {
			// pid为0的元素
			if (m.getDeep() == 1) {
				TreeAll<SysMenu> tree = new TreeAll<SysMenu>();
				this.getMenu1(m.getId(), lists, tree.getChildren());
				tree.setTree(m);
				alls.add(tree);
			}
		}
		return alls;
	}

	private void getMenu1(String id, List<SysMenu> lists, List<TreeAll> alls) {
		for (SysMenu l : lists) {
			if (l.getPid().equals(id)) {
				TreeAll<SysMenu> all = new TreeAll<SysMenu>();
				this.getMenu1(l.getId(), lists, all.getChildren());
				all.setTree(l);
				alls.add(all);
			}
		}
	}

}
