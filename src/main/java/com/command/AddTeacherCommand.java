package com.command;

import com.teacher.Teacher;
import com.teacher.TeacherRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddTeacherCommand implements ICommand {
    @Autowired
    private TeacherRegister teacherRegister;

    @Override
    public void execute() {
        String firstName;
        String lastName;
        Long pesel;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj:");
        System.out.println("imię: ");
        firstName = scanner.nextLine();
        System.out.println("nazwisko: ");
        lastName = scanner.nextLine();
        System.out.println("pesel: ");
        pesel = Long.valueOf(scanner.nextLine());
        teacherRegister.addTeacher(new Teacher(firstName, lastName, pesel));
    }
}
