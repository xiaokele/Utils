package com.xiaokele.MyUtils.utils;

/**
 * Created by supertramp on 17/1/30.
 */
public class ValueUtils {

    public static String getValue(long value) {

        LogUtils.eLog("---要装换的数值","value="+value);
        //大于一万小于一亿显示几点几万
        if (value > 10000 & value < 100000000) {
            double w =  value /(double) 10000;
            //下面的操作作用是取小数点后两位
            double ww = (double)Math.round(w*100)/100;
            return String.valueOf(ww) + "万";
        } else {
            //超过一亿显示几点几亿
            if (value > 100000000) {
                double y = value / (double)100000000;
                double yy = (double) Math.round(y * 100) / 100;
                return String.valueOf(yy) + "亿";
            }
        }
        return String.valueOf(value);
    }
}
