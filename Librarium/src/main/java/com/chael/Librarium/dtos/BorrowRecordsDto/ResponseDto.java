package com.chael.Librarium.dtos.BorrowRecordsDto;

import com.chael.Librarium.entities.User;

public record ResponseDto(
        User borrower,
        String status
) {
}
