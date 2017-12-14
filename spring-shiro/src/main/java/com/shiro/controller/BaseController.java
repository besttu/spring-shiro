package com.shiro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	public static String ADMIN = "admin/";

	/**
	 * 重定向至地址 url
	 * 
	 * @param url
	 *            请求地址
	 * @return
	 */
	protected String redirectTo(String url) {
		StringBuffer rto = new StringBuffer("redirect:");
		rto.append(url);
		return rto.toString();
	}

}
