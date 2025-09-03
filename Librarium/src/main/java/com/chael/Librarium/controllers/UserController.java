package com.chael.Librarium.controllers;

import com.chael.Librarium.dtos.userDtos.CreateDto;
import com.chael.Librarium.dtos.userDtos.PatchDto;
import com.chael.Librarium.dtos.userDtos.ResponseDto;
import com.chael.Librarium.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public ResponseEntity<ResponseDto> createUser(@RequestBody CreateDto newUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(newUser));
    }

    @GetMapping("/get-users")
    public ResponseEntity<List<ResponseDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/get-librarians")
    public ResponseEntity<List<ResponseDto>> getAllLibrarians() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllLibrarians());
    }

    @GetMapping("/get-admins")
    public ResponseEntity<List<ResponseDto>> getAllAdmins() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllAdmins());
    }

    @PatchMapping("/patch-user/{id}")
    public ResponseEntity<ResponseDto> patchUser(@PathVariable UUID id, @RequestBody PatchDto patchDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(id, patchDto));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.deleteUser(id));
    }
}
