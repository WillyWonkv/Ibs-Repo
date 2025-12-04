package com.example.LoginProject.service;

import com.example.LoginProject.entity.Genre;
import com.example.LoginProject.exception.EmptyListException;
import com.example.LoginProject.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreService {

    private final GenreRepository genreRepository;

    public List<Genre> findAllGenres() {
        try {
            List<Genre> genres = genreRepository.findAll();
            if(genres.isEmpty()) {
                log.warn("No genres found");
                throw new EmptyListException("No genres found");
            }
            log.info("Genres found");
            return genres;
        } catch (EmptyListException e) {
            log.warn("Empty list exception");
            throw new EmptyListException("Empty list exception");
        }
    }

}
