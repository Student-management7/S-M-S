package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.AdminFeesDto;
import com.easyWay.Student_Management_System.Dto.FactListDto;
import com.easyWay.Student_Management_System.Dto.OtherFeesDto;
import com.easyWay.Student_Management_System.Entity.AdminFeesStructure;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.AdminFeesRepo;
import com.easyWay.Student_Management_System.Service.AdminFeesService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AdminFeesServiceImpl implements AdminFeesService {
    @Autowired
    AdminFeesRepo repo;

    @Autowired
    Gson gson;

    @Override
    public String saveFees(AdminFeesDto details) {
        AdminFeesStructure savedData = repo.findByClass(details.getClassName());
        if(!ObjectUtils.isEmpty(savedData)){
            throw new BadRequestException("Class Already Present");
        }
        AdminFeesStructure entity = new AdminFeesStructure();
        extracted(details, entity);
        repo.save(entity);
        return "Saved successfully";
    }

    @Override
    public String deleteFees(UUID id) {
        if (id == null) {
            throw new BadRequestException("Invalid id");
        }

        return repo.findById(id)
                .map(entity -> {
                    repo.delete(entity);
                    return "Deleted successfully.";
                })
                .orElse("No data found for this id = " + id);

    }

    @Override
    public String editFees(AdminFeesDto id) {

        if (id == null || id.getId() == null || ObjectUtils.isEmpty(id.getId())) {
            throw new BadRequestException("Invalid id");
        }

        AdminFeesStructure savedData = repo.getById(id.getId());
        if(ObjectUtils.isEmpty(savedData)){
            throw new BadRequestException("Data not found");
        }
        extracted(id, savedData);
        repo.save(savedData);
        return "Edited data successfully";
    }

    @Override
    public List<AdminFeesDto> getFees(String cls) {

        List<AdminFeesStructure> data = new ArrayList<>();
        if(StringUtil.isBlank(cls)) {

           data  = repo.findAll();
        } else {
           AdminFeesStructure clsINfo = repo.findByClass(cls);

            if (ObjectUtils.isEmpty(clsINfo)) {
                throw new BadRequestException("No data found for the given class");
            }

          data.add(clsINfo);
        }
        if (ObjectUtils.isEmpty(data)) {
            throw new BadRequestException("No data found");
        }

        List<AdminFeesDto> result = new ArrayList<>();

        for (AdminFeesStructure saved : data) {
            AdminFeesDto dto = new AdminFeesDto();
            convertEntityToDto(saved, dto);
            result.add(dto);
        }

        return result;
    }

    private void extracted(AdminFeesDto details, AdminFeesStructure entity) {
        entity.setClassName(details.getClassName());
        entity.setSchoolFee(details.getSchoolFee());
        entity.setBookFee(details.getBookFee());
        entity.setSportsFee(details.getSportsFee());
        entity.setTransportation(details.getTransportation());
        entity.setOtherAmount(gson.toJson(details.getOtherAmount()));
        int total = findTotal(details);
        entity.setTotal(total);
    }

    public int findTotal(AdminFeesDto dto){
        int total = 0;
        total = total+dto.getBookFee();
        total = total+dto.getSchoolFee();
        total = total+dto.getSportsFee();
        total = total+dto.getTransportation();

        for (OtherFeesDto fees:dto.getOtherAmount()){
            total = total+fees.getAmount();
        }
        return total;
    }

    private void convertEntityToDto(AdminFeesStructure entity, AdminFeesDto details) {
        details.setClassName(entity.getClassName());
        details.setSchoolFee(entity.getSchoolFee());
        details.setBookFee(entity.getBookFee());
        details.setSportsFee(entity.getSportsFee());
        details.setId(entity.getId());
        details.setTransportation(entity.getTransportation());
        details.setTotalFee(entity.getTotal());
        Type attendanceListType = new TypeToken<List<OtherFeesDto>>() {}.getType();
        details.setOtherAmount(gson.fromJson(entity.getOtherAmount(), attendanceListType));
    }

}
