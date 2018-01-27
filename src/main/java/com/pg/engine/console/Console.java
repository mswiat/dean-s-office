package com.pg.engine.console;

import com.pg.engine.console.command.*;
import com.pg.engine.console.command.adders.*;
import com.pg.engine.console.command.printers.*;
import com.pg.engine.console.command.validator.NumberInputDataValidator;
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
    private PrintSubjectGradesCommand printSubjectGradesCommand;
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
    @Autowired
    private NumberInputDataValidator numberValidator;
    @Autowired
    private PrintStatistics printStatistics;
    @Autowired
    private PrintActiveStudentsNumber printActiveStudentsNumber;


    public void init() {
        commands.put(1, printStudentInfoCommand);
        commands.put(2, printTeacherInfoCommand);
        commands.put(3, printSubjectInfoCommand);
        commands.put(4, printStudentGradesCommand);
        commands.put(5, printSubjectGradesCommand);
        commands.put(6, addStudentCommand);
        commands.put(7, addTeacherCommand);
        commands.put(8, addSubjectCommand);
        commands.put(9, addTeacherDegree);
        commands.put(10, changeStudentStatus);
        commands.put(11, addGradeCommand);
        commands.put(12, printStatistics);
        commands.put(13, printActiveStudentsNumber);
        commands.put(14, exitCommand);
    }

    public void handleConsole() {
        while (!isExit) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            String numberInput = numberValidator.removeNonDigit(scanner.nextLine());
            int number = numberValidator.validateAndGetNumber(numberInput);
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
        System.out.println("5 - wyświetl oceny z przedmiotu");
        System.out.println();
        System.out.println("6 - dodaj studenta");
        System.out.println("7 - dodaj nauczyciela");
        System.out.println("8 - dodaj przedmiot");
        System.out.println();
        System.out.println("9 - wprowadź tytuł naukowy");
        System.out.println("10 - zmień status studenta");
        System.out.println("11 - wystaw ocenę");
        System.out.println("12 - wyświetl statystyki");
        System.out.println("13 - wyświetl liczbę aktywnych studentów");
        System.out.println();
        System.out.println("14 - wyjście");
    }

    public void finishConsole() {
        isExit = true;
    }
}
