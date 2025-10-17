package com.example.libreriafilm.controller;

import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/film")
public class FilmController {

    private final FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDto>> getAllFilm(){return ResponseEntity.ok(filmService.getAllFilm());}

    @GetMapping("/{id}")
    public ResponseEntity<FilmDto> getFilmById(@PathVariable long id){return ResponseEntity.ok(filmService.getFilmById(id));}

    @PostMapping("/save")
    public ResponseEntity<FilmDto> saveFilm(@RequestBody FilmDto filmDto){return ResponseEntity.ok(filmService.addFilm(filmDto));}

    @PutMapping("/update/{id}")
    public ResponseEntity<FilmDto> updateFilm(@RequestBody FilmDto filmDto, @PathVariable long id){return ResponseEntity.ok(filmService.updateFilm(filmDto, id));}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable long id){

        filmService.deleteFilmById(id);
        return ResponseEntity.ok().build();

    }


}
