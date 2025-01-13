package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.InquireDto;
import com.easyWay.Student_Management_System.Entity.InquireEntity;
import com.easyWay.Student_Management_System.Service.InquireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inquire")
public class InquireController {

    @Autowired
    InquireService inquireService;

    @PostMapping("/save")
    public String saveInquire(@RequestBody InquireDto dto){
      return inquireService.saveInquire(dto);
    }

    @PostMapping("/edit")
    public String editInquire(@RequestBody InquireDto dto){
        return inquireService.editInquire(dto);
    }

    @GetMapping("/get")
    public List<InquireEntity> getInquire(){
        return inquireService.getInquire();
    }

    @PostMapping("delete")
    public String deleteInquire(@RequestParam UUID id){
        return inquireService.deleteInquire(id);
    }
}
