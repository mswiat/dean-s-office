package com.pg.engine.console.command.adders;

import com.pg.engine.console.command.ICommand;
import com.pg.person.teacher.Teacher;
import com.pg.person.teacher.TeacherRegister;
import com.pg.engine.console.command.validator.NameValidator;
import com.pg.engine.console.command.validator.PeselValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
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
        Scanner scanner = new Scanner(System.in);
        validateInputDataAndAddNewTeacher(scanner);
    }

    private void validateInputDataAndAddNewTeacher(Scanner scanner) {
        String lastName;
        String firstName;
        System.out.println("Podaj:");
        System.out.println("imię: ");
        firstName = scanner.nextLine();
        if (nameValidator.validateName(firstName)) {
            System.out.println("nazwisko: ");
            lastName = scanner.nextLine();
            validateLastNameAndPeselAndAddNewTeacher(firstName, scanner, lastName);
        } else {
            LOGGER.info("Nieprawidłowe imię");
        }
    }

    private void validateLastNameAndPeselAndAddNewTeacher(String firstName, Scanner scanner, String lastName) {
        if (nameValidator.validateName(lastName)) {
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
            Map<Integer, Teacher> teachers = teacherRegister.getTeachers();
            boolean isInMap = false;
            for (Map.Entry<Integer, Teacher> entry : teachers.entrySet()) {
                if (entry.getValue().getPesel().equals(pesel)) {
                    isInMap = true;
                }
            }
            if (isInMap) {
                LOGGER.info("Nauczyciel o podanym peselu jest już w bazie.");
            } else {
                Teacher teacher = new Teacher(firstName, lastName, pesel);
                teacherRegister.addTeacher(teacher);
            }
        }else{
            LOGGER.info("Nieprawidłowy pesel.");
        }
    }
}
