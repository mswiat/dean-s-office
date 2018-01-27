package com.pg.engine.console.command.printers;

import com.pg.engine.console.command.ICommand;
import com.pg.subject.grade.GradeRegister;
import com.pg.person.student.Student;
import com.pg.person.student.StudentRegister;
import com.pg.subject.Subject;
import com.pg.subject.SubjectRegister;
import com.pg.engine.console.command.validator.NumberInputDataValidator;
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
public class PrintSubjectGradesCommand implements ICommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintStudentGradesCommand.class);
    private Map<Integer, List<BigDecimal>> grades = new HashMap<>();
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
        System.out.println("Podaj ID przedmiotu: ");
        String subjectIdString = numberValidator.removeNonDigit(scanner.nextLine());
        int subjectId = numberValidator.validateAndGetNumber(subjectIdString);
        Map<Integer, Subject> subjects = subjectRegister.getSubjects();
        if (subjects.containsKey(subjectId)) {
            printGrades(subjectId, subjects);
        } else {
            LOGGER.info("Nie ma takiego przedmiotu w bazie.");
        }
    }

    private void printGrades(int subjectId, Map<Integer, Subject> subjects) {
        System.out.println(subjects.get(subjectId).getName());
        Map<Integer, Student> students = studentRegister.getStudents();
        for (Map.Entry<Integer, Student> entry : students.entrySet()) {
            Student student = entry.getValue();
            Map<Integer, List<BigDecimal>> grades = student.getGrades();
            if (grades.containsKey(subjectId)) {
                System.out.print(student.getFirstName() + " " + student.getLastName() + " " + grades.get(subjectId));
                System.out.print("; średnia: ");
                //BigDecimal average = gradeRegister.getAverage(grades.get(subjectId));
                double average = gradeRegister.getAverage(grades.get(subjectId), subjectId);
                System.out.println(average);
            }
        }
    }
}
