package com.easyWay.Student_Management_System.ServiceImpl;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    public byte[] generateStyledPdf(String name, String motherName, String fatherName, String gender,
                                    String dob, String citizenship, String address, String state, String zip,
                                    String country, String phone, String alternatePhone, String email, String admissionClass) throws IOException {

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);

            PdfFont font = PdfFontFactory.createFont();

            // Add Office Use Only Section at the top
            addOfficeUseSection(document);

            // Title
            document.add(new Paragraph("\nSTUDENT REGISTRATION FORM")
                    .setFont(font).setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));

            // General Information Section
            document.add(new Paragraph("\nGeneral Information\n").setBold().setFontSize(12));

            // Student Details
            addField(document, "Name          ", name);
            addField(document, "Mother’s Name          ", motherName);
            addField(document, "Father’s Name          ", fatherName);
            addField(document, "Gender          ", gender);
            addField(document, "Date of Birth          ", dob);
            addField(document, "Citizenship          ", citizenship);
            addField(document, "Permanent Address          ", address);
            addField(document, "State / Province          ", state);
            addField(document, "Zip Code          ", zip);
            addField(document, "Country          ", country);
            addField(document, "Cellphone          ", phone);
            addField(document, "Alternate Number          ", alternatePhone);
            addField(document, "Email          ", email);
            addField(document, "Admission Class          ", admissionClass);

            // Signature Section
            document.add(new Paragraph("\n\nSignature of the Student: ________________________        Date: ________________________\n\n"));

            // Footer - FOR OFFICE USE ONLY
//            addFooter(document);

            document.close();
            return out.toByteArray();
        }
    }

    private void addOfficeUseSection(Document document) {
        Table table = new Table(new float[]{75, 25}).useAllAvailableWidth();
        table.addCell(new Cell().setBorder(Border.NO_BORDER));  // Empty space
        table.addCell(new Cell()
                .add(new Paragraph("FOR OFFICE USE ONLY\nDate of Registration: ________________"))
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER));
        document.add(table);
    }

    private void addField(Document document, String fieldName, String value) throws IOException {
        // Load the regular font for non-bold text
        PdfFont normalFont = PdfFontFactory.createFont("Helvetica");
        // Load the bold font for field names
        PdfFont boldFont = PdfFontFactory.createFont("Helvetica-Bold");

        // Create the field name with bold font and the value with normal font
        Paragraph fieldParagraph = new Paragraph()
                .add(new Text(fieldName + ": ").setFont(boldFont))  // Field name in bold
                .add(new Text(value != null ? value : "").setFont(normalFont).setCharacterSpacing(1));  // Field value in regular font with small space

        // Set paragraph alignment, font size, and spacing using margins
        fieldParagraph.setFontSize(12)
                .setTextAlignment(TextAlignment.LEFT)
                .setMarginTop(5)   // Adjust space above the field
                .setMarginBottom(5); // Adjust space below the field

        // Add the paragraph to the document
        document.add(fieldParagraph);
    }


    // Uncomment and implement the footer method if required.
    // private void addFooter(Document document) {
    //     document.add(new Paragraph("\nFOR OFFICE USE ONLY").setBold().setTextAlignment(TextAlignment.CENTER));
    //     Table table = new Table(new float[]{50, 50}).useAllAvailableWidth();
    //     table.addCell(new Cell().add(new Paragraph("Name of the verifying person: ________________"))
    //             .setBorder(Border.NO_BORDER));
    //     table.addCell(new Cell().add(new Paragraph("Signature: ________________________"))
    //             .setBorder(Border.NO_BORDER));
    //     table.addCell(new Cell().add(new Paragraph("Date: ________________"))
    //             .setBorder(Border.NO_BORDER));
    //     document.add(table);
    // }
}
