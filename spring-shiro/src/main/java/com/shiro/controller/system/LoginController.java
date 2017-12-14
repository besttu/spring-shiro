package com.shiro.controller.system;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import com.shiro.anno.Log;
import com.shiro.controller.BaseController;
import com.shiro.entity.SysUser;
import com.sun.tools.internal.xjc.model.SymbolSpace;

@Controller
@RequestMapping("/admin")
public class LoginController extends BaseController {
	/**
	 * 登陆操作
	 * 
	 * @param u
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {

		return "admin/login";
	}

	/**
	 * 执行登陆操作
	 * 
	 * @param u
	 * @return
	 */
	@Log("登陆验证")
	@RequestMapping("/doLogin")
	public String doLogin(SysUser u, Model model) {
		System.out.println("kaishidenglu");
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(u.getUsername(), u.getPassword());

		if (!currentUser.isAuthenticated()) {
			// token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				model.addAttribute("error", "未知用户");
				return redirectTo("/login");
			} catch (IncorrectCredentialsException ice) {
				model.addAttribute("error", "密码错误");
				return redirectTo("/login");
			} catch (LockedAccountException lae) {
				model.addAttribute("error", "账号已锁定");
				return redirectTo("/login");
			} catch (AuthenticationException ae) {
				model.addAttribute("error", "服务器繁忙");
				return redirectTo("/login");
			}
		}
		return null;

	}

	@RequestMapping("/captcha")
	public void captcha(HttpServletRequest request, HttpServletResponse response) {
		KaptchaExtend kaptchaExtend = new KaptchaExtend();
		try {
			kaptchaExtend.captcha(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
