package com.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.dao.SysUserMapper;
import com.shiro.entity.SysUser;
import com.shiro.entity.SysUserExample;
import com.shiro.entity.SysUserExample.Criteria;
import com.shiro.service.UserService;
import com.shiro.util.ShiroUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	SysUserMapper userMapper;

	@Override
	public SysUser getUser(String name) {
		// TODO Auto-generated method stub
		SysUserExample e = new SysUserExample();
		Criteria c = e.createCriteria();
		c.andUsernameEqualTo(name);
		List<SysUser> selectByExample = userMapper.selectByExample(e);
		if (selectByExample == null) {
			return null;
		}
		return selectByExample.get(0);
	}


}
