package com.shiro.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.dao.SysRoleMapper;
import com.shiro.dao.SysUserRoleMapper;
import com.shiro.entity.SysUserRole;
import com.shiro.entity.SysUserRoleExample;
import com.shiro.entity.SysUserRoleExample.Criteria;
import com.shiro.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	SysUserRoleMapper userRoleMapper;

	@Override
	public Set<String> findRolesByUid(String id) {

		return userRoleMapper.findRolesByUid(id);

	}

}
