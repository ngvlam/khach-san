package com.sona.admin.room;

import com.sona.common.entity.Account;
import com.sona.common.entity.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE concat(r.id, ' ', r.note) like %?1%")
    public List<Room> findByKeyword(String keyword);
}
