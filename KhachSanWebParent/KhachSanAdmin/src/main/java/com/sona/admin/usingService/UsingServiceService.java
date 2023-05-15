package com.sona.admin.usingService;

import com.sona.common.entity.Room;
import com.sona.common.entity.UsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsingServiceService {
    @Autowired
    UsingServiceRepository usingServiceRepository;

    public List<UsingService> listAll() {
        return (List<UsingService>) usingServiceRepository.findAll();
    }

    public List<UsingService> getByKeyword(String keyword) {
        return usingServiceRepository.findByKeyword(keyword);
    }

    public List<UsingService> getbybookingid(Integer id) {
        return  usingServiceRepository.getUsingServiceByBooking_Id(id);
    }

}
