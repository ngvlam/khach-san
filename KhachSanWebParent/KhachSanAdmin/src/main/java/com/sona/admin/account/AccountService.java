package com.sona.admin.account;

import com.sona.common.entity.Account;
import com.sona.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class AccountService {
    @Autowired
    private AcccountRepository acccountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<Account> listAll() {
        return (List<Account>) acccountRepository.findAll();
    }

    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    public Account save(Account account) {
        boolean isUpdatingUser = (account.getId() != null);

        if (isUpdatingUser) {
            Account existingUser = acccountRepository.findById(account.getId()).get();
            if (account.getPassword().isEmpty()) {
                account.setPassword(existingUser.getPassword());
            } else {
                encodePassword(account);
            }
        } else {
            encodePassword(account);
        }
        return acccountRepository.save(account);
    }

    private void encodePassword(Account account) {
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
    }

    public Account findAccountByUsername(String username) {
        return acccountRepository.getAccountByUsername(username);
    }

    public boolean isUsernameUnique(Integer id, String username) {
        Account accountByUsername = findAccountByUsername(username);
        if (accountByUsername == null) return true;
        boolean isCreatingNew = (id == null);
        if (isCreatingNew) {
            if (accountByUsername != null) return false;
        } else {
            if (accountByUsername.getId() != id) {
                return false;
            }
        }
        return true;
    }

    public Account get(Integer id) throws UserNotFoundException {
        try {
            return acccountRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }
    }

    public void updateAccEnabledStatus(Integer id, boolean enabled) {
        acccountRepository.updateEnabledStatus(id, enabled);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long countById = acccountRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }

        acccountRepository.deleteById(id);
    }

    public List<Account> getByKeyword(String keyword) {
        return acccountRepository.findByKeyword(keyword);
    }

}
