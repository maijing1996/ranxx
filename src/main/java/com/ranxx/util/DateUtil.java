package com.ranxx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ranxx.model.common.DateEntity;



public class DateUtil {

	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String FORMAT_YYYY_MM_dd_hh_mm = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_YYYY_MM_dd_hh_mm_ss = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 特定时间，过x分钟后的时间
	 * @param start
	 * @param longTime
	 * @return
	 */
	public static Date longTime(Date start, int longTime){
		try{
			Date afterDate = new Date(start.getTime() + longTime * 60000);
			return afterDate;
		} catch (Exception e) {
			return longTime2(start, longTime);
		}
	}
	
	/**
	 * 特定时间，过x分钟后的时间
	 * @param start
	 * @param longTime
	 * @return
	 */
	public static Date longTime2(Date start, int longTime){
		try{
			Calendar nowTime = Calendar.getInstance();
			nowTime.setTime(start);
			nowTime.add(Calendar.MINUTE, 10);
			return nowTime.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 两个时间差
	 * 单位：分钟
	 * end - start
	 * @param start
	 * @param end
	 * @return
	 */
	public static int leadTime(Date start, Date end){
		if(start != null && end != null){
			long ic = end.getTime() - start.getTime();
			if(ic > 60000){
				return (int) (ic/60000);
			}
		}
		return 0;
	}
	
	/**
	 * 计算特定时间下，n天前后的时间，时间格式为 FMString
	 * 负数则为 n日前
	 *
	 * @param startDate 开始日期
	 * @param days 天数
     * @return
     */
	public static long getDistanceByDaysString(Date date, int days, String FMString) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat (FMString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,days);
		Date endDate = calendar.getTime();
		return parseDate(dateFormat.format(endDate), "yyyy-MM-dd HH:mm:ss").getTime();
	}
	
	/**
	 * 计算特定时间下，n天前后的时间，时间格式为 FMString
	 * 负数则为 n日前
	 *
	 * @param startDate 开始日期
	 * @param days 天数
     * @return
     */
	public static long getDateLong(Date date, int days, String FMString) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat (FMString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		Date endDate = calendar.getTime();
		return parseDate(dateFormat.format(endDate), "yyyy-MM-dd").getTime();
	}
	
	/**
	 * 字符串转换日期对象
	 * 
	 * @param dateString
	 * @param format
	 * @return
	 * @throws ParseException 
	 */
	public static Date parseDate(String dateString, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateString);
	}
	
	public static String format(Date date) {
		return format(date, FORMAT_YYYY_MM_dd_hh_mm_ss);
	}

	public static String format(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static String format(long date) {
		return format(new Date(date));
	}

	public static String format(long date, String format) {
		return format(new Date(date), format);
	}

	/**
	 * 字符串转换日期对象, 默认格式：YYYY-MM-dd hh:mm:ss
	 * 
	 * @param dateString
	 * @return
	 * @throws ParseException 
	 */
	public static Date parseDate(String dateString) throws ParseException {
		return parseDate(dateString, FORMAT_YYYY_MM_dd_hh_mm_ss);
	}

	/**
	 * 字符串转换日期对象
	 * 
	 * @param dateString
	 * @param format
	 * @return
	 */
	public static Date parseDate2(String dateString, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(dateString);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 计算两个时间段的天数
	 *
	 */
	public static int daysBetween(String smdate,String bdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }
	
	/**
	 * 计算两个时间段的天数
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException{  
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }

	/**
	 * 计算从这个日期开始，n天后的日期
	 *
	 * @param startDate 开始日期
	 * @param days 天数
     * @return
     */
	public static String getDistanceByDays(String startDate, int days) {
		DateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(startDate));
			calendar.add(Calendar.DAY_OF_MONTH,days);
			Date endDate = calendar.getTime();
			return dateFormat.format(endDate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 计算从这个日期开始，n天前后的日期
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDistanceByDay(Date date,int days) throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,days);
		return calendar.getTime();
	}

	/**
	 * 计算从这个日期开始，n月后的日期
	 *
	 * @param startDate 开始日期
	 * @param months 月数
	 * @return
	 */
	public static  Date  getDistanceByMonth(String startDate,int months){
		DateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(startDate));
			calendar.add(Calendar.MONTH,months);
			Date endDate = calendar.getTime();
			return endDate;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 比较两个日期大小
	 *
	 * @param sDate
	 * @param dDate
     * @return
     */
	public static int compare_date(String sDate, String dDate) {
	  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		  try {
				 Date sdt = df.parse(sDate);
				 Date ddt = df.parse(dDate);
				  if (sdt.getTime() > ddt.getTime()) {
						  return 1;
					  } else if (sdt.getTime() < ddt.getTime()) {
						  return -1;
					  } else {
						  return 0;
					  }
			  } catch (Exception exception) {
				  exception.printStackTrace();
			  }
		  return 0;
	  }

	/**
	 * 根据日期来判断日期是星期几
	 *
	 * @param dataString
	 * @return
     */
	public static  String getDateDayOfWeek(String dataString){
		String dayOfWeekString = null;

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(dataString));
			int day = cal.get(Calendar.DAY_OF_WEEK);
			switch (day){
				case 1:
					dayOfWeekString="星期日";
					break;
				case 2:
					dayOfWeekString="星期一";
					break;
				case 3:
					dayOfWeekString="星期二";
					break;
				case 4:
					dayOfWeekString="星期三";
					break;
				case 5:
					dayOfWeekString="星期四";
					break;
				case 6:
					dayOfWeekString="星期五";
					break;
				case 7:
					dayOfWeekString="星期六";
					break;
			}
		} catch (ParseException e) {
			return  null;
		}
		return dayOfWeekString;
	}
	
	/**
	 * 根据日期来判断日期是星期几
	 *
	 * @param dataString
	 * @return
     */
	public static  String getDateDayOfWeek(Date dataString){
		
		String dayOfWeekString = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataString);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		switch (day){
			case 1:
				dayOfWeekString="星期日";
				break;
			case 2:
				dayOfWeekString="星期一";
				break;
			case 3:
				dayOfWeekString="星期二";
				break;
			case 4:
				dayOfWeekString="星期三";
				break;
			case 5:
				dayOfWeekString="星期四";
				break;
			case 6:
				dayOfWeekString="星期五";
				break;
			case 7:
				dayOfWeekString="星期六";
				break;
		}
		return dayOfWeekString;
	}
	
	/**
	 * 获取从某个日期起半个月的日期
	 *
	 * @param date  日期开始
	 * @param days  多少天
     * @return
     */
	public static List<DateEntity>  getDateStringRecently(Date date, int days){
		List<DateEntity> list = new ArrayList<DateEntity>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dfResult = new SimpleDateFormat("MM-dd");
		for(int i =0;i<days;i++) {
			DateEntity dateEntity = new DateEntity();
			String dateString = df.format(date.getTime() + i * 24 * 60 * 60 * 1000);
			String dateStringResult = dfResult.format(date.getTime() + i * 24 * 60 * 60 * 1000);
			String dateDayOfWeek = getDateDayOfWeek(dateString);
			dateEntity.setDateString(dateStringResult.replace("-","/"));
			dateEntity.setDateDayOfWeek(dateDayOfWeek);
			list.add(dateEntity);
		}
		return list;
	}
	
	/**
	 * 获得结束月份
	 * 即下个月1号的时间
	 * @return
	 */
	public static Date getEndMon(String staTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		Date endMon = null;
		try {
			calendar.setTime(sdf.parse(staTime));
			calendar.add(Calendar.MONTH, 1);
			endMon = calendar.getTime();
		} catch (ParseException e) {
			return  null;
		}
		return endMon;
	}
	
	/**
	 * 获得当下单位为秒的时间挫
	 * @param date
	 */
	public static long getTimestamp() {
		Date date = new Date();
		return date.getTime()/1000;
	}
	
	/**
	 * 当下时间戳  （int）
	 * @return
	 */
	public static Integer getTimestamp2() {
		Date date = new Date();
		return (int) (date.getTime()/1000);
	}
	
	
	public static void main(String args[]){
		try {
			System.out.println(new Date(getDistanceByDaysString(new Date(), -1, "yyyy-MM-dd 06:30:00")));
			System.out.println(new Date(getDistanceByDaysString(new Date(), -1, "yyyy-MM-dd 23:30:00")));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
