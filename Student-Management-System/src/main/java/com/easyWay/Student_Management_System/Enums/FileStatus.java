package com.easyWay.Student_Management_System.Enums;

public enum FileStatus {
    IN_PROGRESS(0,"In Progress"),
    UPLOADED(1,"Uploaded"),
    PROCESSED(2,"Processed"),
    PROCESSEDWITHERROR(3,"Processed With Error"),
    ERROR(4,"Error");


    int index;
    String status;

    FileStatus(int index, String status) {
        this.index = index;
        this.status = status;
    }
}