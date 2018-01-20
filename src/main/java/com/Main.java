package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        PostSpringContextInitializer initializer = context.getBean(PostSpringContextInitializer.class);
        initializer.initPostSprintContext();
        Console console = context.getBean(Console.class);
        console.init();
        console.handleConsole();
    }
}
