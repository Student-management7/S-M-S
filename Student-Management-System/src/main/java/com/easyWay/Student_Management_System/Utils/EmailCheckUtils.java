package com.easyWay.Student_Management_System.Utils;

import com.easyWay.Student_Management_System.Entity.StudentInfo;
import com.easyWay.Student_Management_System.Entity.Users;
import com.easyWay.Student_Management_System.Repo.UsersRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class EmailCheckUtils {

    @Autowired
    UsersRepo usersRepo;

    public boolean isEmailAlreadyRegistered(String email, String code ) {
        Users user = usersRepo.findUsersByEmail(email, code);

        if (ObjectUtils.isEmpty(user)){
            return false;
        }else {
            return true;
        }
    }

    public byte[] generateExcel(List<StudentInfo> data, String[] headers, String value) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Student Data");

            // Creating header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
            if(value.equalsIgnoreCase("error")) {
                createErrorRecord(data, sheet);
            } else if (value.equalsIgnoreCase("success")){
                createValidRecord(data, sheet);
            }else {
                createAllRecord(data, sheet);
            }
            workbook.write(out);
            return out.toByteArray();
        }
    }

    private void createErrorRecord(List<StudentInfo> data, Sheet sheet) {
        int j = 0;
        // Filling data
        for (int i = 0; i < data.size(); i++) {

            StudentInfo student = data.get(i);

            if(ObjectUtils.isEmpty(student.getErrorDescription())){
                continue;//0, 1
            }

            Row row = sheet.createRow(j + 1);
            j++;
            row.createCell(0).setCellValue(student.getErrorDescription() != null ? student.getErrorDescription() : "");
            row.createCell(1).setCellValue(student.getErrorCode() != null ? student.getErrorCode() : "");
            row.createCell(2).setCellValue(student.getName() != null ? student.getName() : "");
            row.createCell(3).setCellValue(student.getAddress() != null ? student.getAddress() : "");
            row.createCell(4).setCellValue(student.getCity() != null ? student.getCity() : "");
            row.createCell(5).setCellValue(student.getStd_state() != null ? student.getStd_state() : "");
            row.createCell(6).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "father"));
            row.createCell(7).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "mother"));
            row.createCell(8).setCellValue(student.getContact() != null ? student.getContact() : "");
            row.createCell(9).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "secondaryContact")); // Secondary Contact (if available, modify accordingly)
            row.createCell(10).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "familyAddress")); // Family Address (if available, modify accordingly)
            row.createCell(11).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "familyCity")); // Family City (if available, modify accordingly)
            row.createCell(12).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "familyState")); // Family State (if available, modify accordingly)
            row.createCell(13).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "familyEmail")); // Family Email (if available, modify accordingly)
            row.createCell(14).setCellValue(student.getContact() != null ? student.getContact() : "");
            row.createCell(15).setCellValue(student.getGender() != null ? student.getGender() : "");
            row.createCell(16).setCellValue(student.getDob() != null ? student.getDob() : "");
            row.createCell(17).setCellValue(student.getEmail() != null ? student.getEmail() : "");
            row.createCell(18).setCellValue(student.getCls() != null ? student.getCls() : "");
            row.createCell(19).setCellValue(student.getDepartment() != null ? student.getDepartment() : "");
            row.createCell(20).setCellValue(student.getCategory() != null ? student.getCategory() : "");
            row.createCell(21).setCellValue(student.getAdmissionClass() != null ? student.getAdmissionClass() : "");
        }
    }

    private void createValidRecord(List<StudentInfo> data, Sheet sheet) {
        int j = 0;
        // Filling data
        for (int i = 0; i < data.size(); i++) {

            StudentInfo student = data.get(i);

            if(!ObjectUtils.isEmpty(student.getErrorDescription())){
                continue;
            }

            Row row = sheet.createRow(j + 1);
            j++;

            row.createCell(0).setCellValue(student.getName() != null ? student.getName() : "");
            row.createCell(1).setCellValue(student.getAddress() != null ? student.getAddress() : "");
            row.createCell(2).setCellValue(student.getCity() != null ? student.getCity() : "");
            row.createCell(3).setCellValue(student.getStd_state() != null ? student.getStd_state() : "");
            row.createCell(4).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "father"));
            row.createCell(5).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "mother"));
            row.createCell(6).setCellValue(student.getContact() != null ? student.getContact() : "");
            row.createCell(7).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "secondaryContact")); // Secondary Contact (if available, modify accordingly)
            row.createCell(8).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "familyAddress")); // Family Address (if available, modify accordingly)
            row.createCell(9).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "familyCity")); // Family City (if available, modify accordingly)
            row.createCell(10).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "familyState")); // Family State (if available, modify accordingly)
            row.createCell(11).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "familyEmail")); // Family Email (if available, modify accordingly)
            row.createCell(12).setCellValue(student.getContact() != null ? student.getContact() : "");
            row.createCell(13).setCellValue(student.getGender() != null ? student.getGender() : "");
            row.createCell(14).setCellValue(student.getDob() != null ? student.getDob() : "");
            row.createCell(15).setCellValue(student.getEmail() != null ? student.getEmail() : "");
            row.createCell(16).setCellValue(student.getCls() != null ? student.getCls() : "");
            row.createCell(17).setCellValue(student.getDepartment() != null ? student.getDepartment() : "");
            row.createCell(18).setCellValue(student.getCategory() != null ? student.getCategory() : "");
            row.createCell(19).setCellValue(student.getAdmissionClass() != null ? student.getAdmissionClass() : "");
        }
    }

    private void createAllRecord(List<StudentInfo> data, Sheet sheet) {
        int j = 0;
        // Filling data
        for (int i = 0; i < data.size(); i++) {

            StudentInfo student = data.get(i);

            Row row = sheet.createRow(j + 1);
            j++;

            row.createCell(0).setCellValue(student.getName() != null ? student.getName() : "");
            row.createCell(1).setCellValue(student.getAddress() != null ? student.getAddress() : "");
            row.createCell(2).setCellValue(student.getCity() != null ? student.getCity() : "");
            row.createCell(3).setCellValue(student.getStd_state() != null ? student.getStd_state() : "");
            row.createCell(4).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "father"));
            row.createCell(5).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "mother"));
            row.createCell(6).setCellValue(student.getContact() != null ? student.getContact() : "");
            row.createCell(7).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "secondaryContact")); // Secondary Contact (if available, modify accordingly)
            row.createCell(8).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "familyAddress")); // Family Address (if available, modify accordingly)
            row.createCell(9).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "familyCity")); // Family City (if available, modify accordingly)
            row.createCell(10).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "familyState")); // Family State (if available, modify accordingly)
            row.createCell(11).setCellValue(extractFamilyDetail(student.getFamilyDetails(), "familyEmail")); // Family Email (if available, modify accordingly)
            row.createCell(12).setCellValue(student.getContact() != null ? student.getContact() : "");
            row.createCell(13).setCellValue(student.getGender() != null ? student.getGender() : "");
            row.createCell(14).setCellValue(student.getDob() != null ? student.getDob() : "");
            row.createCell(15).setCellValue(student.getEmail() != null ? student.getEmail() : "");
            row.createCell(16).setCellValue(student.getCls() != null ? student.getCls() : "");
            row.createCell(17).setCellValue(student.getDepartment() != null ? student.getDepartment() : "");
            row.createCell(18).setCellValue(student.getCategory() != null ? student.getCategory() : "");
            row.createCell(19).setCellValue(student.getAdmissionClass() != null ? student.getAdmissionClass() : "");
        }
    }

    private String extractFamilyDetail(String familyDetails, String key) {
        if (familyDetails == null || familyDetails.isEmpty()) return "";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(familyDetails);
            switch (key) {
                case "father":
                    return jsonNode.has("stdo_FatherName") ? jsonNode.get("stdo_FatherName").asText() : "";
                case "mother":
                    return jsonNode.has("stdo_MotherName") ? jsonNode.get("stdo_MotherName").asText() : "";
                case "primaryContact":
                    return jsonNode.has("stdo_primaryContact") ? jsonNode.get("stdo_primaryContact").asText() : "";
                case "secondaryContact":
                    return jsonNode.has("stdo_secondaryContact") ? jsonNode.get("stdo_secondaryContact").asText() : "";
                case "familyAddress":
                    return jsonNode.has("stdo_address") ? jsonNode.get("stdo_address").asText() : "";
                case "familyCity":
                    return jsonNode.has("stdo_city") ? jsonNode.get("stdo_city").asText() : "";
                case "familyState":
                    return jsonNode.has("stdo_state") ? jsonNode.get("stdo_state").asText() : "";
                case "familyEmail":
                    return jsonNode.has("stdo_email") ? jsonNode.get("stdo_email").asText() : "";
                default:
                    return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
