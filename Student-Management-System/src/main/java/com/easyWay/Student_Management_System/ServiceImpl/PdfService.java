package com.easyWay.Student_Management_System.ServiceImpl;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    public byte[] generateStyledPdf(String name, String motherName, String fatherName, String gender,
                                    String dob, String citizenship, String address, String email,
                                    String phone, String admissionClass) throws IOException {

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);

            // Set font
            PdfFont font = PdfFontFactory.createFont();

            // Add header with logo
            addHeader(document);

            // Title
            Paragraph title = new Paragraph("STUDENT REGISTRATION FORM")
                    .setFont(font).setBold().setFontSize(18).setTextAlignment(TextAlignment.CENTER);
            document.add(title);

            document.add(new Paragraph("\n"));

            // Create a table with two columns
            Table table = new Table(UnitValue.createPercentArray(new float[]{30, 70})).useAllAvailableWidth();

            // Add table header
            addTableHeader(table);

            // Add student details
            addTableRow(table, "Name", name);
            addTableRow(table, "Mother's Name", motherName);
            addTableRow(table, "Father's Name", fatherName);
            addTableRow(table, "Gender", gender);
            addTableRow(table, "Date of Birth", dob);
            addTableRow(table, "Citizenship", citizenship);
            addTableRow(table, "Address", address);
            addTableRow(table, "Email", email);
            addTableRow(table, "Phone", phone);
            addTableRow(table, "Admission Class", admissionClass);

            document.add(table);

            // Add footer
            addFooter(document, pdf);

            document.close();
            return out.toByteArray();
        }
    }

    private void addHeader(Document document) throws IOException {
        Table headerTable = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();

        // Add logo (replace with actual image path)
//        Image logo = new Image(com.itextpdf.io.image.ImageDataFactory.create("src/main/resources/static/logo.png"))
//                .scaleAbsolute(50, 50);
//
//        headerTable.addCell(new Cell().add(logo).setBorder(Border.NO_BORDER));
        headerTable.addCell(new Cell().add(new Paragraph("Easy Way Solution"))
                .setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));

        document.add(headerTable);
    }

    private void addTableHeader(Table table) {
        table.addHeaderCell(new Cell().add(new Paragraph("Field")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Details")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
    }

    private void addTableRow(Table table, String field, String value) {
        table.addCell(new Cell().add(new Paragraph(field)));
        table.addCell(new Cell().add(new Paragraph(value != null ? value : "N/A")));
    }


    private void addFooter(Document document, PdfDocument pdf) {
        int pageCount = pdf.getNumberOfPages();
        for (int i = 1; i <= pageCount; i++) {
            PdfPage page = pdf.getPage(i);
            Document tempDoc = new Document(pdf);

            // Corrected showTextAligned method
            tempDoc.showTextAligned(new Paragraph("Page " + i + " of " + pageCount),
                    559, 40, i, TextAlignment.RIGHT, VerticalAlignment.BOTTOM, 0);
        }
    }

}
