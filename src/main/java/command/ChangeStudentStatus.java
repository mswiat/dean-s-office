package command;

import com.*;
import com.student.Student;
import com.student.StudentRegister;
import com.student.StudentStatus;

import java.io.File;
import java.util.Scanner;

public class ChangeStudentStatus implements ICommand {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID ucznia, dla którego chcesz zmienić status: ");
        int id = Integer.valueOf(scanner.nextLine());
        System.out.println("Status:  (ACTIVE/REMOVED/ON_BREAK)");
        String status = scanner.nextLine();
        for(Student student : StudentRegister.getStudents()){
            if(id == student.getId()){
                student.setStatus(StudentStatus.valueOf(status));
                System.out.println("Zmieniono status dla: " + student.getFirstName() + " " + student.getLastName());
            }
        }
        File file = new File("students.csv");
        if(file.exists()){
            file.delete();
        }
        DeanOfficeWriter deanOfficeWriter = new DeanOfficeWriter();
        deanOfficeWriter.save(StudentRegister.getStudents());
    }
}
