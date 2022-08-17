package com.sona.admin.room;

import com.sona.admin.FileUploadUtil;
import com.sona.admin.account.UserNotFoundException;
import com.sona.common.entity.Account;
import com.sona.common.entity.Role;
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
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping("/room")
    public String listAll(Model model, @Param("keyword") String keyword) {
        if (keyword != null) {
            List<Room> roomList = roomService.getByKeyword(keyword);
            model.addAttribute("roomList", roomList);
            model.addAttribute("keyword", keyword);
        } else {
            List<Room> roomList = roomService.listAll();
            model.addAttribute("roomList", roomList);
        }
        return "all-rooms";
    }

    @GetMapping("/room/them-phong")
    public String newRoom(Model model) {
        Room room = new Room();
        List<RoomCategory> categories = roomService.listCategories();
        room.setActive(true);
        model.addAttribute("room", room);
        model.addAttribute("categories", categories);
        return "form-room";
    }
//
//    @PostMapping("/thanh-vien/save")
//    public String saveAccount(Account account, RedirectAttributes redirectAttributes, @RequestParam("avatar") MultipartFile multipartFile) throws IOException {
//        if (!multipartFile.isEmpty()) {
//            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//            account.setPhoto(fileName);
//            Account saveAcc = accountService.save(account);
//            String uploadDir = "user-photos/" + saveAcc.getId();
//            FileUploadUtil.cleanDir(uploadDir);
//            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
//        } else  {
//            account.setPhoto(null);
//            accountService.save(account);
//        }
//        redirectAttributes.addFlashAttribute("message", "Thêm tài khoản mới thành công");
//        return "redirect:/thanh-vien";
//    }
//
//    //Sua thong tin phong
//    @GetMapping("/thanh-vien/sua/{id}")
//    public String editStaff(@PathVariable(name = "id") Integer id,
//                            Model model,
//                            RedirectAttributes redirectAttributes) {
//        try {
//            Account account = accountService.get(id);
//            List<Role> roleList = accountService.listRoles();
//
//            model.addAttribute("account", account);
//            model.addAttribute("roleList", roleList);
//            return "edit-staff";
//        } catch (UserNotFoundException e) {
//            redirectAttributes.addFlashAttribute("message", e.getMessage());
//            return "redirect:/thanh-vien";
//
//        }
//
//    }
//
//    //Cap nhat trang thai phong
//    @GetMapping("/thanh-vien/{id}/enabled/{status}")
//    public String updateUserEnableStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
//        accountService.updateAccEnabledStatus(id, enabled);
//        String status = enabled ? "enabled" : "disabled";
//        String message = "Thành viên có id " + id + " đã " + status;
//        redirectAttributes.addFlashAttribute("message", message);
//        return "redirect:/thanh-vien";
//    }
//
//    // Xoa tai khoan
//    @GetMapping("/thanh-vien/delete/{id}")
//    public String deleteStaff(@PathVariable(name = "id") Integer id,
//                              Model model,
//                              RedirectAttributes redirectAttributes) {
//        try {
//            accountService.delete(id);
//            redirectAttributes.addFlashAttribute("message", "Tài khoản có id " + id + " đã xóa thành công");
//        } catch (UserNotFoundException e) {
//            redirectAttributes.addFlashAttribute("message", e.getMessage());
//        }
//        return "redirect:/thanh-vien";
//    }
}
