package com.easyWay.Student_Management_System.Enums;

public enum FileType {

    CSV(1,"csv"),
    EXCEL(2 , "excel");

    int index;
    String extension;

    FileType(int index, String extension) {
        this.index = index;
        this.extension = extension;
    }

}