package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.AttendanceRequestDto;
import com.easyWay.Student_Management_System.Dto.AttendanceResponseDto;
import com.easyWay.Student_Management_System.Dto.DetailAttendanceDto;
import com.easyWay.Student_Management_System.Entity.StudentAttendance;
import com.easyWay.Student_Management_System.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController

@RequestMapping("/attendance")
public class AttendanceController {

   @Autowired
    AttendanceService attendanceService;


    @PostMapping("/save")
    public String saveAttendances(@RequestBody AttendanceRequestDto details, @RequestParam boolean masterAttendance) {
         return attendanceService.saveAttendances(details, masterAttendance);

    }

    @PostMapping("/getAttendance")
    public List<AttendanceResponseDto> getAttendances(@RequestParam String cls, @RequestParam String fromDate,
                                                      @RequestParam String toDate, @RequestParam String subject
            , @RequestParam boolean masterAttendance) {

        return attendanceService.getAttendances(cls, subject, fromDate, toDate, masterAttendance);

    }

    @PostMapping("/update")
    public String attendanceUpdate(@RequestBody AttendanceRequestDto details , @RequestParam boolean masterAttendance){
        return attendanceService.attendanceUpdate(details ,masterAttendance);
    }

    @GetMapping("/detail")
    public DetailAttendanceDto detailAttendance(@RequestParam UUID id, @RequestParam String startDate,
                                                @RequestParam String endDate, @RequestParam String cls,
                                                @RequestParam String subject, @RequestParam boolean masterAttendance){
        return attendanceService.detailAttendance(id, startDate, endDate, cls, subject, masterAttendance);

    }

}
