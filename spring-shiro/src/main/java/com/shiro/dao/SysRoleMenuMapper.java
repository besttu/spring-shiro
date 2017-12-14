package com.shiro.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.shiro.entity.SysRoleMenu;
import com.shiro.entity.SysRoleMenuExample;

public interface SysRoleMenuMapper {
	long countByExample(SysRoleMenuExample example);

	int deleteByExample(SysRoleMenuExample example);

	int deleteByPrimaryKey(String id);

	int insert(SysRoleMenu record);

	int insertSelective(SysRoleMenu record);

	List<SysRoleMenu> selectByExample(SysRoleMenuExample example);

	SysRoleMenu selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") SysRoleMenu record, @Param("example") SysRoleMenuExample example);

	int updateByExample(@Param("record") SysRoleMenu record, @Param("example") SysRoleMenuExample example);

	int updateByPrimaryKeySelective(SysRoleMenu record);

	int updateByPrimaryKey(SysRoleMenu record);

	Set<String> findMenusByUid(String id);
}