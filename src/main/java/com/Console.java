package com;

import com.command.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class Console {
    private Map<Integer, ICommand> commands = new HashMap<>();
    private boolean isExit = false;
    @Autowired
    private PrintStudentInfoCommand printStudentInfoCommand;
    @Autowired
    private PrintTeacherInfoCommand printTeacherInfoCommand;
    @Autowired
    private PrintSubjectInfoCommand printSubjectInfoCommand;
    @Autowired
    private PrintStudentGradesCommand printStudentGradesCommand;
    @Autowired
    private AddStudentCommand addStudentCommand;
    @Autowired
    private AddTeacherCommand addTeacherCommand;
    @Autowired
    private AddSubjectCommand addSubjectCommand;
    @Autowired
    private AddTeacherDegree addTeacherDegree;
    @Autowired
    private ChangeStudentStatus changeStudentStatus;
    @Autowired
    private AddGradeCommand addGradeCommand;
    @Autowired
    private ExitCommand exitCommand;

    public void init() {
        commands.put(1, printStudentInfoCommand);
        commands.put(2, printTeacherInfoCommand);
        commands.put(3, printSubjectInfoCommand);
        commands.put(4, printStudentGradesCommand);
        commands.put(5, addStudentCommand);
        commands.put(6, addTeacherCommand);
        commands.put(7, addSubjectCommand);
        commands.put(8, addTeacherDegree);
        commands.put(9, changeStudentStatus);
        commands.put(10, addGradeCommand);
        commands.put(11, exitCommand);
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
