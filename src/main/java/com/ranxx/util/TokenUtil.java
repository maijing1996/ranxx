package com.ranxx.util;

public class TokenUtil {

	/**
	 * 获得加密的token
	 * @param str
	 * @return
	 */
	public static String token(String str){
		StringBuffer buffer = new StringBuffer();
		buffer.append(MD5Util.MD5(str));
		buffer.append("@hy");
		return buffer.toString();
	}
}
