package com.example.LoginProject.controller;

import com.example.LoginProject.dto.GenreDTO;
import com.example.LoginProject.dto.mapperDTO.FilmMapperDTO;
import com.example.LoginProject.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {

        List<GenreDTO> genres = genreService.findAllGenres().stream()
                .map(FilmMapperDTO::mapGenreToGenreDTO)
                .toList();
        return ResponseEntity.ok(genres);
    }

}
