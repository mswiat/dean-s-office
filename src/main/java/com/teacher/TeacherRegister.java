package com.teacher;

import com.DeanOfficeWriter;
import com.InfoProvider;
import com.IReader;

import java.io.File;
import java.util.*;

//@Component
public class TeacherRegister {
    private final static Map<Integer, Teacher> teachers = new HashMap<>();
    private static String TEACHERS_FILE = "teachers.csv";

    static {
        File file = new File(TEACHERS_FILE);
        if (file.exists() && !file.isDirectory()) {
            InfoProvider infoProvider = new InfoProvider();
            IReader teacherReader = new TeacherReader();
            infoProvider.getInfo(TEACHERS_FILE, teacherReader);
        }
    }

    public static void addTeacher(Teacher teacher) {
        if (!teachers.containsKey(teacher.getId())) {
            int id = getNextId();
            teacher.setId(id);
            teachers.put(id, teacher);
            System.out.println("Dodano nauczyciela do listy.");
            DeanOfficeWriter officeWriter = new DeanOfficeWriter();
            officeWriter.save(teacher);
        }
        else{
            System.out.println("Nauczyciel istnieje już w bazie.");
        }
    }

    private static int getNextId() {
        Set<Integer> ids = teachers.keySet();
        int nextID = teachers.size();
        for (Integer id : ids) {
            if (nextID < id) {
                nextID = id;
            }
        }
        return nextID + 1;
    }

    public static Map<Integer, Teacher> getTeachers() {
        return teachers;
    }
}
