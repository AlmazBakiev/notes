package Service;

import Data.Note;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SearchMaxIdForNoteService {

    public static void searchMaxId() {
        String line = "";
        int counter = 0;
        for (String title : Note.getAllNotes()) {
            try (BufferedReader br = new BufferedReader(new FileReader(title + ".txt"))) {
                while ((line = br.readLine()) != null) {
                    if (line.contains("id: ")) {
                        int id = Integer.parseInt(line.replace("id: ", "").trim());
                        if (counter < id) {
                            counter = id;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Note.setCounter(counter);
    }
}
