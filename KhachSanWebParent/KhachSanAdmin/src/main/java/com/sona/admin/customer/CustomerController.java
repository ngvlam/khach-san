package com.sona.admin.customer;

import com.sona.admin.account.UserNotFoundException;
import com.sona.admin.room.RoomService;
import com.sona.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/khach-hang")
    public String listAll(Model model, @Param("keyword") String keyword) {
        if (keyword != null) {
            List<Customer> customerList = customerService.getByKeyword(keyword);
            model.addAttribute("customerList", customerList);
            model.addAttribute("keyword", keyword);
        } else {
            List<Customer> customerList = customerService.listAll();
            model.addAttribute("customerList", customerList);
        }
        return "customer/all-customers";
    }


    @GetMapping("/khach-hang/them-khach-hang")
    public String newCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("pageTitle", "Thêm khách hàng");
        model.addAttribute("customer", customer);
        return "customer/form-customer";
    }

    @PostMapping("/khach-hang/save")
    public String saveCustomer(@Valid Customer customer, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
           return  "customer/form-customer";
        }
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Thêm khách hàng mới thành công");
        return "redirect:/khach-hang";
    }

    //Sua thong tin tai khoan
    @GetMapping("/khach-hang/sua/{id}")
    public String editCustomer(@PathVariable(name = "id") Integer id,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        try {
            Customer customer = customerService.get(id);
            model.addAttribute("pageTitle", "Sửa khách hàng");
            model.addAttribute("customer", customer);
            return "customer/form-customer";
        } catch (CustomerNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/khach-hang";
        }
    }

    @GetMapping("/khach-hang/xoa/{id}")
    public String deleteCustomer(@PathVariable(name = "id") Integer id,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        try {
            customerService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Tài khoản có id " + id + " đã xóa thành công");
        } catch (CustomerNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/khach-hang";
    }
}
