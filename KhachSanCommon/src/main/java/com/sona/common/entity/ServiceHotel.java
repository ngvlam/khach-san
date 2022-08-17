package com.sona.common.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "service")
public class ServiceHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 64, nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(name = "is_active")
    private boolean isActive;
}
