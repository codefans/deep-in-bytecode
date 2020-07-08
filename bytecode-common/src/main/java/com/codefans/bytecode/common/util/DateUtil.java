/**
 * Copyright (C), 2015-2020, 京东
 * FileName: DateUtil
 * Author:   codefans
 * Date:     2020/4/1 11:47
 * Description: 日期处理
 */
package com.codefans.bytecode.common.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 日期处理
 *
 * @author: codefans
 * @Date: 2020/04/01 11:47
 * @since: 1.0.0
 */
public class DateUtil {

    private static final String PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_YYYYMMDDHHMMSS_SSS = "yyyy-MM-dd HH:mm:ss,SSS";
    private static final String PATTERN_YYYYMMDD = "yyyy-MM-dd";

    private static final ThreadLocal<DateFormat> yyyyMMddHHmmssDateFormat= new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(PATTERN_YYYYMMDDHHMMSS);
        }
    };
    private static final ThreadLocal<DateFormat> yyyyMMddHHmmssSSSDateFormat= new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(PATTERN_YYYYMMDDHHMMSS_SSS);
        }
    };
    private static final ThreadLocal<DateFormat> yyyyMMddDateFormat= new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(PATTERN_YYYYMMDD);
        }
    };

    public static String formatYYYYMMDDHHMMSS_SSS(Date date) {
        DateFormat df = yyyyMMddHHmmssSSSDateFormat.get();
        return df.format(date);
    }

    public static String formatYYYYMMDDHHMMSS_SSS(long timestamp) {
        return formatYYYYMMDDHHMMSS_SSS(new Date(timestamp));
    }

    public static String format(Date date) {
        DateFormat df = yyyyMMddHHmmssDateFormat.get();
        return df.format(date);
    }

    public static String formatYYYYMMDDHHMMSS(long timestamp) {
        return format(new Date(timestamp));
    }

}