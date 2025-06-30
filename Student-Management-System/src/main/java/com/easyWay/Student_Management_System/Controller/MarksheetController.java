package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.MarksheetDTO;
import com.easyWay.Student_Management_System.Dto.SubjectMark;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/marksheet")
public class MarksheetController {

    @Autowired
    ClaimService claimService;

    private final TemplateEngine templateEngine;

    public MarksheetController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @PostMapping("/download")
    public ResponseEntity<ByteArrayResource> generateMarksheet(@RequestBody MarksheetDTO dto) throws IOException {
        List<SubjectMark> subjects = dto.getSubjects();

        int totalMarks = subjects.stream().mapToInt(SubjectMark::getTotal).sum();
        int maxMarks = subjects.size() * 300;

        // Compute overall grade
        String overallGrade;
        double percentage = (double) totalMarks / maxMarks * 100;
        if (percentage >= 90) {
            overallGrade = "A+";
        } else if (percentage >= 80) {
            overallGrade = "A";
        } else if (percentage >= 70) {
            overallGrade = "B+";
        } else {
            overallGrade = "B";
        }

        Context context = new Context();
        context.setVariable("stdName", dto.getStudentName());
        context.setVariable("rollNo", dto.getRollNo());
        context.setVariable("studentClass", dto.getStudentClass());
        context.setVariable("academicYear", dto.getAcademicYear());
        context.setVariable("subjects", subjects);
        context.setVariable("totalMarks", totalMarks);
        context.setVariable("maxMarks", maxMarks);
        context.setVariable("overallGrade", overallGrade);
        context.setVariable("result", dto.getResult());
        context.setVariable("remarks", dto.getRemarks());
        context.setVariable("date", dto.getDate());
        context.setVariable("schName", claimService.getSchoolName().getSchoolName());
        context.setVariable("pNO", claimService.getSchoolName().getAdminContact());




        String html = templateEngine.process("marksheet", context);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.withHtmlContent(html, null);
        builder.toStream(os);
        builder.run();

        byte[] pdfBytes = os.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=marksheet.pdf");
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .body(resource);
    }
}

