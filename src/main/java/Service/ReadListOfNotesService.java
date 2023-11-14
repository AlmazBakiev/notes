package Service;

import Data.Note;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadListOfNotesService {

    public static void readListOfNotes() {
        try (BufferedReader br = new BufferedReader(new FileReader("listOfNotes.txt"))) {
            String line = br.readLine();
            String[] nodesTitles = line.split(", ");
            for (String namePath : nodesTitles) {
                File file = new File(namePath);
                if (file.exists()) {
                    Note.getAllNotes().add(namePath.replace(".txt", ""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
