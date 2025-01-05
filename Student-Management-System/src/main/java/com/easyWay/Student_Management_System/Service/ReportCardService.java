package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.ReportCardDto;

import java.util.UUID;

public interface ReportCardService {
    String saveReportCard(ReportCardDto dto);

    String editReportService(ReportCardDto dto);

    ReportCardDto getReportCard(UUID id);
}
