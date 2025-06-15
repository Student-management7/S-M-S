package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Entity.Document;
import com.easyWay.Student_Management_System.ServiceImpl.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import java.util.UUID;

@RestController
@RequestMapping("/doc")
public class DocumentController {

    @Autowired
    DocumentService service;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestBody Document doc) {
        try {
            service.uploadPdf(file, doc);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable UUID id) {
        Document doc = service.getDocument(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header("Content-Disposition", "attachment; filename=\"" + doc.getName() + "\"")
                .body(doc.getData());
    }
}
