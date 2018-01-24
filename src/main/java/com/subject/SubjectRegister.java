package com.subject;

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
public class SubjectRegister implements IPostSpringInit {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectRegister.class);
    private final Map<Integer, Subject> subjects = new HashMap<>();
    private static String SUBJECTS_FILE = "subjects.csv";
    @Autowired
    private InfoProvider infoProvider;
    @Autowired
    private SubjectReader subjectReader;

    @Override
    public void init() {
        File file = new File(SUBJECTS_FILE);
        if (file.exists() && !file.isDirectory()) {
            infoProvider.getInfo(SUBJECTS_FILE, subjectReader);
        }
    }

    public void addSubject(Subject subject) {
        if (!subjects.containsKey(subject)) {
            int id = getNextId();
            subject.setId(id);
            subjects.put(id, subject);
            LOGGER.info("Dodano przedmiot do listy.");
            DeanOfficeWriter officeWriter = new DeanOfficeWriter();
            officeWriter.save(subject);
        } else {
            LOGGER.info("Przedmiot istnieje już w bazie.");
        }
    }

    private int getNextId() {
        Set<Integer> ids = subjects.keySet();
        int nextID = subjects.size();
        for (Integer id : ids) {
            if (nextID < id) {
                nextID = id;
            }
        }
        return nextID + 1;
    }

    public Map<Integer, Subject> getSubjects() {
        return subjects;
    }
}
