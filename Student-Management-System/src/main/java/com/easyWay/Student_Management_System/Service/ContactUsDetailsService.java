package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.ContactUsDetailsDto;

import java.util.List;
import java.util.UUID;

public interface ContactUsDetailsService {
    String saveService(ContactUsDetailsDto dto);

    String deleteService(UUID id);

    List<ContactUsDetailsDto> findAllService();
}
