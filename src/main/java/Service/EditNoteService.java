package Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class EditNoteService {

    private final Scanner sc = new Scanner(System.in);

    public void editNote(String namePath) {
        System.out.println("""
                Если вы хотите изметнить существующий текст,
                то необходимо скопировать его и вставить в строку ввода
                """);
        String line = "";
        String regex = "text: ";
        try (BufferedReader br = new BufferedReader(new FileReader(namePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(namePath, true))) {
            line = br.readLine();
            bw.newLine();
            while (line != null) {
                if (line.contains(regex)) {
                    System.out.println(line.replace(regex, ""));
                    System.out.print("Введите текст: ");
                    String text = sc.nextLine();
                    bw.write("text: " + text);
                    bw.newLine();
                } else if (line.contains("lastEdit: ")) {
                    bw.write("lastEdit: " + ReturnStringDateService.returnStringDate(LocalDateTime.now()));
                    bw.newLine();
                } else {
                    bw.write(line);
                    bw.newLine();
                }
                line = br.readLine();
            }
            System.out.println("Заметка успешно изменена!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
