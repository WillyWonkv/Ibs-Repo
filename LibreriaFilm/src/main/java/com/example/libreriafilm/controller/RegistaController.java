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

        try {
            List<RegistaDto> registi = registaService.getAllRegisti();
            return ResponseEntity.ok(registi);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistaDto> getRegistiById(@PathVariable Long id) {

        try {
            RegistaDto regista = registaService.getRegistiById(id);
            return ResponseEntity.ok(regista);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping("/save")
    public ResponseEntity<RegistaDto> addRegista(@Valid @RequestBody RegistaDto registaDto) {

        return ResponseEntity.ok(registaService.addRegista(registaDto));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RegistaDto> updateRegista(@RequestBody RegistaDto registaDto, @PathVariable Long id) {

        try {
            RegistaDto regista = registaService.updateRegista(registaDto, id);
            return ResponseEntity.ok(regista);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RegistaDto> deleteRegista(@PathVariable Long id) {

        try {
            RegistaDto registaDto = registaService.deleteRegista(id);
            return ResponseEntity.ok(registaDto);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }


}
