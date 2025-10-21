package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.UtenteDto;
import com.example.libreriafilm.entity.Ruolo;
import com.example.libreriafilm.repository.RuoloRepository;
import com.example.libreriafilm.security.jwt.JwtService;
import com.example.libreriafilm.security.request.AuthRequest;
import com.example.libreriafilm.security.request.AuthResponse;
import com.example.libreriafilm.entity.Utente;
import com.example.libreriafilm.repository.PrestitoRepository;
import com.example.libreriafilm.repository.UtenteRepository;
import com.example.libreriafilm.MapperDto.UtenteMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final RuoloRepository ruoloRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final PrestitoRepository prestitoRepository;

    public List<UtenteDto> getAllUtente(){

        List<Utente> utenti = utenteRepository.findAll();
        if(utenti.isEmpty()){throw new RuntimeException("No Users");}
        return utenteRepository.findAll().stream()
                .map(UtenteMapperDto::utenteToUtenteDto).toList();
    }

    public UtenteDto getUtenteById(long id) {
        return utenteRepository.findById(id)
                .map(UtenteMapperDto::utenteToUtenteDto)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public UtenteDto deleteUtente(long id){

        return utenteRepository.findById(id).map(utente -> {

                    if(utente.getPrestito().stream().anyMatch(prestito -> prestito.getDataRestituzione() == null)){
                        throw new RuntimeException("Bad Request");
                    }

                    prestitoRepository.deleteAll(utente.getPrestito());

                    utenteRepository.delete(utente);
                    return UtenteMapperDto.utenteToUtenteDto(utente);

                })
                .orElseThrow(() -> new RuntimeException("not found"));

    }

    public AuthResponse registerUtente(Utente utente){

        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        utente.setDataRegistrazione(Date.valueOf(LocalDate.now()));

        Ruolo ruolo = ruoloRepository.findByNome("USER")
                .orElseThrow(() -> new RuntimeException("user not found"));

        utente.getRuolo().add(ruolo);

        utenteRepository.save(utente);

        var JwtToken = jwtService.generateToken(utente);

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
        return AuthResponse.builder().token(jwtService.generateToken(user)).build();

    }

}
