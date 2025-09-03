package com.chael.Librarium.services;

import com.chael.Librarium.dtos.userDtos.CreateDto;
import com.chael.Librarium.dtos.userDtos.PatchDto;
import com.chael.Librarium.dtos.userDtos.ResponseDto;
import com.chael.Librarium.entities.User;
import com.chael.Librarium.entities.enums.Role;
import com.chael.Librarium.exceptions.NoSuchIdFoundException;
import com.chael.Librarium.mappers.UserMapper;
import com.chael.Librarium.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ResponseDto createUser(CreateDto newUser) {
        User user = userMapper.toUser(newUser);
        return userMapper.toResponseDto(userRepository.save(user));
    }

    public List<ResponseDto> getAllUsers() {
        return userRepository.findAllByRole(Role.USER)
                .stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<ResponseDto> getAllLibrarians() {
        return userRepository.findAllByRole(Role.LIBRARIAN)
                .stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<ResponseDto> getAllAdmins() {
        return userRepository.findAllByRole(Role.ADMIN)
                .stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public ResponseDto updateUser(UUID id, PatchDto patchDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchIdFoundException(id));

        if(patchDto.fname() != null) user.setFirstName(patchDto.fname());
        if(patchDto.lname() != null) user.setLastName(patchDto.lname());
        if(patchDto.email() != null) user.setEmail(patchDto.email());
        if(patchDto.password() != null) user.setPassword(patchDto.password());

        return userMapper.toResponseDto(userRepository.save(user));
    }

    public boolean deleteUser(UUID id) {
        userRepository.findById(id)
                .orElseThrow(() -> new NoSuchIdFoundException(id));

        userRepository.deleteById(id);
        return true;
    }
}
