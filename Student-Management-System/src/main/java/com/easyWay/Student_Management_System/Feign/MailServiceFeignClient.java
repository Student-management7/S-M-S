package com.easyWay.Student_Management_System.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sms-mail-service", url = "http://localhost:8082")
public interface MailServiceFeignClient {
    @GetMapping("/sendMail")
    void sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text);
}
