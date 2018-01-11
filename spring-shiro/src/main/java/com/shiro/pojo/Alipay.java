package com.shiro.pojo;

/**
 * 支付宝
 * 
 * @author admin
 *
 */
public class Alipay {
	private String appId;
	/**
	 * 私钥
	 */
	private String privateKey;
	/**
	 * 公钥
	 */
	private String publicKey;
	private String notifyUrl;
	private String returnUrl;
	private String gatewayUrl;

	private String charSet = "utf-8";
	private String signType = "RSA2";

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getGatewayUrl() {
		return gatewayUrl;
	}

	public void setGatewayUrl(String gatewayUrl) {
		this.gatewayUrl = gatewayUrl;
	}

	@Override
	public String toString() {
		return "Alipay [appId=" + appId + ", privateKey=" + privateKey + ", publicKey=" + publicKey + ", notifyUrl="
				+ notifyUrl + ", returnUrl=" + returnUrl + ", gatewayUrl=" + gatewayUrl + "]";
	}

}
