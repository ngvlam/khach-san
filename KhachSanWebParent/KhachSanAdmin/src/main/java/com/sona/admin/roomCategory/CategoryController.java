package com.sona.admin.roomCategory;

import com.sona.admin.room.RoomService;
import com.sona.common.entity.Room;
import com.sona.common.entity.RoomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/loai-phong")
    public String listAll(Model model, @Param("keyword") String keyword) {
        if (keyword != null) {
            List<RoomCategory> categories = categoryService.getByKeyword(keyword);
            model.addAttribute("categories", categories);
            model.addAttribute("keyword", keyword);
        } else {
            List<RoomCategory> categories = categoryService.listAll();
            model.addAttribute("categories", categories);
        }
        return "all-category-rooms";
    }

    @GetMapping("/room/them-loai-phong")
    public String newRoomCategory(Model model) {
        RoomCategory roomCategory = new RoomCategory();
        model.addAttribute("roomCategory", roomCategory);
        return "form-room-category";
    }
}
