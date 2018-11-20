package com.ranxx.mapper;

import org.apache.ibatis.annotations.Param;

import com.ranxx.model.GameSet;
import com.ranxx.util.MyMapper;

public interface GameSetMapper extends MyMapper<GameSet> {
	
	GameSet getInfo();
	
	boolean updateGameSet(@Param("CREATE_REVIE_NUMBER") Integer CREATE_REVIE_NUMBER, @Param("CREATE_REVIE_ADD_NUMBER") Integer CREATE_REVIE_ADD_NUMBER,
			@Param("SHARE_NUMBER") Integer SHARE_NUMBER, @Param("shareRevieSwitch") Integer shareRevieSwitch);
	
}
