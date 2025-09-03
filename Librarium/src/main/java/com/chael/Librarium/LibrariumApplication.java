package com.chael.Librarium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LibrariumApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrariumApplication.class, args);
	}

}

// CRUD AUTHOR   ✅
// CRUD BOOKS    ✅
// UPDATE BORROW-RECORDS SET STATUS = REJECTED AND BORROWED ✅
// SEARCH BOOKS BY TITLE  ✅
// GET BOOKS BY GENRE AND AUTHOR  ✅
// READ BORROW-RECORD BY BORROWER_ID  ✅
// CREATE BORROW-RECORD  ✅
// CRUD USER
