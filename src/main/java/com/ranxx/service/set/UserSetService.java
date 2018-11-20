package com.ranxx.service.set;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranxx.mapper.set.UserSetMapper;
import com.ranxx.model.User;
import com.ranxx.service.common.BaseService;

@Service
public class UserSetService extends BaseService<User, UserSetMapper>{

	@Override
	protected String getTableName() {
		return "user";
	}

	/**
	 * 查询用户
	 * @return
	 */
	public PageInfo<User> userInfo(Integer page, Integer size, String nickname){
		
		if(page != null && size != null) {
			PageHelper.startPage(page, size);
		}else {
			PageHelper.startPage(1, 20);
		}
		List<User> list = mapper.userInfo(nickname);
		PageInfo<User> pageInfo = new PageInfo<User>(list); 
		return pageInfo;
	}
}
