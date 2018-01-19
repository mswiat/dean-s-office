package com.grade;

import java.math.BigDecimal;

public enum GradeEnum {
    BDB(new BigDecimal("5.0"), "bardzo dobry", "bdb"),
    DB(new BigDecimal("4.0"), "dobry", "db"),
    DST(new BigDecimal("3.0"), "dostateczny", "dst"),
    DOP(new BigDecimal("2.0"), "dopuszczalny", "dop");

    private BigDecimal gradeInNumber;
    private String fullGradeName;
    private String shortcut;

    GradeEnum(BigDecimal gradeInNumber, String fullGradeName, String shortcut) {
        this.gradeInNumber = gradeInNumber;
        this.fullGradeName = fullGradeName;
        this.shortcut = shortcut;
    }

    public BigDecimal getGradeInNumber() {
        return gradeInNumber;
    }

    public String getFullGradeName() {
        return fullGradeName;
    }

    public String getShortcut() {
        return shortcut;
    }
}
