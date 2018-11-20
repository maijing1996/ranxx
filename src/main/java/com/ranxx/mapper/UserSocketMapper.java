package com.ranxx.mapper;

import org.apache.ibatis.annotations.Param;

import com.ranxx.model.User;
import com.ranxx.util.MyMapper;

public interface UserSocketMapper extends MyMapper<User> {
	
	User getUser(@Param("openId") String openId);

	boolean saveToken(@Param("token") String token, @Param("openId") String openId);

	User getUserByToken(@Param("token") String token);
}
