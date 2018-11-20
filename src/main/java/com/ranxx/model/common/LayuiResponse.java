package com.ranxx.model.common;

public class LayuiResponse {

	private int code=200;
	private String msg=null;
	private long count=0;
	private Object data=null;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public long getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public LayuiResponse setSuccess(String msg, Object data, long count){
		this.msg = msg;
		this.data = data;
		this.count = count;
		return this;
	}
	public LayuiResponse setError(int code, String msg){
		this.code = code;
		this.msg = msg;
		return this;
	}
}
