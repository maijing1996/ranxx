package com.ranxx.service;

import org.springframework.stereotype.Service;

import com.ranxx.mapper.GameSetMapper;
import com.ranxx.model.GameSet;
import com.ranxx.service.common.BaseService;


@Service
public class GameSetService extends BaseService<GameSet, GameSetMapper> {

	@Override
	protected String getTableName() {
		
		return "gameset";
	}

	/**
	 * 获取游戏设置信息
	 * @return
	 */
	public GameSet getInfo() {
		
		return mapper.getInfo();
	}
	
	/**
	 * 修改游戏设置
	 */
	public boolean updateGameSet(Integer CREATE_REVIE_NUMBER, Integer CREATE_REVIE_ADD_NUMBER, Integer SHARE_NUMBER, Integer shareRevieSwitch) {
		
		return mapper.updateGameSet(CREATE_REVIE_NUMBER, CREATE_REVIE_ADD_NUMBER, SHARE_NUMBER, shareRevieSwitch);
	}
}
