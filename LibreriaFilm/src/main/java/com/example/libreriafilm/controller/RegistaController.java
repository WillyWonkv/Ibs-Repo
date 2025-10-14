package com.example.libreriafilm.controller;

import com.example.libreriafilm.dto.RegistaDto;
import com.example.libreriafilm.service.RegistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/regista")
public class RegistaController {

    @Autowired
    private RegistaService registaService;

    @GetMapping
    public ResponseEntity<List<RegistaDto>> getAllRegisti() {

        List<RegistaDto> registi = registaService.getAllRegisti();

        if(registi.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}

        return ResponseEntity.ok(registi);

    }

}
