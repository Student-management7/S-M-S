package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.ClassResponseDto;
import com.easyWay.Student_Management_System.Service.ClassResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassResponseService classResponseService;


    @GetMapping("/data")
    public ClassResponseDto getClassResponse(){
        return classResponseService.getData();
    }

    @PostMapping("/save")
    public void saveClassResponse(@RequestBody ClassResponseDto classResponseDto){
        classResponseService.saveData(classResponseDto);
    }
    @PostMapping("/edit")
    public String editSubjectInClass(@RequestBody ClassResponseDto details){
       return classResponseService.editSubjectInClass(details);

    }
    @PostMapping("/delete")
    public String deleteClass(@RequestParam UUID id){
        return classResponseService.deleteClass(id);
    }


}
