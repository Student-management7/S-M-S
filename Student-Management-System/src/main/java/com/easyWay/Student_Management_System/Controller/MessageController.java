package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.ServiceImpl.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/whatsapp")
public class MessageController {


    @PostMapping("/send")
    public  String send(String[] args) {
        // Replace this with the phone number you want to send the message to (in the format: +[country_code][number])
        String recipientPhoneNumber = "+917879863767";// Example phone number

        // Message to be sent
        String messageText = "Hello! Your child's attendance has been marked.";

        // Send the WhatsApp message
        WhatsAppService.sendWhatsAppMessage(recipientPhoneNumber, messageText);
        return "Send";
    }
}

