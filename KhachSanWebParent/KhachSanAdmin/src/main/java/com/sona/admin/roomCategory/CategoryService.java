package com.sona.admin.roomCategory;

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
}
