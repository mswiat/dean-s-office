package com.command;

import com.teacher.Teacher;
import com.teacher.TeacherRegister;
import com.validator.NameValidator;
import com.validator.PeselValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddTeacherCommand implements ICommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddTeacherCommand.class);

    @Autowired
    private TeacherRegister teacherRegister;

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
        validateInputDataAndAddNewTeacher(firstName, scanner);
    }

    private void validateInputDataAndAddNewTeacher(String firstName, Scanner scanner) {
        String lastName;
        if (nameValidator.validateFirstName(firstName)) {
            System.out.println("nazwisko: ");
            lastName = scanner.nextLine();
            validateLastNameAndPeselAndAddNewTeacher(firstName, scanner, lastName);
        } else {
            LOGGER.info("Nieprawidłowe imię");
        }
    }

    private void validateLastNameAndPeselAndAddNewTeacher(String firstName, Scanner scanner, String lastName) {
        if (nameValidator.validateLastName(lastName)) {
            System.out.println("pesel: ");
            String peselString = scanner.nextLine();
            validatePeselAndAddNewTeacher(firstName, lastName, peselString);
        }else{
            LOGGER.info("Nieprawidłowe nazwisko");
        }
    }

    private void validatePeselAndAddNewTeacher(String firstName, String lastName, String peselString) {
        Long pesel;
        if (peselValidator.isValid(peselString)) {
            pesel = Long.valueOf(peselString);
            teacherRegister.addTeacher(new Teacher(firstName, lastName, pesel));
        }else{
            LOGGER.info("Nieprawidłowy pesel.");
        }
    }
}
