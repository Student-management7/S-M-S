package com.easyWay.Student_Management_System.Entity;

import com.easyWay.Student_Management_System.Enums.FileStatus;
import com.easyWay.Student_Management_System.Enums.FileType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

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
    private FileType fileType;
    private FileStatus fileStatus;


}
