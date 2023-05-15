package com.sona.admin.invoice;

import com.sona.common.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    public List<Invoice> listall(){
        return (List<Invoice>) invoiceRepository.findAll();
    }


    public double totalRoomCharge() {
        return 1;
    }
}
