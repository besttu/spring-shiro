package com.shiro.pojo.we;

import com.google.gson.annotations.SerializedName;

public class AccessToken {
	@SerializedName("access_token")
	private String token;
	@SerializedName("expires_in")
	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Override
	public String toString() {
		return "AccessToken [token=" + token + ", expiresIn=" + expiresIn + ", getToken()=" + getToken()
				+ ", getExpiresIn()=" + getExpiresIn() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
