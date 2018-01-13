package command;

import com.subject.Subject;
import com.subject.SubjectRegister;

import java.util.Scanner;

public class AddSubjectCommand implements ICommand {
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
        SubjectRegister.addSubject(new Subject(name, duration, ects, test));
    }
}
