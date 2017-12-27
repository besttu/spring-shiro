package com.shiro.service;

import java.util.List;

import com.shiro.entity.SysRole;
import com.shiro.entity.SysRoleMenu;
import com.shiro.pojo.DataTable;

public interface RoleService {
	public List<SysRole> getAll();

	public DataTable<SysRole> getAll(int draw, int start, int length, String search);

	/**
	 * 获取该角色拥有的权限
	 * 
	 * @param roleId
	 */
	public List<SysRoleMenu> getAll(String roleId);

	public void addAuth(String roleId, String[] mid);

	public SysRole getRoleById(String id);

	public void deleteById(String id);

	public void editRole(SysRole role);

	public void addRole(SysRole id);

	public SysRole getRole(String id);
}
