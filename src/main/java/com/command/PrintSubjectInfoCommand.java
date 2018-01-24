package com.command;

import com.subject.Subject;
import com.subject.SubjectRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PrintSubjectInfoCommand implements ICommand {

    @Autowired
    private SubjectRegister subjectRegister;

    @Override
    public void execute() {
        for (Map.Entry<Integer, Subject> entry : subjectRegister.getSubjects().entrySet()) {
            entry.getValue().info();
            System.out.println();
        }
    }
}
