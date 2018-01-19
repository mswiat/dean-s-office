package com.subject;

import com.DeanOfficeWriter;
import com.InfoProvider;
import com.IReader;

import java.io.File;
import java.util.*;

public class SubjectRegister {
    private final static Map<Integer, Subject> subjects = new HashMap<>();
    private static String SUBJECTS_FILE = "subjects.csv";

    static {
        File file = new File(SUBJECTS_FILE);
        if (file.exists() && !file.isDirectory()) {
            InfoProvider infoProvider = new InfoProvider();
            IReader subjectReader = new SubjectReader();
            infoProvider.getInfo(SUBJECTS_FILE, subjectReader);
        }
    }

    public static void addSubject(Subject subject) {
        if (!subjects.containsKey(subject)) {
            int id = getNextId();
            subject.setId(id);
            subjects.put(id, subject);
            System.out.println("Dodano przedmiot do listy.");
            DeanOfficeWriter officeWriter = new DeanOfficeWriter();
            officeWriter.save(subject);
        } else {
            System.out.println("Przedmiot istnieje już w bazie.");
        }
    }

    private static int getNextId() {
        Set<Integer> ids = subjects.keySet();
        int nextID = subjects.size();
        for (Integer id : ids) {
            if (nextID < id) {
                nextID = id;
            }
        }
        return nextID + 1;
    }

    public static Map<Integer, Subject> getSubjects() {
        return subjects;
    }
}
