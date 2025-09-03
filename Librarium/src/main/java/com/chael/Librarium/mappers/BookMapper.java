package com.chael.Librarium.mappers;

import com.chael.Librarium.dtos.bookDtos.CreateDto;
import com.chael.Librarium.dtos.bookDtos.ResponseDto;
import com.chael.Librarium.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toBook(CreateDto newBook);

    @Mapping(source = "title", target = "title")
    ResponseDto toResponseDto(Book book);
}
