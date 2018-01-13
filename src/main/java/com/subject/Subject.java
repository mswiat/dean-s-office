package com.subject;

import com.Savable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subject implements Savable {

    private static Integer subjectCount = 1;
    private static final String SUBJECT_FILE = "subjects.csv";
    private String name;
    private String duration;
    private int ects;
    private String test;
    private Integer id;

    public Subject(String name, String duration, int ects, String test) {
        this.name = name;
        this.duration = duration;
        this.ects = ects;
        this.test = test;
        id = subjectCount++;
    }

    public String getName() {
        return name;
    }

    public void info() {
        System.out.println("Subject ID: " + id);
        System.out.println("nazwa: " + this.name);
        System.out.println("semestr: " + this.duration);
        System.out.println("ECTS: " + this.ects);
        System.out.println("zaliczenie: " + this.test);
    }

    @Override
    public String getDataToSave() {
        StringBuilder dataToSave = new StringBuilder();
        dataToSave
                .append(this.name)
                .append(",")
                .append(this.duration)
                .append(",")
                .append(this.ects)
                .append(",")
                .append(this.test);
        List<String> data = new ArrayList<>();
        data.add(this.name);
        data.add(this.duration);
        data.add(String.valueOf(this.ects));
        data.add(this.test);

        return dataToSave.toString();
    }

    @Override
    public String getFileName() {
        return SUBJECT_FILE;
    }

}
