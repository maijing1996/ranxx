package com.ranxx.mapper;

import org.apache.ibatis.annotations.Param;

import com.ranxx.model.socket.BattleRecord;
import com.ranxx.util.MyMapper;

public interface BattleRecordMapper extends MyMapper<BattleRecord> {

	BattleRecord getByOwnerId(@Param("owner_uid") Long owner_uid);

	boolean enterRoom(@Param("owner_uid") Long uid, @Param("roomId") String roomId, @Param("timestamp") Integer timestamp);
	
}
