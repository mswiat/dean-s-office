package command;

import com.*;
import com.student.Student;
import com.student.StudentRegister;
import com.student.StudentStatus;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class ChangeStudentStatus implements ICommand {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID ucznia, dla którego chcesz zmienić status: ");
        int id = Integer.valueOf(scanner.nextLine());
        System.out.println("Status:  (ACTIVE/REMOVED/ON_BREAK)");
        String status = scanner.nextLine();
        if(StudentRegister.getStudents().containsKey(id)){
            Student student = StudentRegister.getStudents().get(id);
            student.setStatus(StudentStatus.valueOf(status));
            System.out.println("Zmieniono status dla: " + student.getFirstName() + " " + student.getLastName());
        }else{
            System.out.println("Nie ma takiego studenta w bazie.");
        }
        saveChanges();
    }

    private void saveChanges() {
        File file = new File("students.csv");
        if (file.exists()) {
            file.delete();
        }
        DeanOfficeWriter deanOfficeWriter = new DeanOfficeWriter();
        deanOfficeWriter.save(StudentRegister.getStudents());
    }
}
