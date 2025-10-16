package com.example.libreriafilm.controller;

import com.example.libreriafilm.dto.GenereDto;
import com.example.libreriafilm.service.GenereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genere")
public class GenereController {

    @Autowired
    private GenereService genereService;

    @GetMapping
    public ResponseEntity<List<GenereDto>> getAllGeneri(){
        return genereService.getAllGeneri();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenereDto> getGenereById(@PathVariable long id){
        return genereService.getGenereById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<GenereDto> createGenere(@RequestBody GenereDto genereDto){
        return genereService.addGenere(genereDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateGenere(@PathVariable long id, @RequestBody GenereDto genereDto){
        return genereService.updateGenere(id, genereDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteGenere(@PathVariable long id){
        return genereService.deleteGenere(id);
    }

    @PutMapping("/film/{filmId}/generi")
    public ResponseEntity<Object> setGeneriToFilm(@PathVariable long filmId, @RequestParam List<Long> id){
        return genereService.setGeneriToFilm(id, filmId);
    }

}
