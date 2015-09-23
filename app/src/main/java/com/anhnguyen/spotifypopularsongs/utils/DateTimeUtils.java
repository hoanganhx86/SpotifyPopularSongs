package com.anhnguyen.spotifypopularsongs.utils;

import com.anhnguyen.spotifypopularsongs.Conts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtils {

    /**
     * All Dates are normalized to UTC, it is up the client code to convert to the appropriate TimeZone.
     */
    public static final TimeZone UTC;

    static {
        UTC = TimeZone.getTimeZone("UTC");
        TimeZone.setDefault(UTC);
    }

    public static final String PERSISTENT_TIME_FORMAT = "yyyyMMdd-HHmmss";

    public static String getShortDateTimeString(final Date date, final String strFormat){
        final SimpleDateFormat format = (strFormat == null)? new SimpleDateFormat(PERSISTENT_TIME_FORMAT, Locale.US):
            new SimpleDateFormat(strFormat, Locale.US);
        format.setTimeZone(TimeZone.getDefault());
        return format.format(date);
    }

    public static String getLongDateTimeString(final Date date , final String strFormat){
        final SimpleDateFormat format = (strFormat == null)? new SimpleDateFormat(Conts.DEFAULT_DATE_TIME_FORMAT, Locale.US):
            new SimpleDateFormat(strFormat, Locale.US);
        format.setTimeZone(TimeZone.getDefault());
        return  format.format(date);
    }

    public static Date getDateFromString(final String dateString, final String format){
        Date date = new Date();
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        dateFormat.setTimeZone(TimeZone.getDefault());

        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }


    public static long diffTimeInMillis(Date date1, Date date2){
        return date1.getTime() - date2.getTime();
    }

    public static int diffTimeInDays(Date date1, Date date2){
        long diffMillis =  diffTimeInMillis(date1, date2);
        return (int) Math.floor(diffMillis * 1f / (24 * 60 * 60 * 1000));
    }

    public static int diffTimeInHours(Date date1, Date date2){
        long diffMillis =  diffTimeInMillis(date1, date2);
        return (int)Math.floor(diffMillis * 1f / (60 * 60 * 1000));
    }

    public static int diffTimeInMinutes(Date date1, Date date2){
        long diffMillis =  diffTimeInMillis(date1, date2);
        return (int)Math.floor(diffMillis * 1f/ (60 * 1000));
    }

}
