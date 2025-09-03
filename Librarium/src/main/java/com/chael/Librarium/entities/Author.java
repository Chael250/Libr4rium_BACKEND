package com.chael.Librarium.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "T_AUTHORS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Author extends BaseEntity {
    @Column(
            nullable = false,
            name = "name"
    )
    private String artistName;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String bio;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
