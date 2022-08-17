package com.sona.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;

@SpringBootApplication
@EntityScan({"com.sona.common.entity", "com.sona.admin.taiKhoan"})
public class KhachSanAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(KhachSanAdminApplication.class, args);
    }

}
