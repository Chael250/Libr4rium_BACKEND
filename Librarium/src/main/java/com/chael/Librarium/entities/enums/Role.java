package com.chael.Librarium.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_DELETE,
                    Permission.ADMIN_UPDATE,

                    Permission.LIBRARIAN_CREATE,
                    Permission.LIBRARIAN_UPDATE,
                    Permission.LIBRARIAN_DELETE,
                    Permission.LIBRARIAN_READ
            )
    ),
    USER(Collections.emptySet()),
    LIBRARIAN(
                Set.of(
                        Permission.LIBRARIAN_CREATE,
                        Permission.LIBRARIAN_UPDATE,
                        Permission.LIBRARIAN_DELETE,
                        Permission.LIBRARIAN_READ
                )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
