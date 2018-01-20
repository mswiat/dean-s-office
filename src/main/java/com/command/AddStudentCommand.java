package com.command;


import com.PeselValidator;
import com.student.Student;
import com.student.StudentRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class AddStudentCommand implements ICommand {
    @Autowired
    private StudentRegister studentRegister;

    @Override
    public void execute() {
        String firstName;
        String lastName;
        String albumNumber;
        Long pesel;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj:");
        System.out.println("imię: ");
        firstName = scanner.nextLine();
        System.out.println("nazwisko: ");
        lastName = scanner.nextLine();
        System.out.println("numer albumu: ");
        albumNumber = scanner.nextLine();
        System.out.println("pesel: ");
        String peselString = scanner.nextLine();
        PeselValidator peselValidator = new PeselValidator();
        if (peselValidator.isValid(peselString)) {
            pesel = Long.valueOf(peselString);
            studentRegister.addStudent(new Student(firstName, lastName, albumNumber, pesel));
        } else {
            System.out.println("Nieprawidłowy pesel.");
        }
    }
}
