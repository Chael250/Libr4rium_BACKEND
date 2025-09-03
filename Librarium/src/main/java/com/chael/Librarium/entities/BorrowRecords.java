package com.chael.Librarium.entities;

import com.chael.Librarium.entities.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "T_BORROW_RECORDS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BorrowRecords extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(
            name = "due_date",
            nullable = false
    )
    private LocalDate dueDate;

    @Column(
            name = "return_date",
            nullable = false
    )
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
