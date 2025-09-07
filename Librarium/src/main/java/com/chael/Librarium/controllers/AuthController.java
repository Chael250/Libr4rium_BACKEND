package com.chael.Librarium.controllers;

import com.chael.Librarium.dtos.authDtos.AuthRequest;
import com.chael.Librarium.dtos.authDtos.AuthResponse;
import com.chael.Librarium.dtos.authDtos.RegisterRequest;
import com.chael.Librarium.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> register(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            authService.refreshToken(request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
