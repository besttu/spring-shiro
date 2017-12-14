package com.shiro.dao;

import com.shiro.entity.SysUserRole;
import com.shiro.entity.SysUserRoleExample;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {
	long countByExample(SysUserRoleExample example);

	int deleteByExample(SysUserRoleExample example);

	int deleteByPrimaryKey(String id);

	int insert(SysUserRole record);

	int insertSelective(SysUserRole record);

	List<SysUserRole> selectByExample(SysUserRoleExample example);

	SysUserRole selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

	int updateByExample(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

	int updateByPrimaryKeySelective(SysUserRole record);

	int updateByPrimaryKey(SysUserRole record);

	Set<String> findRolesByUid(String userId);
}