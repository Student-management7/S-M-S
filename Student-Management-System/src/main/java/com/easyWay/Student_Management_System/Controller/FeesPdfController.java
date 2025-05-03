package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Entity.StudentFeeInfo;
import com.easyWay.Student_Management_System.Repo.StudentFeesInfoRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

@RestController
@RequestMapping("/pdf")
public class FeesPdfController {


    @Autowired
    StudentFeesInfoRepo studentFeesInfoRepo;

    @Autowired
    ClaimService claimService;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/receipt")
    public ResponseEntity<byte[]> downloadReceipt(@RequestParam UUID id)  {
        ByteArrayOutputStream outputStream = generateReceiptPdf(id); // Your custom method

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("FeeReceipt_" + id + ".pdf")
                .build());

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/api/receipt/email")
    public String sendReceiptByEmail(@RequestParam UUID id) throws MessagingException {
        ByteArrayOutputStream pdfStream = generateReceiptPdf(id);

        // fetch student's email from DB based on roll number
        StudentFeeInfo toEmail = studentFeesInfoRepo.getById(id);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail.getStudentInfo().getEmail());
        helper.setSubject("Fee Payment Receipt");
        helper.setText("Dear Student, Please find your payment receipt attached.");
        helper.addAttachment("FeeReceipt_" + id + ".pdf", new ByteArrayResource(pdfStream.toByteArray()));

        javaMailSender.send(message);

        return"Email sent successfully to " + toEmail.getStudentInfo().getEmail();
    }

    private ByteArrayOutputStream generateReceiptPdf(UUID id)  {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Fetch student/payment details from DB
        StudentFeeInfo student = studentFeesInfoRepo.getById(id);

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("-------------------------------------------------------------"));
        document.add(new Paragraph("         "+claimService.getSchoolName()));
        document.add(new Paragraph("           Fee Payment Receipt"));
        document.add(new Paragraph("-------------------------------------------------------------"));
        document.add(new Paragraph("Student Name   : " + student.getStudentInfo().getName()));
        document.add(new Paragraph("Class          : " + student.getStudentInfo().getCls()));
        document.add(new Paragraph("Payment Date   : " + student.getCreationDateTime()));
        document.add(new Paragraph("Amount Paid    : ₹" + student.getFee()));
        document.add(new Paragraph("Transaction ID : " + student.getId()));
        document.add(new Paragraph("-------------------------------------------------------------"));
        document.add(new Paragraph("      Thank you for your payment!"));
        document.add(new Paragraph("This is a computer-generated receipt."));
        document.add(new Paragraph("-------------------------------------------------------------"));

        document.close();
        return out;
    }

}
