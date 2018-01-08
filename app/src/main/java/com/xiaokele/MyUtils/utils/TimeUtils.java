package com.xiaokele.MyUtils.utils;

/**
 * Created by supertramp on 17/4/9.
 */

public class TimeUtils {

    public static String minToFormat(int minutes)
    {
        StringBuilder sb = new StringBuilder();
        if (minutes < 60)
        {
            sb.append("00");
            sb.append(":");
            sb.append(formatNumber(minutes));
            return sb.toString();
        }
        else
        {
            int hours = minutes / 60;
            int LeftMinutes = minutes % 60;
            sb.append(formatNumber(hours));
            sb.append(":");
            sb.append(formatNumber(LeftMinutes));
            return sb.toString();
        }
    }

    private static String formatNumber(int number)
    {
        if (number < 10)
        {
            return "0" + number;
        }
        return "" + number;
    }

}
