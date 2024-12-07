package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.DeductionDto;
import com.easyWay.Student_Management_System.Dto.FacultySalaryDto;
import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import com.easyWay.Student_Management_System.Entity.FacultySalaryEntity;
import com.easyWay.Student_Management_System.Repo.FacultyInfoRepo;
import com.easyWay.Student_Management_System.Repo.FacultySalaryRepo;
import com.easyWay.Student_Management_System.Service.FacultySalaryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultySalaryServiceImpl implements FacultySalaryService {

    @Autowired
    Gson gson;
    
    @Autowired
    FacultySalaryRepo facultySalaryRepo;

    @Autowired
    FacultyInfoRepo facultyInfoRepo;

    @Override
    public String saveFacultySalary(FacultySalaryDto details) {
        
        FacultySalaryEntity entity = new FacultySalaryEntity();
        extracted(details, entity);
        FacultyInfo facultyInfo = facultyInfoRepo.getById(details.getFacultyID());
        entity.setFacultyInfo(facultyInfo);
        facultySalaryRepo.save(entity);
        return "Salary saved successfully";
    }
    int calcTotal(FacultySalaryDto dto){

        int total = 0;
        int tax = (dto.getFacultySalary() * dto.getFacultyTax()) / 100;
        total = total+dto.getFacultySalary();
        total = total-tax;
        total = total - dto.getFacultyTransport();
        for (DeductionDto deductionDto : dto.getFacultyDeduction()){
            total = total-deductionDto.getAmount();
        }

        return total;
    }

    private void extracted(FacultySalaryDto details, FacultySalaryEntity entity) {
        entity.setFacultySalary(details.getFacultySalary());
        entity.setFacultyTax(details.getFacultyTax());
        entity.setFacultyDeduction(gson.toJson(details.getFacultyDeduction()));
        entity.setFacultyTransport(details.getFacultyTransport());
        int total = calcTotal(details);
        entity.setTotal(total);
    }
}
