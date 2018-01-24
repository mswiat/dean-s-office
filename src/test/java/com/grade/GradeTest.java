package com.grade;

import org.junit.Assert;
import org.junit.Test;


public class GradeTest {

    @Test
    public void shouldReturnGradeFile(){
        //given
        Grade grade = new Grade(1,1,GradeEnum.BDB);
        //when
        String result = grade.getFileName();
        //then
        Assert.assertEquals("grades.csv", result);
    }
    @Test
    public void shouldReturnTheSameDataToSave(){
        //given
        Grade grade = new Grade(1,1,GradeEnum.BDB);
        //when
        String result = grade.getDataToSave();
        //then
        Assert.assertEquals("1,1,BDB", result);
    }

}