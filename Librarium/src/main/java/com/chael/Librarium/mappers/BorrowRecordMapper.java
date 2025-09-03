package com.chael.Librarium.mappers;

import com.chael.Librarium.dtos.BorrowRecordsDto.ResponseDto;
import com.chael.Librarium.entities.BorrowRecords;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BorrowRecordMapper {

    @Mapping(source = "borrower", target = "borrower")
    ResponseDto toResponseDto(BorrowRecords borrowRecords);
}
