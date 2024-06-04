package es.viewnext.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class Utils {

    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    

    private Utils() {
    }

    /**
     * Imprime un separador de 80 caracteres en la consola. El separador se compone
     * de guiones (-) y se utiliza para separar secciones en la salida de la
     * aplicación.
     */
    public static void printSeparator() {
        String textLine = "";
        for (int i = 0; i < 80; i++) {
            textLine = textLine + "-";
        }
        LOG.info(textLine);
    }

    /**
     * Devuelve la fecha actual en formato YYYYMMDD.
     * 
     * @return la fecha actual como una cadena en formato YYYYMMDD
     */
    public static String getCurrentDate() {
        LocalDate now = LocalDate.now();
        return now.format(DateTimeFormatter.ofPattern("YYYYMMDD"));
    }

    /**
     * Devuelve la hora actual en formato HHmmss.
     * 
     * @return la hora actual como una cadena en formato HHmmss
     */
    public static String getCurrentHour() {
        LocalTime now = LocalTime.now();
        return now.format(DateTimeFormatter.ofPattern("HHmmss"));
    }

    /**
     * Convierte una cadena de texto en una fecha utilizando un formato de fecha
     * predeterminado.
     * 
     * @param value la cadena de texto que se va a convertir en una fecha
     * @return la fecha correspondiente a la cadena de texto, o null si no se puede
     *         parsear
     */
    public static Date format(final String value) {
        if (value == null) {
            return null;
        }

        Date date = null;

        try {
            date = dateFormat.parse(value);
        } catch (ParseException e) {
            LOG.error(e.getMessage());
        }

        return date;
    }

    /**
     * Convierte una fecha en un objeto Date a una cadena de texto formateada.
     * 
     * @param date la fecha a convertir
     * @return la fecha como una cadena de texto formateada, o null si la fecha es
     *         nula
     */
    public static String dateToString(final Date date) {
        if (date == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    /**
     * Valida si una dirección de correo electrónico es válida según un patrón
     * determinado.
     * 
     * @param email la dirección de correo electrónico a validar
     * @return true si la dirección de correo electrónico es válida, false en caso
     *         contrario
     */
    public static boolean validateEmail(String email) {
        if (!StringUtils.hasText(email)) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * Valida si una persona es mayor de edad según su fecha de nacimiento.
     * 
     * @param fechaNac la fecha de nacimiento de la persona
     * @return true si la persona es mayor de edad (18 años o más), false en caso
     *         contrario
     */
    public static boolean validateAge(LocalDate fechaNac) {
        if (fechaNac == null) {
            return false;
        }

        LocalDate fechaNacimiento = fechaNac;

        LocalDate fechaActual = LocalDate.now();

        int edad = Period.between(fechaNacimiento, fechaActual).getYears();

        return edad >= 18;
    }

}
