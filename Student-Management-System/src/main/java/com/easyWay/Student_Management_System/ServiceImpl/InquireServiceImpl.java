package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.InquireDto;
import com.easyWay.Student_Management_System.Entity.InquireEntity;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.InquireRepo;
import com.easyWay.Student_Management_System.Service.InquireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;

@Service
public class InquireServiceImpl implements InquireService {

    @Autowired
    InquireRepo infoRepo;

    @Override
    public String saveInquire(InquireDto dto) {
        InquireEntity inquireEntity = new InquireEntity();
        extracted(dto, inquireEntity);
        return "saved successfully";
    }

    @Override
    public String editInquire(InquireDto dto) {
        InquireEntity entity = infoRepo.getById(dto.getId());
        
        if(ObjectUtils.isEmpty(entity)){
            throw new BadRequestException("No data found");
        }
        
        extracted(dto, entity);
        return "Edited successfully";
    }

    @Override
    public  List<InquireEntity> getInquire() {
        List<InquireEntity> entities = infoRepo.findAll();

        if(ObjectUtils.isEmpty(entities)){
            throw new BadRequestException("No data found");
        }
        return entities;
    }

    @Override
    public String deleteInquire(UUID id) {
        infoRepo.deleteById(id);
        return "deleted successfully";
    }

    private void extracted(InquireDto dto, InquireEntity inquireEntity) {
        inquireEntity.setSchoolName(dto.getSchoolName());
        inquireEntity.setNotes(dto.getNotes());
        inquireEntity.setLocation(dto.getLocation());
        inquireEntity.setInquiryDate(dto.getInquiryDate());
        inquireEntity.setCurrentStatus(dto.getCurrentStatus());
        inquireEntity.setAdminContactInfo(dto.getAdminContactInfo());
        infoRepo.save(inquireEntity);
    }
}
