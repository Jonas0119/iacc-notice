// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Locale;
import com.tjfae.notice.utils.string.Format;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils
{
    public static String YYYY_MM_DD;
    public static String YYYY_MM_DD_HH_MM_SS;
    public static String YYYYMMDDHHMMSS;
    
    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }
    
    public static int countDutyday(final Date start, final Date end) {
        if (start == null || end == null) {
            return 0;
        }
        if (start.after(end)) {
            return 0;
        }
        final Calendar c_start = Calendar.getInstance();
        final Calendar c_end = Calendar.getInstance();
        c_start.setTime(start);
        c_end.setTime(end);
        c_start.set(11, 0);
        c_start.set(12, 0);
        c_start.set(13, 0);
        c_start.set(14, 0);
        c_end.set(11, 0);
        c_end.set(12, 0);
        c_end.set(13, 0);
        c_end.set(14, 0);
        int dutyDay = 0;
        while (c_start.compareTo(c_end) < 0) {
            if (c_start.get(7) != 1 && c_start.get(7) != 7) {
                ++dutyDay;
            }
            c_start.add(6, 1);
        }
        return dutyDay;
    }
    
    public static int countHoliday(final Date start, final Date end) {
        if (start == null || end == null) {
            return 0;
        }
        if (start.after(end)) {
            return 0;
        }
        final Calendar c_start = Calendar.getInstance();
        final Calendar c_end = Calendar.getInstance();
        c_start.setTime(start);
        c_end.setTime(end);
        int holiday = 0;
        while (start.compareTo(end) <= 0) {
            if (c_start.get(7) == 1 && c_start.get(7) == 7) {
                ++holiday;
            }
            c_start.add(6, 1);
        }
        return holiday;
    }
    
    public static Date getStartDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        return calendar.getTime();
    }
    
    public static Date getEndDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 23, 59, 59);
        return calendar.getTime();
    }
    
    public static Date getStartDate(final Date dateTime) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        return calendar.getTime();
    }
    
    public static Date getEndDate(final Date dateTime) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 23, 59, 59);
        return calendar.getTime();
    }
    
    public static Date getCurrentDate() {
        final Date date = new Date();
        return date;
    }
    
    public static String getCurrentDay() {
        return Format.formatDay(getCurrentDate());
    }
    
    public static String getYear() {
        return new SimpleDateFormat("yy", Locale.CHINESE).format(new Date());
    }
    
    public static String timeCalculate(final Date start, Date end) {
        if (start == null) {
            return "--";
        }
        if (end == null) {
            end = getCurrentDate();
        }
        final Long minus = end.getTime() - start.getTime();
        if (minus < 0L) {
            return "-1";
        }
        final Long day = minus / 86400000L;
        final Long hour = (minus - day * 86400000L) / 3600000L;
        final Long minutes = (minus - day * 86400000L - hour * 3600000L) / 60000L;
        final Long seconds = (minus - day * 86400000L - hour * 3600000L - minutes * 60L * 1000L) / 1000L;
        if (day > 0L) {
            return day + "\u5929 " + hour + "\u5c0f\u65f6" + minutes + "\u5206";
        }
        return hour + "\u5c0f\u65f6" + minutes + "\u5206";
    }
    
    public static String getWeekOfDate(final Date date) {
        if (date == null) {
            return null;
        }
        final SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        return dateFm.format(date);
    }
    
    public static Date addYear(final Date date) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(1, 1);
        calendar.add(5, -1);
        calendar.set(5, 15);
        return calendar.getTime();
    }
    
    public static Date addNYear(final Date date, final int n) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(1, n);
        calendar.add(5, -1);
        return calendar.getTime();
    }
    
    public static Date addNDay(final Date date, final int n) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, n);
        return calendar.getTime();
    }
    
    public static Date addNMonth(final Date date, final int n) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, n);
        return calendar.getTime();
    }
    
    public static Date addNHour(final Date date, final int n) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(10, n);
        return calendar.getTime();
    }
    
    public static Date addNMinute(final Date date, final int n) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(12, n);
        return calendar.getTime();
    }
    
    public static void main(final String[] args) {
        final int day = getNowDay();
        System.out.println(day);
    }
    
    public static Date getDayBegin() {
        final Calendar cal = new GregorianCalendar();
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }
    
    public static Date getDayEnd() {
        final Calendar cal = new GregorianCalendar();
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        return cal.getTime();
    }
    
    public static Date getBeginDayOfYesterday() {
        final Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(5, -1);
        return cal.getTime();
    }
    
    public static Date getEndDayOfYesterDay() {
        final Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(5, -1);
        return cal.getTime();
    }
    
    public static Date getBeginDayOfTomorrow() {
        final Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(5, 1);
        return cal.getTime();
    }
    
    public static Date getEndDayOfTomorrow() {
        final Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(5, 1);
        return cal.getTime();
    }
    
    public static Date getBeginDayOfWeek() {
        final Date date = new Date();
        if (date == null) {
            return null;
        }
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(7);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(5, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }
    
    public static Date getEndDayOfWeek() {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(7, 6);
        final Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }
    
    public static Date getBeginDayOfMonth() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }
    
    public static Date getEndDayOfMonth() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        final int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }
    
    public static Date getBeginDayOfYear() {
        final Calendar cal = Calendar.getInstance();
        cal.set(1, getNowYear());
        cal.set(2, 0);
        cal.set(5, 1);
        return getDayStartTime(cal.getTime());
    }
    
    public static Date getEndDayOfYear() {
        final Calendar cal = Calendar.getInstance();
        cal.set(1, getNowYear());
        cal.set(2, 11);
        cal.set(5, 31);
        return getDayEndTime(cal.getTime());
    }
    
    public static Timestamp getDayStartTime(final Date d) {
        final Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        calendar.set(14, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }
    
    public static Timestamp getDayEndTime(final Date d) {
        final Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 23, 59, 59);
        calendar.set(14, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }
    
    public static Integer getNowYear() {
        final Date date = new Date();
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        return gc.get(1);
    }
    
    public static int getNowMonth() {
        final Date date = new Date();
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }
    
    public static int getNowDay() {
        final Date date = new Date();
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        return gc.get(5);
    }
    
    public static int getDiffDays(final Date beginDate, final Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        final long diff = (endDate.getTime() - beginDate.getTime()) / 86400000L;
        final int days = new Long(diff).intValue();
        return days;
    }
    
    public static long dateDiff(final Date beginDate, final Date endDate) {
        final long date1ms = beginDate.getTime();
        final long date2ms = endDate.getTime();
        return date2ms - date1ms;
    }
    
    public static Date max(final Date beginDate, final Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return beginDate;
        }
        return endDate;
    }
    
    public static Date min(final Date beginDate, final Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }
    
    public static Date getFirstSeasonDate(final Date date) {
        final int[] SEASON = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final int sean = SEASON[cal.get(2)];
        cal.set(2, sean * 3 - 3);
        return cal.getTime();
    }
    
    public static Date getNextDay(final Date date, final int i) {
        final Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(5, cal.get(5) + i);
        return cal.getTime();
    }
    
    public static Date getFrontDay(final Date date, final int i) {
        final Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(5, cal.get(5) - i);
        return cal.getTime();
    }
    
    public static List getTimeList(final int beginYear, final int beginMonth, final int endYear, final int endMonth, final int k) {
        final List list = new ArrayList();
        if (beginYear == endYear) {
            for (int j = beginMonth; j <= endMonth; ++j) {
                list.add(getTimeList(beginYear, j, k));
            }
        }
        else {
            for (int j = beginMonth; j < 12; ++j) {
                list.add(getTimeList(beginYear, j, k));
            }
            for (int i = beginYear + 1; i < endYear; ++i) {
                for (int l = 0; l < 12; ++l) {
                    list.add(getTimeList(i, l, k));
                }
            }
            for (int j = 0; j <= endMonth; ++j) {
                list.add(getTimeList(endYear, j, k));
            }
        }
        return list;
    }
    
    public static List getTimeList(final int beginYear, final int beginMonth, final int k) {
        final List list = new ArrayList();
        Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
        final int max = begincal.getActualMaximum(5);
        for (int i = 1; i < max; i += k) {
            list.add(begincal.getTime());
            begincal.add(5, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }
    
    public static String getNowDateString() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    public static final String dateTimeNow() {
        return parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date());
    }
    
    static {
        DateUtils.YYYY_MM_DD = "yyyy-MM-dd";
        DateUtils.YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        DateUtils.YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    }
}
