package com.command;

import com.grade.Grade;
import com.grade.GradeEnum;
import com.grade.GradeRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddGradeCommand implements ICommand {
    @Autowired
    private GradeRegister gradeRegister;

    @Override
    public void execute() {
        int studentId;
        int subjectId;
        String grade;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID studenta:");
        studentId = Integer.valueOf(scanner.nextLine());
        System.out.println("Podaj ID przedmiotu:");
        subjectId = Integer.valueOf(scanner.nextLine());
        System.out.println("Podaj ocenę: BDB/DB/DST/DOP");
        grade = scanner.nextLine();
        gradeRegister.addGrade(new Grade(studentId,subjectId, GradeEnum.valueOf(grade)));
    }
}
