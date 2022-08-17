package com.sona.admin.customer;

import com.sona.common.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> listAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Integer id) throws CustomerNotFoundException {
        Long countById = customerRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new CustomerNotFoundException("Không tìm thấy tài khoản với id " + id);
        }

        customerRepository.deleteById(id);
    }

    public Customer get(Integer id) throws CustomerNotFoundException {
        try {
            return customerRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new CustomerNotFoundException("Không tìm thấy tài khoản với id " + id);
        }
    }

    public boolean isUsernameUnique(Integer id, String email) {
        Customer customerByEmail = customerRepository.getAccountByEmail(email);
        if (customerByEmail == null) return true;
        boolean isCreatingNew = (id == null);
        if (isCreatingNew) {
            if (customerByEmail != null) return false;
        } else {
            if (customerByEmail.getId() != id) {
                return false;
            }
        }
        return true;
    }
    public List<Customer> getByKeyword(String keyword) {
        return customerRepository.findByKeyword(keyword);
    }

}
