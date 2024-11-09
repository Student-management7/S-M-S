package com.easyWay.Student_Management_System.Entity;

import com.easyWay.Student_Management_System.Enums.FileStatus;
import com.easyWay.Student_Management_System.Enums.FileType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "file_tracking")
public class FileTracking extends BaseEntity {

    private String fileName;
    private Long  total;
    private Long  success;
    private Long failure;
    @Enumerated(EnumType.STRING)
    private FileType fileType;
    @Enumerated(EnumType.STRING)
    private FileStatus fileStatus;

    @OneToMany( mappedBy = "fileTracking",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<StudentInfo> studentInfo;


}
