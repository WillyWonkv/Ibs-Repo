package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.UtenteDto;
import com.example.libreriafilm.repository.PrestitoRepository;
import com.example.libreriafilm.repository.UtenteRepository;
import com.example.libreriafilm.MapperDto.UtenteMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtenteService {

    private UtenteRepository utenteRepository;
    private PrestitoRepository prestitoRepository;

    @Autowired
    public UtenteService(UtenteRepository utenteRepository, PrestitoRepository prestitoRepository) {
        this.utenteRepository = utenteRepository;
        this.prestitoRepository = prestitoRepository;
    }

    public ResponseEntity<Object> getAllUtente(){
        return ResponseEntity.ok(utenteRepository.findAll().stream()
                .map(UtenteMapperDto::utenteToUtenteDto).toList());
    }

    public ResponseEntity<UtenteDto> getUtenteById(long id) {
        return utenteRepository.findById(id)
                .map(u -> ResponseEntity.ok(UtenteMapperDto.utenteToUtenteDto(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> registerUtente(UtenteDto utenteDto){

        return ResponseEntity.ok(utenteRepository.save(UtenteMapperDto.newUtente(utenteDto)));

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

    public ResponseEntity<Object> loginUtente(String email, String password){

        if(utenteRepository.findAll().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password))) {

            return ResponseEntity.ok().build();

        }

        return ResponseEntity.notFound().build();

    }

}
