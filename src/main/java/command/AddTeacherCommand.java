package command;

import com.teacher.Teacher;
import com.teacher.TeacherRegister;

import java.util.Scanner;

public class AddTeacherCommand implements ICommand {
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
        TeacherRegister.addTeacher(new Teacher(firstName, lastName, pesel));
    }
}
