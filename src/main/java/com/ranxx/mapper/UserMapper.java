package com.ranxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ranxx.model.User;
import com.ranxx.util.MyMapper;


public interface UserMapper extends MyMapper<User> {
	
	List<User> listinfo();
	
	User getUser(@Param("openid") String openid);

	boolean update(@Param("openid") String openid, @Param("grade") Integer grade);
	
	boolean save(@Param("openid") String openid, @Param("grade") Integer grade, 
			@Param("sex") Integer sex, @Param("nickname") String nickname, @Param("headimgurl") String headimgurl);
	
	boolean updateLevel(@Param("openid") String openid, @Param("nowLevel") Integer nowLevel);
	
	boolean updateUser(@Param("openid") String openid, @Param("nickname") String nickname, @Param("headimgurl") String headimgurl);

	boolean updateReplay(@Param("openid") String openid,@Param("replay") String replay);
	
	
	//---------------------------------------------------
	
	
	//---------------------------------------------------
}
