package com.ranxx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranxx.mapper.UserMapper;
import com.ranxx.model.User;
import com.ranxx.service.common.BaseService;

@Service
public class UserService extends BaseService<User, UserMapper> {

	@Override
	protected String getTableName() {
		
		return "user";
	}
	
	/**
	 * 回放游戏过程
	 * @return
	 */
	public boolean updateReplay(String openid,String replay) {
		
		return mapper.updateReplay(openid,replay);
	}
	
	/**
	 * 修改用户的昵称头像
	 * @param openid
	 * @param nickname
	 * @param headimgurl
	 * @return
	 */
	public boolean updateUser(String openid, String nickname, String headimgurl) {
		
		return mapper.updateUser(openid, nickname, headimgurl);
	}
	
	
	/**
	 * 修改关卡
	 * @param openid
	 * @param nowLevel
	 * @return
	 */
	public boolean updateLevel(String openid,Integer nowLevel) {
		
		return mapper.updateLevel(openid,nowLevel);
	}
	
	/**
	 * 保存用户信息
	 * @param openid
	 * @param grade
	 * @param sex
	 * @param nickname
	 * @param headimgurl
	 * @return
	 */
	public boolean save(String openid,Integer grade,Integer sex,String nickname,String headimgurl) {
		
		return mapper.save(openid,grade,sex,nickname,headimgurl);
	}
	
	/**
	 * 修改分数接口
	 * @param openid
	 * @param grade
	 * @return
	 */
	public boolean update(String openid,Integer grade) {
		
		return mapper.update(openid,grade);
	}

	/**
	 * 查詢查询用户以及当前的排名 
	 * @param user
	 * @return
	 */
	public User getUser(String openid) {
		
		return mapper.getUser(openid);
	}
	
	/**
	 * 查询所有的用户
	 * @return
	 */
	public PageInfo<User> listinfo(){
		PageHelper.startPage(1, 100);
		List<User> list = mapper.listinfo();
		PageInfo<User> pageInfo = new PageInfo<User>(list); 
		return pageInfo;
	}
	
}
