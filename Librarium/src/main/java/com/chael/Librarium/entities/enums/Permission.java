package com.chael.Librarium.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    LIBRARIAN_READ("librarian:read"),
    LIBRARIAN_UPDATE("librarian:update"),
    LIBRARIAN_CREATE("librarians:create"),
    LIBRARIAN_DELETE("librarians:delete");

    @Getter
    private final String permission;
}
