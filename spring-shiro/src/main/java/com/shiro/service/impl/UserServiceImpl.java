package com.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.mysql.fabric.xmlrpc.base.Data;
import com.shiro.dao.SysDeptMapper;
import com.shiro.dao.SysUserMapper;
import com.shiro.entity.SysDept;
import com.shiro.entity.SysDeptExample;
import com.shiro.entity.SysUser;
import com.shiro.entity.SysUserExample;
import com.shiro.entity.SysUserExample.Criteria;
import com.shiro.pojo.DataTable;
import com.shiro.service.UserService;
import com.shiro.util.ShiroUtil;
import com.sun.tools.javac.resources.compiler;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	SysUserMapper userMapper;
	@Autowired
	SysDeptMapper deptMapper;

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

	@Override
	public DataTable<SysUser> startPage(int draw, int start, int length, String search) {
		SysUserExample e = new SysUserExample();
		// TODO Auto-generated method stub
		Criteria c = e.createCriteria();
		if (StringUtil.isNotEmpty(search)) {
			c.andUsernameLike("%" + search + "%");
		}
		PageHelper.offsetPage(start, length);
		List<SysUser> lists = userMapper.selectByExample(e);
		PageInfo<SysUser> pageInfo = new PageInfo<>(lists);
		long total = pageInfo.getTotal();
		DataTable<SysUser> l = new DataTable<SysUser>();
		l.setRecordsFiltered(total);
		l.setRecordsTotal(total);
		l.setData(lists);
		l.setDraw(draw);
		for (SysUser u : lists) {
			if (u.getDeptid() == null) {
				continue;
			}
			SysDeptExample example = new SysDeptExample();
			com.shiro.entity.SysDeptExample.Criteria createCriteria = example.createCriteria();
			createCriteria.andIdEqualTo(u.getDeptid());
			SysDept selectByExample = deptMapper.selectByExample(example).get(0);
			u.setDeptid(selectByExample.getDeptname());
		}
		return l;
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public SysUser getUserById(String id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

}
