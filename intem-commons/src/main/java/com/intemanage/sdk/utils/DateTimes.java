package com.intemanage.sdk.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author panyunfeng04
 * @Date 2019-11-01
 */
public class DateTimes extends DateUtils {

    public static final String PATTERN_TIME = "HH:mm:ss";
    public static final String PATTERN_TIME_HOUR_MINUTE = "HH:mm";
    public static final String PATTERN_HYPHEN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_HYPHEN_DATETIME_WITH_MILLIS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String PATTERN_HYPHEN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_SLASH_DATETIME = "yyyy/MM/dd HH:mm:ss";
    public static final String PATTERN_SLASH_DATETIME_WITH_MILLIS = "yyyy/MM/dd HH:mm:ss.SSS";
    public static final String PATTERN_SLASH_DATE = "yyyy/MM/dd";
    public static final String PATTERN_DATETIME_SERIES = "yyyyMMddHHmmss";
    public static final String PATTERN_DATETIME_SERIES_WITH_MILLIS = "yyyyMMddHHmmssSSS";
    public static final String PATTERN_DATE_SERIES = "yyyyMMdd";

    public static Date millsToDate(Long mills) {
        if (mills == null) {
            return null;
        }
        return new Date(mills);
    }

    public static Date millsToDate(Long mills, Date defValue) {
        if (mills == null) {
            return defValue;
        }
        return new Date(mills);
    }

    /**
     * 毫秒转日期， 忽略小数位
     *
     * @param mills
     * @return
     */
    public static Date millsToDateIgnoreF(Long mills) {
        return millsToDateIgnoreF(mills, null);
    }

    /**
     * 毫秒转日期， 忽略小数位
     *
     * @param mills
     * @return
     */
    public static Date millsToDateIgnoreF(Long mills, Date defValue) {
        if (mills == null) {
            return defValue;
        }

        mills = mills.longValue() / 1000 * 1000;
        return new Date(mills);
    }

    public static Long dateToMills(Date date) {
        if (date != null) {
            return date.getTime();
        }
        return null;
    }

    public static Long dateToMills(Date date, Long defValue) {
        if (date != null) {
            return date.getTime();
        }
        return defValue;
    }

    public static String formatToHyphenDatetime(Date date) {
        return format(date, PATTERN_HYPHEN_DATETIME);
    }

    public static String formatToHyphenDate(Date date) {
        return format(date, PATTERN_HYPHEN_DATE);
    }

    public static String formatToSlashDatetime(Date date) {
        return format(date, PATTERN_SLASH_DATETIME);
    }

    public static String formatToSlashDate(Date date) {
        return format(date, PATTERN_SLASH_DATE);
    }

    public static String timeSeriesOfNow() {
        return new SimpleDateFormat(PATTERN_DATETIME_SERIES).format(new Date());
    }

    public static String datetimeSeries(Date date) {
        return format(date, PATTERN_DATE_SERIES);
    }

    public static String format(Date date, String pattern) {
        if (date == null) {
            return Strings.EMPTY;
        }

        if (Strings.isBlank(pattern)) {
            throw new IllegalArgumentException("The pattern must not null.");
        }

        return new SimpleDateFormat(pattern).format(date);
    }

}
