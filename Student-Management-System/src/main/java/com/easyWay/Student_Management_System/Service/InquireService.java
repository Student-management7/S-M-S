package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.InquireDto;
import com.easyWay.Student_Management_System.Entity.InquireEntity;

import java.util.List;
import java.util.UUID;

public interface InquireService {

    String saveInquire(InquireDto dto);

    String editInquire(InquireDto dto);

    List<InquireEntity> getInquire();

    String deleteInquire(UUID id);
}
