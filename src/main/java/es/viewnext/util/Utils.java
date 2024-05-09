package es.viewnext.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

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
}
