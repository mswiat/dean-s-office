package com.pg.subject;

import org.junit.Assert;
import org.junit.Test;

public class SubjectTest {

    @Test
    public void shouldReturnSubjectFile(){
        //given
        Subject subject = new Subject("chemia", "I", 5, "test");
        //when
        String result = subject.getFileName();
        //then
        Assert.assertEquals("subjects.csv", result);
    }
    @Test
    public void shouldReturnTheSameDataToSave(){
        //given
        Subject subject = new Subject("chemia", "I", 5, "test");
        subject.setId(1);
        //when
        String result = subject.getDataToSave();
        //then
        Assert.assertEquals("1,chemia,I,5,test", result);
    }
    @Test
    public void shouldReturnNameChemia(){
        //given
        Subject subject = new Subject("chemia", "I", 5, "test");
        //when
        String result = subject.getName();
        //then
        Assert.assertEquals("chemia", result);
    }
    @Test
    public void shouldReturnId1(){
        //given
        Subject subject = new Subject("chemia", "I", 5, "test");
        subject.setId(1);
        //when
        int result = subject.getId();
        //then
        Assert.assertEquals(1, result);
    }

}