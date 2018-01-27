package com.pg.engine.console.command.printers;

import com.pg.engine.console.command.ICommand;
import com.pg.person.teacher.Teacher;
import com.pg.person.teacher.TeacherRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PrintTeacherInfoCommand implements ICommand {
    @Autowired
    private TeacherRegister teacherRegister;

    @Override
    public void execute() {
        for (Map.Entry<Integer, Teacher> entry : teacherRegister.getTeachers().entrySet()) {
            entry.getValue().info();
            System.out.println();
        }
    }
}
