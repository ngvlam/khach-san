package com.sona.admin.usingService;

import com.sona.admin.room.RoomService;
import com.sona.admin.serviceHotel.ServiceHotelService;
import com.sona.common.entity.Room;
import com.sona.common.entity.ServiceHotel;
import com.sona.common.entity.UsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsingServiceController {
    @Autowired
    UsingServiceService service;
    @Autowired
    ServiceHotelService serviceHotelService;
    @Autowired
    RoomService roomService;

    @GetMapping("/su-dung-dich-vu")
    public String listAll(Model model, @Param("keyword") String keyword) {
        if (keyword != null) {
            List<UsingService> usingServices = service.getByKeyword(keyword);
            double sum=0;
            List<Room> listRoomsUsing = roomService.getAllRoomsByStatus(1);
            model.addAttribute("listRoomsUsing", listRoomsUsing);
            model.addAttribute("usingServices", usingServices);
            model.addAttribute("keyword", keyword);
        } else {
            List<UsingService> usingServices = service.listAll();
            model.addAttribute("usingServices", usingServices);
        }
        return "usingService/all-using-services";
    }


    @GetMapping("/su-dung-dich-vu/them-su-dung-dich-vu")
    public String newUsingService(Model model) {
        UsingService usingService = new UsingService();
        usingService.setQuantity(1);
        List<ServiceHotel> serviceHotels = serviceHotelService.getAllServiceActiving();
        List<Room> listRoomsUsing = roomService.getAllRoomsByStatus(1);
        model.addAttribute("serviceHotels", serviceHotels);
        model.addAttribute("listRoomsUsing", listRoomsUsing);
        model.addAttribute("usingService", usingService);
        model.addAttribute("pageTitle", "Thêm sử dụng dịch vụ");
        return "usingService/form-using-service";
    }


}
