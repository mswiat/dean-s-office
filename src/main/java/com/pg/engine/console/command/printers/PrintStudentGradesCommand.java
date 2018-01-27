package com.pg.engine.console.command.printers;

import com.pg.engine.console.command.ICommand;
import com.pg.subject.grade.GradeRegister;
import com.pg.person.student.Student;
import com.pg.person.student.StudentRegister;
import com.pg.subject.SubjectRegister;
import com.pg.engine.console.command.validator.NumberInputDataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
@Component
public class PrintStudentGradesCommand implements ICommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintStudentGradesCommand.class);
    private Student student;
    @Autowired
    private StudentRegister studentRegister;
    @Autowired
    private SubjectRegister subjectRegister;
    @Autowired
    private GradeRegister gradeRegister;
    @Autowired
    private NumberInputDataValidator numberValidator;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID studenta: ");
        String studentIdString = numberValidator.removeNonDigit(scanner.nextLine());
        int studentId = numberValidator.validateAndGetNumber(studentIdString);
        if (studentRegister.getStudents().containsKey(studentId)) {
            student = studentRegister.getStudents().get(studentId);
            Map<Integer, List<BigDecimal>>grades = student.getGrades();
            printGrades(grades);
        } else {
            LOGGER.info("Nie ma takiego studenta w bazie.");
        }
    }

    private void printGrades(Map<Integer,List<BigDecimal>> grades) {
        System.out.println(student.getFirstName() + " " + student.getLastName());
        int subjectID;
        for (Map.Entry<Integer, List<BigDecimal>> entry : grades.entrySet()) {
            subjectID = entry.getKey();
            System.out.println(subjectRegister.getSubjects().get(subjectID).getName() + ": ");
            System.out.print(entry.getValue() + "; średnia: ");
            String average = String.valueOf(gradeRegister.getAverage(entry.getValue(), subjectID));
            System.out.println(average);
            System.out.println();
        }
    }
}
