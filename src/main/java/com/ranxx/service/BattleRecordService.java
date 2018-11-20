package com.ranxx.service;

import org.springframework.stereotype.Service;

import com.ranxx.mapper.BattleRecordMapper;
import com.ranxx.model.socket.BattleRecord;
import com.ranxx.service.common.BaseDomainService;
import com.ranxx.util.BusinessException;

@Service
public class BattleRecordService extends BaseDomainService<BattleRecord, BattleRecordMapper> {

	@Override
	protected String getTableName() {
		
		return "battle_record";
	}
	
	/**
	 * 创建房间
	 * @param battleRecord
	 * @return
	 * @throws BusinessException
	 */
	public boolean createRoom(BattleRecord battleRecord) throws BusinessException {
		
		return this.save(battleRecord);
	}

	/**
	 * 用来判断用户是否创建了房间
	 * @return
	 */
	public BattleRecord getByOwnerId(Long owner_uid) {
		
		return mapper.getByOwnerId(owner_uid);
	}
	
//	/**
//	 * 进入房间
//	 * @param uid
//	 * @param roomId
//	 * @param timestamp
//	 */
//	public boolean enterRoom(Long uid, String roomId, Integer timestamp) {
//		
//		return mapper.enterRoom(uid, roomId, timestamp);
//	}


}
