package com.shiro.service;

import java.util.List;

import com.shiro.entity.SysRole;
import com.shiro.pojo.DataTable;

public interface RoleService {
	public List<SysRole> getAll();

	public DataTable<SysRole> getAll(int draw, int start, int length, String search);
}
