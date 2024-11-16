package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.AdminFeesDto;
import com.easyWay.Student_Management_System.Dto.OtherFeesDto;
import com.easyWay.Student_Management_System.Entity.AdminFeesStructure;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.AdminFeesRepo;
import com.easyWay.Student_Management_System.Service.AdminFeesService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
}
