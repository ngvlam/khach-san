package com.sona.admin.serviceHotel;

import com.sona.common.entity.Customer;
import com.sona.common.entity.ServiceHotel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceHotelRepository extends CrudRepository<ServiceHotel, Integer> {
    @Query("SELECT c FROM ServiceHotel c WHERE concat(c.id, ' ', c.name, ' ', c.price) like %?1%")
    public List<ServiceHotel> findByKeyword(String keyword);

    Long countById(Integer id);

    @Query("UPDATE ServiceHotel s SET s.isActive = ?2 where s.id = ?1")
    @Modifying
    public void updateEnabledStatus(Integer id, boolean enabled);

    @Query("select s from ServiceHotel s where s.isActive = true")
    List<ServiceHotel> getAllServiceActiving();
}
