package com.pg.person.student;

import com.pg.engine.files.IReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;

@Component
public class StudentReader implements IReader {

    @Autowired
    private StudentRegister studentRegister;

    @Override
    public void read(String line, BufferedReader br) {
        String[] splittedLine = line.split(",");
        int id = Integer.parseInt(splittedLine[0]);
        String firstName = splittedLine[1];
        String lastName = splittedLine[2];
        String albumNumber = splittedLine[3];
        Long pesel = Long.valueOf(splittedLine[4]);
        String status = splittedLine[5];
        Student student = new Student(firstName, lastName, albumNumber, Long.valueOf(pesel));
        student.setId(id);
        student.setStatus(StudentStatus.valueOf(status));
        Map<Integer, Student> students = studentRegister.getStudents();
        students.put(id, student);
    }
}
