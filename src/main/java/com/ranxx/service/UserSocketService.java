package com.ranxx.service;

import org.springframework.stereotype.Service;

import com.ranxx.mapper.UserSocketMapper;
import com.ranxx.model.User;
import com.ranxx.model.socket.UserSocket;
import com.ranxx.service.common.BaseService;

@Service
public class UserSocketService extends BaseService<User, UserSocketMapper> {

	@Override
	protected String getTableName() {
		
		return "user";
	}

	/**
	 * 根据openId查询用户信息
	 * @param openId
	 * @return
	 */
	public User getUser(String openId) {
		
		return mapper.getUser(openId);
	}

	/**
	 * 保存/修改token
	 * @param randomString
	 * @param openId
	 * @return
	 */
	public boolean saveToken(String token, String openId) {
		
		return mapper.saveToken(token, openId);
	}

	public User getUserByToken(String token) {
		
		return mapper.getUserByToken(token);
	}
}
