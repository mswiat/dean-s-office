package com.command;

import com.grade.GradeRegister;
import com.student.Student;
import com.student.StudentRegister;
import com.subject.SubjectRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
@Component
public class PrintStudentGradesCommand implements ICommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintStudentGradesCommand.class);
    private Student student;
    private Map<Integer, List<BigDecimal>> grades = new HashMap<>();
    @Autowired
    private StudentRegister studentRegister;
    @Autowired
    private SubjectRegister subjectRegister;
    @Autowired
    private GradeRegister gradeRegister;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID studenta: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        if (studentRegister.getStudents().containsKey(studentId)) {
            student = studentRegister.getStudents().get(studentId);
            grades = gradeRegister.getGrades(student);
            printGrades();
        } else {
            LOGGER.info("Nie ma takiego studenta w bazie.");
        }
    }

    private void printGrades() {
        System.out.println(student.getFirstName() + " " + student.getLastName());
        int subjectID;
        for (Map.Entry<Integer, List<BigDecimal>> entry : grades.entrySet()) {
            subjectID = entry.getKey();
            System.out.println(subjectRegister.getSubjects().get(subjectID).getName() + ": ");
            System.out.print(entry.getValue() + "; średnia: ");
            String average = String.valueOf(gradeRegister.getAverage(entry.getValue()));
            System.out.println(average);
            System.out.println();
        }
    }
}
