package com.shiro.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.dao.SysLogMapper;
import com.shiro.entity.SysLog;
import com.shiro.entity.SysLogExample;
import com.shiro.entity.SysLogExample.Criteria;
import com.shiro.pojo.DataTable;
import com.shiro.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	SysLogMapper logMapper;

	@Override
	public void insert(SysLog log) {
		// TODO Auto-generated method stub
		logMapper.insert(log);
	}

	public DataTable<SysLog> getAll(int draw, int start, int length, String search) {
		// TODO Auto-generated method stub
		SysLogExample example = new SysLogExample();
		example.setOrderByClause("createTime DESC");
		Criteria c = example.createCriteria();
		if (!StringUtils.isEmpty(search)) {
			c.andTitleLike("%" + search + "%");
		}
		PageHelper.offsetPage(start, length);
		List<SysLog> selectByExample = logMapper.selectByExample(example);
		PageInfo<SysLog> page = new PageInfo<SysLog>(selectByExample);
		long total = page.getTotal();
		DataTable<SysLog> table = new DataTable<SysLog>();
		table.setDraw(++draw);
		table.setRecordsFiltered(total);
		table.setRecordsTotal(total);
		table.setData(selectByExample);
		return table;
	}

	@Override
	public DataTable<SysLog> getAll(int draw, int start, int length, String search, Date start_date, Date end_date) {
		// TODO Auto-generated method stub
		SysLogExample example = new SysLogExample();
		example.setOrderByClause("createTime DESC");
		Criteria c = example.createCriteria();
		if (start_date != null && end_date != null) {
			c.andCreatetimeBetween(start_date, end_date);
		}
		if (!StringUtils.isEmpty(search)) {
			c.andTitleLike("%" + search + "%");
		}
		PageHelper.offsetPage(start, length);
		List<SysLog> selectByExample = logMapper.selectByExample(example);
		PageInfo<SysLog> page = new PageInfo<SysLog>(selectByExample);
		long total = page.getTotal();
		DataTable<SysLog> table = new DataTable<SysLog>();
		table.setDraw(++draw);
		table.setRecordsFiltered(total);
		table.setRecordsTotal(total);
		table.setData(selectByExample);
		return table;
	}

}
