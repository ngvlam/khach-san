package com.sona.admin.usingService;

import com.sona.common.entity.UsingService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsingServiceRepository extends CrudRepository<UsingService, Integer> {

    @Query("SELECT u FROM UsingService u WHERE concat(u.id, ' ') like %?1%")
    List<UsingService> findByKeyword(String keyword);
    List<UsingService> getUsingServiceByBooking_Id(Integer booking_id);
}
