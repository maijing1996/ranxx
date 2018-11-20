package com.ranxx.mapper.set;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ranxx.model.User;
import com.ranxx.util.MyMapper;

public interface UserSetMapper extends MyMapper<User> {
	
	List<User> userInfo(@Param("nickname") String nickname);
}
