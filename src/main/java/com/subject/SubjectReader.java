package com.subject;

import com.Reader;
import com.subject.Subject;
import com.subject.SubjectRegister;

import java.io.BufferedReader;

public class SubjectReader implements Reader {

    @Override
    public void read(String line, BufferedReader br) {
        String[] splittedLine = line.split(",");
        String name = splittedLine[0];
        String duration = splittedLine[1];
        int ects = Integer.valueOf(splittedLine[2]);
        String test = splittedLine[3];
        SubjectRegister.getSubjects().add(new Subject(name, duration, ects, test));
    }
}
