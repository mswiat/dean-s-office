package com.command;

import com.subject.Subject;
import com.subject.SubjectRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class AddSubjectCommand implements ICommand {
    @Autowired
    private SubjectRegister subjectRegister;

    @Override
    public void execute() {
        String name;
        String duration;
        int ects;
        String test;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj:");
        System.out.println("nazwa: ");
        name = scanner.nextLine();
        System.out.println("semestr: ");
        duration = scanner.nextLine();
        System.out.println("ECTS: ");
        ects = Integer.valueOf(scanner.nextLine());
        System.out.println("zaliczenie: ");
        test = scanner.nextLine();
        subjectRegister.addSubject(new Subject(name, duration, ects, test));
    }
}
