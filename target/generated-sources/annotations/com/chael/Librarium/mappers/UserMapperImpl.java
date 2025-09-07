package com.chael.Librarium.mappers;

import com.chael.Librarium.dtos.userDtos.ResponseDto;
import com.chael.Librarium.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-07T15:24:00+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public ResponseDto toResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        String email = null;
        String password = null;

        email = user.getEmail();
        password = user.getPassword();

        ResponseDto responseDto = new ResponseDto( email, password );

        return responseDto;
    }
}
