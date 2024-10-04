package com.easyWay.Student_Management_System.Enums;

public enum FileStatus {
    IN_PROGRESS(0,"In_Progress"),
    UPLOADED(1,"Uploaded"),
    PROCESSED(2,"processed");;

    int index;
    String status;

    FileStatus(int index, String status) {
        this.index = index;
        this.status = status;
    }
}
