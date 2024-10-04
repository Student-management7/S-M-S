package com.easyWay.Student_Management_System.Enums;

import lombok.Getter;

@Getter
public enum StudendtHeader {
    NAME(1, "Name"),
    ADDRESS(3, "Address"),
    CITY(4, "City"),
    STATE(5, "State");

    int index;
    String value;
    StudendtHeader(int index, String value) {
        this.index = index;
        this.value = value;
    }

}
