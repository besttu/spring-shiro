package com.shiro.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.mysql.fabric.xmlrpc.base.Data;
import com.shiro.common.GlobalSetting;
import com.shiro.common.shiro.MyRealm;
import com.shiro.common.shiro.PasswordHash;
import com.shiro.dao.SysDeptMapper;
import com.shiro.dao.SysUserMapper;
import com.shiro.dao.SysUserRoleMapper;
import com.shiro.entity.SysDept;
import com.shiro.entity.SysDeptExample;
import com.shiro.entity.SysUser;
import com.shiro.entity.SysUserExample;
import com.shiro.entity.SysUserExample.Criteria;
import com.shiro.entity.SysUserRole;
import com.shiro.entity.SysUserRoleExample;
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
	@Autowired
	SysUserRoleMapper userRoleMapper;
	@Autowired
	PasswordHash passwordHash;

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
			String pid = u.getDeptid();
			if (pid != null) {
				SysDept selectByPrimaryKey = deptMapper.selectByPrimaryKey(u.getDeptid());
				if (selectByPrimaryKey == null) {
					continue;
				}
				u.setDeptid(selectByPrimaryKey.getDeptname());
			}
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

	@Override
	public void editUser(SysUser u, String[] roleIds) {
		// TODO Auto-generated method stub
		if (u.getPassword() == null || u.getPassword().trim().equals("")) {
			u.setPassword(null);
		} else {
			u.setPassword(MyRealm.validatePassword(u.getPassword()));
		}
		if (u.getUserimg() == null || u.getUserimg().trim().equals("")) {
			u.setUserimg("static/dist/img/avatar5.png");
		}
		userMapper.updateByPrimaryKeySelective(u);// 更新用户信息
		// 绑定之前先删除原有的用户角色之间的关联
		SysUserRoleExample e = new SysUserRoleExample();
		com.shiro.entity.SysUserRoleExample.Criteria c = e.createCriteria();
		c.andUseridEqualTo(u.getId());
		userRoleMapper.deleteByExample(e);
		SysUserRole ur = new SysUserRole();
		if (roleIds != null) {
			for (String r : roleIds) {
				String id = UUID.randomUUID().toString();
				ur.setRoleid(r);
				ur.setUserid(u.getId());
				ur.setId(id);
				userRoleMapper.insert(ur);
			}

		}

	}

	@Override
	public void saveUser(SysUser user, String[] roleIds) {
		// TODO Auto-generated method stub
		user.setId(UUID.randomUUID().toString());
		if (user != null && !user.equals("")) {
			String p = passwordHash.toHex(user.getPassword(), GlobalSetting.salt);
			System.out.println("ps:" + p);
			user.setPassword(p);
		}
		SysUserRole ur = new SysUserRole();
		if (roleIds != null) {
			for (String s : roleIds) {
				String id = UUID.randomUUID().toString();
				ur.setRoleid(s);
				ur.setUserid(user.getId());
				ur.setId(id);
				userRoleMapper.insert(ur);
			}
		}
		userMapper.insertSelective(user);

	}

}
