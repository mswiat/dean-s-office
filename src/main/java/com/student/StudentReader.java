package com.student;

import com.IReader;

import java.io.*;

public class StudentReader implements IReader {

    @Override
    public void read(String line, BufferedReader br) {
        String[] splittedLine = line.split(",");
        int id = Integer.valueOf(splittedLine[0]);
        String firstName = splittedLine[1];
        String lastName = splittedLine[2];
        String albumNumber = splittedLine[3];
        Long pesel = Long.valueOf(splittedLine[4]);
        String status = splittedLine[5];
        Student student = new Student(firstName, lastName, albumNumber, Long.valueOf(pesel));
        student.setId(id);
        student.setStatus(StudentStatus.valueOf(status));
        StudentRegister.getStudents().put(id, student);
    }
}
