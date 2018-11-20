package com.ranxx.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ranxx.model.GameSet;
import com.ranxx.model.common.BaseResponse;
import com.ranxx.service.GameSetService;
import com.ranxx.util.BusinessException;
import com.ranxx.util.StringUtil;

import net.sf.json.JSONObject;

@ResponseBody
@RestController
@RequestMapping(value = "/run/set")
public class GameSetController {
	
	@Autowired
	private GameSetService gameSetService;
	
	/**
	 * 获取游戏设置信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse getInfo() {
		
		BaseResponse baseResponse = new BaseResponse();
		GameSet gameSet = gameSetService.getInfo();
		return baseResponse.setValue("查询成功！", JSONObject.fromObject(gameSet)); 
	}
	
	/**
	 * 修改游戏设置
	 * @return
	 * @throws BusinessException 
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse updateGameSet(@RequestBody Map<String, String> map) throws BusinessException {
		
		BaseResponse baseResponse = new BaseResponse();
		GameSet gameSet = new GameSet();
		gameSet.setId(StringUtil.strToLong(map.get("id")));
		gameSet.setCREATE_REVIE_NUMBER(StringUtil.strToInt(map.get("create_REVIE_NUMBER")));//初始复活次数
		gameSet.setCREATE_REVIE_ADD_NUMBER(StringUtil.strToInt(map.get("create_REVIE_ADD_NUMBER")));//分享增加复活次数
		gameSet.setSHARE_NUMBER(StringUtil.strToInt(map.get("share_NUMBER")));//分享复活使用次数
		gameSet.setShareRevieSwitch(StringUtil.strToInt(map.get("shareRevieSwitch")));//分享复活开关
		boolean updateGameSet = gameSetService.update(gameSet);
		boolean updateGameSet2 = gameSetService.updateGameSet(StringUtil.strToInt(map.get("create_REVIE_NUMBER")), StringUtil.strToInt(map.get("create_REVIE_ADD_NUMBER")), 
				StringUtil.strToInt(map.get("share_NUMBER")), StringUtil.strToInt(map.get("shareRevieSwitch")));
		if(updateGameSet2) {
			return baseResponse.setValue("修改成功！", null);
		}else {
			return baseResponse.setError(500, "修改失败！");
		}
	}
}
