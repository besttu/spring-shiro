package com.shiro.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.shiro.entity.SysMenu;

/**
 * 菜单树
 * 
 * @author Gaojun.Zhou
 * @date 2016年12月26日 上午10:34:31
 */
public class TreeMenu implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO()
	 */

	private static final long serialVersionUID = 1L;
	/**
	 * 菜单
	 */
	private SysMenu sysMenu;

	/**
	 * 子菜单
	 */
	private List<SysMenu> children = new ArrayList<SysMenu>();

	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

}
