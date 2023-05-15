package com.sona.admin.serviceHotel;

import com.sona.admin.customer.CustomerNotFoundException;
import com.sona.admin.customer.CustomerService;
import com.sona.common.entity.Customer;
import com.sona.common.entity.ServiceHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ServiceHotelController {
    @Autowired
    ServiceHotelService serviceHotelService;
    // lấy danh sách dịch vụ
    @GetMapping("/dich-vu")
    public String listAll(Model model, @Param("keyword") String keyword) {
        if (keyword != null) {
            List<ServiceHotel> serviceHotels = serviceHotelService.getByKeyword(keyword);
            model.addAttribute("serviceHotels", serviceHotels);
            model.addAttribute("keyword", keyword);
        } else {
            List<ServiceHotel> serviceHotels = serviceHotelService.listAll();
            model.addAttribute("serviceHotels", serviceHotels);
        }
        return "service/all-services";
    }
    // thêm dịch vụ
    @GetMapping("/dich-vu/them-dich-vu")
    public String newService(Model model) {
        ServiceHotel serviceHotel = new ServiceHotel();
        serviceHotel.setActive(true);
        model.addAttribute("serviceHotel", serviceHotel);
        model.addAttribute("pageTitle", "Thêm dịch vụ");
        return "service/form-service";
    }

    @PostMapping("/dich-vu/save")
    public String saveService(ServiceHotel serviceHotel, RedirectAttributes redirectAttributes) {
        serviceHotelService.save(serviceHotel);
        redirectAttributes.addFlashAttribute("message", "Thêm dịch vụ mới thành công");
        return "redirect:/dich-vu";
    }

    //Sua thong tin dịch vụ
    @GetMapping("/dich-vu/sua/{id}")
    public String editService(@PathVariable(name = "id") Integer id,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        try {
            ServiceHotel serviceHotel = serviceHotelService.get(id);
            model.addAttribute("serviceHotel", serviceHotel);
            return "service/form-service";
        } catch (ServiceHotelNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/dich-vu";
        }
    }
    // xóa dịch vụ
    @GetMapping("/dich-vu/xoa/{id}")
    public String deleteService(@PathVariable(name = "id") Integer id,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        try {
            serviceHotelService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Dịch vụ có id " + id + " đã xóa thành công");
        } catch (ServiceHotelNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/dich-vu";
    }

    @GetMapping("/dich-vu/{id}/enabled/{status}")
    public String updateServiceEnableStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
        serviceHotelService.updateServiceEnabledStatus(id, enabled);
        String status = enabled ? "enabled" : "disabled";
        String message = "Dịch vụ có id " + id + " đã " + status;
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/dich-vu";
    }
}


