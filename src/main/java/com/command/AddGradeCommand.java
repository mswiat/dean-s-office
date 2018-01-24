package com.command;

import com.grade.Grade;
import com.grade.GradeEnum;
import com.grade.GradeRegister;
import com.student.StudentRegister;
import com.subject.SubjectRegister;
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

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        validateInputDataAndAddGrade(scanner);
    }

    private void validateInputDataAndAddGrade(Scanner scanner) {
        int studentId;
        System.out.println("Podaj ID studenta:");
        studentId = validateAndGetId(scanner);
        if (studentIsInStudentRegister(studentId)) {
            System.out.println("Podaj ID przedmiotu:");
            validateSubjectIDAndEnumGradeAndAddGrade(scanner, studentId);
        } else {
            LOGGER.info("Błędne ID studenta");
        }
    }

    private void validateSubjectIDAndEnumGradeAndAddGrade(Scanner scanner, int studentId) {
        int subjectId;
        subjectId = validateAndGetId(scanner);
        if (subjectIsInSubjectRegister(subjectId)) {
            System.out.println("Podaj ocenę: BDB/DB/DST/DOP");
            validateGradeEnumAndAddGrade(scanner, studentId, subjectId);
        } else {
            LOGGER.info("Błędne ID przedmiotu");
        }
    }

    private void validateGradeEnumAndAddGrade(Scanner scanner, int studentId, int subjectId) {
        boolean isGradeEnumValid = false;
        String gradeString;
        gradeString = getGradeString(scanner);
        for (GradeEnum gradeEnum : GradeEnum.values()) {
            if (gradeEnum.toString().equals(gradeString)) {
                gradeRegister.addGrade(new Grade(studentId, subjectId, GradeEnum.valueOf(gradeString)));
                isGradeEnumValid = true;
            }
        }
        if (!isGradeEnumValid) {
            LOGGER.info("Błędnie wpisana ocena.");
        }
    }

    private boolean studentIsInStudentRegister(int studentId) {
        return studentRegister.getStudents().containsKey(Integer.valueOf(studentId));
    }

    private boolean subjectIsInSubjectRegister(int subjectId) {
        return subjectRegister.getSubjects().containsKey(subjectId);
    }

    int validateAndGetId(Scanner scanner) {
        int id;
        String input = scanner.nextLine();
        input = input.replaceAll("\\D*", "");
        if ("".equals(input)) {
            id = 0;
        } else {
            id = Integer.valueOf(input);
        }
        return id;
    }

    String getGradeString(Scanner scanner) {
        return scanner.nextLine();
    }
}
