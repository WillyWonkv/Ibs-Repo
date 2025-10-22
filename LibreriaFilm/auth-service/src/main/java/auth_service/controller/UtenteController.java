package auth_service.controller;


import auth_service.dto.UtenteDto;
import auth_service.entity.Utente;
import auth_service.service.UtenteService;
import auth_service.security.request.AuthRequest;
import auth_service.security.request.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/utente")
public class UtenteController {

    private final UtenteService utenteService;

    @GetMapping
    public ResponseEntity<List<UtenteDto>> getAllUtente(){

        try{
            List<UtenteDto> utenti = utenteService.getAllUtente();
            return ResponseEntity.ok(utenti);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<UtenteDto> getUtenteById(@PathVariable Long id){

        try {
            UtenteDto utenteDto = utenteService.getUtenteById(id);
            return ResponseEntity.ok(utenteDto);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }

    }

    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<UtenteDto> deleteUtenteById(@PathVariable Long id){

        try {
            UtenteDto utenteDto = utenteService.deleteUtente(id);
            return ResponseEntity.ok(utenteDto);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

    }*/

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUtente(@RequestBody @Valid Utente utente){

        try {
            AuthResponse authResponse = utenteService.registerUtente(utente);
            return ResponseEntity.ok(authResponse);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUtente(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(utenteService.loginUtente(authRequest));
    }


}
