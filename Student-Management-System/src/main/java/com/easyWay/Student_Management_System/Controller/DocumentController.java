package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.DocumentDto;
import com.easyWay.Student_Management_System.Entity.Document;
import com.easyWay.Student_Management_System.ServiceImpl.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doc")
public class DocumentController {

    @Autowired
    DocumentService service;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String title
            , @RequestParam boolean publish, @RequestParam String cls, @RequestParam String subject) {

        try {
            service.uploadPdf(file, title, publish, cls, subject);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<DocumentDto> getAllDoc() {
        return service.getAll();
    }

    @PostMapping("/update")
    public String updateDoc(@RequestParam("file") MultipartFile file, @RequestParam String title
            , @RequestParam boolean publish, @RequestParam String cls, @RequestParam String subject, @RequestParam UUID id ) throws IOException {

        return service.updateDoc(file, title, publish, cls, subject, id);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable UUID id) {
        Document doc = service.getDocument(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header("Content-Disposition", "attachment; filename=\"" + doc.getName() + "\"")
                .body(doc.getData());
    }

    @PostMapping("/delete")
    public String deleteFile(@RequestParam UUID id) {
        return service.deleteFile(id);
    }

    @PostMapping("/getNotes")
    public List<DocumentDto> getNotes(@RequestParam String code) {
        return service.getNotes(code);
    }

    @PostMapping("/syllabus/update")
    public String updatePublish(@RequestBody List<DocumentDto> dto) {

        return service.updatePublish(dto);
    }
}

