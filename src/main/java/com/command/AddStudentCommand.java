package com.command;


import com.validator.NameValidator;
import com.validator.PeselValidator;
import com.student.Student;
import com.student.StudentRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        String firstName;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj:");
        System.out.println("imię: ");
        firstName = scanner.nextLine();
        validateInputDataAndAddNewStudent(firstName, scanner);
    }

    private void validateInputDataAndAddNewStudent(String firstName, Scanner scanner) {
        String lastName;
        if (nameValidator.validateFirstName(firstName)) {
            System.out.println("nazwisko: ");
            lastName = scanner.nextLine();
            validateLastNameAndPeselAndAddNewStudent(firstName, lastName, scanner);
        }else{
            LOGGER.info("Nieprawidłowe imię");
        }
    }

    private void validateLastNameAndPeselAndAddNewStudent(String firstName, String lastName, Scanner scanner) {
        String albumNumber;
        if (nameValidator.validateLastName(lastName)) {
            System.out.println("numer albumu: ");
            albumNumber = scanner.nextLine();
            System.out.println("pesel: ");
            String peselString = scanner.nextLine();
            validatePeselAndAddNewStudent(firstName, lastName, albumNumber, peselString);
        }else{
            LOGGER.info("Nieprawidłowe nazwisko");
        }
    }

    private void validatePeselAndAddNewStudent(String firstName, String lastName, String albumNumber, String peselString) {
        Long pesel;
        if (peselValidator.isValid(peselString)) {
            pesel = Long.valueOf(peselString);
            Student student = new Student(firstName, lastName, albumNumber, pesel);
            //sprawdzenie czy jest już w bazie student o tym peselu:
            studentRegister.addStudent(student);
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
