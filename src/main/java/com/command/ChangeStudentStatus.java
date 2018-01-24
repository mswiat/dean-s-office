package com.command;

import com.*;
import com.student.Student;
import com.student.StudentRegister;
import com.student.StudentStatus;
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

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID ucznia, dla którego chcesz zmienić status: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Status:  (ACTIVE/REMOVED/ON_BREAK)");
        String status = scanner.nextLine();
        if (studentRegister.getStudents().containsKey(id)) {
            Student student = studentRegister.getStudents().get(id);
            student.setStatus(StudentStatus.valueOf(status));
            LOGGER.info("Zmieniono status dla: " + student.getFirstName() + " " + student.getLastName());
        } else {
            LOGGER.info("Nie ma takiego studenta w bazie.");
        }
        saveChanges();
    }

    private void saveChanges() {
        File file = new File(STUDENTS_CSV);
        if (file.exists()) {
            boolean delete = file.delete();
            if(!delete){
                throw new IllegalStateException("Couldn't delete " + STUDENTS_CSV);
            }
        }
        DeanOfficeWriter deanOfficeWriter = new DeanOfficeWriter();
        deanOfficeWriter.save(studentRegister.getStudents());
    }
}
