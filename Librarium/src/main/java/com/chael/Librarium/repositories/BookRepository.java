package com.chael.Librarium.repositories;

import com.chael.Librarium.entities.Author;
import com.chael.Librarium.entities.Book;
import com.chael.Librarium.entities.enums.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByGenre(BookGenre genre);

    @Query("SELECT b FROM T_BOOKS b WHERE b.author.id = :authorId")
    List<Book> findByAuthor(UUID authorId);
}
