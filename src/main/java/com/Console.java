package com;

import command.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Console {
    private Map<Integer, ICommand> commands = new HashMap<>();
    private boolean isExit = false;

    public void init() {
        commands.put(1, new PrintStudentInfoCommand());
        commands.put(2, new PrintTeacherInfoCommand());
        commands.put(3, new PrintSubjectInfoCommand());
        commands.put(4, new PrintStudentGradesCommand());
        commands.put(5, new AddStudentCommand());
        commands.put(6, new AddTeacherCommand());
        commands.put(7, new AddSubjectCommand());
        commands.put(8, new AddTeacherDegree());
        commands.put(9, new ChangeStudentStatus());
        commands.put(10, new AddGradeCommand());
        commands.put(11, new ExitCommand(this));
    }

    public void handleConsole() {
        int number;
        while (!isExit) {
            printMenu();
            Scanner sc = new Scanner(System.in);
            number = Integer.valueOf(sc.nextLine());
            ICommand command = commands.get(number);
            if (command == null) {
                command = new NotFoundCommand();
            }
            command.execute();
        }
    }

    public void printMenu() {
        System.out.println("------------MENU------------");
        System.out.println("1 - wyświetl listę studentów");
        System.out.println("2 - wyświetl listę nauczycieli");
        System.out.println("3 - wyświetl listę przedmiotów");
        System.out.println("4 - wyświetl oceny studenta");
        System.out.println();
        System.out.println("5 - dodaj studenta");
        System.out.println("6 - dodaj nauczyciela");
        System.out.println("7 - dodaj przedmiot");
        System.out.println();
        System.out.println("8 - wprowadź tytuł naukowy");
        System.out.println("9 - zmień status studenta");
        System.out.println("10 - wystaw ocenę");

        System.out.println();
        System.out.println("11 - wyjście");
    }

    public void finishConsole() {
        isExit = true;
    }
}
