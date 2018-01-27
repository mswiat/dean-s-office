package com.pg.engine.console.command.adders;


import com.pg.engine.console.command.ICommand;
import com.pg.engine.console.command.validator.NameValidator;
import com.pg.engine.console.command.validator.PeselValidator;
import com.pg.person.student.Student;
import com.pg.person.student.StudentRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Component
public class AddStudentCommand implements ICommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddStudentCommand.class);
    @Autowired
    private StudentRegister studentRegister;

    @Autowired
    private PeselValidator peselValidator;

    @Autowired
    private NameValidator nameValidator;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        validateInputDataAndAddNewStudent(scanner);
    }

    private void validateInputDataAndAddNewStudent(Scanner scanner) {
        String lastName;
        String firstName;
        System.out.println("Podaj:");
        System.out.println("imię: ");
        firstName = scanner.nextLine();
        if (nameValidator.validateName(firstName)) {
            System.out.println("nazwisko: ");
            lastName = scanner.nextLine();
            validateLastNameAndPeselAndAddNewStudent(firstName, lastName, scanner);
        } else {
            LOGGER.info("Nieprawidłowe imię");
        }
    }

    private void validateLastNameAndPeselAndAddNewStudent(String firstName, String lastName, Scanner scanner) {
        String albumNumber;
        if (nameValidator.validateName(lastName)) {
            System.out.println("numer albumu: ");
            albumNumber = scanner.nextLine();
            System.out.println("pesel: ");
            String peselString = scanner.nextLine();
            validatePeselAndAddNewStudent(firstName, lastName, albumNumber, peselString);
        } else {
            LOGGER.info("Nieprawidłowe nazwisko");
        }
    }

    private void validatePeselAndAddNewStudent(String firstName, String lastName, String albumNumber, String peselString) {
        Long pesel;
        if (peselValidator.isValid(peselString)) {
            pesel = Long.valueOf(peselString);
            Map<Integer, Student> students = studentRegister.getStudents();
            boolean isInMap = false;
            for (Map.Entry<Integer, Student> entry : students.entrySet()) {
                if (entry.getValue().getPesel().equals(pesel)) {
                    isInMap = true;
                }
            }
            if (isInMap) {
                LOGGER.info("Student o podanym peselu jest już w bazie.");
            } else {
                Student student = new Student(firstName, lastName, albumNumber, pesel);
                studentRegister.addStudent(student);
            }
        } else {
            LOGGER.info("Nieprawidłowy pesel.");
        }
    }
}


//    boolean isFirstNameValid = nameValidator.validateFirstName(firstName);
//    boolean isLastNameValid = nameValidator.validateLastName(lastName);
//    boolean isPeselValid = PeselValidator.validatePesel(peselString);
//
//        if (isFirstNameValid && isLastNameValid && isPeselValid) {
//                studentRegister.addStudent(new Student(firstName, lastName, albumNumber, pesel));
//                }else{
//                LOGGER.info("Nieprawidłowe wprowadzone dane");
//                }
