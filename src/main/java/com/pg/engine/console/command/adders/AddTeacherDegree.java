package com.pg.engine.console.command.adders;

import com.pg.engine.console.command.ICommand;
import com.pg.engine.files.DeanOfficeWriter;
import com.pg.person.teacher.Teacher;
import com.pg.person.teacher.TeacherRegister;
import com.pg.engine.console.command.validator.NumberInputDataValidator;
import com.pg.engine.console.command.validator.TextInputDataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

@Component
public class AddTeacherDegree implements ICommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddTeacherDegree.class);
    private static final String TEACHERS_CSV = "teachers.csv";

    @Autowired
    private TeacherRegister teacherRegister;

    @Autowired
    private NumberInputDataValidator numberValidator;

    @Autowired
    private TextInputDataValidator textValidator;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        validateInputDataAndAddTeacherDegree(scanner);
        saveChanges();
    }

    private void validateInputDataAndAddTeacherDegree(Scanner scanner) {
        System.out.println("Podaj ID nauczyciela, dla którego chcesz dodać tytuł naukowy: ");
        String teacherIdString = numberValidator.removeNonDigit(scanner.nextLine());
        int teacherId = numberValidator.validateAndGetNumber(teacherIdString);
        if (teacherIsInTeacherRegister(teacherId)) {
            System.out.println("Tytuł naukowy: ");
            String degree = scanner.nextLine();
            degree = textValidator.removeAllDigit(degree);
            if("".equals(degree)){
                degree = "b/d";
            }
            Teacher teacher = teacherRegister.getTeachers().get(teacherId);
            teacher.setDegree(degree);
            LOGGER.info("Dodano tytuł naukowy dla: " + teacher.getFirstName() + " " + teacher.getLastName());
        } else {
            LOGGER.info("Nie ma takiego nauczyciela w bazie.");
        }
    }

    private boolean teacherIsInTeacherRegister(int teacherId) {
        return teacherRegister.getTeachers().containsKey(teacherId);
    }

    private void saveChanges() {
        File file = new File(TEACHERS_CSV);
        if (file.exists()) {
            boolean delete = file.delete();
            if (!delete) {
                throw new IllegalStateException("Couldn't delete " + TEACHERS_CSV);
            }
        }
        DeanOfficeWriter deanOfficeWriter = new DeanOfficeWriter();
        deanOfficeWriter.save(teacherRegister.getTeachers());
    }
}
