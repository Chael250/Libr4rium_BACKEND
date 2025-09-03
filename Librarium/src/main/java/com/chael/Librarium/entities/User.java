package com.chael.Librarium.entities;

import com.chael.Librarium.entities.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "T_USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends BaseEntity{

    @Column(
            name = "f_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "l_name",
            nullable=false
    )
    private String lastName;

    @Column(
            nullable = false
    )
    private String email;

    @Column(
            name = "password_hash",
            nullable=false
    )
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
