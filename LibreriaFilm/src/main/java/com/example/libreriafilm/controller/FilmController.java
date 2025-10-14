package com.example.libreriafilm.controller;

import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.entity.Film;
import com.example.libreriafilm.entity.Prestito;
import com.example.libreriafilm.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/save")
    public ResponseEntity<Film> saveFilm(@RequestBody FilmDto filmDto){

        Film filmNew = filmService.addLibro(filmDto);

        if(filmNew==null){return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}

        return ResponseEntity.ok(filmNew);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FilmDto> updateFilm(@RequestBody FilmDto filmDto, @PathVariable long id){

        FilmDto filmUpdated = filmService.updateFilm(filmDto, id);

        if(filmUpdated==null){return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}

        return ResponseEntity.ok(filmUpdated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FilmDto> deleteFilm(@PathVariable long id){

        ResponseEntity<FilmDto> filmDto = filmService.deleteFilmById(id);

        return filmDto;

    }


}
