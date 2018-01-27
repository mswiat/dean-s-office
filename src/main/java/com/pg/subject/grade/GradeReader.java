package com.pg.subject.grade;

import com.pg.engine.files.IReader;
import com.pg.person.student.Student;
import com.pg.person.student.StudentRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GradeReader implements IReader {

    @Autowired
    private StudentRegister studentRegister;

    private List<BigDecimal> gradesStatistics =new ArrayList<>();
    @Override
    public void read(String line, BufferedReader br) {
        String[] splittedLine = line.split(",");
        int studentId = Integer.parseInt(splittedLine[0]);
        Map<Integer, Student> students = studentRegister.getStudents();
        if (students.containsKey(studentId)) {
            int subjectId = Integer.parseInt(splittedLine[1]);
            BigDecimal grade = GradeEnum.valueOf(splittedLine[2]).getGradeInNumber();
            Map<Integer, List<BigDecimal>> gradesMap = students.get(studentId).getGrades();
            if (gradesMap.containsKey(subjectId)) {
                gradesMap.get(subjectId).add(grade);
                gradesStatistics.add(grade);
            } else {
                List<BigDecimal> grades = new ArrayList<>();
                grades.add(grade);
                gradesMap.put(subjectId, grades);
                gradesStatistics.add(grade);
            }
        }
    }

    public List<BigDecimal> getGradesStatistics() {
        return gradesStatistics;
    }
}
