package com.example.libreriafilm.controller;

import com.example.libreriafilm.dto.RegistaDto;

import com.example.libreriafilm.service.RegistaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regista")
public class RegistaController {

    private final RegistaService registaService;

    @GetMapping
    public ResponseEntity<List<RegistaDto>> getAllRegisti() {
        return ResponseEntity.ok(registaService.getAllRegisti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistaDto> getRegistiById(@PathVariable Long id) {

        return ResponseEntity.ok(registaService.getRegistiById(id));

    }

    @PostMapping("/save")
    public ResponseEntity<RegistaDto> addRegista(@Valid @RequestBody RegistaDto registaDto) {

        return ResponseEntity.ok(registaService.addRegista(registaDto));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RegistaDto> updateRegista(@RequestBody RegistaDto registaDto, @PathVariable Long id) {

        return ResponseEntity.ok(registaService.updateRegista(registaDto, id));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RegistaDto> deleteRegista(@PathVariable Long id) {

        return ResponseEntity.ok(registaService.deleteRegista(id));

    }


}
