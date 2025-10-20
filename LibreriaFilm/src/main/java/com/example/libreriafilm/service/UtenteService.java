package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.UtenteDto;
import com.example.libreriafilm.entity.Role;
import com.example.libreriafilm.service.request.AuthRequest;
import com.example.libreriafilm.service.request.AuthResponse;
import com.example.libreriafilm.entity.Utente;
import com.example.libreriafilm.repository.PrestitoRepository;
import com.example.libreriafilm.repository.UtenteRepository;
import com.example.libreriafilm.MapperDto.UtenteMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final PrestitoRepository prestitoRepository;

    public List<UtenteDto> getAllUtente(){
        return utenteRepository.findAll().stream()
                .map(UtenteMapperDto::utenteToUtenteDto).toList();
    }

    public UtenteDto getUtenteById(long id) {
        return utenteRepository.findById(id)
                .map(UtenteMapperDto::utenteToUtenteDto)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public AuthResponse registerUtente(Utente utente){

        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        utente.setRole(Role.USER);
        utente.setDataRegistrazione(Date.valueOf(LocalDate.now()));
        utenteRepository.save(utente);

        var JwtToken = jwtService.GenerateToken(utente.getEmail());

        return AuthResponse.builder().token(JwtToken).build();

    }

    public AuthResponse loginUtente(AuthRequest utente){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        utente.getEmail(),
                        utente.getPassword()
                )
        );

        var user = utenteRepository.findByEmail(utente.getEmail()).orElseThrow();
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", Role.USER);
        return AuthResponse.builder().token(jwtService.createToken(claims ,user.getEmail())).build();

    }

    public ResponseEntity<Object> deleteUtente(long id){

        return utenteRepository.findById(id).map(utente -> {

            if(utente.getPrestito().stream().anyMatch(prestito -> prestito.getDataRestituzione() == null)){
                return ResponseEntity.badRequest().build();
            }

            prestitoRepository.deleteAll(utente.getPrestito());

            utenteRepository.delete(utente);
            return ResponseEntity.ok().build();

        })
        .orElseGet(() -> ResponseEntity.notFound().build());

    }

}
