package command;


import com.student.Student;
import com.student.StudentRegister;

import java.util.Scanner;

public class AddStudentCommand implements ICommand {
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
        pesel = Long.valueOf(scanner.nextLine());
        StudentRegister.addStudent(new Student(firstName, lastName, albumNumber, pesel));
    }
}
