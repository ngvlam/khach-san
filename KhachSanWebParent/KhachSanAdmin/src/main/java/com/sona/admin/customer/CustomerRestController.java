package com.sona.admin.customer;

import com.sona.admin.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {
    @Autowired
    private CustomerService service;

    @PostMapping("/khach-hang/check_email")
    public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email) {
        return service.isUsernameUnique(id, email) ? "OK" : "Duplicated";
    }
}
