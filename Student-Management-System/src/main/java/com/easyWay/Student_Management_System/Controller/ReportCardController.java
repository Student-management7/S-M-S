package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.ReportCardDto;
import com.easyWay.Student_Management_System.Service.ReportCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/report")
public class ReportCardController {
    @Autowired
    ReportCardService reportCardService;

    @PostMapping("/save")
    public String saveReportCard(@RequestBody ReportCardDto dto){
        return reportCardService.saveReportCard(dto);
    }

    @PostMapping("/edit")
    public String editReportCard(@RequestBody ReportCardDto dto){
        return reportCardService.editReportService(dto);
    }

    @GetMapping("/getStudentReport")
    public List<ReportCardDto> getReportCard(@RequestParam(required = false) UUID id){
        return reportCardService.getReportCard(id);
    }

}
