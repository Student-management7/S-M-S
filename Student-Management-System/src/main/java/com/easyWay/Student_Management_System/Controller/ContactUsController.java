package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.ContactUsDetailsDto;
import com.easyWay.Student_Management_System.Service.ContactUsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contactUs")
public class ContactUsController {
    @Autowired
    ContactUsDetailsService contactUsDetailsService;

    @PostMapping("/save")
    public String saveContactDetails(@RequestBody ContactUsDetailsDto dto){
        return contactUsDetailsService.saveService(dto);
    }

    @PostMapping("/delete")
    public String deleteContactDetails(@RequestParam UUID id){
        return contactUsDetailsService.deleteService(id);
    }

    @GetMapping("/findAll")
    public List<ContactUsDetailsDto> findAll(){
        return contactUsDetailsService.findAllService();
    }


}
