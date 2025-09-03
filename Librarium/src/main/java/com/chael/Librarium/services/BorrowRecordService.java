package com.chael.Librarium.services;

import com.chael.Librarium.dtos.BorrowRecordsDto.CreateDto;
import com.chael.Librarium.dtos.BorrowRecordsDto.ResponseDto;
import com.chael.Librarium.entities.Book;
import com.chael.Librarium.entities.BorrowRecords;
import com.chael.Librarium.entities.User;
import com.chael.Librarium.entities.enums.Status;
import com.chael.Librarium.exceptions.NoSuchIdFoundException;
import com.chael.Librarium.mappers.BorrowRecordMapper;
import com.chael.Librarium.repositories.BookRepository;
import com.chael.Librarium.repositories.BorrowRecordRepository;
import com.chael.Librarium.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BorrowRecordMapper borrowRecordMapper;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository, BorrowRecordMapper borrowRecordMapper, UserRepository userRepository, BookRepository bookRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.borrowRecordMapper = borrowRecordMapper;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public ResponseDto createBorrowRecord(CreateDto newBorrowRecord) {
        User user = userRepository.findById(newBorrowRecord.borrower())
                .orElseThrow(() -> new NoSuchIdFoundException(newBorrowRecord.borrower()));

        Book book = bookRepository.findById(newBorrowRecord.book())
                .orElseThrow(() -> new NoSuchIdFoundException(newBorrowRecord.book()));

        BorrowRecords borrowRecords = new BorrowRecords();
        borrowRecords.setBook(book);
        borrowRecords.setBorrower(user);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(newBorrowRecord.due(), formatter);
        borrowRecords.setDueDate(localDate);

        borrowRecords.setStatus(Status.BORROWED);

        return borrowRecordMapper.toResponseDto(borrowRecordRepository.save(borrowRecords));
    }

    public ResponseDto getBorrowedRecordsByBorrower(UUID borrowerId) {
        userRepository.findById(borrowerId)
                .orElseThrow(() -> new NoSuchIdFoundException(borrowerId));

        return borrowRecordMapper.toResponseDto(borrowRecordRepository.findBorrowRecordsByBorrowerId(borrowerId));
    }

    public ResponseDto updateReturnDate(UUID id, String returnDate) {
        BorrowRecords br = borrowRecordRepository.findById(id)
                .orElseThrow(() -> new NoSuchIdFoundException(id));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(returnDate, formatter);
        br.setReturnDate(localDate);
        return borrowRecordMapper.toResponseDto(borrowRecordRepository.save(br));
    }
}
