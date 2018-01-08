package com.xiaokele.MyUtils.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.QiyiLive.live.BaseApplication;
import com.QiyiLive.live.common.DateHelper;
import com.QiyiLive.live.common.LogHelper;
import com.QiyiLive.live.entity.ApplicationEntity;
import com.QiyiLive.live.global.AppPath;
import com.QiyiLive.live.global.LogGlobal;
import com.QiyiLive.live.global.SaveDataGlobal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

/**
 * @author 任东卫
 * @version 1.0
 * @fileName FileUtils.java
 * @package
 * @description 文件工具类
 * @email 86930007@qq.com
 */
public class FileUtils {

    private final static String CACHE_NAME = "cache.dat";
    private static File webpPath;
    //图片下载时间
    private static long downLoadTime;

    /**
     * 判断SD是否可以
     *
     * @return
     */
    public static boolean isSdcardExist() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    public static void saveBitmap(Bitmap bm, String url) {

        LogGlobal.log("fileName:" + url);
        File f = new File(AppPath.getDownloadPath(), getPicName(url));
        try {
            f.createNewFile();
        } catch (IOException e) {

        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            LogGlobal.log("saveBitmap false-FileNotFoundException");
        }
        bm.compress(Bitmap.CompressFormat.PNG, 90, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            LogGlobal.log("saveBitmap false-IOException");
        }
    }

    public static void saveBitmap(Bitmap bm) {

        String name = MD5.getMessageDigest((bm.getHeight() * bm.getWidth() + "").getBytes()) + ".png";
        LogGlobal.log("fileName:" + name);
        File f = new File(AppPath.getDownloadPath(), name);
        if (f.exists()) {
            return;
        }
        try {
            f.createNewFile();
        } catch (IOException e) {

        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            LogGlobal.log("saveBitmap false-FileNotFoundException");
        }
        bm.compress(Bitmap.CompressFormat.PNG, 90, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            LogGlobal.log("saveBitmap false-IOException");
        }
    }

    public static String getFileName(String url) {
        String[] strs = url.split("/");
        return strs[strs.length - 1];
    }

    public static String getPicName(String url) {
        String[] strs = url.split("/");
        return strs[strs.length - 1] + ".png";
    }

    public static String formatLocalImageName(String url) {
        return "file://" + url;
    }

    /**
     * 通过判断文件是否存在
     *
     * @param path
     * @return
     */

    public static boolean isFileExists(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                return false;
            }

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }

    /**
     * 创建根目录
     *
     * @param path 目录路径
     */
    public static void createDirFile(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 创建文件
     *
     * @param path 文件路径
     * @return 创建的文件
     */
    public static File createNewFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return null;
            }
        }
        return file;
    }

    /**
     * 创建webp存储文件
     *
     * @param path 文件路径
     * @return 创建的文件
     */
    public static File createWebpFile(String path) {
        webpPath = new File(path);
        if (!webpPath.exists()) {
            webpPath.mkdirs();
        }
        return webpPath;
    }

    /**
     * 获取webp存储文件
     * */
    public static String getWebpPath(){
        return AppPath.getDownloadPath();
    }

    /**
     * 删除文件夹
     *
     * @param folderPath 文件夹的路径
     */
    public static void delFolder(String folderPath) {
        delAllFile(folderPath);
        String filePath = folderPath;
        filePath = filePath.toString();
        File myFilePath = new File(filePath);
        myFilePath.delete();
    }

    /**
     * 删除文件
     *
     * @param path 文件的路径
     */
    public static void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);
                delFolder(path + "/" + tempList[i]);
            }
        }
    }

    /**
     * 获取文件的Uri
     *
     * @param path 文件的路径
     * @return
     */
    public static Uri getUriFromFile(String path) {
        File file = new File(path);
        return Uri.fromFile(file);
    }

    /**
     * 换算文件大小
     *
     * @param size
     * @return
     */
    public static String formatFileSize(long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "未知大小";
        if (size < 1024) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1048576) {
            fileSizeString = df.format((double) size / 1024) + "K";
        } else if (size < 1073741824) {
            fileSizeString = df.format((double) size / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) size / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static long getAmrDuration(File file) throws IOException {
        long duration = -1;
        int[] packedSize = {12, 13, 15, 17, 19, 20, 26, 31, 5, 0, 0, 0, 0, 0,
                0, 0};
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            long length = file.length();// 文件的长度
            int pos = 6;// 设置初始位置
            int frameCount = 0;// 初始帧数
            int packedPos = -1;
            // ///////////////////////////////////////////////////
            byte[] datas = new byte[1];// 初始数据值
            while (pos <= length) {
                randomAccessFile.seek(pos);
                if (randomAccessFile.read(datas, 0, 1) != 1) {
                    duration = length > 0 ? ((length - 6) / 650) : 0;
                    break;
                }
                packedPos = (datas[0] >> 3) & 0x0F;
                pos += packedSize[packedPos] + 1;
                frameCount++;
            }
            // ///////////////////////////////////////////////////
            duration += frameCount * 20;// 帧数*20
        } finally {
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        }
        return duration;
    }

    /**
     * 下载文件到本地
     *
     * @param urlString 被下载的文件地址
     * @param pathName  本地文件保存位置
     * @throws Exception 各种异常
     */
    public static void downloadFileToCache(final String urlString, final String pathName) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                // 构造URL
                URL url = null;
                try {
                    url = new URL(urlString);
                    // 打开连接
                    URLConnection con = url.openConnection();
                    // 输入流
                    InputStream is = con.getInputStream();
                    // 1K的数据缓冲
                    byte[] bs = new byte[1024];
                    // 读取到的数据长度
                    int len;
                    // 输出的文件流
                    OutputStream os = new FileOutputStream(pathName);
                    // 开始读取
                    while ((len = is.read(bs)) != -1) {
                        os.write(bs, 0, len);
                    }
                    // 完毕，关闭所有链接
                    os.close();
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                long endTime = System.currentTimeMillis();
                downLoadTime =endTime-startTime;
            }
        }).start();
    }

    /**
     * 将application实例写入文件
     */
    public static void persistentCache(BaseApplication appApl) {
        File sdFile = new File(AppPath.getCachePath(), CACHE_NAME);
        ApplicationEntity entity = getApplicationEntity(appApl);
        try {
            FileOutputStream fos = new FileOutputStream(sdFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(entity);// 写入
            oos.close();
            fos.close(); // 关闭输出流
        } catch (Exception e) {
            LogGlobal.log("persistentCache-----" + e.toString());
        }
    }

    public static BaseApplication getCache(Context context) {
        ApplicationEntity entity = null;

        try {

//            FileInputStream inputStream = new FileInputStream(AppPath.getCachePath() + CACHE_NAME);
//            entity = (ApplicationEntity) new ObjectInputStream(inputStream).readObject();
//            inputStream.close();

            File file = new File(AppPath.getCachePath() + CACHE_NAME);
            if (!file.exists()) {
                file.mkdir();
            }
            FileInputStream inputStream = new FileInputStream(file);
            entity = (ApplicationEntity) new ObjectInputStream(inputStream).readObject();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null == entity) {
            return null;
        }
        return rebulidApplication(context, entity);

    }

    private static ApplicationEntity getApplicationEntity(BaseApplication appApl) {

        ApplicationEntity entity = new ApplicationEntity();
        entity.setmActiveAddSave(appApl.mActiveAddSave);
        entity.setmCity(SaveDataGlobal.getString(SaveDataGlobal.MY_CIITY,""));
        entity.setmForgetPasswordTime(appApl.mForgetPasswordTime);
        entity.setmFriendCircleTime(appApl.mFriendCircleTime);
        entity.setmHasLatAndLng(appApl.mHasLatAndLng);
        entity.setmLat(Integer.valueOf(SaveDataGlobal.getString(SaveDataGlobal.MY_LAT,"")));
        entity.setmLng(Integer.valueOf(SaveDataGlobal.getString(SaveDataGlobal.MY_LNG,"")));
        entity.setmMsgId(appApl.mMsgId);
        entity.setmNotificationId(appApl.mNotificationId);
        entity.setmPreloadEntity(appApl.mPreload);
        entity.setmProvince(appApl.mProvince);
        entity.setmRechargeInfoList(appApl.mRechargeInfoList);
        entity.setmRegisterAge(appApl.mRegisterAge);
        entity.setmRegisterBday(appApl.mRegisterBday);
        entity.setmRegisterGender(appApl.mRegisterGender);
        entity.setmRegisterHobby(appApl.mRegisterHobby);
        entity.setmRegisterNickname(appApl.mRegisterNickname);
        entity.setmRegisterParentUid(appApl.mRegisterParentUid);
        entity.setmRegisterPassword(appApl.mRegisterPassword);
        entity.setmRegisterTime(appApl.mRegisterTime);
        entity.setmRegisterUsername(appApl.mRegisterUsername);
        entity.setmUserInfo(appApl.mUserInfo);
        return entity;

    }

    private static BaseApplication rebulidApplication(Context context, ApplicationEntity entity) {

        BaseApplication appApl = (BaseApplication) context.getApplicationContext();
        appApl.mActiveAddSave = entity.getmActiveAddSave();
        SaveDataGlobal.putString(SaveDataGlobal.MY_CIITY,entity.getmCity()) ;
        appApl.mForgetPasswordTime = entity.getmForgetPasswordTime();
        appApl.mFriendCircleTime = entity.getmFriendCircleTime();
        appApl.mHasLatAndLng = entity.ismHasLatAndLng();
        SaveDataGlobal.putString(SaveDataGlobal.MY_LAT,entity.getmLat()+"") ;
        SaveDataGlobal.putString(SaveDataGlobal.MY_LNG,entity.getmLng()+"") ;
        appApl.mMsgId = entity.getmMsgId();
        appApl.mNotificationId = entity.getmNotificationId();
        appApl.mPreload = entity.getmPreloadEntity();
        appApl.mProvince = entity.getmProvince();
        appApl.mRechargeInfoList = entity.getmRechargeInfoList();
        appApl.mRegisterAge = entity.getmRegisterAge();
        appApl.mRegisterAvatar = entity.getmRegisterBday();
        appApl.mRegisterBday = entity.getmRegisterBday();
        appApl.mRegisterGender = entity.getmRegisterGender();
        appApl.mRegisterHobby = entity.getmRegisterHobby();
        appApl.mRegisterNickname = entity.getmRegisterNickname();
        appApl.mRegisterParentUid = entity.getmRegisterParentUid();
        appApl.mRegisterPassword = entity.getmRegisterPassword();
        appApl.mRegisterTime = entity.getmRegisterTime();
        appApl.mRegisterUsername = entity.getmRegisterUsername();
        appApl.mUserInfo = entity.getmUserInfo();

        GiftConversionUtil.getInstace().ParseData(appApl.mPreload.getGift_live(), context);
        return appApl;

    }

    public static boolean isCacheExist() {

        File file = new File(AppPath.getCachePath() + CACHE_NAME);
        if (file.exists()) {
            return true;
        }
        return false;

    }

    public static void deleteCache() {

        File file = new File(AppPath.getCachePath() + CACHE_NAME);
        if (file.exists()) {
            file.delete();
        }

    }

    public static String getErrorLog(Context context) {

        StringBuilder sb = new StringBuilder();
        try {

            FileInputStream inputStream = new FileInputStream(AppPath.getLogPath() + LogHelper.LOG_ERROR + DateHelper.getDate() + ".txt");

            //当文件没有结束时每次读取一个字节显示
            while (inputStream.available() > 0) {
                sb.append((char) inputStream.read());
            }
            inputStream.close();
        } catch (Exception e) {
            LogGlobal.log("getErrorLog-----" + e.toString());
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 读取SD卡上的文件
     *
     * @param filepath
     * @return
     */
    public static String readSdcardFile(String filepath) {
        String content = "";
        File file = new File(filepath);
        if (file.isDirectory()) {
            LogGlobal.log("The File doesn't not exist.");
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    while ((line = buffreader.readLine()) != null) {
                        content += line + "\n";
                    }
                    instream.close();
                }
            } catch (FileNotFoundException e) {
                LogGlobal.log("The File doesn't not exist.");
            } catch (IOException e) {
                LogGlobal.log(e.getMessage());
            }
        }
        return content;
    }
}
