package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.AttendanceDto;
import com.easyWay.Student_Management_System.Dto.FactListDto;
import com.easyWay.Student_Management_System.Dto.FactQualificationDto;
import com.easyWay.Student_Management_System.Dto.FacultyAttendanceRequestDto;
import com.easyWay.Student_Management_System.Entity.FacultyAttendance;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.FacultyAttendanceRepo;
import com.easyWay.Student_Management_System.Service.FacultyAttendanceService;
import com.easyWay.Student_Management_System.Utils.TimeUtils;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<FacultyAttendanceRequestDto> getAttendance(String from, String to) {
        LocalDateTime fromDate = TimeUtils.toStartOfDay(from);
        LocalDateTime toDate = TimeUtils.toEndOfDay(to);
        List<FacultyAttendance> savedData = repo.findAllByTimeBetween(fromDate, toDate);

        if (ObjectUtils.isEmpty(savedData)) {
            throw new BadRequestException("No record found");
        }

        List<FacultyAttendanceRequestDto> dtoList = new ArrayList<>();
        for (FacultyAttendance saved : savedData) {
            FacultyAttendanceRequestDto dto = new FacultyAttendanceRequestDto();
            dto.setDate(saved.getCreationDateTime().toString());
            Type attendanceListType = new TypeToken<List<FactListDto>>() {}.getType();
            List<FactListDto> data = gson.fromJson(saved.getFacultyList(), attendanceListType);
            dto.setFactList(data);
            dto.setId(saved.getId());
            dtoList.add(dto);
        }
        return dtoList;
    }

}
