package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.AdminFeesDto;

import java.util.UUID;

public interface AdminFeesService {

    String saveFees(AdminFeesDto details);

    String deleteFees(UUID id);
}
