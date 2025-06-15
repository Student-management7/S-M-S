package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Entity.Document;
import com.easyWay.Student_Management_System.Repo.DocumentRepository;
import com.easyWay.Student_Management_System.Security.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository repository;

    @Autowired
    ClaimService claimService;

    public Document uploadPdf(MultipartFile file, String tittle
            , boolean publish, String cls, String subject) throws Exception {
        Document doc = new Document();
        doc.setName(file.getOriginalFilename());
        doc.setData(file.getBytes());
        doc.setSchoolCode(claimService.getLoggedInUserSchoolCode());
        doc.setSubject(subject);
        doc.setCls(cls);
        doc.setTittle(tittle);
        doc.setPublish(publish);
        return repository.save(doc);
    }

    public Document getDocument(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
