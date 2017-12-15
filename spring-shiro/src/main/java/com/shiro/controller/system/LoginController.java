package com.shiro.controller.system;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import com.shiro.anno.Log;
import com.shiro.controller.BaseController;
import com.shiro.entity.SysUser;

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

		return "admin/login2";
	}

	/**
	 * 执行登陆操作
	 * 
	 * @param u
	 * @return
	 */
	@Log("登陆验证")
	@RequestMapping("/doLogin")
	public String doLogin(SysUser u, RedirectAttributesModelMap model) {
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(u.getUsername(), u.getPassword());
		if (!currentUser.isAuthenticated()) {
			// token.setRememberMe(true);
			try {
				token.setRememberMe(true);
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				model.addFlashAttribute("error", "未知用户");
				return redirectTo("admin/login");
			} catch (IncorrectCredentialsException ice) {
				model.addFlashAttribute("error", "密码错误");
				return redirectTo("admin/login");
			} catch (LockedAccountException lae) {
				model.addFlashAttribute("error", "账号已锁定");
				return redirectTo("admin/login");
			} catch (ExcessiveAttemptsException ae) {
				model.addFlashAttribute("error", "请10分钟后再重试");
				return redirectTo("admin/login");
			} catch (Exception e) {
				model.addFlashAttribute("error", "其他错误");
				return redirectTo("admin/login");
			}
		}
		return redirectTo("/admin/");

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
