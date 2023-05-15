package com.sona.admin.roomCategory;

import com.sona.admin.FileUploadUtil;
import com.sona.admin.room.RoomNotFoundException;
import com.sona.admin.room.RoomService;
import com.sona.common.entity.Account;
import com.sona.common.entity.Room;
import com.sona.common.entity.RoomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/loai-phong")
    public String listAll(Model model, @Param("keyword") String keyword) {
        if (keyword != null) {
            List<RoomCategory> categories = categoryService.getByKeyword(keyword);
            model.addAttribute("categories", categories);
            model.addAttribute("keyword", keyword);
        } else {
            List<RoomCategory> categories = categoryService.listAll();
            model.addAttribute("categories", categories);
        }
        return "roomCategory/all-room-categories";
    }

    @PostMapping("/loai-phong/save")
    public String saveRoomCategory(RoomCategory roomCategory, RedirectAttributes redirectAttributes,
                                   @RequestParam("fileImage") MultipartFile multipartFile, @RequestParam("extraImage") MultipartFile[] extraImageMultiparts) throws IOException {
        setMainImageName(multipartFile, roomCategory);
        setExtraImageNames(extraImageMultiparts, roomCategory);
        RoomCategory savedCategory = categoryService.save(roomCategory);
        saveUploadedImages(multipartFile, extraImageMultiparts, savedCategory);

        redirectAttributes.addFlashAttribute("message", "Thêm loại phòng mới thành công");
        return "redirect:/loai-phong";
    }

    private void setMainImageName(MultipartFile mainImageMultipart, RoomCategory roomCategory) {
        if (!mainImageMultipart.isEmpty()) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(mainImageMultipart.getOriginalFilename()));
            roomCategory.setMainImage(fileName);
        }
    }

    private void setExtraImageNames(MultipartFile[] extraImageMultiparts, RoomCategory roomCategory) {
        if (extraImageMultiparts.length > 0) {
            for (MultipartFile multipartFile : extraImageMultiparts) {
                if (!multipartFile.isEmpty()) {
                    String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                    roomCategory.addExtraImage(fileName);
                }
            }
        }
    }

    private void saveUploadedImages(MultipartFile mainMultipart, MultipartFile[] extraImageMultiparts, RoomCategory savedCategory) throws IOException {
       if (!mainMultipart.isEmpty()) {
           String fileName = StringUtils.cleanPath(Objects.requireNonNull(mainMultipart.getOriginalFilename()));
           String uploadDir = "category-images/" + savedCategory.getId();
           FileUploadUtil.cleanDir(uploadDir);
           FileUploadUtil.saveFile(uploadDir, fileName, mainMultipart);
       }

       if (extraImageMultiparts.length > 0) {
           String uploadDir = "category-images/" + savedCategory.getId() + "/extras";

           for (MultipartFile multipartFile : extraImageMultiparts) {
               if (multipartFile.isEmpty()) continue;
               String fileName = StringUtils.cleanPath(Objects.requireNonNull(mainMultipart.getOriginalFilename()));
               FileUploadUtil.saveFile(uploadDir, fileName, mainMultipart);
           }
       }
    }

    @GetMapping("/loai-phong/them-loai-phong")
    public String newRoomCategory(Model model) {
        RoomCategory roomCategory = new RoomCategory();
        model.addAttribute("roomCategory", roomCategory);
        model.addAttribute("pageTitle", "Thêm loại phòng");
        return "roomCategory/form-room-category";
    }

    @GetMapping("/loai-phong/xoa/{id}")
    public String deleteCategory(@PathVariable(name = "id") Integer id,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        try {
            categoryService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Loại phòng " + id + " đã xóa thành công");
        } catch (RoomCategoryNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/loai-phong";
    }
}
