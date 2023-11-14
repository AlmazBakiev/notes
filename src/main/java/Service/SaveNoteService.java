package Service;

import Data.Note;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveNoteService {

    public void saveNote(Note note) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(note.getPath()))) {
            bw.write("{");
            bw.newLine();
            bw.write("id: " + note.getId());
            bw.newLine();
            bw.write("title: " + note.getTitle());
            bw.newLine();
            bw.write("text: " + note.getText());
            bw.newLine();
            bw.write("dateOfCreation: " + ReturnStringDateService.returnStringDate(note.getDateOfCreation()));
            bw.newLine();
            bw.write("lastEdit: " + ReturnStringDateService.returnStringDate(note.getLastEdit()));
            bw.newLine();
            bw.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveListOfNotesWithFileNames(ArrayList<String> listOfNotes) {
        try (FileWriter writer = new FileWriter("listOfNotes.txt")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < listOfNotes.size(); i++) {
                if (i == listOfNotes.size() - 1) {
                    sb.append(listOfNotes.get(i)).append(".txt");
                } else {
                    sb.append(listOfNotes.get(i)).append(".txt").append(", ");
                }
            }
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
