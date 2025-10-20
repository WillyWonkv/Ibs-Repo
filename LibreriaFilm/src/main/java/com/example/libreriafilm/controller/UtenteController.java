package com.example.libreriafilm.controller;


import com.example.libreriafilm.dto.UtenteDto;
import com.example.libreriafilm.entity.Utente;
import com.example.libreriafilm.service.JwtService;
import com.example.libreriafilm.service.UtenteService;
import com.example.libreriafilm.service.request.AuthRequest;
import com.example.libreriafilm.service.request.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/utente")
public class UtenteController {

    private final UtenteService utenteService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<List<UtenteDto>> getAllUtente(){
        return ResponseEntity.ok(utenteService.getAllUtente());
    }

    @GetMapping("{id}")
    public ResponseEntity<UtenteDto> getUtenteById(@PathVariable Long id){
        return ResponseEntity.ok(utenteService.getUtenteById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUtente(@RequestBody Utente utente){
        return ResponseEntity.ok(utenteService.registerUtente(utente));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUtente(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(utenteService.loginUtente(authRequest));
    }




}
