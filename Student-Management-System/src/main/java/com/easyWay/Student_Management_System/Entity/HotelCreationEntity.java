package com.easyWay.Student_Management_System.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class HotelCreationEntity extends BaseEntityHotel{

    public String hotelName;
    public String ownerName;
    public String contactNumber;
    public String email;
    public String password;
    public String address;
    public String city;
    public String state;
    public String pincode;
    public String country;
    public String totalRooms;
    public String subscription;
    public String gstNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id", nullable = false)
    @JsonIgnore
    private Users userInfo4;
}
