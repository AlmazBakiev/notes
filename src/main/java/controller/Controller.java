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
                    1 - ������� �������
                    2 - ������ �������
                    3 - ������������� �������
                    4 - ������� �������
                    exit - �����
                    """);
            System.out.print("������� �������: ");
            switch (sc.nextLine()) {
                case "1" -> createNote();
                case "2" -> getAllNotes();
                case "3" -> editNote();
                case "4" -> deleteNote();
                case "exit" -> {
                    return;
                }
                default -> System.out.println("����� ������� ���!\n");
            }
            SaveNoteService.saveListOfNotesWithFileNames(allNotes);
        }
    }

    private void deleteNote() {
        String title = "";
        System.out.println("�������� ������� ������� ������ �������!");
        getAllNotes();
        System.out.print("������� ��� �����: ");
        title = sc.nextLine();
        if (!allNotes.contains(title)) {
            System.out.println("������ ����� ��� ��� ����������� ������ ����!");
        } else {
            delete.deleteNote(title);
        }
    }

    private void editNote() {
        String title = "";
        System.out.println("�������� ������� ��� ��������������!");
        getAllNotes();
        while (!title.equalsIgnoreCase("exit")) {
            System.out.print("������� ��� �����: ");
            title = sc.nextLine();
            if (!allNotes.contains(title)) {
                System.out.println("""
                        ������ ����� ��� ��� ����������� ������ ����!
                        ���������� ��� ��� ������ ��� �����.
                        ���� ������ ����� ������� exit.
                        """);
            } else {
                edit.editNote(title + ".txt");
                title = "exit";
            }
        }
    }

    private void getAllNotes() {
        for (String title : Note.getAllNotes()) {
            System.out.printf("���������: %-15s ����: %s.txt\n", title, title);
        }
        System.out.println();
    }

    private void createNote() {
        System.out.print("������� �������� �������: ");
        String title = sc.nextLine();
        System.out.print("������� ����� �������: ");
        String text = sc.nextLine();
        Note.createNote(title, text);
    }
}
