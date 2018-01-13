package com.student;

import com.Person;
import com.Savable;

public class Student extends Person implements Savable {
    private static String STUDENT_FILE = "students.csv";

    private int id;
    private String albumNumber;
    private StudentStatus status = StudentStatus.ACTIVE;


    public Student(String firstName, String lastName, String albumNumber, Long pesel) {
        super(firstName, lastName, pesel);
        this.albumNumber = albumNumber;
    }

    public void info() {
        System.out.println("Student ID: " + id);
        System.out.println("imię: " + firstName);
        System.out.println("nazwisko: " + lastName);
        System.out.println("numer albumu: " + albumNumber);
        System.out.println("PESEL: " + pesel);
        System.out.println("status: " + status.getValuePL());
    }

    public String getAlbumNumber() {
        return albumNumber;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDataToSave() {
        StringBuilder dataToSave = new StringBuilder();
        dataToSave
                .append(this.id)
                .append(",")
                .append(this.firstName)
                .append(",")
                .append(this.lastName)
                .append(",")
                .append(this.albumNumber)
                .append(",")
                .append(this.pesel)
                .append(",")
                .append(this.status.name());
        return dataToSave.toString();
    }

    @Override
    public String getFileName() {
        return STUDENT_FILE;
    }

    @Override
    public String toString() {
        return "STUDENTE! " + id;
    }
}
