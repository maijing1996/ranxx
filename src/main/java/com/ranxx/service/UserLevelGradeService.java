package com.ranxx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ranxx.mapper.UserLevelGradeMapper;
import com.ranxx.model.UserLevelGrade;
import com.ranxx.model.dto.UserLevelGradeMax;
import com.ranxx.service.common.BaseService;
import com.ranxx.util.BusinessException;

@Service
public class UserLevelGradeService extends BaseService<UserLevelGrade,UserLevelGradeMapper> {

	@Autowired
	private UserService userService;
	
	
	@Override
	protected String getTableName() {
		
		return "user_level_grade";
	}
	
	/**
	 * 查询用户每隔关卡信息
	 * @param uid
	 * @return
	 */
	public List<UserLevelGradeMax> getLevelGrade(Long uid) {
		
		return mapper.getLevelGrade(uid);
	}
	
	/**
	 * 保存用户关卡数
	 * @param uid
	 * @param level
	 * @return
	 */
	public boolean save(Long uid, Integer level) {
		
		return mapper.save(uid, level);
	}

	/**
	 * 修改分数
	 * @return
	 */
	@Transactional(rollbackFor = BusinessException.class)
	public boolean updateGrade(Long uid, Integer level, Integer grade, String openid, Integer maxgrade) {
		
		updateLevelGrade(uid, level, grade);
		boolean update = userService.update(openid, maxgrade);
		return update;
		
	}
	
	
	/**
	 * 修改用户关卡的分数
	 * @param uid
	 * @param level
	 * @param grade
	 * @return
	 */
	public boolean updateLevelGrade(Long uid, Integer level, Integer grade) {
		
		return mapper.updateLevelGrade(uid, level, grade);
	}

	/**
	 * 修改用户关卡的观看次数
	 * @param uid
	 * @param level
	 * @param lookNum
	 * @return
	 */
	public boolean updateLooNum(Long uid, Integer level, Integer lookNum) {
		
		return mapper.updateLooNum(uid, level, lookNum);
	}

	/**
	 * 修改通关关卡
	 * @param level
	 * @param is_carnet
	 * @param uid
	 * @return
	 */
	public boolean updateCarnet(Integer level, Integer is_carnet, Long uid) {
		
		return mapper.updateCarnet(level, is_carnet, uid);
	}

}
