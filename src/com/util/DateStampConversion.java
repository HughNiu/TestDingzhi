package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间时间戳互转
 */
public class DateStampConversion {
    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String d) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(d);
        return String.valueOf(date.getTime()/1000);
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s+"000");
        Date date = new Date(lt);
        return sdf.format(date);
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static long getCurrentStamp(){
        return System.currentTimeMillis()/1000;
    }

    public static void main(String[] args) {
        System.out.println(getCurrentStamp());
    }

}
