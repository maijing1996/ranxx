package com.ranxx.model;

import javax.persistence.Transient;

import com.ranxx.model.common.BaseEntity;

public class User extends BaseEntity {
	
	private String openid;// 用户的标识
	private String nickname;// 昵称
	private Integer sex;// 用户的性别（1是男性，2是女性，0是未知）
	private String headimgurl;// 用户头像
	private Integer grade;//用戶成績(分数)
	private Integer nowLevel;//关卡
	private String replay;//回放
	private String token;//用户token 标识
	private Integer more_win_num;//多人赢的次数
	private Integer more_num;//多人玩的次数
	private Integer fashion_num;//皮肤
	
	@Transient
	private Integer ranks;
	
	public Integer getMore_win_num() {
		return more_win_num;
	}
	public void setMore_win_num(Integer more_win_num) {
		this.more_win_num = more_win_num;
	}
	public Integer getMore_num() {
		return more_num;
	}
	public void setMore_num(Integer more_num) {
		this.more_num = more_num;
	}
	public Integer getFashion_num() {
		return fashion_num;
	}
	public void setFashion_num(Integer fashion_num) {
		this.fashion_num = fashion_num;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getRanks() {
		return ranks;
	}
	public void setRanks(Integer ranks) {
		this.ranks = ranks;
	}
	public Integer getNowLevel() {
		return nowLevel;
	}
	public void setNowLevel(Integer nowLevel) {
		this.nowLevel = nowLevel;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "User [openid=" + openid + ", nickname=" + nickname + ", sex=" + sex + ", headimgurl=" + headimgurl
				+ ", grade=" + grade + ", nowLevel=" + nowLevel + ", replay=" + replay + "]";
	}
	
}
