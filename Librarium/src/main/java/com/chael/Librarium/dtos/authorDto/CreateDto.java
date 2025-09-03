package com.chael.Librarium.dtos.authorDto;

import jakarta.validation.constraints.NotEmpty;

public record CreateDto(
        @NotEmpty(message = "Enter artist name")
        String artistName,

        @NotEmpty(message = "Provide a bio")
        String bio
) {
}
