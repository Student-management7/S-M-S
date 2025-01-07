package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.ContactUsDetailsDto;
import com.easyWay.Student_Management_System.Entity.ContactUsEntity;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.ContactUsRepo;
import com.easyWay.Student_Management_System.Service.ContactUsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ContactUsDetailsImpl implements ContactUsDetailsService {
    @Autowired
    ContactUsRepo contactUsRepo;


    @Override
    public String saveService(ContactUsDetailsDto dto) {
        ContactUsEntity contactUsEntity = new ContactUsEntity();
        convertDtoToEntity(dto , contactUsEntity);
        contactUsRepo.save(contactUsEntity);
        return "save successfully";
    }

    @Override
    public String deleteService(UUID id) {
        if (ObjectUtils.isEmpty(id)){
            throw new BadRequestException("id can't be null");
        }
        contactUsRepo.deleteById(id);
        return "deleted successfully";
    }

    @Override
    public List<ContactUsDetailsDto> findAllService() {

        List<ContactUsEntity> contactUsEntities = contactUsRepo.findAll();

        if (ObjectUtils.isEmpty(contactUsEntities)){
            throw new BadRequestException("No data found");
        }
        List<ContactUsDetailsDto> dtos = new ArrayList<>();

        for (ContactUsEntity entity :contactUsEntities ){
            ContactUsDetailsDto dto = new ContactUsDetailsDto();
            dto.setId(entity.getId());
            dto.setContact(entity.getContact());
            dto.setEmail(entity.getEmail());
            dto.setRemarks(entity.getRemarks());
            dto.setUsername(entity.getUsername());
            dtos.add(dto);
        }

        return dtos;
    }

    public void convertDtoToEntity(ContactUsDetailsDto dto , ContactUsEntity entity){
             entity.setEmail(dto.getEmail());
             entity.setContact(dto.getContact());
             entity.setUsername(dto.getUsername());
             entity.setRemarks(dto.getRemarks());
    }
}
