package global.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Дум on 27.11.2016.
 */

public class DateUtility {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String getTodayDate() {
        return DATE_FORMAT.format(new Date());
    }

    public static String getStartMonthDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        return DATE_FORMAT.format(c.getTime());
    }
}
