package com.sona.admin;

import com.sona.admin.account.AcccountRepository;
import com.sona.admin.account.AccountService;
import com.sona.admin.booking.BookingService;
import com.sona.admin.room.RoomService;
import com.sona.common.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private RoomService roomService;
    @GetMapping("")
    public String viewHomePage(Model model) {
        model.addAttribute("totalBooking", bookingService.getTotalBooking());
        model.addAttribute("totalRoom", roomService.getTotalAvailableRoom());
        return "index";
    }

    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        return "login";
    }
}
