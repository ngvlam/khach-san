package com.sona.admin.room;

import com.sona.admin.account.AcccountRepository;
import com.sona.admin.roomCategory.CategoryRepository;
import com.sona.common.entity.Account;
import com.sona.common.entity.Role;
import com.sona.common.entity.Room;
import com.sona.common.entity.RoomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Room> listAll() {
        return (List<Room>) roomRepository.findAll();
    }
    public List<RoomCategory> listCategories() {
        return (List<RoomCategory>) categoryRepository.findAll();
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getByKeyword(String keyword) {
        return roomRepository.findByKeyword(keyword);
    }


}
