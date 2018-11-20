package com.ranxx.model;

public class Token {
	// 获取到的凭证  
    private String token;  
    // 获取到ticket  
    private String ticket;  
    // 凭证有效时间，单位：秒  
    private int expiresIn;  
    // 添加时间  
    private long addTime;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public long getAddTime() {
		return addTime;
	}
	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}  
    
    

}
