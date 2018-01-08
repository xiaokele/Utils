package com.xiaokele.MyUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by QiyiLive on 2018/1/8.
 */

public class OtherUtils {

    /**
     * Md5 32位 or 16位 加密
     *
     * @param plainText
     * @return 32位加密
     */
    public static String Md5(String plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    /**
     * 手机号正则判断
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isPhoneNumber(String str) throws PatternSyntaxException {
        if (str != null) {
            String pattern = "(13\\d|14[579]|15[^4\\D]|17[^49\\D]|18\\d)\\d{8}";

            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(str);
            return m.matches();
        } else {
            return false;
        }
    }
}
