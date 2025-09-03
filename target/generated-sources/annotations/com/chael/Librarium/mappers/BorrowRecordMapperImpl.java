package com.chael.Librarium.mappers;

import com.chael.Librarium.dtos.BorrowRecordsDto.ResponseDto;
import com.chael.Librarium.entities.BorrowRecords;
import com.chael.Librarium.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-03T17:52:29+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class BorrowRecordMapperImpl implements BorrowRecordMapper {

    @Override
    public ResponseDto toResponseDto(BorrowRecords borrowRecords) {
        if ( borrowRecords == null ) {
            return null;
        }

        User borrower = null;
        String status = null;

        borrower = borrowRecords.getBorrower();
        if ( borrowRecords.getStatus() != null ) {
            status = borrowRecords.getStatus().name();
        }

        ResponseDto responseDto = new ResponseDto( borrower, status );

        return responseDto;
    }
}
