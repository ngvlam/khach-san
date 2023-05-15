package com.sona.admin.serviceHotel;

import com.sona.admin.customer.CustomerNotFoundException;
import com.sona.admin.customer.CustomerRepository;
import com.sona.common.entity.Customer;
import com.sona.common.entity.ServiceHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ServiceHotelService {
    @Autowired
    private ServiceHotelRepository serviceHotelRepository;

    public List<ServiceHotel> listAll() {
        return (List<ServiceHotel>) serviceHotelRepository.findAll();
    }
    public List<ServiceHotel> getAllServiceActiving() {
        return serviceHotelRepository.getAllServiceActiving();
    }

    public ServiceHotel save(ServiceHotel serviceHotel) {
        return serviceHotelRepository.save(serviceHotel);
    }

    public void delete(Integer id) throws ServiceHotelNotFoundException {
        Long countById = serviceHotelRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new ServiceHotelNotFoundException("Không tìm thấy dịch vụ với id " + id);
        }

        serviceHotelRepository.deleteById(id);
    }

    public ServiceHotel get(Integer id) throws ServiceHotelNotFoundException {
        try {
            return serviceHotelRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new ServiceHotelNotFoundException("Không tìm thấy dịch vụ với id " + id);
        }
    }

    public List<ServiceHotel> getByKeyword(String keyword) {
        return serviceHotelRepository.findByKeyword(keyword);
    }

    public void updateServiceEnabledStatus(Integer id, boolean enabled) {
        serviceHotelRepository.updateEnabledStatus(id, enabled);
    }
}
