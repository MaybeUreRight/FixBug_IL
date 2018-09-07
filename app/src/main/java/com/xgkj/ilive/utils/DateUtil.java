package com.xgkj.ilive.utils;

import android.content.Context;
import android.net.TrafficStats;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 作者：刘净辉
 * 日期: 2017/7/19 0019 12:04
 * 作用: 日期转换
 */

public class DateUtil {

    private static long lastTotalRxBytes = 0;
    private static long lastTimeStamp = 0;

    /**
     * long型的转换成年月日
     * */
    public static String formatTimeInMillis(long timeInMillis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);
        Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy.MM.dd");
        String fmt = dateFormat.format(date);
        return fmt;
    }

    /**
     * 得到网络速度
     * 每隔两秒调用一次
     * @param context
     * @return
     */
    public static String getNetSpeed(Context context) {
        String netSpeed = "0 kb/s";
        long nowTotalRxBytes = TrafficStats.getUidRxBytes(context.getApplicationInfo().uid)==TrafficStats.UNSUPPORTED ? 0 :(TrafficStats.getTotalRxBytes()/1024);//转为KB;
        long nowTimeStamp = System.currentTimeMillis();
        long speed = ((nowTotalRxBytes - lastTotalRxBytes) * 1000 / (nowTimeStamp - lastTimeStamp));//毫秒转换

        lastTimeStamp = nowTimeStamp;
        lastTotalRxBytes = nowTotalRxBytes;
        netSpeed  = String.valueOf(speed) + " kb/s";
        return  netSpeed;
    }

}
