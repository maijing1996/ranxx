package com.ranxx.model;

public class Message {

	private String type;
	private String openId;
	private String token;
	private String roomId;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	@Override
	public String toString() {
		return "Message [type=" + type + ", openId=" + openId + ", token=" + token + ", roomId=" + roomId + "]";
	}
	
	
}
