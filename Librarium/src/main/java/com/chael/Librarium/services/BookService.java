package com.chael.Librarium.services;

import com.chael.Librarium.dtos.bookDtos.CreateDto;
import com.chael.Librarium.dtos.bookDtos.PatchDto;
import com.chael.Librarium.dtos.bookDtos.ResponseDto;
import com.chael.Librarium.entities.Author;
import com.chael.Librarium.entities.Book;
import com.chael.Librarium.entities.enums.BookGenre;
import com.chael.Librarium.exceptions.NoSuchIdFoundException;
import com.chael.Librarium.mappers.BookMapper;
import com.chael.Librarium.repositories.AuthorRepository;
import com.chael.Librarium.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, BookMapper bookMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
    }

    public ResponseDto createBook(CreateDto createDto) {
        Book book = bookMapper.toBook(createDto);
        bookRepository.save(book);
        return bookMapper.toResponseDto(book);
    }

    public List<ResponseDto> findAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public ResponseDto findBookById(UUID id) {
        return bookRepository.findById(id)
                .map(bookMapper::toResponseDto)
                .orElseThrow(() -> new NoSuchIdFoundException(id));
    }

    public ResponseDto patchBookById(UUID id, PatchDto patchDto) {
        Book book =  bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchIdFoundException(id));

        if(patchDto.title() != null) {
            book.setTitle(patchDto.title());
        }

        if (patchDto.isbn() != null) {
            book.setIsbn(patchDto.isbn());
        }

        if(patchDto.publicationYear() != null) {
            book.setPublicationYear(patchDto.publicationYear());
        }

        if(patchDto.genre() != null) {
            book.setGenre(patchDto.genre());
        }

        return bookMapper.toResponseDto(bookRepository.save(book));
    }

    public boolean deleteBookById(UUID id) {
        Book book =  bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchIdFoundException(id));

        bookRepository.deleteById(id);
        return true;
    }

    public List<ResponseDto> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<ResponseDto> findByGenre(BookGenre genre) {
        return bookRepository.findByGenre(genre)
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<ResponseDto> findByAuthor(UUID authorId) {
        return bookRepository.findByAuthor(authorId)
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public ResponseDto updateBookToAuthor(UUID id, UUID authorId){
        bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchIdFoundException(id));

        authorRepository.findById(authorId)
                .orElseThrow(() -> new NoSuchIdFoundException(authorId));

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        book.setAuthor(author);
        return bookMapper.toResponseDto(bookRepository.save(book));
    }
}
