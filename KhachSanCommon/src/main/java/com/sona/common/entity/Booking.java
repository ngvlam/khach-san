package com.sona.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ngay_dk")
    private Date ngayDK;
    @Column(name = "ngay_bd")
    private Date ngayBD;
    @Column(name = "ngay_kt")
    private Date ngayKT;
    @Column(name = "tien_coc")
    private Double tienCoc;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

}
