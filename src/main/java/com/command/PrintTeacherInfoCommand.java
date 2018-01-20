package com.command;

import com.teacher.Teacher;
import com.teacher.TeacherRegister;
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
