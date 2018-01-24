package com.teacher;

import com.DeanOfficeWriter;
import com.IPostSpringInit;
import com.InfoProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class TeacherRegister implements IPostSpringInit {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherRegister.class);
    private final Map<Integer, Teacher> teachers = new HashMap<>();
    private static String TEACHERS_FILE = "teachers.csv";
    @Autowired
    private TeacherReader teacherReader;
    @Autowired
    private InfoProvider infoProvider;

    @Override
    public void init() {
        File file = new File(TEACHERS_FILE);
        if (file.exists() && !file.isDirectory()) {
            infoProvider.getInfo(TEACHERS_FILE, teacherReader);
        }
    }

    public void addTeacher(Teacher teacher) {
        if (!teachers.containsKey(teacher.getId())) {
            int id = getNextId();
            teacher.setId(id);
            teachers.put(id, teacher);
            LOGGER.info("Dodano nauczyciela do listy.");
            DeanOfficeWriter officeWriter = new DeanOfficeWriter();
            officeWriter.save(teacher);
        } else {
            LOGGER.info("Nauczyciel istnieje już w bazie.");
        }
    }

    private int getNextId() {
        Set<Integer> ids = teachers.keySet();
        int nextID = teachers.size();
        for (Integer id : ids) {
            if (nextID < id) {
                nextID = id;
            }
        }
        return nextID + 1;
    }

    public Map<Integer, Teacher> getTeachers() {
        return teachers;
    }
}
