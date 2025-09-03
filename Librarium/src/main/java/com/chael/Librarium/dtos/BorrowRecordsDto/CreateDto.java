package com.chael.Librarium.dtos.BorrowRecordsDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateDto(
        @NotEmpty(message = "Provide a borrower")
        @NotNull()
        UUID borrower,

        @NotEmpty(message = "Provide a borrower")
        @NotNull()
        UUID book,

        @NotEmpty(message = "Provide a borrower")
        @NotNull()
        String due
) {
}
