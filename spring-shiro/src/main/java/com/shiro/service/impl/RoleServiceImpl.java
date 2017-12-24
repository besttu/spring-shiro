package com.shiro.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.shiro.dao.SysRoleMapper;
import com.shiro.entity.SysRole;
import com.shiro.entity.SysRoleExample;
import com.shiro.entity.SysRoleExample.Criteria;
import com.shiro.pojo.DataTable;
import com.shiro.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	SysRoleMapper roleMapper;

	public List<SysRole> getAll() {
		return roleMapper.selectByExample(null);
	}

	@Override
	public DataTable<SysRole> getAll(int draw, int start, int length, String search) {
		// TODO Auto-generated method stub
		List<SysRole> selectByExample = roleMapper.selectByExample(null);
		PageHelper.offsetPage(start, length);
		SysRoleExample example = new SysRoleExample();
		Criteria c = example.createCriteria();
		if (StringUtils.isNotBlank(search)) {
			c.andRolenameLike("%" + search + "%");
		}
		List<SysRole> lists = roleMapper.selectByExample(example);
		PageInfo<SysRole> pageInfo = new PageInfo<>(lists);
		long total = pageInfo.getTotal();
		DataTable<SysRole> table = new DataTable<SysRole>();
		table.setData(lists);
		table.setDraw(++draw);
		table.setRecordsFiltered(total);
		table.setRecordsTotal(total);
		return table;
	}

}
