package com.grade;

import com.IReader;
import com.student.Student;
import com.student.StudentRegister;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GradeReader implements IReader {

    private Student student;

    public GradeReader(Student student) {
        this.student = student;
    }


    @Override
    public void read(String line, BufferedReader br) {
        String[] splittedLine = line.split(",");
        int studentId = Integer.valueOf(splittedLine[0]);
        if (studentId == student.getId()) {
            int subjectId = Integer.valueOf(splittedLine[1]);
            BigDecimal grade = GradeEnum.valueOf(splittedLine[2]).getGradeInNumber();
            Map<Integer, List<BigDecimal>> gradesMap = student.getGrades();
            if (gradesMap.containsKey(subjectId)) {
                gradesMap.get(subjectId).add(grade);
            } else {
                List<BigDecimal> gradesList = new ArrayList<>();
                gradesList.add(grade);
                gradesMap.put(subjectId, gradesList);
            }
        }
    }
}
