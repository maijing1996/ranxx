package com.ranxx.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ranxx.model.common.BaseEntity;

public class GameSet extends BaseEntity {
	
	private Integer CREATE_REVIE_NUMBER;//初始复活次数
	private Integer CREATE_REVIE_ADD_NUMBER;//分享增加复活次数
	private Integer SHARE_NUMBER;//分享复活使用次数
	private Integer shareRevieSwitch;//分享复活开关
	
	@JsonProperty
	public Integer getCREATE_REVIE_NUMBER() {
		return CREATE_REVIE_NUMBER;
	}
	@JsonProperty
	public void setCREATE_REVIE_NUMBER(Integer cREATE_REVIE_NUMBER) {
		CREATE_REVIE_NUMBER = cREATE_REVIE_NUMBER;
	}
	@JsonProperty
	public Integer getCREATE_REVIE_ADD_NUMBER() {
		return CREATE_REVIE_ADD_NUMBER;
	}
	@JsonProperty
	public void setCREATE_REVIE_ADD_NUMBER(Integer cREATE_REVIE_ADD_NUMBER) {
		CREATE_REVIE_ADD_NUMBER = cREATE_REVIE_ADD_NUMBER;
	}
	@JsonProperty
	public Integer getSHARE_NUMBER() {
		return SHARE_NUMBER;
	}
	@JsonProperty
	public void setSHARE_NUMBER(Integer sHARE_NUMBER) {
		SHARE_NUMBER = sHARE_NUMBER;
	}
	public Integer getShareRevieSwitch() {
		return shareRevieSwitch;
	}
	public void setShareRevieSwitch(Integer shareRevieSwitch) {
		this.shareRevieSwitch = shareRevieSwitch;
	}
	@Override
	public String toString() {
		return "GameSet [CREATE_REVIE_NUMBER=" + CREATE_REVIE_NUMBER + ", CREATE_REVIE_ADD_NUMBER="
				+ CREATE_REVIE_ADD_NUMBER + ", SHARE_NUMBER=" + SHARE_NUMBER + ", shareRevieSwitch=" + shareRevieSwitch
				+ "]";
	}
	
	
}
