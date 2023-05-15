package com.sona.admin.invoice;

import com.sona.admin.usingService.UsingServiceService;
import com.sona.common.entity.Invoice;
import com.sona.common.entity.UsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    UsingServiceService service;
    @GetMapping("/hoa-don")
    public String listAll(Model model) {
            List<Invoice> invoices = invoiceService.listall();
            model.addAttribute("invoices", invoices);
        return "invoices";
    }
}
