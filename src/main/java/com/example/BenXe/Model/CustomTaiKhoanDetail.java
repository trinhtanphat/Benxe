package com.example.BenXe.Model;

import com.example.BenXe.Repository.ITaiKhoanRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomTaiKhoanDetail implements UserDetails {
    public final TaiKhoan user;
    public final ITaiKhoanRepository userRepository;

    public CustomTaiKhoanDetail(TaiKhoan user, ITaiKhoanRepository userRepository) {
        this.user = user;
        this.userRepository = userRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        String role = userRepository.getRolesOfUser(user.getId());
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getMatKhau();
    }

    @Override
    public String getUsername() {
        return user.getTenDangNhap();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    public ITaiKhoanRepository getUserRepository() {
        return this.userRepository;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public TaiKhoan getTaiKhoan() {
        return user;
    }
    public String getTenLoaiTK() {
        return user.getLoaitk().getMaLoai().toString();
    }
}