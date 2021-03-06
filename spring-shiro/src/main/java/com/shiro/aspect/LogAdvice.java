package com.shiro.aspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.shiro.anno.Log;
import com.shiro.entity.SysLog;
import com.shiro.entity.SysUser;
import com.shiro.service.LogService;
import com.shiro.util.ShiroUtil;

/**
 * 正常业务日志记录
 * 
 * @author Administrator
 *
 */
@Aspect
@Component
public class LogAdvice {

	public static final Logger LOG = Logger.getLogger(LogAdvice.class);
	@Autowired
	LogService logService;

	@Pointcut("@annotation(com.shiro.anno.Log)")
	public void controllerAspect() {

	}

	/**
	 * 当方法正常返回是执行
	 * 
	 * @param joinPoint
	 */
	@AfterReturning("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Log log = method.getAnnotation(Log.class);
		SysUser sysUser = ShiroUtil.getSessionUser();
		if (log != null) {
			SysLog sysLog = new SysLog();
			sysLog.setId(UUID.randomUUID().toString());
			sysLog.setCreatetime(new Date());
			sysLog.setTitle(log.value());
			sysLog.setUsername((sysUser != null) ? sysUser.getUsername() : "system");
			sysLog.setUrl(request.getRequestURI().toString());
			sysLog.setParams(new Gson().toJson(request.getParameterMap()));
			logService.insert(sysLog);
			LOG.info("记录日志:" + sysLog.toString());
		}
	}
}
