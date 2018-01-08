package com.xiaokele.MyUtils.utils;

import android.text.TextUtils;

import com.QiyiLive.live.entity.CityInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CityUserConversionUtil {

    private List<String> mProvice = new ArrayList<String>();
    private HashMap<String, List<String>> mProvice_City = new HashMap<String, List<String>>();
    private static CityUserConversionUtil mCityUserUtil;

    public static CityUserConversionUtil getInstace() {
        if (mCityUserUtil == null) {
            mCityUserUtil = new CityUserConversionUtil();
        }
        return mCityUserUtil;
    }

    public List<String> getProviceList(List<CityInfo> mDate) {
        if (mProvice == null || mProvice.size() <= 0)
            parseData(mDate);
        return mProvice;

    }

    public List<String> getCityList(String provice, List<CityInfo> mDate) {
        if (TextUtils.isEmpty(provice))
            provice = mProvice.get(0);
        if (mProvice_City.get(provice) == null || mProvice_City.get(provice).size() <= 0)
            parseData(mDate);

        List<String> list = mProvice_City.get(provice);
        if (null != list && list.size() > 0) {
            return list;
        } else {
            return mProvice_City.get(mProvice.get(0));
        }
    }

    public void parseData(List<CityInfo> mDate) {
        for (CityInfo info : mDate) {
            int flag = 0;
            for (String provice : mProvice) {
                if (provice.equals(info.getProvince())) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                if (!info.getProvince().equals(""))
                    mProvice.add(info.getProvince());
                mProvice_City.put(info.getProvince(), new ArrayList<String>());
                mProvice_City.get(info.getProvince()).add("全部");
            }
            mProvice_City.get(info.getProvince()).add(info.getCity());
        }
    }

    public int getCurrentProvice(String provice) {
        return mProvice.indexOf(provice);
    }

    public int getCurrentCity(String provice, String city) {
        return mProvice_City.get(provice).indexOf(city);
    }

}
