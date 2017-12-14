package com.shiro.service;

import com.shiro.entity.SysUser;

public interface UserService {
	/**
	 * 通过name查找用户
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public SysUser getUser(String name);

}
