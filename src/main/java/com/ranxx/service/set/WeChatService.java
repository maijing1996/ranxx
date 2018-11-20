package com.ranxx.service.set;

import org.springframework.stereotype.Service;

import com.ranxx.mapper.WxConfigMapper;
import com.ranxx.model.WxConfig;
import com.ranxx.service.common.BaseService;

@Service
public class WeChatService extends BaseService<WxConfig, WxConfigMapper>{

	@Override
	protected String getTableName() {
		return "wx_config";
	}

	/**
	 * 获取微信配置信息
	 * @return
	 */
	public WxConfig getWxConfig() {
		
		return mapper.getWxConfig();
	}
	
}
