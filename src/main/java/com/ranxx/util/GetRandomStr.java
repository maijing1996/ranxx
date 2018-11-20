package com.ranxx.util;
 
import java.util.Random;
 
/**
 * Created by Administrator on 2017/1/9.
 * 获取随机字符串
 */
 
public class GetRandomStr {
    public String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
