package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.AttendanceDto;
import com.easyWay.Student_Management_System.Dto.AttendanceRequestDto;
import com.easyWay.Student_Management_System.Entity.StudentAttendance;
import com.easyWay.Student_Management_System.Repo.AttendanceInfoRepo;
import com.easyWay.Student_Management_System.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceInfoRepo attendanceInfoRepo;
    @Override
    public String saveAttendances(AttendanceRequestDto details) {

        for (AttendanceDto attendance: details.getStudentList()){
            StudentAttendance studentAttendance = new StudentAttendance();
            convertDtoToEntity (details,studentAttendance);
            studentAttendance.setAttendance(attendance.getAttendance());
            studentAttendance.setStdId(attendance.getStdId());
            attendanceInfoRepo.save(studentAttendance);
        }
        return "Saved Successfully" ;
    }

    private void convertDtoToEntity(AttendanceRequestDto dto , StudentAttendance entity ){

        entity.setStdId(UUID.randomUUID());
        entity.setClassName(dto.className);
        entity.setSubject(dto.subject);


    }
}
