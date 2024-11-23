package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.HolidayRequestDto;

import java.util.List;
import java.util.UUID;

public interface HolidayInfoService {

    String saveHolidayInfo(HolidayRequestDto details);

    String editHolidayInfo(HolidayRequestDto details);

    String deleteHolidayInfo(UUID id);

    List<HolidayRequestDto> getAllHolidayRequestDto();
}
