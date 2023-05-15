package com.sona.admin;

import com.sona.admin.room.RoomRepository;
import com.sona.admin.roomCategory.CategoryRepository;
import com.sona.common.entity.RoomCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class testSaveProductWithImages {
    @Autowired
    private CategoryRepository repo;

    @Test
    public void testSaveProductWithImages() {
        Integer categoryId = 1;
        RoomCategory roomCategory = repo.findById(categoryId).get();
        roomCategory.setMainImage("main image.jpg");
        roomCategory.addExtraImage("extra image 1.png");
        roomCategory.addExtraImage("extra image 2.png");

        RoomCategory savedCategory = repo.save(roomCategory);
    }
}
