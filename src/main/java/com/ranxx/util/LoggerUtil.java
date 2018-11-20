package com.ranxx.util;

import java.util.Date;

import org.slf4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 日志打印公共类
 * 2018年3月15日
 * @author oumu
 */
public class LoggerUtil {

	/**
	 * 出现错误的打印日志
	 * @param logger
	 * @param location
	 * @param description
	 * @param params
	 * @param e
	 */
	public static void error(Logger logger, String location, String description, Object params, Exception e){
		logger.error("------------------错误------------------");
		logger.error("------所在方法：" + location);
		logger.error("------详情描述：" + description);
		logger.error("------错误原因：" + e);
		logger.error("------参数详情：{}", JSONObject.toJSONString(params));
		logger.error("------------------错误------------------");
	}
	
	/**
	 * 单条数据打印
	 * @param logger
	 * @param info
	 */
	public static void oneInfo(Logger logger, String info) {
		logger.error("----------------------" + info + "----------------------");
	}
	
	/**
	 * 不保存的信息打印
	 * @param logger
	 * @param description
	 * @param carId
	 * @param driverId
	 */
	public static void notSave(Logger logger, String description, Long carId, Long driverId) {
		logger.error("------原因描述：" + description);
		logger.error("------车辆ID：" + carId);
		logger.error("------学员ID：" + driverId);
	}
	
	/**
	 * 需要保存的文件，日志打印
	 * @param logger
	 * @param param1
	 * @param param2
	 */
	public static void save(Logger logger, Object param1, Object param2) {
		logger.error("------------需要保存的数据------------");
		logger.error("------------开始-数据：{}", JSONObject.toJSONString(param1));
		logger.error("------------结束-数据：{}", JSONObject.toJSONString(param2));
		logger.error("------------需要保存的数据------------");
	}
	
	/**
	 * 不保存学时信息的详细描述
	 * @param logger
	 * @param start
	 * @param end
	 * @param driverId
	 * @param driveTime
	 */
	public static void notSaveDetail(Logger logger, Date start, Date end, Long driverId, Integer driveTime) {
		logger.error("------学时不保存，有以下原因：------");
		logger.error("------1、不在有效时间内使用------");
		logger.error("------2、学时已超出当天最大范围值------");
		logger.error("------3、未获取学时信息学时------");
		logger.error("------开始日期：" + start);
		logger.error("------结束日期：" + end);
		logger.error("------学员Id：" + driverId);
		logger.error("------学时时间：" + driveTime);
	}
	
	/**
	 * 计算结果打印
	 * @param logger
	 * @param maxInt
	 * @param ic
	 * @param driveTime
	 * @param size
	 * @param start
	 * @param newEnd
	 */
	public static void calculateReturn(Logger logger, Integer maxInt, Integer ic, Integer driveTime,
			Integer size, Date start, Date newEnd) {
		logger.error("------使用的学时：" + maxInt);
		logger.error("------计算的学时：" + ic);
		logger.error("------采集的学时：" + driveTime);
		logger.error("------数据数量：" + size);
		logger.error("------开始时间：" + start);
		logger.error("------结束时间：" + newEnd);
	}
}
