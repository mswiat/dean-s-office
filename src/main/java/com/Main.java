package com;

import com.pg.engine.console.Console;
import com.pg.engine.IPostSpringInit;
import com.pg.subject.grade.GradeRegister;
import com.pg.person.student.StudentRegister;
import com.pg.subject.SubjectRegister;
import com.pg.person.teacher.TeacherRegister;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        initBean(context, StudentRegister.class);
        initBean(context, TeacherRegister.class);
        initBean(context, SubjectRegister.class);
        initBean(context, GradeRegister.class);
        Console console = context.getBean(Console.class);
        console.init();
        console.handleConsole();
    }

    private static <T extends IPostSpringInit> void initBean(ApplicationContext context, Class<T> beanClass) {
        T bean = context.getBean(beanClass);
        bean.init();
    }



}
