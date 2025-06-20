package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.DocumentDto;
import com.easyWay.Student_Management_System.Dto.NotificationDto;
import com.easyWay.Student_Management_System.Entity.Document;
import com.easyWay.Student_Management_System.Entity.NotificationEntity;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.DocumentRepository;
import com.easyWay.Student_Management_System.Repo.StudentInfoRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository repository;

    @Autowired
    ClaimService claimService;

    @Autowired
    StudentInfoRepo studentInfoRepo;

    public Document uploadPdf(MultipartFile file, String tittle
            , boolean publish, String cls, String subject) throws Exception {

        if(!Objects.requireNonNull(file.getContentType()).equalsIgnoreCase("application/pdf")){
            throw new BadRequestException("Incorrect File Format please upload pdf file only");
        }
        Document doc = new Document();
        doc.setName(file.getOriginalFilename());
        doc.setData(file.getBytes());
        doc.setSchoolCode(claimService.getLoggedInUserSchoolCode());
        doc.setSubject(subject);
        doc.setCls(cls);
        doc.setTitle(tittle);
        doc.setPublish(publish);
        return repository.save(doc);
    }

    public Document getDocument(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public List<DocumentDto> getAll() {

        List<Document> documentList = repository.findAll();

        if(ObjectUtils.isEmpty(documentList)){
            throw new BadRequestException("No record found");
        }

        List<DocumentDto> dtoList = new ArrayList<>();
        for (Document doc: documentList){
            if(claimService.getLoggedInUserSchoolCode().equalsIgnoreCase(doc.getSchoolCode())) {
                DocumentDto dto = new DocumentDto();
                dto.setId(doc.getId());
                dto.setSubject(doc.getSubject());
                dto.setTitle(doc.getTitle());
                dto.setName(doc.getName());
                dto.setCls(doc.getCls());
                dto.setPublish(doc.isPublish());

                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    public String updateDoc(Document document) {
        repository.save(document);
        return "Updated successfully";
    }

    public String deleteFile(UUID id) {
        repository.deleteById(id);
        return "Deleted successfully";
    }

    public List<DocumentDto> getNotes(String code) {
        List<StudentInfo> studentInfoList = studentInfoRepo.findAll();
        String schoolCode = "";
        String cls = "";
        for (StudentInfo studentInfo : studentInfoList) {
            if (StringUtil.isBlank(studentInfo.getContact()) || StringUtil.isBlank(studentInfo.getName())) {
                continue;
            }

            String name = studentInfo.getName();
            String contact = studentInfo.getContact();

            if (name.length() < 4 || contact.length() < 10) {
                continue; // Skip students with insufficient data
            }

            String systemCode = name.substring(0, 4) + contact.substring(6, 10);
            if (systemCode.equalsIgnoreCase(code)) {
                schoolCode = studentInfo.getSchoolCode();
                cls = studentInfo.getCls();
                break;
            }
        }

        List<Document> notes = repository.findAll();
        if (ObjectUtils.isEmpty(notes)) {
            throw new BadRequestException("No Data Found for this school");
        }


        List<DocumentDto> dtoList = new ArrayList<>();
        for (Document doc: notes){
            if(!(doc.getCls().equalsIgnoreCase(cls) && doc.getSchoolCode().equalsIgnoreCase(schoolCode))){
                continue;
            }
            DocumentDto dto = new DocumentDto();
            dto.setId(doc.getId());
            dto.setSubject(doc.getSubject());
            dto.setTitle(doc.getTitle());
            dto.setName(doc.getName());
            dto.setCls(doc.getCls());
            dto.setPublish(doc.isPublish());

            dtoList.add(dto);

        }
        return dtoList;
    }

    public String updatePublish(List<DocumentDto> dto) {

        List<Document> document = repository.findAll();
        if (ObjectUtils.isEmpty(document)){
            throw new BadRequestException("No record found for update");
        }
        for(DocumentDto data :dto){
           for (Document doc :document){
               if (doc.getId().equals(data.getId())){

                   doc.setPublish(data.isPublish());
                   repository.save(doc);
                   continue;
               }
           }


        }
        return "updated successfully";
    }
}
