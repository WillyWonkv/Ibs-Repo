package com.example.libreriafilm.controller;

import com.example.libreriafilm.dto.AttoreDto;
import com.example.libreriafilm.service.AttoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attore")
public class AttoreController {

    private final AttoreService attoreService;

    @GetMapping
    public ResponseEntity<List<AttoreDto>> getAllAttori() {

        try {
            List<AttoreDto> attori = attoreService.getAllAttori();
            return ResponseEntity.ok(attori);
        }catch (RuntimeException e){
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<AttoreDto> getAttoreById(@PathVariable long id) {

        try {
            AttoreDto attore = attoreService.getAttoreById(id);
            return ResponseEntity.ok(attore);
        }catch (RuntimeException e){
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping("/save")
    public ResponseEntity<AttoreDto> saveAttore(@RequestBody AttoreDto attoreDto) {

        return ResponseEntity.ok(attoreService.addAttore(attoreDto));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AttoreDto> updateAttore(@RequestBody AttoreDto attoreDto, @PathVariable long id) {

        try {
            AttoreDto attore = attoreService.updateAttore(attoreDto, id);
            return ResponseEntity.ok(attore);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AttoreDto> deleteAttore(@PathVariable long id) {

        try {
            AttoreDto attore = attoreService.deleteAttore(id);
            return ResponseEntity.ok(attore);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }

    }

}
