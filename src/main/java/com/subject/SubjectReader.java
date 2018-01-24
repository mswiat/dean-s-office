package com.subject;

import com.IReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;

@Component
public class SubjectReader implements IReader {
    @Autowired
    private SubjectRegister subjectRegister;

    @Override
    public void read(String line, BufferedReader br) {
        String[] splittedLine = line.split(",");
        int id = Integer.parseInt(splittedLine[0]);
        String name = splittedLine[1];
        String duration = splittedLine[2];
        int ects = Integer.parseInt(splittedLine[3]);
        String test = splittedLine[4];
        Subject subject = new Subject(name, duration, ects, test);
        subject.setId(id);
        subjectRegister.getSubjects().put(id, subject);
    }
}
