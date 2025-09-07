package com.chael.Librarium.mappers;

import com.chael.Librarium.dtos.authorDto.CreateDto;
import com.chael.Librarium.dtos.authorDto.ResponseDto;
import com.chael.Librarium.entities.Author;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-07T15:24:00+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public Author toAuthor(CreateDto newAuthor) {
        if ( newAuthor == null ) {
            return null;
        }

        Author.AuthorBuilder<?, ?> author = Author.builder();

        author.artistName( newAuthor.artistName() );
        author.bio( newAuthor.bio() );

        return author.build();
    }

    @Override
    public CreateDto toCreateDto(Author author) {
        if ( author == null ) {
            return null;
        }

        String artistName = null;
        String bio = null;

        artistName = author.getArtistName();
        bio = author.getBio();

        CreateDto createDto = new CreateDto( artistName, bio );

        return createDto;
    }

    @Override
    public ResponseDto toResponseDto(Author author) {
        if ( author == null ) {
            return null;
        }

        String artistName = null;

        artistName = author.getArtistName();

        ResponseDto responseDto = new ResponseDto( artistName );

        return responseDto;
    }
}
