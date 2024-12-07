package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.HolidayRequestDto;
import com.easyWay.Student_Management_System.Service.HolidayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/holiday")
public class HolidayInfoController {

    @Autowired
    HolidayInfoService holidayInfoService;

    @PostMapping("/save")
    public String saveHolidayInfo(@RequestBody HolidayRequestDto details) {
        return holidayInfoService.saveHolidayInfo(details);
    }

    @PostMapping("/edit")
    public String editHolidayInfo(@RequestBody HolidayRequestDto details) {
        return holidayInfoService.editHolidayInfo(details);
    }

    @PostMapping("/delete")
    public String deleteHolidayInfo(@RequestParam UUID id) {
        return holidayInfoService.deleteHolidayInfo(id);
    }

    @GetMapping("/get")
    public List<HolidayRequestDto> getHolidayRequestDto(){
        return holidayInfoService.getAllHolidayRequestDto();
    }


}
