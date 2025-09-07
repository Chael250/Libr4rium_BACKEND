package com.chael.Librarium.repositories;

import com.chael.Librarium.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, String> {
    @Query("SELECT t from Token t INNER JOIN T_USERS u ON t.user.id = u.id WHERE u.id = :userId AND (t.expired = false OR t.revoked = false) AND t.tokenType = 'ACCESS'")
    List<Token> findAllValidTokenByUser(UUID userId);

    Optional<Token> findByToken(String token);
}
