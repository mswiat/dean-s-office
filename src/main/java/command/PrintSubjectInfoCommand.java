package command;

import com.subject.Subject;
import com.subject.SubjectRegister;
import com.teacher.Teacher;

import java.util.Map;

public class PrintSubjectInfoCommand implements ICommand {
    @Override
    public void execute() {
        for (Map.Entry<Integer, Subject> entry : SubjectRegister.getSubjects().entrySet()) {
            entry.getValue().info();
            System.out.println();
        }
    }
}
