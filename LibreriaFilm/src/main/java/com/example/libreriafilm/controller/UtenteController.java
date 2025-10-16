package com.example.libreriafilm.controller;

import com.example.libreriafilm.dto.UtenteDto;
import com.example.libreriafilm.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public ResponseEntity<Object> getAllUtente(){
        return utenteService.getAllUtente();
    }

    @GetMapping("{id}")
    public ResponseEntity<UtenteDto> getUtenteById(@PathVariable Long id){
        return utenteService.getUtenteById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUtente(@RequestBody UtenteDto utenteDto){
        return utenteService.registerUtente(utenteDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUtente(@PathVariable Long id){
        return utenteService.deleteUtente(id);
    }

    @GetMapping("/login")
    public ResponseEntity<Object> loginUtente(@RequestParam String username, @RequestParam String password){
        return utenteService.loginUtente(username, password);
    }

}
