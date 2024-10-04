package com.easyWay.Student_Management_System.Entity;

import com.easyWay.Student_Management_System.Dto.FamilyDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_info")
public class StudentInfo extends BaseEntity {

    public String name;
    public String address;
    public String city;
    public String std_state;
    @Column(columnDefinition = "text")
    public String familyDetails;
    public String contact;
    public String gender;
    public String dob;
    public String email;
    public String cls;
    public String department;
    public String category;
    public String creation;

}
