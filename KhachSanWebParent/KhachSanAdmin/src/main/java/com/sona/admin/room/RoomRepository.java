package com.sona.admin.room;

import com.sona.common.entity.Account;
import com.sona.common.entity.Room;
import com.sona.common.entity.RoomCategory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE concat(r.id, ' ', r.note) like %?1%")
    public List<Room> findByKeyword(String keyword);

    @Query("UPDATE Room r SET r.isActive = ?2 where r.id = ?1")
    @Modifying
    public void updateActiveStatus(Integer id, boolean isActive);

    Long countById(Integer id);

    List<Room> getRoomsByStatus(int status);

    @Query("select r from Room r where r.status=?1 and r.isActive=?2")
    List<Room> getRoomsByStatusAndActive(int status, boolean active);

    List<Room> getRoomsByRoomCategory(RoomCategory roomCategory);
}
