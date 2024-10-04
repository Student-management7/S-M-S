package com.easyWay.Student_Management_System.Enums;

import lombok.Getter;

@Getter
public enum StudendtHeader {
    NAME(0, "Name*"),
    ADDRESS(1, "Address*"),
    CITY(2, "City"),
    STATE(3, "State"),
    FATHER(4 ,"Father Name");


    int index;
    String value;
    StudendtHeader(int index, String value) {
        this.index = index;
        this.value = value;
    }

}
