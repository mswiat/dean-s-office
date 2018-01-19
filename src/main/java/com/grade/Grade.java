package com.grade;

import com.Savable;

public class Grade implements Savable {
    private static final String GRADES_FILE = "grades.csv";
    private int studentId;
    private int subjectId;
    private GradeEnum grade;

    public Grade(int studentId, int subjectId, GradeEnum grade) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.grade = grade;
    }

    @Override
    public String getDataToSave() {
        StringBuilder dataToSave = new StringBuilder();
        dataToSave
                .append(this.studentId)
                .append(",")
                .append(this.subjectId)
                .append(",")
                .append(this.grade);
        return dataToSave.toString();
    }

    @Override
    public String getFileName() {
        return GRADES_FILE;
    }
}
