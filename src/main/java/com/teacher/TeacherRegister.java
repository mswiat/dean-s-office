package com.teacher;

import com.DeanOfficeWriter;
import com.InfoProvider;
import com.Reader;
import com.teacher.Teacher;
import com.teacher.TeacherReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//@Component
public class TeacherRegister {
    private final static List<Teacher> teachers = new ArrayList<>();
    private static String TEACHERS_FILE = "teachers.csv";

    static {
        File file = new File(TEACHERS_FILE);
        if (file.exists() && !file.isDirectory()) {
            InfoProvider infoProvider = new InfoProvider();
            Reader teacherReader = new TeacherReader();
            infoProvider.getInfo(TEACHERS_FILE, teacherReader);
        }
    }

    public static void addTeacher(Teacher teacher) {
        if (!teachers.contains(teacher)) {
            int id = teachers.size() + 1;
            teacher.setId(id);
            teachers.add(teacher);
            System.out.println("Dodano nauczyciela do listy.");
            DeanOfficeWriter officeWriter = new DeanOfficeWriter();
            officeWriter.save(teacher);
        }
        else{
            System.out.println("Nauczyciel istnieje już w bazie.");
        }
    }
    public static List<Teacher> getTeachers() {
        return teachers;
    }
}
