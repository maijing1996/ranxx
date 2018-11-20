package com.ranxx.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ranxx.model.WxConfig;
import com.ranxx.model.common.BaseResponse;
import com.ranxx.service.WxConfigService;
import com.ranxx.util.BusinessException;

@ResponseBody
@RestController
@RequestMapping(value = "/run/config")
public class WxConfigController {
	
	@Autowired
	private WxConfigService wxConfigService;
	
	/**
	 * 查询微信配置信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public BaseResponse getWxConfig() {
		BaseResponse baseResponse = new BaseResponse();
		
		WxConfig wxConfig = wxConfigService.getWxConfig();
		return baseResponse.setValue("查询成功！", wxConfig);
	}
	
	/**
	 * 更改微信配置信息
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)//, produces="application/json;charset=UTF-8"
	public BaseResponse updateConfig(@RequestBody Map<String, String> map) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		boolean configUpdate = wxConfigService.update(map.get("appid"), map.get("secret"));
		return baseResponse.setValue("修改成功！", configUpdate);
	}
}
