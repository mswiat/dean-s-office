package com.teacher;

import com.Reader;

import java.io.BufferedReader;

public class TeacherReader implements Reader {
    @Override
    public void read(String line, BufferedReader br) {
        String[] splittedLine = line.split(",");
        int id = Integer.valueOf(splittedLine[0]);
        String firstName = splittedLine[1];
        String lastName = splittedLine[2];
        String degree = splittedLine[3];
        Long pesel = Long.valueOf(splittedLine[4]);
        Teacher teacher = new Teacher(firstName, lastName, pesel);
        teacher.setId(id);
        teacher.setDegree(degree);
        TeacherRegister.getTeachers().add(teacher);
    }
}
