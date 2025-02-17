package com.easyWay.Student_Management_System.ServiceImpl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class WhatsAppService {
    private static final String ACCOUNT_SID = "AC0d9e2b76233620d77d40be76858824a2";
    private static final String AUTH_TOKEN = "dd54555b91303916a21e6b30a6d77927";
    private static final String FROM_WHATSAPP_NUMBER = "whatsapp:+14155238886";  // Twilio sandbox number

    public static void sendWhatsAppMessage(String to, String messageText) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber("whatsapp:" + to),
                        new PhoneNumber(FROM_WHATSAPP_NUMBER),
                        messageText)
                .create();

        System.out.println("Message Sent: " + message.getSid());
    }
}

