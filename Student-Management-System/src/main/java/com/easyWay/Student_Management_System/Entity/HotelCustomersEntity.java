package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class HotelCustomersEntity extends BaseEntityHotel{
    private String name;
    private String address;
    private String city;
    private String state;
    private String contact;
    private String adharNo;
    private String nationality;


    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] fingerprint_data;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] face_image;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] adharImgF;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "adharImgB")
    private byte[] adharImgB;

}

