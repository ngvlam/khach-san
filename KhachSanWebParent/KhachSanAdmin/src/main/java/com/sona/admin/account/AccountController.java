package com.sona.admin.account;

import com.sona.admin.FileUploadUtil;
import com.sona.common.entity.Account;
import com.sona.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/thanh-vien")
    public String listAll(Model model, @Param("keyword") String keyword) {
        if (keyword != null) {
            List<Account> accountList = accountService.getByKeyword(keyword);
            model.addAttribute("accountList", accountList);
            model.addAttribute("keyword", keyword);
        } else {
            List<Account> accountList = accountService.listAll();
            model.addAttribute("accountList", accountList);
        }
        return "all-staff";
    }

    @GetMapping("/thanh-vien/them-thanh-vien")
    public String newAccount(Model model) {
        Account account = new Account();
        List<Role> roleList = accountService.listRoles();
        account.setEnabled(true);
        model.addAttribute("account", account);
        model.addAttribute("roleList", roleList);
        return "add-staff";
    }

    @PostMapping("/thanh-vien/save")
    public String saveAccount(Account account, RedirectAttributes redirectAttributes, @RequestParam("avatar") MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            account.setPhoto(fileName);
            Account saveAcc = accountService.save(account);
            String uploadDir = "user-photos/" + saveAcc.getId();
            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else  {
             account.setPhoto(null);
            accountService.save(account);
        }
        redirectAttributes.addFlashAttribute("message", "Thêm tài khoản mới thành công");
        return "redirect:/thanh-vien";
    }

    //Sua thong tin tai khoan
    @GetMapping("/thanh-vien/sua/{id}")
    public String editStaff(@PathVariable(name = "id") Integer id,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        try {
            Account account = accountService.get(id);
            List<Role> roleList = accountService.listRoles();

            model.addAttribute("account", account);
            model.addAttribute("roleList", roleList);
            return "edit-staff";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/thanh-vien";
        }
    }

    //Cap nhat trang thai nguoi dung
    @GetMapping("/thanh-vien/{id}/enabled/{status}")
    public String updateUserEnableStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
        accountService.updateAccEnabledStatus(id, enabled);
        String status = enabled ? "enabled" : "disabled";
        String message = "Thành viên có id " + id + " đã " + status;
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/thanh-vien";
    }

        // Xoa tai khoan
        @GetMapping("/thanh-vien/xoa/{id}")
        public String deleteStaff(@PathVariable(name = "id") Integer id,
                Model model,
                RedirectAttributes redirectAttributes) {
            try {
                accountService.delete(id);
                redirectAttributes.addFlashAttribute("message", "Tài khoản có id " + id + " đã xóa thành công");
            } catch (UserNotFoundException e) {
                redirectAttributes.addFlashAttribute("message", e.getMessage());
            }
            return "redirect:/thanh-vien";
        }
}
