package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.ServiceImpl.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf(@RequestParam String name,
                                              @RequestParam String motherName,
                                              @RequestParam String fatherName,
                                              @RequestParam String gender,
                                              @RequestParam String dob,
                                              @RequestParam String citizenship,
                                              @RequestParam String address,
                                              @RequestParam String state,
                                              @RequestParam String zip,
                                              @RequestParam String country,
                                              @RequestParam String phone,
                                              @RequestParam String alternatePhone,
                                              @RequestParam String email,
                                              @RequestParam String admissionClass) {
        try {
            byte[] pdfBytes = pdfService.generateStyledPdf(name, motherName, fatherName, gender, dob, citizenship,
                    address, state, zip, country, phone, alternatePhone, email, admissionClass);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "Student_Registration_Form.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
