package com.pg.person.student;

import com.pg.engine.files.ISaver;
import com.pg.person.Person;

import java.math.BigDecimal;
import java.util.*;

public class Student extends Person implements ISaver {
    private static final String DELIMITER = ",";
    private static String STUDENT_FILE = "students.csv";

    private int id;
    private String albumNumber;
    private StudentStatus status = StudentStatus.ACTIVE;
    private Map<Integer, List<BigDecimal>> grades = new HashMap<>();

    public Student(String firstName, String lastName, String albumNumber, Long pesel) {
        super(firstName, lastName, pesel);
        this.albumNumber = albumNumber;
    }

    @Override
    public void info() {
        System.out.println("Student ID: " + id);
        System.out.println("imię: " + firstName);
        System.out.println("nazwisko: " + lastName);
        System.out.println("numer albumu: " + albumNumber);
        System.out.println("PESEL: " + pesel);
        System.out.println("status: " + status.getValuePL());
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
                .append(this.albumNumber)
                .append(DELIMITER)
                .append(this.pesel)
                .append(DELIMITER)
                .append(this.status.name());
        return dataToSave.toString();
    }

    @Override
    public String getFileName() {
        return STUDENT_FILE;
    }

    public String getAlbumNumber() {
        return albumNumber;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, List<BigDecimal>> getGrades() {
        return grades;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public void setGrades(Map<Integer, List<BigDecimal>> grades) {
        this.grades = grades;
    }

    public void setId(int id) {
        this.id = id;
    }
}
