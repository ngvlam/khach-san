package com.sona.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "room_category")
public class RoomCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 64, nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column
    private float size;
    @Column
    private String amenities;
    @Column
    private String photo;
    @Column
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roomCategory")
    private List<Room> rooms;
}
