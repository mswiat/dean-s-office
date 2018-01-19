package com.student;

import com.DeanOfficeWriter;
import com.InfoProvider;
import com.IReader;

import java.io.File;
import java.util.*;

//@Component
public class StudentRegister {
    //student register powinien byc beanem w springu
    //nie powinno się ustawiać list i map jako static -> wycieki pamięci
    //garbage collector nigdy nie usunie zmiennej statycznej

    //students -> Singleton !
    private final static Map<Integer, Student> students = new HashMap<>();
    private static String STUDENTS_FILE = "students.csv";

    static {
        File file = new File(STUDENTS_FILE);
        if (file.exists() && !file.isDirectory()) {
            InfoProvider infoProvider = new InfoProvider();
            IReader studentReader = new StudentReader();
            infoProvider.getInfo(STUDENTS_FILE, studentReader);
        }
    }

    public static void addStudent(Student student) {
        if (!students.containsKey(student.getId())) {
            int id = getNextId();
            student.setId(id);
            students.put(id, student);
            System.out.println("Dodano studenta do listy.");
            DeanOfficeWriter officeWriter = new DeanOfficeWriter();
            officeWriter.save(student);
        } else {
            System.out.println("Student istnieje już w bazie.");
        }
    }

    private static int getNextId() {
        Set<Integer> ids = students.keySet();
        int nextID = students.size();
        for (Integer id : ids) {
            if (nextID < id) {
                nextID = id;
            }
        }
        return nextID + 1;
    }

    public static Map<Integer, Student> getStudents() {
        return students;
    }
}
