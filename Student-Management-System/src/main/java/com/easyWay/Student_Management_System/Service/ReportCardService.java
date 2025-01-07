package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.ReportCardDto;

import java.util.List;
import java.util.UUID;

public interface ReportCardService {
    String saveReportCard(ReportCardDto dto);

    String editReportService(ReportCardDto dto);

    List<ReportCardDto> getReportCard(UUID id);
}
