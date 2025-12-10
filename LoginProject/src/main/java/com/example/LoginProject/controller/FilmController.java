package com.example.LoginProject.controller;

import com.example.LoginProject.dto.FilmDTO;

import com.example.LoginProject.dto.mapperDTO.FilmMapperDTO;
import com.example.LoginProject.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/genre/{id}")
    public ResponseEntity<List<FilmDTO>> getFilmByGenere(@PathVariable long id) {
        List<FilmDTO> films = filmService.findFilmByGenre(id).stream()
                .map(FilmMapperDTO::mapFilmToFilmDTO)
                .toList();
        return ResponseEntity.ok(films);
    }

    @GetMapping("/cover/{filename}")
    public ResponseEntity<Resource> getFilmCover(@PathVariable String filename)  throws IOException {

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(filmService.getFilmCover(filename));
    }

    @GetMapping("/search")
    public ResponseEntity<List<FilmDTO>> getFilmByTitle(@RequestParam String title) {
        List<FilmDTO> films = filmService.findFilmByTitle(title).stream()
                .map(FilmMapperDTO::mapFilmToFilmDTO)
                .toList();
        return ResponseEntity.ok(films);
    }

    @PreAuthorize("hasAuthority('CREATE')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FilmDTO> saveFilm(
            @RequestPart("film") FilmDTO film,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(FilmMapperDTO.mapFilmToFilmDTO(filmService.saveFilm(film, file.getInputStream())));

    }

    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilmById(@PathVariable long id) {
        filmService.deleteFilmById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('UPDATE')")
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FilmDTO> updateFilm(
            @RequestPart("film") FilmDTO film,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException
    {
        return ResponseEntity.ok(FilmMapperDTO.mapFilmToFilmDTO(filmService.updateFilm(film, file.getInputStream())));
    }

}
