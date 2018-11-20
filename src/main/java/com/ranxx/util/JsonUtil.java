package com.ranxx.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	/**
     * List<T> 转 json 保存到数据库
     */
    public static <T> String listToJson(List<T> ts) {
        String jsons = JSON.toJSONString(ts);
        return jsons;
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }
    
    /**
     * JSON 转 POJO
     */
     public static <T> T getObject(String pojo, Class<T> tclass) {
        try {
            return JSONObject.parseObject(pojo, tclass);
        } catch (Exception e) {
        	logger.info(tclass + "转 JSON 失败");
        }
        return null;
     }
     
     /**
      * POJO 转 JSON    
      */
     public static <T> String getJson(T tResponse){
         String pojo = JSONObject.toJSONString(tResponse);
         return pojo;
     }
}
