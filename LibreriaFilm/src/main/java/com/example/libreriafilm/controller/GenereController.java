package com.example.libreriafilm.controller;

import com.example.libreriafilm.dto.GenereDto;
import com.example.libreriafilm.service.GenereService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genere")
@RequiredArgsConstructor
public class GenereController {

    private final GenereService genereService;

    @GetMapping
    public ResponseEntity<List<GenereDto>> getAllGeneri() {
        return ResponseEntity.ok(genereService.getAllGeneri());

    }

    @GetMapping("/{id}")
    public ResponseEntity<GenereDto> getGenereById(@PathVariable long id){
        return ResponseEntity.ok(genereService.getGenereById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<GenereDto> createGenere(@RequestBody GenereDto genereDto){
        return ResponseEntity.ok(genereService.addGenere(genereDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GenereDto> updateGenere(@PathVariable long id, @RequestBody GenereDto genereDto){
        return ResponseEntity.ok(genereService.updateGenere(id, genereDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenereDto> deleteGenere(@PathVariable long id){
        return ResponseEntity.ok(genereService.deleteGenere(id));
    }


}
