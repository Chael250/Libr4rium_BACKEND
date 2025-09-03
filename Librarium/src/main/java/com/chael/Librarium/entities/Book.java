package com.chael.Librarium.entities;

import com.chael.Librarium.entities.enums.BookGenre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "T_BOOKS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Book extends BaseEntity{
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String isbn;

    @Column(
            nullable = false,
            name = "publisher_year"
    )
    private String publicationYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookGenre genre;

    @Column(columnDefinition = "int default 1")
    private Integer copies;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
