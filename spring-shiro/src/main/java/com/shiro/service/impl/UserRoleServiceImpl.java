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

	@Override
	public Set<String> getRoleIdByUid(String id) {
		// TODO Auto-generated method stub
		SysUserRoleExample e = new SysUserRoleExample();
		Criteria c = e.createCriteria();
		c.andUseridEqualTo(id);
		List<SysUserRole> selectByExample = userRoleMapper.selectByExample(e);
		Set<String> l = new HashSet<String>();
		for (SysUserRole ur : selectByExample) {
			String roleid = ur.getRoleid();
			l.add(roleid);
		}
		l.add("");
		return l;
	}

}
