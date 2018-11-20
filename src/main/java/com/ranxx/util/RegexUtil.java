package com.ranxx.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 * 2018年7月12日
 * @author oumu
 */
public class RegexUtil {
	
	public static String DATE_REGEX = "(20|19)[0-9]{2}-(0[1-9]|1[0-9])-(0[1-9]|[1-2][0-9]|3[0-1])";//日期校验
	
	public static String NUMBER_REGEX = "[0-9]+";//数字校验
	
	public static String PASSWORD_REGEX = "[a-zA-Z0-9]{6,20}";//密码校验
	
	public static String ACCOUNT_REGEX = "[a-zA-Z0-9]{4,18}";//账户校验
	
	public static String IDS_REGEX = "[0-9]{1,10}(,[0-9]{1,10})*";//ids 校验
	
	public static String EMAIL_REGEX = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	
	public static String PHONE_REGEX = "(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}";
	
	public static String SERIES_REGEX = "[\\w|\\W]+(,[\\w|\\W])*";
	
	public static String ID_REGEX = "[0-9]{1,10}";
	
	/**
	 * 正则匹配，符合条件返回  true
	 * 
	 * @param Regex
	 * @param param
	 * @return
	 */
	public static boolean matches(String regex, String param) {
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(param);
        return m.matches();
	}
	
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(matches(SERIES_REGEX, "*,d"));
	}
}
