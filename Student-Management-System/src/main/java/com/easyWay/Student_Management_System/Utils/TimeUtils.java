package com.easyWay.Student_Management_System.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public static LocalDateTime toStartOfDay(String dateString) {
        LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
        return date.atStartOfDay();
    }

    public static LocalDateTime toEndOfDay(String dateString) {
        LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
        return date.atTime(23, 59, 59);
    }
}
