package com.chael.Librarium.services;

import com.chael.Librarium.dtos.authDtos.AuthRequest;
import com.chael.Librarium.dtos.authDtos.AuthResponse;
import com.chael.Librarium.dtos.authDtos.RegisterRequest;
import com.chael.Librarium.entities.Token;
import com.chael.Librarium.entities.User;
import com.chael.Librarium.entities.enums.TokenType;
import com.chael.Librarium.repositories.TokenRepository;
import com.chael.Librarium.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest registerRequest) {
        // Save new user to db
        User user  = User
                .builder()
                .firstName(registerRequest.fname())
                .lastName(registerRequest.lname())
                .email(registerRequest.email())
                .password(registerRequest.password())
                .role(registerRequest.role())
                .build();

        var savedUser = userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        // return tokens
        var refresh = jwtService.generateRefreshToken(user);
        Token accessToken = Token
                .builder()
                .user(savedUser)
                .token(jwt)
                .tokenType(TokenType.ACCESS_BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(accessToken);

        Token refreshToken = Token
                .builder()
                .user(savedUser)
                .token(refresh)
                .tokenType(TokenType.REFRESH_BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(refreshToken);

        return AuthResponse
                .builder()
                .accessToken(jwt)
                .refreshToken(refresh)
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        var user =  userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException(request.email()));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        Token accessToken = Token.builder()
                .user(user)
                .token(jwt)
                .tokenType(TokenType.ACCESS_BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(accessToken);

        Token refToken = Token.builder()
                .user(user)
                .token(jwt)
                .tokenType(TokenType.REFRESH_BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(refToken);
        return AuthResponse.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken)
                .build();
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String authHeader = request.getHeader("Authorization"); // header that contains bearer token
        String refreshToken;
        String userEmail;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if(userEmail != null &&  SecurityContextHolder.getContext().getAuthentication() == null) {
            User userDetails = this.userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new UsernameNotFoundException(userEmail));
            boolean isTokenValid = tokenRepository.findByToken(refreshToken)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if(jwtService.isTokenValid(refreshToken, userDetails) && isTokenValid) {
                var accessToken = jwtService.generateToken(userDetails);
                revokeAllUserAccessTokens(userDetails);
                var at = Token.builder()
                        .user(userDetails)
                        .token(accessToken)
                        .tokenType(TokenType.ACCESS_BEARER)
                        .revoked(false)
                        .expired(false)
                        .build();
                tokenRepository.save(at);
                var authResponse = AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private void revokeAllUserAccessTokens(User userDetails) {
        var validToken = tokenRepository.findAllValidTokenByUser(userDetails.getId());
        if(validToken.isEmpty()) return;
        validToken.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validToken);
    }
}
