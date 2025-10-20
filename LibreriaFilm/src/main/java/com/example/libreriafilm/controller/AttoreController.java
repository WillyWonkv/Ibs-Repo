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

        return ResponseEntity.ok(attoreService.getAllAttori());

    }

    @GetMapping("/{id}")
    public ResponseEntity<AttoreDto> getAttoreById(@PathVariable long id) {

        return ResponseEntity.ok(attoreService.getAttoreById(id));

    }

    @PostMapping("/save")
    public ResponseEntity<AttoreDto> saveAttore(@RequestBody AttoreDto attoreDto) {

        return ResponseEntity.ok(attoreService.addAttore(attoreDto));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AttoreDto> updateAttore(@RequestBody AttoreDto attoreDto, @PathVariable long id) {

        return ResponseEntity.ok(attoreService.updateAttore(attoreDto, id));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AttoreDto> deleteAttore(@PathVariable long id) {

        return ResponseEntity.ok(attoreService.deleteAttore(id));

    }

}
