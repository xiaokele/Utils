package com.xiaokele.MyUtils.utils;

/**
 * Created by chenxb on 16/5/25.
 */
public class PointUtils {
    private static float mStartX;
    private static float mStartY;

    public static void setStart(float mStartX, float mStartY) {
        PointUtils.mStartX = mStartX;
        PointUtils.mStartY = mStartY;
    }

    private static double distanceFromStart(float x2, float y2) {
        return Math.sqrt((mStartX - x2) * (mStartX - x2) + (mStartY - y2) * (mStartY - y2));
    }

    public static boolean isClick(float x2, float y2) {
        return distanceFromStart(x2, y2) < 10;
    }
}
