package controller;

import Data.Note;
import Service.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    private final Scanner sc = new Scanner(System.in);
    private static final ArrayList<String> allNotes;
    private final EditNoteService edit = new EditNoteService();
    private final DeleteNoteService delete = new DeleteNoteService();

    static {
        allNotes = Note.getAllNotes();
        ReadListOfNotesService.readListOfNotes();
        SearchMaxIdForNoteService.searchMaxId();
    }

    public void start() {
        while (true) {
            System.out.println("""
                    1 - создать заметку
                    2 - список заметок
                    3 - редактировать заметку
                    4 - удалить заметку
                    exit - выйти
                    """);
            System.out.print("Введите команду: ");
            switch (sc.nextLine()) {
                case "1" -> createNote();
                case "2" -> getAllNotes();
                case "3" -> editNote();
                case "4" -> deleteNote();
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Такой команды нет!\n");
            }
            SaveNoteService.saveListOfNotesWithFileNames(allNotes);
        }
    }

    private void deleteNote() {
        String title = "";
        System.out.println("Выберите заметку которую хотите удалить!");
        getAllNotes();
        System.out.print("Введите имя файла: ");
        title = sc.nextLine();
        if (!allNotes.contains(title)) {
            System.out.println("Такого файла нет или непраивльно введен файл!");
        } else {
            delete.deleteNote(title);
        }
    }

    private void editNote() {
        String title = "";
        System.out.println("Выберите заметку для редактирования!");
        getAllNotes();
        while (!title.equalsIgnoreCase("exit")) {
            System.out.print("Введите имя файла: ");
            title = sc.nextLine();
            if (!allNotes.contains(title)) {
                System.out.println("""
                        Такого файла нет или непраивльно введен файл!
                        Попробуйте еще раз ввести имя файла.
                        Если хотите выйти введите exit.
                        """);
            } else {
                edit.editNote(title + ".txt");
                title = "exit";
            }
        }
    }

    private void getAllNotes() {
        for (String title : Note.getAllNotes()) {
            System.out.printf("Заголовок: %-15s Файл: %s.txt\n", title, title);
        }
        System.out.println();
    }

    private void createNote() {
        System.out.print("Введите название заметки: ");
        String title = sc.nextLine();
        System.out.print("Введите текст заметки: ");
        String text = sc.nextLine();
        Note.createNote(title, text);
    }
}
