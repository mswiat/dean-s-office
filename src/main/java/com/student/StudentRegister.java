package com.student;

import com.DeanOfficeWriter;
import com.InfoProvider;
import com.Reader;
import com.student.Student;
import com.student.StudentReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//@Component
public class StudentRegister {
    //student register powinien byc beanem w springu
    //nie powinno się ustawiać list i map jako static -> wycieki pamięci
    //garbage collector nigdy nie usunie zmiennej statycznej

    //students -> Singleton !
    private final static List<Student> students = new ArrayList<>();
    private static String STUDENTS_FILE = "students.csv";

    static {
        File file = new File(STUDENTS_FILE);
        if (file.exists() && !file.isDirectory()) {
            InfoProvider infoProvider = new InfoProvider();
            Reader studentReader = new StudentReader();
            infoProvider.getInfo(STUDENTS_FILE, studentReader);
        }
    }

    public static void addStudent(Student student) {
        if (!students.contains(student)) {
            int id = students.size() + 1;
            student.setId(id);
            students.add(student);
            System.out.println("Dodano studenta do listy.");
            DeanOfficeWriter officeWriter = new DeanOfficeWriter();
            officeWriter.save(student);
        } else {
            System.out.println("Student istnieje już w bazie.");
        }
    }
    public static List<Student> getStudents() {
        return students;
    }
}
