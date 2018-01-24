package com.teacher;

import org.junit.Assert;
import org.junit.Test;

public class TeacherTest {

    @Test
    public void shouldReturnTheSameDataToSave(){
        //given
        Teacher teacher = new Teacher("Alicja", "Nowak", 92081210788L);
        teacher.setId(1);
        //when
        String result = teacher.getDataToSave();
        //then
        Assert.assertEquals("1,Alicja,Nowak,b/d,92081210788", result);
    }
    @Test
    public void shouldReturnTeacherFile(){
        //given
        Teacher teacher = new Teacher("Alicja", "Nowak", 92081210788L);
        //when
        String result = teacher.getFileName();
        //then
        Assert.assertEquals("teachers.csv", result);
    }
    @Test
    public void shouldReturnbrakDanych(){
        //given
        Teacher teacher = new Teacher("Alicja", "Nowak", 92081210788L);
        //when
        String result = teacher.getDegree();
        //then
        Assert.assertEquals("b/d",result);
    }
    @Test
    public void shouldReturnMgr(){
        //given
        Teacher teacher = new Teacher("Alicja", "Nowak", 92081210788L);
        teacher.setDegree("mgr");
        //when
        String result = teacher.getDegree();
        //then
        Assert.assertEquals("mgr",result);
    }
    @Test
    public void shouldReturnId54(){
        //given
        Teacher teacher = new Teacher("Alicja", "Nowak", 92081210788L);
        teacher.setId(54);
        //when
        int result = teacher.getId();
        //then
        Assert.assertEquals(54, result);
    }

}