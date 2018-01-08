package com.xiaokele.MyUtils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.view.View;

import com.QiyiLive.live.R;
import com.QiyiLive.live.activity.LivePlayerActivity;
import com.QiyiLive.live.activity.LivePublishActivity;
import com.QiyiLive.live.activity.SelectLoginTypeActivity;
import com.QiyiLive.live.dialog.DialogInterface;
import com.QiyiLive.live.dialog.NormalAlertDialog;
import com.QiyiLive.live.global.ActivityType;
import com.QiyiLive.live.global.IntentKey;
import com.QiyiLive.live.network.retrofitmodule.BaseModule;

public class NetworkUtils {

    private static String TAG="NetworkUtils";
    private static NormalAlertDialog dialog;

    /**
     * 检测网络是否可用
     */
    public static boolean checkNetworkAvailable(Context context) {
        Boolean isNetConnective;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            isNetConnective = false;
        } else {
            isNetConnective = true;
        }
        return isNetConnective;
    }

    /**
     * 获取当前网络连接的类型
     *
     * @return 返回值与服务器协商一致
     */
//    public static String getNetWorkType(Context context) {
//        String typeString = "Unknown";
//        PackageManager localPackageManager = context.getPackageManager();
//        if (localPackageManager.checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
//            return "Unknown";
//        }
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
//        if (connectivityManager == null) {
//            return "Unknown";
//        }
//        NetworkInfo localNetworkInfo1 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        if (localNetworkInfo1.getState() == NetworkInfo.State.CONNECTED) {
//            return "WIFI";
//
//        }
//        NetworkInfo localNetworkInfo2 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//        if ((localNetworkInfo2 != null) && (localNetworkInfo2.getState() == NetworkInfo.State.CONNECTED)) {
//            return "GPRS";
//
//        }
//        return typeString;
//    }

    // 检测wifi是否打开
    public static boolean isWiFiEnabled(Context inContext) {
        Context context = inContext.getApplicationContext();
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }

    // 判断现在是否连接到wifi
    public static boolean isWiFiActive(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getApplicationContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connManager.getAllNetworkInfo();
        if (networkInfos == null) {
            return false;
        }
        for (NetworkInfo networkInfo : networkInfos) {
            if (networkInfo.getTypeName().equals("WIFI") && networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    // 打开wifi
    public static void enableWiFi(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            if (wifiManager.getWifiState() != WifiManager.WIFI_STATE_ENABLING) {
                wifiManager.setWifiEnabled(true);
            }
        }
    }

//	public static final boolean isGpsOPen(final Context context) { 
//        LocationManager locationManager  
//                                 = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE); 
//        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快） 
//        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER); 
//        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位） 
//        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER); 
//        if (gps || network) { 
//            return true; 
//        } 
//   
//        return false; 
//    }

    /**
     * 验证请求返回的数据
     * */
    public static boolean isCorrectRet(BaseModule baseModule,Activity activity) {

        LogUtils.eLog(TAG,"登录后返回的数据---"+baseModule.getRet());
        switch (baseModule.getRet()) {
            case "1":
                if (!android.text.TextUtils.isEmpty(baseModule.getMsg()) && !activity.isFinishing()) {

                    dialog = new NormalAlertDialog.Builder(activity)
                            .setHeight(0.23f)  //屏幕高度*0.23
                            .setWidth(0.65f)  //屏幕宽度*0.65
                            .setTitleVisible(true)
                            .setTitleText("提示")
                            .setTitleTextSize(18)
                            .setTitleTextColor(R.color.color_1b1b1b)
                            .setContentText(baseModule.getMsg())
                            .setContentTextSize(18)
                            .setSingleMode(true)
                            .setSingleButtonText("确定")
                            .setSingleButtonTextColor(R.color.main_color_yellow)
                            .setButtonTextSize(18)
                            .setCanceledOnTouchOutside(true)
                            .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                                @Override
                                public void clickSingleButton(NormalAlertDialog dialog, View view) {
                                    dialog.dismiss();
                                }
                            }).build();
                    dialog.show();

                }
                return false;
            case "2":
                if (!(activity instanceof LivePlayerActivity) && !(activity instanceof LivePublishActivity)) {
                    dialog = new NormalAlertDialog.Builder(activity)
                            .setHeight(0.23f)  //屏幕高度*0.23
                            .setWidth(0.65f)  //屏幕宽度*0.65
                            .setTitleVisible(false)
                            .setTitleTextColor(R.color.color_1b1b1b)
                            .setContentText(baseModule.getMsg())
                            .setContentTextSize(18)
                            .setSingleMode(true)
                            .setSingleButtonText("继续")
                            .setSingleButtonTextColor(R.color.main_color_yellow)
                            .setButtonTextSize(18)
                            .setCanceledOnTouchOutside(true)
                            .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                                @Override
                                public void clickSingleButton(NormalAlertDialog dialog, View view) {
                                    dialog.dismiss();
                                }
                            }).build();
                    dialog.show();

                }
                return true;
            case "1000":
                //{"ret":"1000","msg":"非法操作，不授权该操作"}  跳转登录界面
//                logout();
//                mApplication.destroyActivity("LivePlayerActivity");
                Intent intent = new Intent(activity, SelectLoginTypeActivity.class);
                intent.putExtra(IntentKey.FROM_ACTIVITY, ActivityType.OtherLogin);
                activity.startActivity(intent);
//                activity.finish();
                return false;
            default:
                return true;

        }
    }

}
