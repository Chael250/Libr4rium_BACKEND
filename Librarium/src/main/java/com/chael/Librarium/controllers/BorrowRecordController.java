package com.chael.Librarium.controllers;

import com.chael.Librarium.dtos.BorrowRecordsDto.CreateDto;
import com.chael.Librarium.dtos.BorrowRecordsDto.ResponseDto;
import com.chael.Librarium.services.BorrowRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/borrow-records")
public class BorrowRecordController {
    private final BorrowRecordService borrowRecordService;

    public BorrowRecordController(BorrowRecordService borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }

    @GetMapping("/get-borrow-records/{borrowerId}")
    @PreAuthorize("hasAuthority('librarian:read')")
    public ResponseEntity<ResponseDto> getBorrowRecords(@PathVariable UUID borrowerId) {
        return ResponseEntity.status(HttpStatus.OK).body(borrowRecordService.getBorrowedRecordsByBorrower(borrowerId));
    }

    @PostMapping("/add-borrow-records/")
    @PreAuthorize("hasAuthority('librarian:create')")
    public ResponseEntity<ResponseDto> addBorrowRecords(
            @Valid @RequestBody CreateDto newBorrowRecord
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowRecordService.createBorrowRecord(newBorrowRecord));
    }

    @PatchMapping("/patch-record-due/{id}")
    public ResponseEntity<ResponseDto> updateBorrowRecords(
            @RequestBody String newReturn,
            @PathVariable UUID id
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowRecordService.updateReturnDate(id, newReturn));
    }
}
