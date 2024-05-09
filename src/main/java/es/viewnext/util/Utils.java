package es.viewnext.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static void printSeparator() {
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }

        System.out.println("");
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
