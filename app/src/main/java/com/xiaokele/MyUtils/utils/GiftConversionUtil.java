package com.xiaokele.MyUtils.utils;

import android.content.Context;
import android.util.Log;

import com.QiyiLive.live.entity.GiftInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class GiftConversionUtil {

    /**
     * 每一页礼物的个数
     */
    private int pageSize = 6;

    private static GiftConversionUtil mFaceConversionUtil;

    /**
     * 保存于内存中的礼物HashMap
     */
    private HashMap<Integer, GiftInfo> mGiftMap = new HashMap<Integer, GiftInfo>();

    /**
     * 保存于内存中的礼物集合
     */
    private ArrayList<GiftInfo> mGifts = new ArrayList<GiftInfo>();

    /**
     * 礼物分页的结果集合
     */
    public ArrayList<ArrayList<GiftInfo>> giftLists = new ArrayList<ArrayList<GiftInfo>>();

    private GiftConversionUtil() {

    }

    public static GiftConversionUtil getInstace() {
        if (mFaceConversionUtil == null) {
            mFaceConversionUtil = new GiftConversionUtil();
        }
        return mFaceConversionUtil;
    }

    /**
     * 解析字符
     *
     * @param data
     */
    public void ParseData(ArrayList<GiftInfo> data, Context context) {
        try {
            mGifts.clear();
            mGiftMap.clear();
            giftLists.clear();
            if (null != data) {
                for (GiftInfo gift : data) {
                    mGifts.add(gift);
                    mGiftMap.put(gift.getId(), gift);
                }
            }

            int pageCount = (int) Math.ceil(mGifts.size() / 8 + 0.1);

            for (int i = 0; i < pageCount; i++) {
                giftLists.add(getData(i));
            }
        } catch (Exception e) {
            Log.e("GiftConversionUtil", "插入礼物出错了");
            e.printStackTrace();
        }
    }

    /**
     * 获取分页数据
     *
     * @param page
     * @return
     */
    private ArrayList<GiftInfo> getData(int page) {
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;

        if (endIndex > mGifts.size()) {
            endIndex = mGifts.size();
        }
        // 不这么写，会在viewpager加载中报集合操作异常，我也不知道为什么
        ArrayList<GiftInfo> list = new ArrayList<GiftInfo>();
        list.addAll(mGifts.subList(startIndex, endIndex));
        if (list.size() < pageSize) {
            for (int i = list.size(); i < pageSize; i++) {
                GiftInfo object = new GiftInfo();
                list.add(object);
            }
        }
        return list;
    }

    public GiftInfo GetMapItem(int key) {
        return mGiftMap.get(key);
    }
}