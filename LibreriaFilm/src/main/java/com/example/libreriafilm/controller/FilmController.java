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
    public ResponseEntity<List<FilmDto>> getAllFilm(){

        try {
            List<FilmDto> filmDto = filmService.getAllFilm();
            return ResponseEntity.ok(filmDto);
        }catch (RuntimeException e){
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDto> getFilmById(@PathVariable long id){

        try {
            FilmDto filmDto = filmService.getFilmById(id);
            return ResponseEntity.ok(filmDto);
        }catch (RuntimeException e){
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping("/save")
    public ResponseEntity<FilmDto> saveFilm(@RequestBody FilmDto filmDto){

        return ResponseEntity.ok(filmService.addFilm(filmDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FilmDto> updateFilm(@RequestBody FilmDto filmDto, @PathVariable long id){

        try {
            FilmDto film =  filmService.updateFilm(filmDto, id);
            return ResponseEntity.ok(film);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable long id){

        try{
            filmService.deleteFilmById(id);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }

    }


}
