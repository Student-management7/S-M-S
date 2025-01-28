package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.DeductionDto;
import com.easyWay.Student_Management_System.Dto.FacultySalaryDto;
import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import com.easyWay.Student_Management_System.Entity.FacultySalaryEntity;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.FacultyInfoRepo;
import com.easyWay.Student_Management_System.Repo.FacultySalaryRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Service.FacultySalaryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
public class FacultySalaryServiceImpl implements FacultySalaryService {

    @Autowired
    Gson gson;
    
    @Autowired
    FacultySalaryRepo facultySalaryRepo;

    @Autowired
    FacultyInfoRepo facultyInfoRepo;

    @Autowired
    ClaimService claimService;

    @Override
    public String saveFacultySalary(FacultySalaryDto details) {
        
         FacultySalaryEntity entity = new FacultySalaryEntity();
        extracted(details, entity);
        FacultyInfo facultyInfo = facultyInfoRepo.getById(details.getFacultyID());
        entity.setFacultyInfo(facultyInfo);
        entity.setSchoolCode(claimService.getLoggedInUserSchoolCode());
        facultySalaryRepo.save(entity);
        return "Salary saved successfully";
    }

    @Override
    public String deleteSalary(UUID id) {
        facultySalaryRepo.deleteById(id);
        return "deleted successfully";
    }

    @Override
    public String editSalary(FacultySalaryDto dto) {
        FacultySalaryEntity entity = facultySalaryRepo.getById(dto.getId());

        if (ObjectUtils.isEmpty(entity)){
            throw new BadRequestException("No data found for the given id");
        }
        entity.setFacultySalary(dto.getFacultySalary());
        entity.setFacultyTransport(dto.getFacultyTransport());
        entity.setFacultyDeduction(gson.toJson(dto.getFacultyDeduction()));
        entity.setFacultyTax(dto.getFacultyTax());
        facultySalaryRepo.save(entity);

        return "data edit successfully";
    }

    int calcTotal(FacultySalaryDto dto) {

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
