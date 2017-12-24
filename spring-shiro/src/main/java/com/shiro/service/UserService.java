package com.shiro.service;

import com.shiro.entity.SysUser;
import com.shiro.pojo.DataTable;

public interface UserService {
	/**
	 * 通过name查找用户
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public SysUser getUser(String name);

	public DataTable<SysUser> startPage(int draw,int start, int length, String search);

	public void deleteUser(String id);

	public SysUser getUserById(String id);
	
	public void editUser(SysUser u,String[] roleIds);

	public void saveUser(SysUser user, String[] roleIds);

}
