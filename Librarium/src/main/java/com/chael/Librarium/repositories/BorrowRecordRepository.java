package com.chael.Librarium.repositories;

import com.chael.Librarium.entities.BorrowRecords;
import com.chael.Librarium.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecords, UUID> {
    @Modifying
    @Query("UPDATE T_BORROW_RECORDS br SET br.status = :status WHERE br.id = :id")
    Optional<BorrowRecords> updateBorrowRecordsSetStatus(Status status, UUID id);

    BorrowRecords findBorrowRecordsByBorrowerId(UUID id);
}
