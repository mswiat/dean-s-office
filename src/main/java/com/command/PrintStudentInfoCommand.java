package com.command;

import com.student.Student;
import com.student.StudentRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PrintStudentInfoCommand implements ICommand {

    @Autowired
    private StudentRegister studentRegister;

    @Override
    public void execute() {
        for (Map.Entry<Integer, Student> entry : studentRegister.getStudents().entrySet()) {
            entry.getValue().info();
            System.out.println();
        }
    }
}
