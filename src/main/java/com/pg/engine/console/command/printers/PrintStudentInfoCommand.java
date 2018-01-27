package com.pg.engine.console.command.printers;

import com.pg.engine.console.command.ICommand;
import com.pg.person.student.Student;
import com.pg.person.student.StudentRegister;
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
