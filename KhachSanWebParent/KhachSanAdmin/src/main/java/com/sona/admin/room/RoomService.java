package com.sona.admin.room;

import com.sona.admin.account.AcccountRepository;
import com.sona.admin.account.UserNotFoundException;
import com.sona.admin.roomCategory.CategoryRepository;
import com.sona.admin.serviceHotel.ServiceHotelNotFoundException;
import com.sona.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public List<Room> getAllRoomsByStatus(int status) {
        return roomRepository.getRoomsByStatus(status);
    }

    public List<Room> getRoomsByStatusAndActive(int status, boolean active) {
        return roomRepository.getRoomsByStatusAndActive(status, active);
    }

    public Room get(Integer id) throws ServiceHotelNotFoundException {
        try {
            return roomRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new ServiceHotelNotFoundException("Không tìm thấy dịch vụ với id " + id);
        }
    }
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getByKeyword(String keyword) {
        return roomRepository.findByKeyword(keyword);
    }


    public void updateRoomActive(Integer id, boolean isActive) {
        roomRepository.updateActiveStatus(id, isActive);
    }

    public void delete(Integer id) throws RoomNotFoundException{
        Long countById = roomRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new RoomNotFoundException("Không tìm thấy phòng " + id);
        }
        roomRepository.deleteById(id);
    }

    public int getTotalRoom() {
        return listAll().size();
    }

    public int getTotalAvailableRoom() {
        return listAll().stream().filter(r -> r.getStatus() == 1 && r.isActive()).toList().size();
    }
}
