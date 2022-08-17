package com.sona.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "is_active")
    private boolean isActive;
    @Column
    private int status;
    @Column
    private int floor;
    @Column
    private String note;

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private RoomCategory roomCategory;
}
