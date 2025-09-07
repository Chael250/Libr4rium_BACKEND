package com.chael.Librarium.dtos.authDtos;

import lombok.Builder;

@Builder
public record AuthRequest(
        String email,
        String password
) {
}
