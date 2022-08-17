package com.sona.common.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 125, nullable = false)
    private String email;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "identity_card")
    private String identityCard;
    @Column(name = "gender")
    private int gender;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address")
    private String address;
}
