package command;

import com.Person;
import com.student.StudentRegister;

public class PrintStudentInfoCommand implements ICommand {
    @Override
    public void execute() {
        for (Person student : StudentRegister.getStudents()) {
            student.info();
            System.out.println();
        }
    }
}
