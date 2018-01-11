package com.shiro.controller.system;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.code.kaptcha.servlet.KaptchaExtend;
import com.shiro.anno.Log;
import com.shiro.controller.BaseController;
import com.shiro.entity.SysUser;
import com.shiro.util.ServerResponse;

@Controller
@RequestMapping("/admin")
public class LoginController extends BaseController {
	/**
	 * 登陆操作
	 * 
	 * @param u
	 * @return
	 */
	@Log("开始登录")
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
	@PostMapping("/doLogin")
	@ResponseBody
	public ServerResponse<Object> doLogin(SysUser u) {
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(u.getUsername(), u.getPassword());
			token.setRememberMe(true);
			try {
				currentUser.login(token);
				return ServerResponse.createBySuccess();
			} catch (UnknownAccountException uae) {
				return ServerResponse.createByErrorMessage("未知用户");
			} catch (IncorrectCredentialsException ice) {
				return ServerResponse.createByErrorMessage("密码错误");
			} catch (LockedAccountException lae) {
				return ServerResponse.createByErrorMessage("账号已锁定");
			} catch (ExcessiveAttemptsException ae) {
				return ServerResponse.createByErrorMessage("请10分钟后再重试");
			} catch (Exception e) {
				return ServerResponse.createByErrorMessage("未知用户");
			}
		}
		return ServerResponse.createBySuccess("faild", u);

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
