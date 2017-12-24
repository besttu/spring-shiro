package com.shiro.service;

import java.util.List;

import com.shiro.entity.SysDept;

public interface DeptService {
	public SysDept getDeptById(String id);
	
	public List<SysDept> getAll();
}
