package com.arkdev.z9tkvtu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_login_data")
@PrimaryKeyJoinColumn(name = "user_id")
public class UserLoginData extends UserAccount implements UserDetails {
    @Size(max = 20)
    @Column(name = "username", length = 20)
    private String username;

    @Size(max = 255)
    @Column(name = "password_hash")
    private String passwordHash;

    @Size(max = 20)
    @Column(name = "email", length = 20)
    private String email;

    @Size(max = 10)
    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getRole().getPermissions()
                .stream()
                .map(permission ->
                        new SimpleGrantedAuthority("PERMISSION_" + permission.getPermissionName().toUpperCase()))
                .toList();
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}