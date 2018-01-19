package command;

import com.Person;
import com.student.Student;
import com.student.StudentRegister;

import java.util.Map;

public class PrintStudentInfoCommand implements ICommand {
    @Override
    public void execute() {
        for (Map.Entry<Integer, Student> entry : StudentRegister.getStudents().entrySet()) {
            entry.getValue().info();
            System.out.println();
        }
    }
}
