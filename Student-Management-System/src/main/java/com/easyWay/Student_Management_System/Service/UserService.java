package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.UsersDto;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

public interface UserService {

    String registerUser(UsersDto userDto );

    String loginUser (UsersDto dto) throws BadRequestException;

    String forgetUserPassword(UsersDto userDto);

}
