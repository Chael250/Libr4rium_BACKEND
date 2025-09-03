package com.chael.Librarium.controllers;

import com.chael.Librarium.dtos.authorDto.CreateDto;
import com.chael.Librarium.dtos.authorDto.PatchDto;
import com.chael.Librarium.dtos.authorDto.ResponseDto;
import com.chael.Librarium.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/add-author")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ResponseDto> addAuthor(
            @Valid @RequestBody CreateDto newTourist
    ) {
        return ResponseEntity.ok(authorService.createAuthor(newTourist));
    }

    @GetMapping("/get-authors")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ResponseDto>> getAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/get-author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> getAuthor(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PatchMapping("/patch-author/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto> patchAuthor(
            @Valid @RequestBody PatchDto patchDto,
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(authorService.patchAuthorById(id, patchDto));
    }

    @DeleteMapping("/delete-author/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Boolean> deleteAuthor(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(authorService.deleteAuthorById(id));
    }
}
