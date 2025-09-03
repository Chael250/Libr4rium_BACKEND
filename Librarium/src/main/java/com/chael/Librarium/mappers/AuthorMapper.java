package com.chael.Librarium.mappers;

import com.chael.Librarium.dtos.authorDto.CreateDto;
import com.chael.Librarium.dtos.authorDto.ResponseDto;
import com.chael.Librarium.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(CreateDto newAuthor);

    CreateDto toCreateDto(Author author);

    @Mapping(source = "artistName", target = "artistName")
    ResponseDto toResponseDto(Author author);
}
