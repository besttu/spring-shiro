package com.shiro.common.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * 限制用户登录次数
 * 
 * @author admin
 *
 */
public class MyMatcher extends HashedCredentialsMatcher {

	private Cache<String, AtomicInteger> passwordRetryCache; // 创建缓存的对象

	public MyMatcher(CacheManager cacheManager) {
		// 赋予缓存对象，此处获取的是我们在ehcache.xml文件中配置,注意getCache("")获取的是xml中的name
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal(); // 获取用户名

		AtomicInteger retryCount = passwordRetryCache.get(username); // 获取用户登录的次数

		if (retryCount == null) { // 如果用户未登陆过
			retryCount = new AtomicInteger(0); // 新建一个登录次数
			passwordRetryCache.put(username, retryCount); // 放入缓存中
		}

		if (retryCount.incrementAndGet() > 5) { // 如果用户登录次数超过三次（此处可根据需要自定义）
			throw new ExcessiveAttemptsException(); // 抛出用户锁定异常类
		}

		boolean matches = super.doCredentialsMatch(token, info); // 判断用户是否可用，即是否为正确的账号密码

		if (matches) {
			passwordRetryCache.remove(username); // 移除缓存中用户的登录次数
		}

		return matches;
	}

}