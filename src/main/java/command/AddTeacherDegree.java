package command;

import com.DeanOfficeWriter;
import com.teacher.Teacher;
import com.teacher.TeacherRegister;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class AddTeacherDegree implements ICommand {

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID nauczyciela, dla którego chcesz dodać tytuł naukowy: ");
        int id = Integer.valueOf(scanner.nextLine());
        System.out.println("Tytuł naukowy: ");
        String degree = scanner.nextLine();

        if (TeacherRegister.getTeachers().containsKey(id)) {
            Teacher teacher = TeacherRegister.getTeachers().get(id);
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
        deanOfficeWriter.save(TeacherRegister.getTeachers());
    }
}
