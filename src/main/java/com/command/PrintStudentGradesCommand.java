package com.command;

import com.grade.GradeRegister;
import com.student.Student;
import com.student.StudentRegister;
import com.subject.Subject;
import com.subject.SubjectRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
@Component
public class PrintStudentGradesCommand implements ICommand {
    private Student student;
    private int subjectID;
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
        int studentId = Integer.valueOf(scanner.nextLine());

        if (studentRegister.getStudents().containsKey(studentId)) {
            student = studentRegister.getStudents().get(studentId);
            grades = gradeRegister.getGrades(student);
            printGrades();
        } else {
            System.out.println("Nie ma takiego studenta w bazie.");
        }
    }

    private void printGrades() {
        System.out.println(student.getFirstName() + " " + student.getLastName());
        for (Map.Entry<Integer, List<BigDecimal>> entry : grades.entrySet()) {
            subjectID = entry.getKey();
            System.out.print(subjectRegister.getSubjects().get(subjectID).getName() + ": ");
            System.out.print(entry.getValue() + "; średnia: ");
            System.out.println(gradeRegister.getAverage(entry.getValue()));
        }
        System.out.println();
    }
}
