package com.sona.admin.security;

import com.sona.admin.account.AcccountRepository;
import com.sona.admin.account.AccountService;
import com.sona.admin.account.RoleRepository;
import com.sona.common.entity.Account;
import com.sona.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class KhachSanUserDetailsService implements UserDetailsService {

    @Autowired
    private AcccountRepository acccountRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = acccountRepository.getAccountByUsername(username);

        if (account != null) {
            return new KhachSanUserDetails(account);
        }
        throw new UsernameNotFoundException("Không tìm thấy tài khoản có username là: " + username);
    }
}
