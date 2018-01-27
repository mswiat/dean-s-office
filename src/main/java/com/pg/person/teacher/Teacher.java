package com.pg.person.teacher;

import com.pg.engine.files.ISaver;
import com.pg.person.Person;

public class Teacher extends Person implements ISaver {
    private static final String DELIMITER = ",";
    private static final String TEACHER_FILE = "teachers.csv";

    private String degree = "b/d";
    private int id;

    public Teacher(String firstName, String lastName, Long pesel) {
        super(firstName, lastName, pesel);
    }

    @Override
    public void info() {
        System.out.println("Teacher ID: " + id);
        System.out.println("stopień naukowy: " + (degree == null ? "b/d" : degree));
        System.out.println("imię: " + firstName);
        System.out.println("nazwisko: " + lastName);
        System.out.println("PESEL: " + pesel);
    }

    @Override
    public String getDataToSave() {
        StringBuilder dataToSave = new StringBuilder();
        dataToSave
                .append(this.id)
                .append(DELIMITER)
                .append(this.firstName)
                .append(DELIMITER)
                .append(this.lastName)
                .append(DELIMITER)
                .append(degree)
                .append(DELIMITER)
                .append(this.pesel);
        return dataToSave.toString();
    }

    @Override
    public String getFileName() {
        return TEACHER_FILE;
    }

    public String getDegree() {
        return degree;
    }

    public int getId() {
        return id;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setId(int id) {
        this.id = id;
    }

}
