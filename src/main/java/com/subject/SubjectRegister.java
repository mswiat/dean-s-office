package com.subject;

import com.DeanOfficeWriter;
import com.InfoProvider;
import com.Reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SubjectRegister {
    private final static List<Subject> subjects = new ArrayList<>();
    private static String SUBJECTS_FILE = "subjects.csv";

    static {
        File file = new File(SUBJECTS_FILE);
        if (file.exists() && !file.isDirectory()) {
            InfoProvider infoProvider = new InfoProvider();
            Reader subjectReader = new SubjectReader();
            infoProvider.getInfo(SUBJECTS_FILE, subjectReader);
        }
    }

    public static void addSubject(Subject subject) {
        if (!subjects.contains(subject)) {
            subjects.add(subject);
            System.out.println("Dodano przedmiot do listy.");
            DeanOfficeWriter officeWriter = new DeanOfficeWriter();
            officeWriter.save(subject);
        }
        else{
            System.out.println("Przedmiot istnieje już w bazie.");
        }
    }

    public static List<Subject> getSubjects() {
        return subjects;
    }
}
