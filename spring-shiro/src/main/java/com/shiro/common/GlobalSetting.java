package com.shiro.common;

public class GlobalSetting {
	public static String salt = "asdfasg";// 全局盐

	public static interface user {
		int USER_COMMON = 1;// 启用
		int USER_BAN = 0;// ban
	}
}
