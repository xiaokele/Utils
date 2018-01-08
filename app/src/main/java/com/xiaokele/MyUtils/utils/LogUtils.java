package com.xiaokele.MyUtils.utils;

import android.content.Context;
import android.util.Log;

/**
 * 日志工具类
 */

public class LogUtils {

    private static Context mContext;
    private static boolean isOpen=false;

    public static void init(Context context){
        LogUtils.mContext=context;
    }

    public static void isOpenLog(boolean isOpen){
        LogUtils.isOpen = isOpen;
    }

    public static void iLog(String tag,String msg){
          if (isOpen)
          Log.i(tag,msg);
    }

    public static void dLog(String tag,String msg){
        if (isOpen) {
            Log.d(tag, msg);
        }
    } public static void eLog(String tag,String msg){
        if (isOpen) {
            Log.e(tag, msg);
        }
    }
}
