package command;

import com.Person;
import com.teacher.TeacherRegister;

public class PrintTeacherInfoCommand implements ICommand {
    @Override
    public void execute() {
        for(Person teacher : TeacherRegister.getTeachers()){
            teacher.info();
            System.out.println();
        }
    }
}
