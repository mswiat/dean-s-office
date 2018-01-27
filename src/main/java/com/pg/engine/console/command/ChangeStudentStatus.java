package com.pg.engine.console.command;

import com.pg.engine.files.DeanOfficeWriter;
import com.pg.person.student.Student;
import com.pg.person.student.StudentRegister;
import com.pg.person.student.StudentStatus;
import com.pg.engine.console.command.validator.NumberInputDataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

@Component
public class ChangeStudentStatus implements ICommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeStudentStatus.class);
    private static final String STUDENTS_CSV = "students.csv";
    @Autowired
    private StudentRegister studentRegister;

    @Autowired
    private NumberInputDataValidator numberValidator;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        validateInputDataAndChangeStudentStatus(scanner);
        saveChanges();
    }

    private void validateInputDataAndChangeStudentStatus(Scanner scanner) {
        System.out.println("Podaj ID ucznia, dla którego chcesz zmienić status: ");
        String studentIdString = numberValidator.removeNonDigit(scanner.nextLine());
        int studentId = numberValidator.validateAndGetNumber(studentIdString);
        if (studentRegister.getStudents().containsKey(studentId)) {
            System.out.println("Status:  (ACTIVE/REMOVED/ON_BREAK)");
            validateStudentStatus(scanner, studentId);
        } else {
            LOGGER.info("Nie ma takiego studenta w bazie.");
        }
    }

    private void validateStudentStatus(Scanner scanner, int id) {
        String statusInput = scanner.nextLine();
        boolean isStatusValid = false;
        for (StudentStatus status : StudentStatus.values()) {
            if (status.toString().equals(statusInput)) {
                Student student = studentRegister.getStudents().get(id);
                student.setStatus(StudentStatus.valueOf(statusInput));
                LOGGER.info("Zmieniono status dla: " + student.getFirstName() + " " + student.getLastName());
                isStatusValid = true;
            }
        }
        if (!isStatusValid) {
            LOGGER.info("Błędnie wpisany status studenta.");
        }
    }

    private void saveChanges() {
        File file = new File(STUDENTS_CSV);
        if (file.exists()) {
            boolean delete = file.delete();
            if (!delete) {
                throw new IllegalStateException("Couldn't delete " + STUDENTS_CSV);
            }
        }
        DeanOfficeWriter deanOfficeWriter = new DeanOfficeWriter();
        deanOfficeWriter.save(studentRegister.getStudents());
    }
}
