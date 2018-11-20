package com.ranxx.model.socket;

import com.ranxx.model.common.BaseDomain;

public class BattleRecord extends BaseDomain {
	
//	private Long room_id;//房间id
	private Long owner_uid;//房主uid
	private Long client_uid;//客人uid
	private Integer add_time;//房间创建时间
	private Integer destory_time;//房间销毁时间
	private Integer start_time;//游戏开始时间
	private Integer end_time;//游戏结束时间
	private Integer owner_total;//房主得分
	private Integer client_total;//客人得分
	private Integer ready_time;//点击已准备好的时间
	private Integer enter_time;//客人进入房间的时间
	private Integer is_robot;//是否是机器人  1是  0不是
	private Integer owner_die;//主人是否结束游戏  1已结束  0未结束
	private Integer client_die;//客人是否结束游戏  1已结束  0未结束
	private Integer play_count;//比赛次数
	
	public Integer getIs_robot() {
		return is_robot;
	}
	public void setIs_robot(Integer is_robot) {
		this.is_robot = is_robot;
	}
	public Integer getOwner_die() {
		return owner_die;
	}
	public void setOwner_die(Integer owner_die) {
		this.owner_die = owner_die;
	}
	public Integer getClient_die() {
		return client_die;
	}
	public void setClient_die(Integer client_die) {
		this.client_die = client_die;
	}
	public Integer getPlay_count() {
		return play_count;
	}
	public void setPlay_count(Integer play_count) {
		this.play_count = play_count;
	}
	public Long getOwner_uid() {
		return owner_uid;
	}
	public void setOwner_uid(Long owner_uid) {
		this.owner_uid = owner_uid;
	}
	public Long getClient_uid() {
		return client_uid;
	}
	public void setClient_uid(Long client_uid) {
		this.client_uid = client_uid;
	}
	public Integer getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Integer add_time) {
		this.add_time = add_time;
	}
	public Integer getDestory_time() {
		return destory_time;
	}
	public void setDestory_time(Integer destory_time) {
		this.destory_time = destory_time;
	}
	public Integer getStart_time() {
		return start_time;
	}
	public void setStart_time(Integer start_time) {
		this.start_time = start_time;
	}
	public Integer getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Integer end_time) {
		this.end_time = end_time;
	}
	public Integer getOwner_total() {
		return owner_total;
	}
	public void setOwner_total(Integer owner_total) {
		this.owner_total = owner_total;
	}
	public Integer getClient_total() {
		return client_total;
	}
	public void setClient_total(Integer client_total) {
		this.client_total = client_total;
	}
	public Integer getReady_time() {
		return ready_time;
	}
	public void setReady_time(Integer ready_time) {
		this.ready_time = ready_time;
	}
	public Integer getEnter_time() {
		return enter_time;
	}
	public void setEnter_time(Integer enter_time) {
		this.enter_time = enter_time;
	}
	
}
