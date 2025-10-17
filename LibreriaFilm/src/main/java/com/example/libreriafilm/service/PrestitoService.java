package com.example.libreriafilm.service;

import com.example.libreriafilm.repository.FilmRepository;
import com.example.libreriafilm.repository.PrestitoRepository;
import com.example.libreriafilm.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrestitoService {

    private PrestitoRepository prestitoRepository;
    private FilmRepository filmRepository;
    private UtenteRepository utenteRepository;

    /*public ResponseEntity<Object> getAllPrestito() {

        return ResponseEntity.ok().body(prestitoRepository.findAll()
                .stream().map(p -> new PrestitoDto(
                        p.getId(),
                        p.getDataPrestito(),
                        p.getDataRestituzione(),
                        new FilmDto(
                                p.getFilm().getId(),
                                p.getFilm().getTitolo(),
                                p.getFilm().getDescrizione(),
                                p.getFilm().getPrezzo(),
                                p.getDataPrestito()
                        ),
                )).toList());

    }*/


}
