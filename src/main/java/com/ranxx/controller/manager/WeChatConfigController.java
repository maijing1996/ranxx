package com.ranxx.controller.manager;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ranxx.model.WxConfig;
import com.ranxx.model.common.BaseResponse;
import com.ranxx.model.common.LayuiResponse;
import com.ranxx.service.WxConfigService;
import com.ranxx.util.BusinessException;
import com.ranxx.util.StringUtil;

@ResponseBody
@RestController
@RequestMapping(value = "/manager/wechat")
public class WeChatConfigController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WxConfigService wxConfigService;
	
	/**
	 * 查询微信配置
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public LayuiResponse getWeChatConfig() {
		
		LayuiResponse layuiResponse = new LayuiResponse();
		WxConfig wxConfig = wxConfigService.getWxConfig();
		layuiResponse.setCode(0);
		return layuiResponse.setSuccess(null, wxConfig, 1);
		
	}
	
	/**
	 * 修改微信信息
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse update(@RequestBody Map<String, String> map) throws BusinessException {
		
		BaseResponse baseResponse = new BaseResponse();
		String appid = map.get("appid");
		String secret = map.get("secret");
		if(appid == null || appid.trim().isEmpty()) {
			return baseResponse.setError(404, "appid为空！");
		}else if(secret == null || secret.trim().isEmpty()){
			return baseResponse.setError(404, "secret为空！");
		}else {
			WxConfig wxConfig = new WxConfig();
			wxConfig.setAppid(appid);
			wxConfig.setSecret(secret);
			wxConfig.setId(StringUtil.strToLong(map.get("id")));
			wxConfigService.update(wxConfig);
			return baseResponse.setValue("操作成功！", null);
		}
	}
	
}
