package com.shiro.service;

import java.util.List;

import com.shiro.entity.SysDept;
import com.shiro.entity.SysLog;
import com.shiro.pojo.DataTable;

public interface LogService {

	public void insert(SysLog log);

	public  DataTable<SysLog> getAll(int draw, int start, int length, String search);

}
