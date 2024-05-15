package es.viewnext.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static void printSeparator() {
        String textLine = "";
        for (int i = 0; i < 80; i++) {
            textLine = textLine + "-";
        }
        log.info(textLine);
    }

    public static String getCurrentDate() {
        LocalDate now = LocalDate.now();
        return now.format(DateTimeFormatter.ofPattern("YYYYMMDD"));
    }

    public static String getCurrentHour() {
        LocalTime now = LocalTime.now();
        return now.format(DateTimeFormatter.ofPattern("HHmmss"));
    }

    public static Date format(final String value) {
        if (value == null) {
            return null;
        }

        Date date = null;

        try {
            date = dateFormat.parse(value);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }

        return date;
    }

    public static String dateToString(final Date date) {
        if (date == null) {
            return null;
        }

        return null;
    }
}
