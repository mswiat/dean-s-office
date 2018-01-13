package command;

import com.DeanOfficeWriter;
import com.teacher.Teacher;
import com.teacher.TeacherRegister;

import java.io.File;
import java.util.Scanner;

public class AddTeacherDegree implements ICommand {

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID nauczyciela, dla którego chcesz dodać tytuł naukowy: ");
        int id = Integer.valueOf(scanner.nextLine());
        System.out.println("Tytuł naukowy: ");
        String degree = scanner.nextLine();
        for (Teacher teacher : TeacherRegister.getTeachers()) {
            if (id == teacher.getId()) {
                teacher.setDegree(degree);
                System.out.println("Dodano tytuł naukowy dla: " + teacher.getFirstName() + " " + teacher.getLastName());
            }
        }
        File file = new File("teachers.csv");
        if (file.exists()) {
            file.delete();
        }
        DeanOfficeWriter deanOfficeWriter = new DeanOfficeWriter();
        deanOfficeWriter.save(TeacherRegister.getTeachers());
    }
}
