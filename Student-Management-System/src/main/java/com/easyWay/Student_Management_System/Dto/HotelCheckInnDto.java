package com.easyWay.Student_Management_System.Dto;

import com.easyWay.Student_Management_System.Entity.HotelCustomersEntity;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.UUID;

@Data
public class HotelCheckInnDto {


    @Column(name = "arrival_date")
    private String arrivalDate;

    @Column(name = "guest_names")
    private String guestNames;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

    @Column(name = "company")
    private String company;

    @Column(name = "id_details")
    private String idDetails;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "male_count")
    private String maleCount;

    @Column(name = "female_count")
    private String femaleCount;

    @Column(name = "child_count")
    private String childCount;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "coming_from")
    private String comingFrom;

    @Column(name = "going_to")
    private String goingTo;

    @Column(name = "departure_date")
    private String departureDate;

    @Column(name = "transport")
    private String transport;

    @Column(name = "deposit")
    private String deposit;

    @Column(name = "bill_no")
    private String billNo;

    @Column(name = "amount")
    private String amount;

    @Column(name = "remarks")
    private String remarks;

    private HotelCustomersEntity customersEntity;
}
