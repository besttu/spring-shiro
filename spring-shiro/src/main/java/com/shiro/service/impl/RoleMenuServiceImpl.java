package com.shiro.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.dao.SysRoleMenuMapper;
import com.shiro.service.RoleMenuService;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
	@Autowired
	SysRoleMenuMapper roleMenuMapper;

	public Set<String> findMenusByUid(String id) {
		// TODO Auto-generated method stub
		Set<String> findMenusByUid = roleMenuMapper.findMenusByUid(id);
		return findMenusByUid;
	}

}
