package com.teacher;

import com.Person;
import com.Savable;

public class Teacher extends Person implements Savable {
    private static final String TEACHER_FILE = "teachers.csv";

    private String degree;
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

    @Override
    public String getDataToSave() {
        StringBuilder dataToSave = new StringBuilder();
        dataToSave
                .append(this.id)
                .append(",")
                .append(this.firstName)
                .append(",")
                .append(this.lastName)
                .append(",")
                .append((degree == null ? "b/d" : degree))
                .append(",")
                .append(this.pesel);
        return dataToSave.toString();
    }

    @Override
    public String getFileName() {
        return TEACHER_FILE;
    }

}
