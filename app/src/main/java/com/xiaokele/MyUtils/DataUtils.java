package com.xiaokele.MyUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by QiyiLive on 2018/1/8.
 */

public class DataUtils {

    /**
     * 将long时间转成yyyy-MM-dd HH:mm:ss字符串<br>
     *
     * @param timeInMillis 时间long值
     * @return yyyy-MM-dd HH:mm:ss
     */
//    public static String getDateTimeFromMillis(long timeInMillis) {
//        return getDateTimeFormat(new Date(timeInMillis));
//    }

    /**
     * 将date转成yyyy-MM-dd HH:mm:ss字符串
     * <br>
     *
     * @param date Date对象
     * @return yyyy-MM-dd HH:mm:ss
     */
//    public static String getDateTimeFormat(Date date) {
//        return dateSimpleFormat(date, defaultDateTimeFormat.get());
//    }

    /**
     * 将年月日的int转成yyyy-MM-dd的字符串
     *
     * @param year  年
     * @param month 月 1-12
     * @param day   日
     *              注：月表示Calendar的月，比实际小1
     *              对输入项未做判断
     */
//    public static String getDateFormat(int year, int month, int day) {
//        return getDateFormat(getDate(year, month, day));
//    }

    /**
     * 获得HH:mm:ss的时间
     *
     * @param date
     * @return
     */
//    public static String getTimeFormat(Date date) {
//        return dateSimpleFormat(date, defaultTimeFormat.get());
//    }

    /**
     * 格式化日期显示格式
     *
     * @param sdate  原始日期格式 "yyyy-MM-dd"
     * @param format 格式化后日期格式
     * @return 格式化后的日期显示
     */
//    public static String dateFormat(String sdate, String format) {
//        SimpleDateFormat formatter = new SimpleDateFormat(format);
//        java.sql.Date date = java.sql.Date.valueOf(sdate);
//        return dateSimpleFormat(date, formatter);
//    }

    /**
     * 格式化日期显示格式
     *
     * @param date   Date对象
     * @param format 格式化后日期格式
     * @return 格式化后的日期显示
     */
//    public static String dateFormat(Date date, String format) {
//        SimpleDateFormat formatter = new SimpleDateFormat(format);
//        return dateSimpleFormat(date, formatter);
//    }

    /**
     * 将date转成字符串
     *
     * @param date   Date
     * @param format SimpleDateFormat
     *               <br>
     *               注： SimpleDateFormat为空时，采用默认的yyyy-MM-dd HH:mm:ss格式
     * @return yyyy-MM-dd HH:mm:ss
     */
//    public static String dateSimpleFormat(Date date, SimpleDateFormat format) {
//        if (format == null)
//            format = defaultDateTimeFormat.get();
//        return (date == null ? "" : format.format(date));
//    }

    /**
     * 将"yyyy-MM-dd HH:mm:ss" 格式的字符串转成Date
     *
     * @param strDate 时间字符串
     * @return Date
     */
//    public static Date getDateByDateTimeFormat(String strDate) {
//        return getDateByFormat(strDate, defaultDateTimeFormat.get());
//    }

    /**
     * 将"yyyy-MM-dd" 格式的字符串转成Date
     *
     * @param strDate
     * @return Date
     */
//    public static Date getDateByDateFormat(String strDate) {
//        return getDateByFormat(strDate, defaultDateFormat.get());
//    }

    /**
     * 将指定格式的时间字符串转成Date对象
     *
     * @param strDate 时间字符串
     * @param format  格式化字符串
     * @return Date
     */
//    public static Date getDateByFormat(String strDate, String format) {
//        return getDateByFormat(strDate, new SimpleDateFormat(format));
//    }

    /**
     * 将String字符串按照一定格式转成Date<br>
     * 注： SimpleDateFormat为空时，采用默认的yyyy-MM-dd HH:mm:ss格式
     *
     * @param strDate 时间字符串
     * @param format  SimpleDateFormat对象
     * @throws ParseException 日期格式转换出错
     */
//    private static Date getDateByFormat(String strDate, SimpleDateFormat format) {
//        if (format == null)
//            format = defaultDateTimeFormat.get();
//        try {
//            return format.parse(strDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 将年月日的int转成date
     *
     * @param year  年
     * @param month 月 1-12
     * @param day   日
     *              注：月表示Calendar的月，比实际小1
     */
    public static Date getDate(int year, int month, int day) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(year, month - 1, day);
        return mCalendar.getTime();
    }

    /**
     * 求两个日期相差天数
     *
     * @param strat 起始日期，格式yyyy-MM-dd
     * @param end   终止日期，格式yyyy-MM-dd
     * @return 两个日期相差天数
     */
    public static long getIntervalDays(String strat, String end) {
        return ((java.sql.Date.valueOf(end)).getTime() - (java.sql.Date
                .valueOf(strat)).getTime()) / (3600 * 24 * 1000);
    }

    /**
     * 获得当前年份
     *
     * @return year(int)
     */
    public static int getCurrentYear() {
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.get(Calendar.YEAR);
    }

    /**
     * 获得当前月份
     *
     * @return month(int) 1-12
     */
    public static int getCurrentMonth() {
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得当月几号
     *
     * @return day(int)
     */
    public static int getDayOfMonth() {
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得今天的日期(格式：yyyy-MM-dd)
     *
     * @return yyyy-MM-dd
     */
//    public static String getToday() {
//        Calendar mCalendar = Calendar.getInstance();
//        return getDateFormat(mCalendar.getTime());
//    }

    /**
     * 获得昨天的日期(格式：yyyy-MM-dd)
     *
     * @return yyyy-MM-dd
     */
//    public static String getYesterday() {
//        Calendar mCalendar = Calendar.getInstance();
//        mCalendar.add(Calendar.DATE, -1);
//        return getDateFormat(mCalendar.getTime());
//    }

    /**
     * 获得前天的日期(格式：yyyy-MM-dd)
     *
     * @return yyyy-MM-dd
     */
//    public static String getBeforeYesterday() {
//        Calendar mCalendar = Calendar.getInstance();
//        mCalendar.add(Calendar.DATE, -2);
//        return getDateFormat(mCalendar.getTime());
//    }

    /**
     * 获得几天之前或者几天之后的日期
     *
     * @param diff 差值：正的往后推，负的往前推
     * @return
     */
//    public static String getOtherDay(int diff) {
//        Calendar mCalendar = Calendar.getInstance();
//        mCalendar.add(Calendar.DATE, diff);
//        return getDateFormat(mCalendar.getTime());
//    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param //date 给定的日期对象
     * @param amount 需要添加的天数，如果是向前的天数，使用负数就可以.
     * @return Date 加上一定天数以后的Date对象.
     */
//    public static String getCalcDateFormat(String sDate, int amount) {
//        Date date = getCalcDate(getDateByDateFormat(sDate), amount);
//        return getDateFormat(date);
//    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param date   给定的日期对象
     * @param amount 需要添加的天数，如果是向前的天数，使用负数就可以.
     * @return Date 加上一定天数以后的Date对象.
     */
    public static Date getCalcDate(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, amount);
        return cal.getTime();
    }
}
