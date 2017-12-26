package com.shiro.common.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.shiro.common.GlobalSetting;
import com.shiro.entity.SysUser;
import com.shiro.entity.SysUserRole;
import com.shiro.service.RoleMenuService;
import com.shiro.service.UserRoleService;
import com.shiro.service.UserService;

public class MyRealm extends AuthorizingRealm {

	/**
	 * 用户服务
	 */
	@Autowired
	private UserService userService;
	/**
	 * 用户角色服务
	 */
	@Autowired
	private UserRoleService sysUserRoleService;
	/**
	 * 角色菜单服务
	 */
	@Autowired
	private RoleMenuService sysRoleMenuService;

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub

		UsernamePasswordToken user = (UsernamePasswordToken) token;
		SysUser sysUser = userService.getUser(user.getUsername());
		if (sysUser == null) {
			throw new UnknownAccountException();
		}
		if (sysUser.getUserstate() != GlobalSetting.user.USER_COMMON) {
			throw new LockedAccountException();
		}
		ByteSource byteSource = ByteSource.Util.bytes(GlobalSetting.salt);// salt
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), byteSource,
				getName());
		return info;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roleNames = sysUserRoleService.findRolesByUid(sysUser.getId());
		Set<String> permissions = sysRoleMenuService.findMenusByUid(sysUser.getId());
		info.setRoles(roleNames);
		info.setStringPermissions(permissions);
		return info;
	}

	public static String validatePassword(String passworld) {
		return new SimpleHash("MD5", passworld, GlobalSetting.salt, 1024).toString();
	}

	// 清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

	/**
	 * 密码加密 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// MD5,"原密码","盐",加密次数
		String pwd = new SimpleHash("MD5", "123456", GlobalSetting.salt, 1024).toString();
		System.out.println(pwd);

	}

}
