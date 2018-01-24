package com.grade;

import com.Savable;

public class Grade implements Savable {
    private static final String DELIMITER = ",";
    private static final String GRADES_FILE = "grades.csv";
    private int studentId;
    private int subjectId;
    private GradeEnum gradeEnum;

    public Grade(int studentId, int subjectId, GradeEnum gradeEnum) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.gradeEnum = gradeEnum;
    }

    @Override
    public String getDataToSave() {
        StringBuilder dataToSave = new StringBuilder();
        dataToSave
                .append(this.studentId)
                .append(DELIMITER)
                .append(this.subjectId)
                .append(DELIMITER)
                .append(this.gradeEnum);
        return dataToSave.toString();
    }

    @Override
    public String getFileName() {
        return GRADES_FILE;
    }
}
