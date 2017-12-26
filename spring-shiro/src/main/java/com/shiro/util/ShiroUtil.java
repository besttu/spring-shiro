package com.shiro.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import com.shiro.entity.SysUser;

/**
 * Shiro工具类
 * 
 * @author jameszhou
 *
 */
public class ShiroUtil {

	/**
	 * 密码加密
	 * 
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String md51024Pwd(String password, Object salt) {
		return new SimpleHash("MD5", password, salt, 1024).toString();
	}

	/**
	 * 清除当前用户的授权信息
	 */
	public static void clearAuthorizationInfo(org.apache.shiro.cache.ehcache.EhCacheManager shiroEacheManager) {
		String[] cacheNames = shiroEacheManager.getCacheManager().getCacheNames();
		for (String s : cacheNames) {
			System.out.println("cacheName:" + s);
		}
		Cache<Object, Object> cache = shiroEacheManager.getCache("com.shiro.common.shiro.MyRealm.authorizationCache");
		Object object = cache.get("admin");
		System.out.println("object:" + object);
		System.out.println("name:" + getSessionUser().getUsername());
		cache.remove(getSessionUser().getUsername());
	}

	/**
	 * 获取当前Session中的用户
	 * 
	 * @return
	 */
	public static SysUser getSessionUser() {

		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Object object = subject.getPrincipal();
			if (object != null) {
				SysUser sysUser = (SysUser) object;
				return sysUser;
			}
		}
		return null;
	}

	/**
	 * 获取当前用户ID
	 * 
	 * @return
	 */
	public static String getSessionUid() {

		SysUser sysUser = getSessionUser();

		if (sysUser != null) {

			return sysUser.getId();
		}

		return null;
	}
}
