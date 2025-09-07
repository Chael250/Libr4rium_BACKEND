package com.chael.Librarium.mappers;

import com.chael.Librarium.dtos.userDtos.ResponseDto;
import com.chael.Librarium.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    ResponseDto toResponseDto(User user);
}
