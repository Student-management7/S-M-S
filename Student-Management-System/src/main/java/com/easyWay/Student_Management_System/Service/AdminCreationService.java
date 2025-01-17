package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.AdminCreationDto;

import java.util.List;

public interface AdminCreationService {
    String saveAdmin(AdminCreationDto dto);

    List<AdminCreationDto> getAdminDetails();
}
