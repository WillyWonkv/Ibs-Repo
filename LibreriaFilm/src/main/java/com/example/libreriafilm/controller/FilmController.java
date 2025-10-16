package com.example.libreriafilm.controller;

import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.entity.Film;
import com.example.libreriafilm.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDto>> getAllFilm(){

        return filmService.getAllFilm();

    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDto> getFilmById(@PathVariable long id){

        return filmService.getFilmById(id);

    }

    @PostMapping("/save")
    public ResponseEntity<FilmDto> saveFilm(@RequestBody FilmDto filmDto){

        return filmService.addLibro(filmDto);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FilmDto> updateFilm(@RequestBody FilmDto filmDto, @PathVariable long id){

        return filmService.updateFilm(filmDto, id);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Film> deleteFilm(@PathVariable long id){

        return filmService.deleteFilmById(id);

    }


}
