package com.sona.admin.room;

import com.sona.admin.serviceHotel.ServiceHotelNotFoundException;
import com.sona.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping("/room")
    public String listAll(Model model, @Param("keyword") String keyword) {
        if (keyword != null) {
            List<Room> roomList = roomService.getByKeyword(keyword);
            model.addAttribute("roomList", roomList);
            model.addAttribute("keyword", keyword);
        } else {
            List<Room> roomList = roomService.listAll();
            model.addAttribute("roomList", roomList);
        }
        return "room/all-rooms";
    }

//    @GetMapping("/roomByCategory")
//    public List<Room> getRoomByCategory(@Param("id") Integer id) {
//
//    }
    @GetMapping("/room/them-phong")
    public String newRoom(Model model) {
        Room room = new Room();
        List<RoomCategory> categories = roomService.listCategories();
        room.setActive(true);
        model.addAttribute("room", room);
        model.addAttribute("categories", categories);
        model.addAttribute("pageTitle", "Thêm phòng");
        return "room/form-room";
    }
    @PostMapping("/room/save")
    public String saveCustomer(Room room, RedirectAttributes redirectAttributes) {
        roomService.save(room);
        redirectAttributes.addFlashAttribute("message", "Thêm phòng mới thành công");
        return "redirect:/room";
    }
    @GetMapping("/room/sua/{id}")
    public String editService(@PathVariable(name = "id") Integer id,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        try {
            Room room = roomService.get(id);
            List<RoomCategory> categories = roomService.listCategories();
            model.addAttribute("room", room);
            model.addAttribute("categories", categories);
            model.addAttribute("pageTitle", "Sửa phòng");
            return "room/form-room";
        } catch (ServiceHotelNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/room";
        }
    }
    //Cap nhat hoạt dong phong
    @GetMapping("/room/{id}/hoat-dong/{active}")
    public String updateRoomActive(@PathVariable("id") Integer id, @PathVariable("active") boolean active, RedirectAttributes redirectAttributes) {
        roomService.updateRoomActive(id, active);
        String status = active ? "cho phép sử dụng" : "không cho phép sử dụng";
        String message = "Phòng " + id + " đã " + status;
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/room";
    }
    // Xoa phong
    @GetMapping("/room/xoa/{id}")
    public String deleteRoom(@PathVariable(name = "id") Integer id,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        try {
            roomService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Phòng " + id + " đã xóa thành công");
        } catch (RoomNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/room";
    }
}
