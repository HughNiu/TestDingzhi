package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间时间戳互转
 */
public class DateStampConversion {

    public static final String YYYY_MM_DD_HH_MM_SS  = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD  = "yyyy-MM-dd";

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String format,String d) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(d);
        return String.valueOf(date.getTime());
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String format,String s){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return  sdf.format(new Date(Long.parseLong(s)));
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static long getCurrentStamp(){
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime(String format){
        SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    /**
     * 获取n天前的日期
     * @return
     * @throws ParseException
     */
    public static String getPreTime(String format,int day) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, day);
        Date monday = c.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
    }

    /**
     * 获取时间的年
     */
    public static int getYear(String format,String date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = sdf.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return  c.get(Calendar.YEAR);
    }

    /**
     * 获取时间的月
     */
    public static int getMonth(String format,String date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = sdf.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return  c.get(Calendar.MONTH)+1;
    }

    /**
     * 获取时间的日
     */
    public static int getDay(String format,String date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = sdf.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return  c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取时间的时
     */
    public static int getHour(String format,String date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = sdf.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return  c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取时间的分
     */
    public static int getMinutes(String format,String date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = sdf.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return  c.get(Calendar.MINUTE);
    }


    public static String getTime(String time) throws ParseException {
        time = DateStampConversion.stampToDate(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time);
        //当前时间
        String currentTime = DateStampConversion.getCurrentTime(DateStampConversion.YYYY_MM_DD_HH_MM_SS);

        if(getDay(YYYY_MM_DD_HH_MM_SS,time)==getDay(YYYY_MM_DD_HH_MM_SS,currentTime)) {//当前天相同
            time = getHour(YYYY_MM_DD_HH_MM_SS, time) + ":" + getMinutes(YYYY_MM_DD_HH_MM_SS, time);
        }
        else if(getMonth(YYYY_MM_DD_HH_MM_SS,time)==getMonth(YYYY_MM_DD_HH_MM_SS,currentTime)) {//当前月相同
            time = getMonth(YYYY_MM_DD_HH_MM_SS, time) + "-" + getDay(YYYY_MM_DD_HH_MM_SS, time) + " "
                    + getHour(YYYY_MM_DD_HH_MM_SS, time) + ":" + getMinutes(YYYY_MM_DD_HH_MM_SS, time);
        }
        else if(DateStampConversion.getYear(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time)==getYear(DateStampConversion.YYYY_MM_DD_HH_MM_SS,currentTime)) {//当前年相同
            time = getMonth(YYYY_MM_DD_HH_MM_SS, time) + "-" + getDay(YYYY_MM_DD_HH_MM_SS, time) + " "
                    + getHour(YYYY_MM_DD_HH_MM_SS, time) + ":" + getMinutes(YYYY_MM_DD_HH_MM_SS, time);
        }
        return time;

    }



    public static void main(String[] args) throws ParseException{
        System.out.println(getHour(YYYY_MM_DD_HH_MM_SS,"2015-08-09 12:25:36"));
        System.out.println(getMinutes(YYYY_MM_DD_HH_MM_SS,"2015-08-09 12:25:36"));
        System.out.println(stampToDate(YYYY_MM_DD_HH_MM_SS,"1602895425000"));

        Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
        System.out.println();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));   // 时间戳转换成时间
        System.out.println(sd);
    }


}
