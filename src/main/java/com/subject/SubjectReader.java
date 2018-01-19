package com.subject;

import com.IReader;

import java.io.BufferedReader;

public class SubjectReader implements IReader {

    @Override
    public void read(String line, BufferedReader br) {
        String[] splittedLine = line.split(",");
        int id = Integer.valueOf(splittedLine[0]);
        String name = splittedLine[1];
        String duration = splittedLine[2];
        int ects = Integer.valueOf(splittedLine[3]);
        String test = splittedLine[4];
        Subject subject = new Subject(name, duration, ects, test);
        subject.setId(id);
        SubjectRegister.getSubjects().put(id, subject);
    }
}
