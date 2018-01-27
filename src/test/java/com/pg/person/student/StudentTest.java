package com.pg.person.student;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentTest {

    @Test
    public void shouldReturnAlbumNumber123(){
        //given
        Student student = new Student("Maria", "Swiatkowska", "123", 92081210788L);
        //when
        String result = student.getAlbumNumber();
        //then
        Assert.assertEquals("123", result);
    }

    @Test
    public  void shouldReturnActiveStatus(){
        //given
        Student student = new Student("Maria", "Swiatkowska", "123", 92081210788L);
        //when
        StudentStatus result = student.getStatus();
        //then
        Assert.assertEquals(StudentStatus.ACTIVE, result);
    }
    @Test
    public void shouldReturnChangedStatusFromAvtiveToRemoved(){
        //given
        Student student = new Student("Maria", "Swiatkowska", "123", 92081210788L);
        student.setStatus(StudentStatus.REMOVED);
        //when
        StudentStatus result = student.getStatus();
        //then
        Assert.assertEquals(StudentStatus.REMOVED, result);
    }
    @Test
    public void shouldReturnTwoGrades5And4(){
        //given
        Student student = new Student("Maria", "Swiatkowska", "123", 92081210788L);
        List<BigDecimal>grades = new ArrayList<>();
        grades.add(new BigDecimal("5.0"));
        grades.add(new BigDecimal("4.0"));
        Map<Integer, List<BigDecimal>> gradesMap = new HashMap<>();
        gradesMap.put(1, grades);
        student.setGrades(gradesMap);
        //when
        Map<Integer, List<BigDecimal>> result = student.getGrades();
        //then
        Assert.assertEquals(gradesMap, result);
    }
    @Test
    public void shouldReturnId1(){
        //given
        Student student = new Student("Maria", "Swiatkowska", "123", 92081210788L);
        student.setId(1);
        //when
        int result = student.getId();
        //then
        Assert.assertEquals(1, result);
    }
    @Test
    public void shouldReturnTheSameDataToSave(){
        //given
        Student student = new Student("Maria", "Swiatkowska", "123", 92081210788L);
        student.setId(1);
        //when
        String result = student.getDataToSave();
        //then
        Assert.assertEquals("1,Maria,Swiatkowska,123,92081210788,ACTIVE", result);
    }
    @Test
    public void shouldReturnStudentFile(){
        //given
        Student student = new Student("Maria", "Swiatkowska", "123", 92081210788L);
        //when
        String result = student.getFileName();
        //then
        Assert.assertEquals("students.csv", result);
    }

}