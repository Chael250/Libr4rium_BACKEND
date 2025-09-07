package com.chael.Librarium.dtos.authDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
public record AuthResponse(
        @JsonProperty("at")
        String accessToken,

        @JsonProperty("rt")
        String refreshToken
) {
}
