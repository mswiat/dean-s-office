package com.pg.person.student;

public enum StudentStatus {
    ACTIVE("Aktywny", "Active"),
    REMOVED("Skreślony", "Removed"),
    ON_BREAK("Urlopowany", "On break");

    private String valuePL;
    private String valueEN;

    StudentStatus(String valuePL, String valueEN) {
        this.valuePL = valuePL;
        this.valueEN = valueEN;
    }

    public String getValuePL() {
        return valuePL;
    }

    public String getValueEN() {
        return valueEN;
    }

}
