//package com.easyWay.Student_Management_System.ServiceImpl;
//
//import com.easyWay.Student_Management_System.Entity.SchoolCreationEntity;
//import com.easyWay.Student_Management_System.Repo.SchoolCreationRepo;
//import com.easyWay.Student_Management_System.Security.ClaimService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//
//public class WhatsAppServiceImpl implements WhatsAppService {
//
//    @Autowired
//    SchoolCreationRepo repo;
//
//    @Autowired
//    ClaimService claimService;
//
//    @Override
//    public String sendMessage(String to, String message){
//
//        SchoolCreationEntity config = repo.findByUserSchoolCode(claimService.getLoggedInUserSchoolCode());
//
//        String apiUrl = config.getApiUrl();
//        if (apiUrl == null || apiUrl.isEmpty()) {
//            apiUrl = "https://graph.facebook.com/v19.0/" + config.getPhoneNumberId() + "/messages";
//        }
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(config.getAccessToken());
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        String body = """
//        {
//          "messaging_product": "whatsapp",
//          "to": "%s",
//          "type": "text",
//          "text": { "body": "%s" }
//        }
//        """.formatted(to, message);
//
//        HttpEntity<String> request = new HttpEntity<>(body, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
//
//        return response.getBody();
//    }
//
//}
