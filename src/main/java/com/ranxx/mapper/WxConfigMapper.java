package com.ranxx.mapper;

import org.apache.ibatis.annotations.Param;

import com.ranxx.model.WxConfig;
import com.ranxx.util.MyMapper;

public interface WxConfigMapper extends MyMapper<WxConfig> {
	
	WxConfig getWxConfig();

	boolean update(@Param("appid") String appid, @Param("secret") String secret);
}
