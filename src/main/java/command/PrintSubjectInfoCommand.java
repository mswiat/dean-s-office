package command;

import com.subject.Subject;
import com.subject.SubjectRegister;

public class PrintSubjectInfoCommand implements ICommand {
    @Override
    public void execute() {
        for(Subject subject : SubjectRegister.getSubjects()){
            subject.info();
            System.out.println();
        }
    }
}
