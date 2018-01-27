package com.pg.subject.grade;

import com.pg.engine.files.DeanOfficeWriter;
import com.pg.engine.files.InfoProvider;
import com.pg.engine.IPostSpringInit;
import com.pg.subject.Subject;
import com.pg.subject.SubjectRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

@Component
public class GradeRegister implements IPostSpringInit {
    private static final Logger LOGGER = LoggerFactory.getLogger(GradeRegister.class);
    private static final String GRADES_FILE = "grades.csv";
    @Autowired
    private InfoProvider infoProvider;
    @Autowired
    private GradeReader gradeReader;
    @Autowired
    private SubjectRegister subjectRegister;

    @Override
    public void init() {
        File file = new File(GRADES_FILE);
        if (file.exists() && !file.isDirectory()) {
            infoProvider.readData(GRADES_FILE, gradeReader);
        }
    }

    public void addGrade(Grade grade) {
        DeanOfficeWriter officeWriter = new DeanOfficeWriter();
        officeWriter.save(grade);
        LOGGER.info("Dodano ocenę");
    }

    public double getAverage(List<BigDecimal> grades, int subjectId) {
        BigDecimal average = BigDecimal.ZERO;
        double averageDouble = 0.0;
        for (BigDecimal grade : grades) {
            average = average.add(grade);
            averageDouble += grade.doubleValue();
        }
        Integer gradeNumbers = grades.size();
        //average = average.divide(new BigDecimal(grades.size()));
        averageDouble = averageDouble/gradeNumbers;
        return averageDouble;
    }
}
