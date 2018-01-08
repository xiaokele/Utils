package com.xiaokele.MyUtils.utils;

import android.util.Log;

import com.QiyiLive.live.entity.GiftInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class GiftLiveConversionUtil {

    private static GiftLiveConversionUtil mFaceConversionUtil;

    /**
     * 保存于内存中的礼物HashMap
     */
    private HashMap<Integer, GiftInfo> mGiftMap = new HashMap<Integer, GiftInfo>();

    /**
     * 保存于内存中的礼物集合
     */
    private ArrayList<GiftInfo> mGifts = new ArrayList<GiftInfo>();

    //每页礼物的集合
    private ArrayList<ArrayList<GiftInfo>> mGiftInfoListList = new ArrayList<>();

    /**
     * 每一页礼物的个数
     */
    private final int PAGE_SIZE = 8;

    private GiftLiveConversionUtil() {

    }

    public static GiftLiveConversionUtil getInstace() {
        if (mFaceConversionUtil == null) {
            mFaceConversionUtil = new GiftLiveConversionUtil();
        }
        return mFaceConversionUtil;
    }

    /**
     * 解析字符
     *
     * @param data
     */
    public void ParseData(ArrayList<GiftInfo> data) {
        try {
            mGifts.clear();
            mGiftMap.clear();
            //模拟添加两页礼物
//            for (int i=0;i<2;i++) {
                for (GiftInfo gift : data) {
                    mGifts.add(gift);
                    mGiftMap.put(gift.getId(), gift);
                    //遍历如果有图片就存储到文件中

                }
//            }

        } catch (Exception e) {
            Log.e("GiftConversionUtil", "插入礼物出错了");
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void parseLiveGift() {
        mGiftInfoListList.clear();

//		if(mGiftInfoListList.size() > 0){
//			return;
//		}

        try {
            //礼物页数
            int pageCount = (int) Math.ceil(mGifts.size() / PAGE_SIZE +
                    mGifts.size() % PAGE_SIZE);
            for (int i = 0; i < pageCount; i++) {
                mGiftInfoListList.add(getGiftList(i, mGifts));
            }
            LogUtils.eLog("---礼物的页数","num="+mGiftInfoListList.size());
            LogUtils.eLog("---礼物的个数","num="+mGifts.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<GiftInfo> getGiftList(int page, ArrayList<GiftInfo> giftList) {
        int startIndex = page * PAGE_SIZE;
        int endIndex = startIndex + PAGE_SIZE;
        if (endIndex > giftList.size()) {
            endIndex = giftList.size();
        }
        // 不这么写，会在viewpager加载中报集合操作异常，我也不知道为什么
        ArrayList<GiftInfo> list = new ArrayList<GiftInfo>();
        list.addAll(giftList.subList(startIndex, endIndex));
//		if (list.size() < PAGE_SIZE) {
//			for (int i = list.size(); i < PAGE_SIZE; i++) {
//				GiftInfo object = new GiftInfo();
//				list.add(object);
//			}
//		}
        return list;
    }


    public GiftInfo GetMapItem(int key) {
        return mGiftMap.get(key);
    }

    public ArrayList<ArrayList<GiftInfo>> getGiftLists() {
        return mGiftInfoListList;
    }
}