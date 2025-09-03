package com.chael.Librarium.dtos.bookDtos;

import com.chael.Librarium.entities.enums.BookGenre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateDto(
        @NotEmpty(message = "Provide a title")
        @NotBlank
        String title,
        @NotEmpty(message = "Provide an isbn")
        String isbn,
        @NotEmpty(message = "Provide a publication year")
        @NotNull
        String publicationYear,
        @NotNull(message = "Provide a genre")
        BookGenre genre
) {
}
