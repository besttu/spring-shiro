package com.shiro.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.common.shiro.MyRealm;
import com.shiro.dao.SysRoleMapper;
import com.shiro.dao.SysRoleMenuMapper;
import com.shiro.entity.SysRole;
import com.shiro.entity.SysRoleExample;
import com.shiro.entity.SysRoleExample.Criteria;
import com.shiro.entity.SysRoleMenu;
import com.shiro.entity.SysRoleMenuExample;
import com.shiro.pojo.DataTable;
import com.shiro.service.RoleService;
import com.shiro.util.ShiroUtil;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	SysRoleMapper roleMapper;
	@Autowired
	SysRoleMenuMapper roleMenuMapper;
	@Autowired
	EhCacheCacheManager cahceManager;
	@Autowired
	org.apache.shiro.cache.ehcache.EhCacheManager shiroEacheManager;
	@Autowired
	MyRealm realm;

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

	@Override
	public List<SysRoleMenu> getAll(String roleId) {
		// TODO Auto-generated method stub
		SysRoleMenuExample example = new SysRoleMenuExample();
		com.shiro.entity.SysRoleMenuExample.Criteria c = example.createCriteria();
		c.andRoleidEqualTo(roleId);
		return roleMenuMapper.selectByExample(example);
	}

	@Override
	@CacheEvict(value = "menuCache", allEntries = true)
	public void addAuth(String roleId, String[] mid) {
		// TODO Auto-generated method stub
		// 授权之前需要清除用户缓存
		cahceManager.getCache("menuCache").clear();
		realm.clearCached();
		SysRoleMenuExample example = new SysRoleMenuExample();
		com.shiro.entity.SysRoleMenuExample.Criteria c = example.createCriteria();
		c.andRoleidEqualTo(roleId);
		roleMenuMapper.deleteByExample(example);
		if (mid != null) {
			SysRoleMenu rm = new SysRoleMenu();
			rm.setRoleid(roleId);
			for (String s : mid) {
				rm.setId(UUID.randomUUID().toString());
				rm.setMenuid(s);
				roleMenuMapper.insert(rm);
			}

		}
	}

	@Override
	public SysRole getRoleById(String id) {
		// TODO Auto-generated method stub
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		roleMapper.deleteByPrimaryKey(id);
	}

}
