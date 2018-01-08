package com.xiaokele.MyUtils;

import android.util.Base64;

/**
 * Created by QiyiLive on 2018/1/8.
 */

public class Base64Utils {

    /**
     * 字符串进行Base64编码
     * @param str
     */
    public static String StringToBase64(String str){
        String encodedString = Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
        return encodedString;
    }

    /**
     * 字符串进行Base64解码
     * @param encodedString
     * @return
     */
    public static String Base64ToString(String encodedString){
        String decodedString =new String(Base64.decode(encodedString,Base64.DEFAULT));
        return decodedString;
    }
}
