package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.PermissionsDto;
import com.easyWay.Student_Management_System.Dto.SelfDto;

import java.util.List;

public interface PermissionService {

    String savePermission(PermissionsDto dto , String user);

    List<SelfDto> getAllPermission();


}
