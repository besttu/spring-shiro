package com.shiro.service;

import java.util.List;

import com.shiro.entity.SysDept;
import com.shiro.entity.SysSetting;

public interface SettingService {
	/**
	 * 得到全局变量，并且缓存起来
	 * 
	 * @return
	 */
	public List<SysSetting> getAll();

	/**
	 * 清除缓存
	 */
	public void clearCacheSetting();

	public void update(SysSetting s);
}
