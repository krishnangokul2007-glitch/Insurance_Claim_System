package util;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class DateUtil {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static String today() {
        return LocalDate.now().format(DATE_FORMAT);
    }
    public static String now() {
        return LocalDateTime.now().format(DATE_TIME_FORMAT);
    }
}
