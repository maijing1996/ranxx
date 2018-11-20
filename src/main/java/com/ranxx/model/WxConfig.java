package com.ranxx.model;

import com.ranxx.model.common.BaseEntity;

public class WxConfig extends BaseEntity {

	private String appid;
	private String secret;
	private String token;
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
