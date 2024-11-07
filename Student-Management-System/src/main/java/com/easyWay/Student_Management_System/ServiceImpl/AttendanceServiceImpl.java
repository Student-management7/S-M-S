package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.AttendanceDto;
import com.easyWay.Student_Management_System.Dto.AttendanceRequestDto;
import com.easyWay.Student_Management_System.Dto.AttendanceResponseDto;
import com.easyWay.Student_Management_System.Entity.StudentAttendance;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.AttendanceInfoRepo;
import com.easyWay.Student_Management_System.Service.AttendanceService;
import com.easyWay.Student_Management_System.Utils.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceInfoRepo attendanceInfoRepo;

    @Autowired
    Gson gson;

    @Override
    public String saveAttendances(AttendanceRequestDto details) {
            StudentAttendance studentAttendance = new StudentAttendance();
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime from = TimeUtils.toStartOfDay(date.format(formatter));
            LocalDateTime to = TimeUtils.toEndOfDay(date.format(formatter));
            List<StudentAttendance> savedData = attendanceInfoRepo.findByClassAndSubject(details.getClassName() ,
                    details.getSubject() ,from ,to);
            if (!ObjectUtils.isEmpty(savedData)){
                throw new BadRequestException("Attendance is already present");
            }
            convertDtoToEntity (details,studentAttendance);
            attendanceInfoRepo.save(studentAttendance);
            return "Saved Successfully" ;
    }

    @Override
    public List<AttendanceResponseDto> getAttendances(String cls, String subject, String fromDate, String toDate) {
        LocalDateTime from = TimeUtils.toStartOfDay(fromDate);
        LocalDateTime to = TimeUtils.toEndOfDay(toDate);
        List<StudentAttendance> savedStudent = attendanceInfoRepo.findByClassAndSubject(cls, subject, from, to);
        if(ObjectUtils.isEmpty(savedStudent)){
            throw new BadRequestException("No data found for the given period");
        }
        List<AttendanceResponseDto> resultList = new ArrayList<>();
        for (StudentAttendance attendance : savedStudent){
            AttendanceResponseDto attend = new AttendanceResponseDto();
            attend.setDate(attendance.getCreationDateTime());
            Type attendanceListType = new TypeToken<List<AttendanceDto>>() {}.getType();
            List<AttendanceDto> data = gson.fromJson(attendance.getStudentList(), attendanceListType);
            attend.setStudents(data);
            log.info("Student {}", data);
            resultList.add(attend);

        }
        return resultList;
    }

    private void convertDtoToEntity(AttendanceRequestDto dto , StudentAttendance entity ){

        if(StringUtil.isBlank(dto.getClassName())){
            throw new BadRequestException("Class Can't be blank");
        }

        if(StringUtil.isBlank(dto.getSubject())){
            throw new BadRequestException("Subject Can't be blank");
        }

        entity.setClassName(dto.getClassName());
        entity.setSubject(dto.getSubject());
        entity.setStudentList(gson.toJson(dto.getStudentList()));
    }

}

