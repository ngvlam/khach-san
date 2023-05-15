package com.sona.khachsansite.room;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomController {
    @GetMapping("/rooms")
    public String viewRoomsPage() {
        return "rooms";
    }
    @GetMapping("/chi-tiet-phong")
    public String viewDetail() {
        return "room-details";
    }

    @GetMapping("/dat-phong")
    public String viewBooking() {
        return "form-booking";
    }

    @GetMapping("/dat-thanh-cong")
    public String viewSucess() {
        return "booking-success";
    }
}
