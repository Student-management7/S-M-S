//package com.easyWay.Student_Management_System.Controller;
//
//import com.easyWay.Student_Management_System.Service.WhatsAppService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/whatsapp")
//public class WhatsAppController {
//
//    @Autowired
//    public WhatsAppService whatsAppService;
//
//    @PostMapping("/send")
//    public ResponseEntity<String> send(@RequestParam String to,
//                                       @RequestParam String message) {
//        String result = whatsAppService.sendMessage( to, message);
//        return ResponseEntity.ok(result);
//    }
//}
