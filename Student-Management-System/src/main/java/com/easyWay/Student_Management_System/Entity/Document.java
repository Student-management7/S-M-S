package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Document extends BaseEntity{

    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;
    private String title;
    private String cls;
    private String subject;
    private boolean publish;
}
