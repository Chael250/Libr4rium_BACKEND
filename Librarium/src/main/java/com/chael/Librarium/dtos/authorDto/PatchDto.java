package com.chael.Librarium.dtos.authorDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public record PatchDto(
        @NotEmpty(message = "Enter artist name")
        String artistName,

        @NotEmpty(message = "Provide a bio")
        String bio
) {
}
