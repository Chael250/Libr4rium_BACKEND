package com.chael.Librarium.services;

import com.chael.Librarium.dtos.authorDto.CreateDto;
import com.chael.Librarium.dtos.authorDto.PatchDto;
import com.chael.Librarium.dtos.authorDto.ResponseDto;
import com.chael.Librarium.entities.Author;
import com.chael.Librarium.exceptions.NoSuchIdFoundException;
import com.chael.Librarium.mappers.AuthorMapper;
import com.chael.Librarium.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public ResponseDto createAuthor(CreateDto newAuthor) {
        Author author = authorMapper.toAuthor(newAuthor);
        authorRepository.save(author);
        return authorMapper.toResponseDto(author);
    }

    public List<ResponseDto> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public ResponseDto getAuthorById(UUID id) {
        return authorRepository.findById(id)
                .map(authorMapper::toResponseDto)
                .orElseThrow(() -> new NoSuchIdFoundException(id));
    }

    public ResponseDto patchAuthorById(UUID id, PatchDto patchDto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchIdFoundException(id));

        if(patchDto.artistName() != null){
            author.setArtistName(patchDto.artistName());
        }

        if(patchDto.bio() != null){
            author.setBio(patchDto.bio());
        }

        return authorMapper.toResponseDto(authorRepository.save(author));
    }

    public boolean deleteAuthorById(UUID id) {
        if(!authorRepository.existsById(id)){
            throw new NoSuchIdFoundException(id);
        }

        authorRepository.deleteById(id);
        return true;
    }
}
