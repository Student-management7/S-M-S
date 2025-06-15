package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Entity.Document;
import com.easyWay.Student_Management_System.Repo.DocumentRepository;
import com.easyWay.Student_Management_System.Security.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository repository;

    @Autowired
    ClaimService claimService;

    public Document uploadPdf(MultipartFile file, Document data) throws Exception {
        Document doc = new Document();
        doc.setName(file.getOriginalFilename());
        doc.setData(file.getBytes());
        doc.setSchoolCode(claimService.getLoggedInUserSchoolCode());
        doc.setSubject(data.getSubject());
        doc.setCls(data.getCls());
        doc.setTittle(data.getTittle());
        doc.setPublish(data.isPublish());
        return repository.save(doc);
    }

    public Document getDocument(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
