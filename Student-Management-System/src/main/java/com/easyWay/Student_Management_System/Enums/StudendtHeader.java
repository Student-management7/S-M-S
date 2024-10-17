package com.easyWay.Student_Management_System.Enums;

import lombok.Getter;

@Getter
public enum StudendtHeader {
    NAME(0, "Name*"),
    ADDRESS(1, "Address*"),
    CITY(2, "City"),
    STATE(3, "State"),
    FATHER_NAME(4, "Father Name"),
    MOTHER_NAME(5, "Mother Name"),
    PRIMARY_CONTACT(6, "Primary Contact"),
    SECONDARY_CONTACT(7, "Secondary Contact"),
    FAMILY_ADDRESS(8, "Family Address"),
    FAMILY_CITY(9, "Family City"),
    FAMILY_STATE(10, "Family State"),
    FAMILY_EMAIL(11, "Family Email"),
    CONTACT(12, "Contact"),
    GENDER(13, "Gender"),
    DOB(14, "Date of Birth"),
    EMAIL(15, "Email"),
    CLASS(16, "Class"),
    DEPARTMENT(17, "Department"),
    CATEGORY(18, "Category");


    int index;
    String value;
    StudendtHeader(int index, String value) {
        this.index = index;
        this.value = value;
    }

}