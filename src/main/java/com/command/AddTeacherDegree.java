package com.command;

import com.DeanOfficeWriter;
import com.teacher.Teacher;
import com.teacher.TeacherRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

@Component
public class AddTeacherDegree implements ICommand {
    @Autowired
    private TeacherRegister teacherRegister;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID nauczyciela, dla którego chcesz dodać tytuł naukowy: ");
        int id = Integer.valueOf(scanner.nextLine());
        System.out.println("Tytuł naukowy: ");
        String degree = scanner.nextLine();

        if (teacherRegister.getTeachers().containsKey(id)) {
            Teacher teacher = teacherRegister.getTeachers().get(id);
            teacher.setDegree(degree);
            System.out.println("Dodano tytuł naukowy dla: " + teacher.getFirstName() + " " + teacher.getLastName());
        } else {
            System.out.println("Nie ma takiego nauczyciela w bazie.");
        }
        saveChanges();
    }

    private void saveChanges() {
        File file = new File("teachers.csv");
        if (file.exists()) {
            file.delete();
        }
        DeanOfficeWriter deanOfficeWriter = new DeanOfficeWriter();
        deanOfficeWriter.save(teacherRegister.getTeachers());
    }
}
