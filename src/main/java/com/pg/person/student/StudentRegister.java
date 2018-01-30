package com.pg.person.student;

import com.pg.engine.files.InfoProvider;
import com.pg.engine.files.DeanOfficeWriter;
import com.pg.engine.IPostSpringInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class StudentRegister implements IPostSpringInit {

    private static final Logger logger = LoggerFactory.getLogger(StudentRegister.class);
    private final Map<Integer, Student> students = new HashMap<>();
    private static String STUDENTS_FILE = "students.csv";
    @Autowired
    private StudentReader studentReader;
    @Autowired
    private InfoProvider infoProvider;

    @Override
    public void init() {
        File file = new File(STUDENTS_FILE);
        if (file.exists() && !file.isDirectory()) {
            infoProvider.readData(STUDENTS_FILE, studentReader);
        }
    }

    public void addStudent(Student student) {
        if (!students.containsKey(student.getId())) {
            int id = getNextId();
            student.setId(id);
            students.put(id, student);
            logger.info("Dodano studenta do listy.");
            DeanOfficeWriter officeWriter = new DeanOfficeWriter();
            officeWriter.save(student);
        } else {
            logger.info("Student istnieje już w bazie.");
        }
    }

    private int getNextId() {
        Set<Integer> ids = students.keySet();
        int nextID = students.size();
        for (Integer id : ids) {
            if (nextID < id) {
                nextID = id;
            }
        }
        return nextID + 1;
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }
}
