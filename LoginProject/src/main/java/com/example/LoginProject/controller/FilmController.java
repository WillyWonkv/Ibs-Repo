package com.example.LoginProject.controller;

import com.example.LoginProject.dto.FilmDTO;

import com.example.LoginProject.dto.mapperDTO.FilmMapperDTO;
import com.example.LoginProject.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
@RequestMapping("/film")
public class FilmController {

    private final FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        List<FilmDTO> films = filmService.findAllFilms().stream()
                .map(FilmMapperDTO::mapFilmToFilmDTO)
                .toList();

        return ResponseEntity.ok(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable long id) {
        return ResponseEntity.ok(FilmMapperDTO.mapFilmToFilmDTO(filmService.findFilmById(id)));
    }

    @PreAuthorize("hasAuthority('CREATE')")
    @PostMapping
    public ResponseEntity<FilmDTO> saveFilm(@RequestBody FilmDTO film) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(FilmMapperDTO.mapFilmToFilmDTO(filmService.saveFilm(film)));
    }

    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilmById(@PathVariable long id) {
        filmService.deleteFilmById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('UPDATE')")
    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@RequestBody FilmDTO film, @PathVariable long id) {
        film.setId(id);
        return ResponseEntity.ok(FilmMapperDTO.mapFilmToFilmDTO(filmService.updateFilm(film)));
    }

}
