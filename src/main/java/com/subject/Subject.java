package com.subject;

import com.Savable;

public class Subject implements Savable {
    private static final String DELIMITER = ",";
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
                .append(this.id)
                .append(DELIMITER)
                .append(this.name)
                .append(DELIMITER)
                .append(this.duration)
                .append(DELIMITER)
                .append(this.ects)
                .append(DELIMITER)
                .append(this.test);
        return dataToSave.toString();
    }

    @Override
    public String getFileName() {
        return SUBJECT_FILE;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
