/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author TINH HUYNH
 */
public class DateTimeUtils {

    public static String convertTimestampToString(Timestamp timestamp, String dateFormat) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        result = sdf.format(timestamp);
        return result;
    }

    public static String convertDateToString(Date date, String dateFormat) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        result = sdf.format(date);
        return result;
    }

    public static Timestamp convertStringToTimeStamp(String str, String dateFormat) {
        Timestamp result = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            Date date = sdf.parse(str);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(date.getTime());
            result = new Timestamp(c.getTimeInMillis());
        } catch (ParseException ex) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(new Date(System.currentTimeMillis()).getTime());
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            result = new Timestamp(c.getTimeInMillis());
        }
        return result;
    }

    public static Date convertStringToDate(String str, String dateFormat) {
        Date result = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            result = sdf.parse(str);
        } catch (ParseException ex) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(new Date(System.currentTimeMillis()).getTime());
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            result = c.getTime();
        }
        return result;
    }

    public static Date addMoreDays(Date date, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        date = cal.getTime();
        return date;
    }
    
    public static Timestamp addMoreDays(Timestamp timestamp, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.DATE, 1);
        timestamp.setTime(cal.getTimeInMillis());
        return timestamp;
    }

}
