package com.pg.engine.console.command.adders;

import com.pg.engine.console.command.ICommand;
import com.pg.subject.grade.Grade;
import com.pg.subject.grade.GradeEnum;
import com.pg.subject.grade.GradeReader;
import com.pg.subject.grade.GradeRegister;
import com.pg.person.student.StudentRegister;
import com.pg.subject.SubjectRegister;
import com.pg.engine.console.command.validator.NumberInputDataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddGradeCommand implements ICommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddGradeCommand.class);

    @Autowired
    private GradeRegister gradeRegister;

    @Autowired
    private StudentRegister studentRegister;

    @Autowired
    private SubjectRegister subjectRegister;

    @Autowired
    private NumberInputDataValidator numberValidator;

    @Autowired
    private GradeReader gradeReader;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        validateInputDataAndAddGrade(scanner);
    }

    private void validateInputDataAndAddGrade(Scanner scanner) {
        System.out.println("Podaj ID studenta:");
        String studentIdString = numberValidator.removeNonDigit(scanner.nextLine());
        int studentId = numberValidator.validateAndGetNumber(studentIdString);
        if (studentIsInStudentRegister(studentId)) {
            System.out.println("Podaj ID przedmiotu:");
            validateSubjectIDAndEnumGradeAndAddGrade(scanner, studentId);
        } else {
            LOGGER.info("Błędne ID studenta");
        }
    }

    private void validateSubjectIDAndEnumGradeAndAddGrade(Scanner scanner, int studentId) {
        String subjectIdString = numberValidator.removeNonDigit(scanner.nextLine());
        int subjectId = numberValidator.validateAndGetNumber(subjectIdString);
        if (subjectIsInSubjectRegister(subjectId)) {
            System.out.println("Podaj ocenę: BDB/DB/DST/DOP");
            validateGradeEnumAndAddGrade(scanner, studentId, subjectId);
        } else {
            LOGGER.info("Błędne ID przedmiotu");
        }
    }

    private void validateGradeEnumAndAddGrade(Scanner scanner, int studentId, int subjectId) {
        String gradeString = getGradeString(scanner);
        try {
            GradeEnum gradeEnum = GradeEnum.valueOf(gradeString);
            gradeRegister.addGrade(new Grade(studentId, subjectId, gradeEnum));
            studentRegister.getStudents().get(studentId).getGrades().get(subjectId).add(gradeEnum.getGradeInNumber());
            gradeReader.getGradesStatistics().add(gradeEnum.getGradeInNumber());
        } catch (IllegalArgumentException e) {
            LOGGER.info("Błędnie wpisana ocena.");
        }
    }

    private boolean studentIsInStudentRegister(int studentId) {
        return studentRegister.getStudents().containsKey(Integer.valueOf(studentId));
    }

    private boolean subjectIsInSubjectRegister(int subjectId) {
        return subjectRegister.getSubjects().containsKey(subjectId);
    }

    private String getGradeString(Scanner scanner) {
        return scanner.nextLine();
    }
}
