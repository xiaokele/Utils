package com.xiaokele.MyUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by QiyiLive on 2018/1/8.
 */

public class NetUtils {

    /**
     * 检测当前网络的类型 是否是wifi
     *
     * @param context
     * @return
     */
    public static int checkedNetWorkType(Context context) {
        if (!checkedNetWork(context)) {
            return 0;//无网络
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting()) {
            return 1;//wifi
        } else {
            return 2;//非wifi
        }
    }

    /**
     * 检查是否连接网络
     *
     * @param context
     * @return
     */
    public static boolean checkedNetWork(Context context) {
        // 获得连接设备管理器
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return false;
        /**
         * 获取网络连接对象
         */
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isAvailable()) {
            return false;
        }
        return true;
    }
}
