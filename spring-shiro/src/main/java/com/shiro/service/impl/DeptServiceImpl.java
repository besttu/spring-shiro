package com.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.dao.SysDeptMapper;
import com.shiro.entity.SysDept;
import com.shiro.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	SysDeptMapper deptMapper;

	public SysDept getDeptById(String id) {
		return deptMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysDept> getAll() {
		// TODO Auto-generated method stub
		return deptMapper.selectByExample(null);
	}
}
