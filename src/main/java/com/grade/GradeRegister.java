package com.grade;

import com.DeanOfficeWriter;
import com.IReader;
import com.InfoProvider;

import java.io.File;

public class GradeRegister {

    public static void addGrade(Grade grade) {
        DeanOfficeWriter officeWriter = new DeanOfficeWriter();
        officeWriter.save(grade);
        System.out.println("Dodano ocenę");
    }
}
