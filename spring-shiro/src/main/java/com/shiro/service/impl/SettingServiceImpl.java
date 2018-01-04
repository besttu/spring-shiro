package com.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.shiro.dao.SysSettingMapper;
import com.shiro.entity.SysSetting;
import com.shiro.service.SettingService;

import net.sf.ehcache.CacheManager;

@Service
public class SettingServiceImpl implements SettingService {
	@Autowired
	SysSettingMapper settionMapper;

	@Cacheable(value = "settingCache", key = "1")
	public List<SysSetting> getAll() {
		List<SysSetting> selectByExample = settionMapper.selectByExample(null);
		return selectByExample;
	}

	@CacheEvict(value = "settingCache", key = "1")
	public void clearCacheSetting() {

	}

	@Override
	public void update(SysSetting s) {
		// TODO Auto-generated method stub
		settionMapper.updateByPrimaryKeySelective(s);
	}

}
