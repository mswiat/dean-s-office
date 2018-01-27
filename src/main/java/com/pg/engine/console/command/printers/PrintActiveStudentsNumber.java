package com.pg.engine.console.command.printers;

import com.pg.engine.console.command.ICommand;
import com.pg.person.student.Student;
import com.pg.person.student.StudentRegister;
import com.pg.person.student.StudentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PrintActiveStudentsNumber implements ICommand{

    @Autowired
    private StudentRegister studentRegister;

    @Override
    public void execute() {
        int activeStudentNumber = 0;
        for(Map.Entry<Integer, Student> students : studentRegister.getStudents().entrySet()){
            if(students.getValue().getStatus().equals(StudentStatus.ACTIVE)){
                activeStudentNumber++;
            }
        }
        System.out.println("Liczba aktywnych studentów: " + activeStudentNumber);

    }
}
