package Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReturnStringDateService {

    public static String returnStringDate(LocalDateTime ldt) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return ldt.format(pattern);
    }
}
