package com.command;

import com.DeanOfficeWriter;
import com.teacher.Teacher;
import com.teacher.TeacherRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

@Component
public class AddTeacherDegree implements ICommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddTeacherDegree.class);
    private static final String TEACHERS_CSV = "teachers.csv";
    @Autowired
    private TeacherRegister teacherRegister;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID nauczyciela, dla którego chcesz dodać tytuł naukowy: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Tytuł naukowy: ");
        String degree = scanner.nextLine();

        if (teacherRegister.getTeachers().containsKey(id)) {
            Teacher teacher = teacherRegister.getTeachers().get(id);
            teacher.setDegree(degree);
            LOGGER.info("Dodano tytuł naukowy dla: " + teacher.getFirstName() + " " + teacher.getLastName());
        } else {
            LOGGER.info("Nie ma takiego nauczyciela w bazie.");
        }
        saveChanges();
    }

    private void saveChanges() {
        File file = new File(TEACHERS_CSV);
        if (file.exists()) {
            boolean delete = file.delete();
            if (!delete) {
                throw new IllegalStateException("Couldn't delete " + TEACHERS_CSV);
            }
        }
        DeanOfficeWriter deanOfficeWriter = new DeanOfficeWriter();
        deanOfficeWriter.save(teacherRegister.getTeachers());
    }
}
