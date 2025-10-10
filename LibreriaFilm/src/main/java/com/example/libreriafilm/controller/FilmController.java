package com.example.libreriafilm.controller;

import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDto>> getAllFilm(){

        List<FilmDto> filmDto = filmService.getAllFilm();

        if(filmDto.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(filmDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDto> getFilmById(@PathVariable String id){

        FilmDto film = filmService.getFilmById(Long.parseLong(id));

        if(film==null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        return ResponseEntity.ok(film);

    }

    public ResponseEntity<FilmDto> saveFilm(FilmDto filmDto){

        FilmDto filmNew = filmService.addLibro(filmDto);
        return ResponseEntity.ok(filmNew);

    }




}
