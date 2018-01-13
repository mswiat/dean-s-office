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
        commands.put(4, new AddStudentCommand());
        commands.put(5, new AddTeacherCommand());
        commands.put(6, new AddSubjectCommand());
        commands.put(7, new AddTeacherDegree());
        commands.put(8, new ChangeStudentStatus());
        commands.put(9, new ExitCommand(this));
    }

    public void handleConsole() {
        int number = 0;
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
        System.out.println();
        System.out.println("4 - dodaj studenta");
        System.out.println("5 - dodaj nauczyciela");
        System.out.println("6 - dodaj przedmiot");
        System.out.println();
        System.out.println("7 - wprowadź tytuł naukowy");
        System.out.println("8 - zmień status studenta");
        System.out.println();
        System.out.println("9 - wyjście");
    }

    public void finishConsole() {
        isExit = true;
    }
}
