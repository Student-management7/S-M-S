package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.FactListDto;
import com.easyWay.Student_Management_System.Dto.PermissionsDto;
import com.easyWay.Student_Management_System.Dto.SelfDto;
import com.easyWay.Student_Management_System.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionsController {

    @Autowired
    PermissionService permissionService;

    @PostMapping("/save")
    public String savePermissions( @RequestBody PermissionsDto dto) {
        try {
            return permissionService.savePermission(dto);
        } catch (Exception e){
            return e.getMessage();
        }

    }

    @GetMapping("/get")
    public List<SelfDto> getAllPermissions(){
        return permissionService.getAllPermission();
    }

}
