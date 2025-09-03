package com.chael.Librarium.dtos.userDtos;

import com.chael.Librarium.entities.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateDto(
        @NotEmpty(message = "provide a fname")
        @NotNull
        String fname,

        @NotEmpty(message = "provide a lname")
        @NotNull
        String lname,

        @NotEmpty(message = "provide a email")
        @NotNull
        String email,

        @NotEmpty(message = "provide a password")
        @NotNull
        String password,

        @NotEmpty(message = "provide a role")
        @NotNull
        Role role
) {
}
