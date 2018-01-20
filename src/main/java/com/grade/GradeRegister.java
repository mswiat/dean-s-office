package com.grade;

import com.DeanOfficeWriter;
import com.IReader;
import com.InfoProvider;
import com.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class GradeRegister {
    private static String GRADES_FILE = "grades.csv";
    @Autowired
    private InfoProvider infoProvider;

    public static void addGrade(Grade grade) {
        DeanOfficeWriter officeWriter = new DeanOfficeWriter();
        officeWriter.save(grade);
        System.out.println("Dodano ocenę");
    }

    public Map<Integer, List<BigDecimal>> getGrades(Student student) {
        IReader gradeReader = new GradeReader(student);
        infoProvider.getInfo(GRADES_FILE, gradeReader);
        return student.getGrades();
    }

    public BigDecimal getAverage(List<BigDecimal> grades) {
        BigDecimal average = BigDecimal.ZERO;
        for (BigDecimal grade : grades) {
            average = average.add(grade);
        }
        average = average.divide(new BigDecimal(grades.size()));
        return average;
    }
}
