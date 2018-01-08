package com.xiaokele.MyUtils;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by QiyiLive on 2018/1/8.
 */

public class GpsUtils {
    /**
     * 检测GPS是否打开
     *
     * @return
     */
    public static boolean checkGPSIsOpen(Context context) {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            isOpen=true;
        }else{
            isOpen = false;
        }

        return isOpen;
    }

    /**
     * 跳转GPS设置
     */
    public static void openGPSSettings(final Context context) {
        if (checkGPSIsOpen(context)) {
//            initLocation(); //自己写的定位方法
        } else {
////            //没有打开则弹出对话框
//            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
//
//            builder.setTitle("温馨提示");
//            builder.setMessage("当前应用需要打开定位功能。请点击\"设置\"-\"定位服务\"打开定位功能。");
//            //设置对话框是可取消的
//            builder.setCancelable(false);
//
//            builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                    //跳转GPS设置界面
//                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                    context.startActivity(intent);
//                }
//            });
//            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                    ActivityManager.getInstance().exit();
//                }
//            });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
        }
    }
}
