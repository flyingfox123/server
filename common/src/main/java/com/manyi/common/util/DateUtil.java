
package com.manyi.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @Description: 日期公共类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class DateUtil {

    public  static final int DOUBLE_MON = 10;

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    /**
       根据月份获取月初时间
     */
    public static String getMonthStart(int  month) {


        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        if(month < DOUBLE_MON)
        {
            return year +"-0"+month+"-01"+" 00:00:01";
        }
        else
        {
            return year +"-"+month+"-01"+" 00:00:01";
        }


    }


    /**
     根据月份获取月初时间
     */
    public static String getMonthEnd(int  month) {


        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        if(month < DOUBLE_MON)
        {
            return year +"-0"+month+"-31"+" 23:59:59";
        }
        else
        {
            return year +"-"+month+"-31"+" 23:59:59";
        }


    }

    /**
     * 将时间字符串转换为Date类型
     * @param dateStr
     * @return Date
     */
    public static Date toDate(String dateStr) {
        Date date = null;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd");
        try {
            date = formater.parse(dateStr);
        } catch (ParseException e) {
            logger.error("to date failed" ,e);
        }
        return date;
    }

    /**
     * 按照提供的格式将字符串转换成Date类型
     * @param dateStr
     * @param formaterString
     * @return
     */
    public static Date toDate(String dateStr, String formaterString) {
        Date date = null;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern(formaterString);
        try {
            date = formater.parse(dateStr);
        } catch (ParseException e) {
            logger.error("Parser failed" ,e);
        }
        return date;
    }


    /**
     * 将Date类型时间转换为字符串
     * @param date
     * @return
     */
    public static String getCurrentString(Date date) {

        String time;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd HH:mm:ss");
        time = formater.format(date);
        return time;
    }

    /**
     * 将Date类型时间转换为字符串
     * @param date
     * @return
     */
    public static String toString(Date date) {

        String time;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd");
        time = formater.format(date);
        return time;
    }

    /**
     * 按照参数提供的格式将Date类型时间转换为字符串
     * @param date
     * @param formaterString
     * @return
     */
    public static String toString(Date date, String formaterString) {
        String time;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern(formaterString);
        time = formater.format(date);
        return time;
    }

//    /**
//     * method 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp）
//     * @param dateString
//     *            需要转换为timestamp的字符串
//     * @return dataTime timestamp
//     */
//    public final static java.sql.Timestamp string2Time(String dateString)
//            throws java.text.ParseException {
//        DateFormat dateFormat;
////      dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS",
////              Locale.ENGLISH);// 设定格式
//        dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
//        dateFormat.setLenient(false);
//        java.util.Date timeDate = dateFormat.parse(dateString);// util类型
//        java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());// Timestamp类型,timeDate.getTime()返回一个long型
//        return dateTime;
//    }
//
//    /**
//     * method 将字符串类型的日期按照转换为一个timestamp（时间戳记java.sql.Timestamp）
//     *
//     * @param dateString 需要转换为timestamp的字符串
//     * @param formaterString dateString字符串的解析格式
//     * @return
//     * @throws java.text.ParseException
//     */
//    public final static java.sql.Timestamp string2Time(String dateString,
//                                                       String formaterString) throws java.text.ParseException {
//        DateFormat dateFormat;
//        dateFormat = new SimpleDateFormat(formaterString);// 设定格式
//        // dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
//        dateFormat.setLenient(false);
//        java.util.Date timeDate = dateFormat.parse(dateString);// util类型
//        java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());// Timestamp类型,timeDate.getTime()返回一个long型
//        return dateTime;
//    }

    /*
    获取年初时间
     */
    public static String getYearStart(int year) {

        return year +"-01-01"+" 00:00:01";
    }

    /**
     * 获取年末时间
     * @param year
     * @return
     */
    public static String getYearEnd(int year) {

        return year +"-12-31"+" 23:59:59";
    }
}
