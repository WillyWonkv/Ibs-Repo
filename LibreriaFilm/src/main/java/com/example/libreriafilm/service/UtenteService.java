package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.UtenteDto;
import com.example.libreriafilm.entity.Utente;
import com.example.libreriafilm.repository.PrestitoRepository;
import com.example.libreriafilm.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PrestitoRepository prestitoRepository;

    public ResponseEntity<Object> getAllUtente(){
        return ResponseEntity.ok(utenteRepository.findAll().stream()
                .map(u -> new UtenteDto(
                        u.getId(),
                        u.getNome(),
                        u.getCognome(),
                        u.getEmail(),
                        u.getPassword(),
                        u.getSoldi(),
                        u.getDataRegistrazione(),
                        null
                )).toList());
    }

    public ResponseEntity<UtenteDto> getUtenteById(long id) {
        return utenteRepository.findById(id)
                .map(u -> ResponseEntity.ok(new UtenteDto(
                        u.getId(),
                        u.getNome(),
                        u.getCognome(),
                        u.getEmail(),
                        u.getPassword(),
                        u.getSoldi(),
                        u.getDataRegistrazione(),
                        null
                )))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> registerUtente(UtenteDto utenteDto){

        Utente utente = new Utente();
        utente.setNome(utenteDto.nome());
        utente.setCognome(utenteDto.cognome());
        utente.setEmail(utenteDto.email());
        utente.setPassword(utenteDto.password());
        utente.setSoldi(utenteDto.soldi());
        utente.setDataRegistrazione(Date.valueOf(LocalDate.now()));
        return ResponseEntity.ok(utenteRepository.save(utente));

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

    /*public ResponseEntity<Object> loginUtente(UtenteDto utenteDto){



        return

    }*/

}
