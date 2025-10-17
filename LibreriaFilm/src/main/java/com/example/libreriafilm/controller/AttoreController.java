package com.example.libreriafilm.controller;

import com.example.libreriafilm.dto.AttoreDto;
import com.example.libreriafilm.service.AttoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attore")
public class AttoreController {

    @Autowired
    private AttoreService attoreService;

    @GetMapping
    public ResponseEntity<List<AttoreDto>> getAllAttori() {

        return attoreService.getAllAttori();

    }

    @GetMapping("/{id}")
    public ResponseEntity<AttoreDto> getAttoreById(@PathVariable long id) {

        return attoreService.getAttoreById(id);

    }

    @PostMapping("/save")
    public ResponseEntity<AttoreDto> saveAttore(@RequestBody AttoreDto attoreDto) {

        return attoreService.addAttore(attoreDto);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateAttore(@RequestBody AttoreDto attoreDto, @PathVariable long id) {

        return attoreService.updateAttore(attoreDto, id);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AttoreDto> deleteAttore(@PathVariable long id) {

        return attoreService.deleteAttore(id);

    }

    @PutMapping("/film/{filmId}/attori")
    public ResponseEntity<AttoreDto> setAttoriToFilm (@RequestParam List<Long> id, @PathVariable long filmId) {

        return attoreService.setAttoriToFilm(id,filmId);

    }

}
