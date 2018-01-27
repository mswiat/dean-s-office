package com.pg.person.teacher;

import com.pg.engine.files.IReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;

@Component
public class TeacherReader implements IReader {

    @Autowired
    private TeacherRegister teacherRegister;

    @Override
    public void read(String line, BufferedReader br) {
        String[] splittedLine = line.split(",");
        int id = Integer.parseInt(splittedLine[0]);
        String firstName = splittedLine[1];
        String lastName = splittedLine[2];
        String degree = splittedLine[3];
        Long pesel = Long.valueOf(splittedLine[4]);
        Teacher teacher = new Teacher(firstName, lastName, pesel);
        teacher.setId(id);
        teacher.setDegree(degree);
        teacherRegister.getTeachers().put(id, teacher);
    }
}
