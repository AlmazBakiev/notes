package Service;

import Data.Note;

import java.io.File;

public class DeleteNoteService {

    public void deleteNote(String title) {
        Note.getAllNotes().remove(title);
        File file = new File(title + ".txt");
        boolean delete = file.delete();
        if (delete) {
            System.out.println("Ваш файл успешно удален!");
        } else {
            System.out.println("Ваш файл не удален!");
        }
    }
}
