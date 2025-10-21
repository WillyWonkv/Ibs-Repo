package com.example.libreriafilm.controller;

import com.example.libreriafilm.dto.GenereDto;
import com.example.libreriafilm.service.GenereService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genere")
@RequiredArgsConstructor
public class GenereController {

    private final GenereService genereService;

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW')")
    public ResponseEntity<List<GenereDto>> getAllGeneri() {

        try {
            List<GenereDto> generes = genereService.getAllGeneri();
            return ResponseEntity.ok(generes);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW')")
    public ResponseEntity<GenereDto> getGenereById(@PathVariable long id){

        try {
            GenereDto genere = genereService.getGenereById(id);
            return ResponseEntity.ok(genere);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<GenereDto> createGenere(@RequestBody GenereDto genereDto){
        return ResponseEntity.ok(genereService.addGenere(genereDto));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<GenereDto> updateGenere(@PathVariable long id, @RequestBody GenereDto genereDto){

        try {
            GenereDto genere = genereService.updateGenere(id, genereDto);
            return ResponseEntity.ok(genere);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<GenereDto> deleteGenere(@PathVariable long id){

        try {
            GenereDto genere = genereService.deleteGenere(id);
            return ResponseEntity.ok(genere);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }


}
