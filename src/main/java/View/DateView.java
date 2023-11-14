package View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateView {

    public static void printOnConsole(LocalDateTime ldt) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        System.out.println(ldt.format(pattern));
    }
}
