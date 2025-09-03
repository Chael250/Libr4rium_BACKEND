package com.chael.Librarium.dtos.userDtos;

import com.chael.Librarium.entities.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record PatchDto(
        String fname,
        String lname,
        String email,
        String password,
        Role role
) {
}
