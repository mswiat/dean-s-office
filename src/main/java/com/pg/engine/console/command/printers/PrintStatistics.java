package com.pg.engine.console.command.printers;

import com.pg.engine.console.command.ICommand;
import com.pg.person.student.StudentRegister;
import com.pg.person.teacher.TeacherRegister;
import com.pg.subject.SubjectRegister;
import com.pg.subject.grade.GradeReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrintStatistics implements ICommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintStatistics.class);

    @Autowired
    private StudentRegister studentRegister;
    @Autowired
    private TeacherRegister teacherRegister;
    @Autowired
    private SubjectRegister subjectRegister;
    @Autowired
    private GradeReader gradeReader;


    @Override
    public void execute() {
        int studentNumber = studentRegister.getStudents().keySet().size();
        System.out.println("Liczba studentów: " + studentNumber);
        int teacherNumber = teacherRegister.getTeachers().keySet().size();
        System.out.println("Liczba nauczycieli: " + teacherNumber);
        int subjectNumberr = subjectRegister.getSubjects().keySet().size();
        System.out.println("Liczba przedmiotów: " + subjectNumberr);
        int gradesNumber = gradeReader.getGradesStatistics().size();
        System.out.println("Liczba wystawionych ocen: " + gradesNumber);
    }
}
