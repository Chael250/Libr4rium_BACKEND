package com.chael.Librarium.entities;

import com.chael.Librarium.entities.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "T_USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends BaseEntity implements UserDetails {

    @Column(
            name = "f_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "l_name",
            nullable=false
    )
    private String lastName;

    @Column(
            nullable = false
    )
    private String email;

    @Column(
            name = "password_hash",
            nullable=false
    )
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
