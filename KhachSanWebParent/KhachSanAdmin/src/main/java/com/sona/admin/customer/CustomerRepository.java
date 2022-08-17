package com.sona.admin.customer;

import com.sona.common.entity.Customer;
import com.sona.common.entity.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Query("SELECT c FROM Customer c WHERE concat(c.id, ' ', c.email, ' ', c.address, ' ', c.fullName) like %?1%")
    public List<Customer> findByKeyword(String keyword);

    Long countById(Integer id);

    @Query("select c from Customer c where c.email = :email")
    Customer getAccountByEmail(@Param("email") String email);
}
