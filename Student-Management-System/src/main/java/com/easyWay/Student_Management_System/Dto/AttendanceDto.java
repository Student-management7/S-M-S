package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AttendanceDto {

    public UUID stdId;
    public String attendance;

    public UUID getStdId() {
        return stdId;
    }

    public void setStdId(UUID stdId) {
        this.stdId = stdId;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}
