package global.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Дум on 27.11.2016.
 */

public class DateUtility {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");

    public static String getTodayDate() {
        return DATE_FORMAT.format(new Date());
    }

    public static String toString(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String toStringTime(Date date) {
        if (date == null) return null;
        return DATETIME_FORMAT.format(date);
    }

    public static Date parseDate(String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseDateTime(String date) {
        if (date == null) return null;
        try {
            return DATETIME_FORMAT.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getStartMonthDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        return DATE_FORMAT.format(c.getTime());
    }
}
