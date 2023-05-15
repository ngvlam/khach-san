package com.sona.admin.booking;

import com.sona.common.entity.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface BookingRepository extends CrudRepository<Booking, Integer> {
    @Query("select b from Booking b WHERE concat(b.id, ' ', b.checkinDate, ' ', b.checkoutDate, ' ', b.customer.fullName, ' ', b.registerDate) like %?1%")
    List<Booking> findByKeyword(String keyword);

    Long countById(Integer id);
}
