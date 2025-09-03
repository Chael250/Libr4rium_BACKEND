package com.chael.Librarium.controllers;

import com.chael.Librarium.dtos.bookDtos.CreateDto;
import com.chael.Librarium.dtos.bookDtos.PatchDto;
import com.chael.Librarium.dtos.bookDtos.ResponseDto;
import com.chael.Librarium.entities.Book;
import com.chael.Librarium.entities.enums.BookGenre;
import com.chael.Librarium.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add-book")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody CreateDto newBook) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(newBook));
    }

    @GetMapping("/get-books")
    public ResponseEntity<List<ResponseDto>> getBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllBooks());
    }

    @GetMapping("/get-book/{id}")
    public ResponseEntity<ResponseDto> getBookById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBookById(id));
    }

    @GetMapping("/search-book")
    public ResponseEntity<List<ResponseDto>> searchBook(
            @RequestBody String text
    ){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.searchByTitle(text));
    }

    @GetMapping("/get-books-genre")
    public ResponseEntity<List<ResponseDto>> getBookByGenre(
            @RequestParam BookGenre genre
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findByGenre(genre));
    }

    @GetMapping("/get-books/{id}")
    public ResponseEntity<List<ResponseDto>> getBookByAuthorId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findByAuthor(id));
    }

    @PatchMapping("/patch-book/{id}")
    public ResponseEntity<ResponseDto>  patchBook(
            @PathVariable UUID id,
            @Valid @RequestBody PatchDto patchDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.patchBookById(id, patchDto));
    }

    @PatchMapping("/patch-book/{id}/{authorId}")
    public ResponseEntity<ResponseDto> patchBook(
            @PathVariable("id") UUID id,
            @PathVariable("authorId") UUID authorId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.updateBookToAuthor(id, authorId));
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bookService.deleteBookById(id));
    }
}
