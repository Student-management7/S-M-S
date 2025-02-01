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

    @GetMapping("/download-styled-pdf")
    public ResponseEntity<byte[]> downloadStyledPdf(@RequestParam String name,
                                                    @RequestParam String motherName,
                                                    @RequestParam String fatherName,
                                                    @RequestParam String gender,
                                                    @RequestParam String dob,
                                                    @RequestParam String citizenship,
                                                    @RequestParam String address,
                                                    @RequestParam String email,
                                                    @RequestParam String phone,
                                                    @RequestParam String admissionClass) throws IOException {
        byte[] pdfBytes = pdfService.generateStyledPdf(name, motherName, fatherName, gender, dob, citizenship, address, email, phone, admissionClass);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Styled_Student_Registration.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(pdfBytes);
    }
}

