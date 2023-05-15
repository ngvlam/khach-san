package com.sona.admin.roomCategory;

import com.sona.admin.room.RoomNotFoundException;
import com.sona.common.entity.Room;
import com.sona.common.entity.RoomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<RoomCategory> listAll() {
        return (List<RoomCategory>) categoryRepository.findAll();
    }

    public List<RoomCategory> getByKeyword(String keyword) {
        return categoryRepository.findByKeyword(keyword);
    }

    public RoomCategory save(RoomCategory roomCategory) {
        return categoryRepository.save(roomCategory);
    }

    public void delete(Integer id) throws RoomCategoryNotFoundException{
        Long countById = categoryRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new RoomCategoryNotFoundException("Không tìm thấy loại phòng " + id);
        }
        categoryRepository.deleteById(id);
    }
}
