package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.FacultyAttendanceRequestDto;
import com.easyWay.Student_Management_System.Entity.FacultyAttendance;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.FacultyAttendanceRepo;
import com.easyWay.Student_Management_System.Service.FacultyAttendanceService;
import com.easyWay.Student_Management_System.Utils.TimeUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FacultyAttendanceServiceImpl implements FacultyAttendanceService {

    @Autowired
    Gson gson;

    @Autowired
    FacultyAttendanceRepo repo;

    @Override
    public String saveAttendance(FacultyAttendanceRequestDto dto) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime from = TimeUtils.toStartOfDay(date.format(formatter));
        LocalDateTime to = TimeUtils.toEndOfDay(date.format(formatter));
        FacultyAttendance savedData = repo.findByTime(from, to);

        if(!ObjectUtils.isEmpty(savedData)){
            throw new BadRequestException("Attendance already present");
        }

        FacultyAttendance entity = new FacultyAttendance();
        entity.setFacultyList(gson.toJson(dto.getFactList()));
        repo.save(entity);
        return "Saved Successfully";
    }

    @Override
    public String editAttendance(FacultyAttendanceRequestDto dto) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime from = TimeUtils.toStartOfDay(date.format(formatter));
        LocalDateTime to = TimeUtils.toEndOfDay(date.format(formatter));
        FacultyAttendance savedData = repo.findByTime(from, to);

        if(ObjectUtils.isEmpty(savedData)){
            throw  new BadRequestException("No record found");
        }

        savedData.setFacultyList(gson.toJson(dto.getFactList()));
        repo.save(savedData);
        return "Updated successfully";
    }
}
