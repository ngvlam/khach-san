package com.sona.admin.security;

import com.sona.common.entity.Account;
import com.sona.common.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class KhachSanUserDetails implements UserDetails {
    private Account account;

    public KhachSanUserDetails(Account account) {
        this.account = account;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = account.getRole();
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return this.account.getFullName();
    }
    public String getPhoto() {
        return this.account.getPhoto();
    }

}
