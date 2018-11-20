package com.ranxx.model;

import com.ranxx.model.common.BaseEntity;

public class UserLevelGrade extends BaseEntity {
	
	private Long uid;
	private Integer level;
	private Integer grade;
	private Integer lookNum;
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getLookNum() {
		return lookNum;
	}
	public void setLookNum(Integer lookNum) {
		this.lookNum = lookNum;
	}
	
}
