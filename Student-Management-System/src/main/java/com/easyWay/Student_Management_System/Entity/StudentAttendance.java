package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
public class StudentAttendance {
    @Id
    private  UUID stdId;

    private String className;
    private String subject;

    private String attendance;

    private LocalDateTime creationDateTime = LocalDateTime.now();

    @Override
    public String toString() {
        return "StudentAttendance{" +
                "stdId=" + stdId +
                ", className='" + className + '\'' +
                ", subject='" + subject + '\'' +
                ", attendance='" + attendance + '\'' +
                '}';
    }

    public UUID getStdId() {
        return stdId;
    }

    public void setStdId(UUID stdId) {
        this.stdId = stdId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}
