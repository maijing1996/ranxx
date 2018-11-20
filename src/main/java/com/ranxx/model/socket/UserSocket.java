package com.ranxx.model.socket;

import javax.persistence.Transient;

public class UserSocket {
	
	private String avatar;//用户头像
	private String nickName;//用户昵称
	private String token;//用户token
	
	@Transient
	private Integer owner;//1是房主，0非房主
	
	@Transient
	private Integer ready;//非房主才有此字段,值为1  房主为0
	
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public Integer getReady() {
		return ready;
	}
	public void setReady(Integer ready) {
		this.ready = ready;
	}
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
