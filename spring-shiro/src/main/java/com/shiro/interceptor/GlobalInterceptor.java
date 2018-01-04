package com.shiro.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shiro.entity.SysSetting;
import com.shiro.service.SettingService;

public class GlobalInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	SettingService settingService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		request.setAttribute("path", basePath);
		List<SysSetting> settings = settingService.getAll();
		for (SysSetting setting : settings) {
			request.setAttribute(setting.getSyskey(), setting.getSysvalue());
		}
		return true;
	}
}
