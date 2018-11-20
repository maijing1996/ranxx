package com.ranxx.model.dto;

public class UserLevelGradeMax {
	
	private Long id;
	private Long uid;
	private Integer level;//关卡
	private Integer grade;//分数
	private Integer lookNum;//观看次数
	private Integer maxNum;//最大观看次数
	private Integer is_carnet;//是否通关
	
	public Integer getIs_carnet() {
		return is_carnet;
	}
	public void setIs_carnet(Integer is_carnet) {
		this.is_carnet = is_carnet;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Integer getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	
	@Override
	public String toString() {
		return "UserLevelGradeMax [id=" + id + ", uid=" + uid + ", level=" + level + ", grade=" + grade + ", lookNum="
				+ lookNum + ", maxNum=" + maxNum + ", is_carnet=" + is_carnet + "]";
	}
	
}
