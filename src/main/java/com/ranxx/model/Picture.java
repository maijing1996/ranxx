package com.ranxx.model;

import com.ranxx.model.common.BaseEntity;

public class Picture extends BaseEntity {
	
	private String imgUrl;
	private String titleText;
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getTitleText() {
		return titleText;
	}
	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}
	
	
}
