package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.PermissionsDto;
import com.easyWay.Student_Management_System.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissions")
public class PermissionsController {

    @Autowired
    PermissionService permissionService;

    @PostMapping("/save")
    public ResponseEntity<String> savePermissions( @RequestBody PermissionsDto dto) {
        try {
            return ResponseEntity.ok(permissionService.savePermission(dto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
