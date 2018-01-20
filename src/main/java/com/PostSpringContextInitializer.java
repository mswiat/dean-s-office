package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PostSpringContextInitializer {

    @Autowired
    private Set<IPostSpringInit> toInit;

    public void initPostSprintContext() {
        for (IPostSpringInit init : toInit) {
            init.init();
        }
    }
}
