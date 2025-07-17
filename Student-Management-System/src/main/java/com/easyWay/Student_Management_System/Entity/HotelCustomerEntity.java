package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class HotelCustomerEntity extends BaseEntityHotel{
    private String name;
    private String address;
    private String city;
    private String state;
    private String contact;
    private String adharNo;
    private String nationality;
    private String fingerprint_data;
    private String face_image;
    private String adharImgF;
    private String adharImgB;
}

