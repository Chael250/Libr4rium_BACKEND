package com.chael.Librarium.dtos.bookDtos;

import com.chael.Librarium.entities.enums.BookGenre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.aspectj.bridge.IMessage;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public record PatchDto(
        String title,
        String isbn,
        String publicationYear,
        BookGenre genre,
        UUID author
) {
}
