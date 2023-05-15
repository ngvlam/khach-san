package com.sona.admin.booking;

import com.sona.admin.customer.CustomerService;
import com.sona.admin.room.RoomService;
import com.sona.common.entity.Booking;
import com.sona.common.entity.Customer;
import com.sona.common.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    RoomService roomService;
    @Autowired
    CustomerService customerService;
    // lấy danh sách đặt phòng
    @GetMapping("/booking")
    public String listAll(Model model, @Param("keyword") String keyword) {
        if (keyword != null) {
            List<Booking> bookings = bookingService.getByKeyword(keyword);


            model.addAttribute("bookings", bookings);
            model.addAttribute("keyword", keyword);
        } else {
            List<Booking> bookings = bookingService.listAll();
            model.addAttribute("bookings", bookings);
        }
        return "booking/all-booking";
    }
    // thêm đặt phòng
    @GetMapping("/booking/them-dat-phong")
    public String newBooking(Model model) {
        Booking booking = new Booking();
        //Lay cac phong dang san sang va cho su dung
        List<Room> rooms = roomService.getRoomsByStatusAndActive(1, true);
        List<Customer> customers = customerService.listAll();

        model.addAttribute("booking", booking);
        LocalDate now = LocalDate.now();
        model.addAttribute("now", now);
        model.addAttribute("rooms", rooms);
        model.addAttribute("customers", customers);
        model.addAttribute("pageTitle", "Thêm đơn đặt phòng");
        return "booking/form-booking";
    }
    // sửa đặt phòng
    @PostMapping("/booking/save")
    public String saveBooking(Booking booking, RedirectAttributes redirectAttributes) {
        booking.setRegisterDate(new Date());
        booking.setStatus(1);
        bookingService.save(booking);
        redirectAttributes.addFlashAttribute("message", "Đặt phòng thành công");
        return "redirect:/booking";
    }
    // sửa đặt phòng
    @GetMapping("/booking/sua/{id}")
    public String editBooking(@PathVariable(name = "id") Integer id,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        try {
            Booking booking = bookingService.get(id);
            List<Room> rooms = roomService.getRoomsByStatusAndActive(1, true);
            List<Customer> customers = customerService.listAll();


            model.addAttribute("booking", booking);
            model.addAttribute("rooms", rooms);
            model.addAttribute("customers", customers);
            model.addAttribute("pageTitle", "Sửa đơn đặt phòng");
            return "booking/form-booking";
        } catch (BookingNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/booking";
        }
    }
    //xóa đặt phòng
    @GetMapping("/booking/xoa/{id}")
    public String deleteBooking(@PathVariable(name = "id") Integer id,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        try {

            bookingService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Đã xóa đơn đặt phòng " + id + " thành công");
        } catch (BookingNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/booking";
    }
}
