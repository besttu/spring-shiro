package com.shiro.service.impl;

import java.io.IOException;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.shiro.util.we.WeChatUtil;

@Service
public class TaskJob {
	@Autowired
	EhCacheCacheManager cacheManger;

	public void job1() {
		System.out.println("test1.........................");
		try {
			WeChatUtil.getAccessToken(cacheManger);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
