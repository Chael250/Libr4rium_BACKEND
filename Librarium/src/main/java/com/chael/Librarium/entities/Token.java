package com.chael.Librarium.entities;

import com.chael.Librarium.entities.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor // for using builder
@Entity // the name provided is used in HQL/JPQL
@Table(name = "T_TOKENS") // the name in the database
public class Token extends BaseEntity{
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean expired;

    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
