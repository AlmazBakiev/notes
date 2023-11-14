package Data;

import Service.SaveNoteService;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Note {

    private final int id;
    private static int counter = 0;
    private final String title;
    private String text;
    private final LocalDateTime dateOfCreation;
    private LocalDateTime lastEdit;
    private final File path;
    private final SaveNoteService saveNoteService = new SaveNoteService();
    private static ArrayList<String> allNotes = new ArrayList<>();

    private Note(String title, String text) {
        counter++;
        id = counter;
        this.title = title;
        this.text = text;
        path = new File(title + ".txt");
        dateOfCreation = LocalDateTime.now();
        lastEdit = LocalDateTime.now();
        saveNoteService.saveNote(this);
        allNotes.add(title);
    }

    private Note(String text) {
        counter++;
        id = counter;
        this.title = "Заметка №" + id;
        this.text = text;
        path = new File(title + ".txt");
        dateOfCreation = LocalDateTime.now();
        lastEdit = LocalDateTime.now();
        saveNoteService.saveNote(this);
    }

    public static void createNote(String title, String text) {
        if (title == null || title.equals("")) {
            new Note(text);
        } else {
            new Note(title, text);
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public LocalDateTime getLastEdit() {
        return lastEdit;
    }

    public File getPath() {
        return path;
    }

    public static ArrayList<String > getAllNotes() {
        return allNotes;
    }

    public static void setCounter(int counter) {
        Note.counter = counter;
    }
}
