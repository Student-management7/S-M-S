package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.AdminFeesDto;

import java.util.List;
import java.util.UUID;

public interface AdminFeesService {

    String saveFees(AdminFeesDto details);

    String deleteFees(UUID id);

    String editFees(AdminFeesDto id);

    List<AdminFeesDto> getFees();
}
