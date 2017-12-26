package com.shiro.common.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.shiro.util.ShiroUtil;

/**
 * shiro密码加密配置
 * 
 * @author L.cm
 *
 */
public class PasswordHash implements InitializingBean {
	private String algorithmName;
	private int hashIterations;

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public int getHashIterations() {
		return hashIterations;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.hasLength(algorithmName, "algorithmName mast be MD5、SHA-1、SHA-256、SHA-384、SHA-512");
	}

	/**
	 * 加密算法
	 * 
	 * @param source
	 * @param salt
	 * @return
	 */
	public String toHex(Object source, Object salt) {
		return new SimpleHash(algorithmName, source, salt, hashIterations).toHex();
	}
}