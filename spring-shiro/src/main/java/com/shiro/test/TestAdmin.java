package com.shiro.test;

import java.util.Date;
import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.shiro.entity.SysMenu;
import com.shiro.entity.SysRole;
import com.shiro.entity.SysUser;
import com.shiro.service.MenuService;
import com.shiro.service.RoleMenuService;
import com.shiro.service.RoleService;
import com.shiro.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
public class TestAdmin {

	@Autowired
	private RoleService sysRoleService;
	@Autowired
	private RoleMenuService sysRoleMenuService;
	@Autowired
	private MenuService sysMenuService;
	@Autowired
	private UserService sysUserService;

	/**
	 * 创建一个Admin用户
	 */
	@Test
	public void addAdmin() {
		// sysMenuService.getAllTree();
	}

	/**
	 * 修改Admin的密码位123456
	 */
	@Test
	public void changeAdminPwd() {
		// MD5,"密码","盐",加密次数
		String pwd = new SimpleHash("MD5", "123456", "admin", 1024).toString();
		System.out.println(pwd);
	}

}
