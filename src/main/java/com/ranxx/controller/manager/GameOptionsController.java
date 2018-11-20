package com.ranxx.controller.manager;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ranxx.model.GameSet;
import com.ranxx.model.common.BaseResponse;
import com.ranxx.model.common.LayuiResponse;
import com.ranxx.service.GameSetService;
import com.ranxx.util.BusinessException;
import com.ranxx.util.StringUtil;

import ch.qos.logback.classic.Logger;

@ResponseBody
@RestController
@RequestMapping(value = "/manager/game/set")
public class GameOptionsController {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GameSetService gameSetService;
	
	/**
	 * 获取游戏设置信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public LayuiResponse getInfo() {
		LayuiResponse layuiResponse = new LayuiResponse();
		GameSet gameSet = gameSetService.getInfo();
		layuiResponse.setCode(0);
		return layuiResponse.setSuccess(null, gameSet, 0);
	}
	
	/**
	 * 修改游戏设置
	 * @return
	 * @throws BusinessException 
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse updateGameSet(@RequestBody Map<String, String> map) {
		
		BaseResponse baseResponse = new BaseResponse();
//		GameSet gameSet = new GameSet();
//		gameSet.setId(StringUtil.strToLong(map.get("id")));
//		gameSet.setCREATE_REVIE_NUMBER(StringUtil.strToInt(map.get("create_revie_number")));//初始复活次数
//		gameSet.setCREATE_REVIE_ADD_NUMBER(StringUtil.strToInt(map.get("create_revie_add_number")));//分享增加复活次数
//		gameSet.setSHARE_NUMBER(StringUtil.strToInt(map.get("share_number")));//分享复活使用次数
//		gameSet.setShareRevieSwitch(StringUtil.strToInt(map.get("shareRevieSwitch")));//分享复活开关
//		boolean updateGameSet = gameSetService.update(gameSet);
		try {
			Integer shareRevieSwitch = StringUtil.strToInt(map.get("shareRevieSwitch"));
			Integer create_revie_number = StringUtil.strToInt(map.get("create_revie_number"));
			Integer create_revie_add_number = StringUtil.strToInt(map.get("create_revie_add_number"));
			Integer share_number = StringUtil.strToInt(map.get("share_number"));
			if(create_revie_number == null) {
				return baseResponse.setError(404, "请输入数字");
			}else if(create_revie_add_number == null) {
				return baseResponse.setError(404, "请输入数字");
			}else if(share_number == null) {
				return baseResponse.setError(404, "请输入数字");
			}else if(shareRevieSwitch == 0 || shareRevieSwitch == 1) {
				boolean updateGameSet2 = gameSetService.updateGameSet(create_revie_number, create_revie_add_number, 
						share_number, shareRevieSwitch);
				if(updateGameSet2) {
					logger.info("修改成功！", updateGameSet2);
					return baseResponse.setValue("修改成功！", null);
				}else {
					return baseResponse.setError(500, "修改失败！");
				}
			}else {
				return baseResponse.setError(500, "分享开关请输入0或者1");
			}
			
		} catch (Exception e) {
			return baseResponse.setError(500, "修改失败！");
		}
	}
}
