package command;

import com.Person;
import com.teacher.Teacher;
import com.teacher.TeacherRegister;

import java.util.Map;

public class PrintTeacherInfoCommand implements ICommand {
    @Override
    public void execute() {
        for (Map.Entry<Integer, Teacher> entry : TeacherRegister.getTeachers().entrySet()) {
            entry.getValue().info();
            System.out.println();
        }
    }
}
