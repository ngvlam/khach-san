package com.sona.admin.roomCategory;

import com.sona.common.entity.Room;
import com.sona.common.entity.RoomCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<RoomCategory, Integer> {

    @Query("SELECT r FROM RoomCategory r WHERE concat(r.id, ' ', r.name, '', r.description) like %?1%")
    List<RoomCategory> findByKeyword(String keyword);

    Long countById(Integer id);
}
