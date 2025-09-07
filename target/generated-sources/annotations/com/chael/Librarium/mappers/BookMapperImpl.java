package com.chael.Librarium.mappers;

import com.chael.Librarium.dtos.bookDtos.CreateDto;
import com.chael.Librarium.dtos.bookDtos.ResponseDto;
import com.chael.Librarium.entities.Book;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-07T15:24:00+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book toBook(CreateDto newBook) {
        if ( newBook == null ) {
            return null;
        }

        Book.BookBuilder<?, ?> book = Book.builder();

        book.title( newBook.title() );
        book.isbn( newBook.isbn() );
        book.publicationYear( newBook.publicationYear() );
        book.genre( newBook.genre() );

        return book.build();
    }

    @Override
    public ResponseDto toResponseDto(Book book) {
        if ( book == null ) {
            return null;
        }

        String title = null;

        title = book.getTitle();

        ResponseDto responseDto = new ResponseDto( title );

        return responseDto;
    }
}
