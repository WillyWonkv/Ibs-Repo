package com.example.libreriafilm.controller;

import com.example.libreriafilm.dto.FilmDto;
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

}
