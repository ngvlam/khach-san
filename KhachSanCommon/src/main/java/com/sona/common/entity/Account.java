package com.sona.common.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 128, nullable = false, unique = true)
    private String username;
    @Column(length = 64, nullable = false)
    private String password;
    @Column(name = "full_name", length = 45, nullable = false)
    private String fullName;
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "gender")
    private int gender;
    @Column(name = "address")
    private String address;
    @Column(length = 64)
    private String photo;
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public void setDateOfBirth(String dateOfBirth){
        try {
            this.dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Transient
    public String getPhotosImagePath() {
        if (id == null || photo == null) return "/img/default-avatar.png";
        return "/user-photos/" + this.id + "/" + this.photo;
    }
}
