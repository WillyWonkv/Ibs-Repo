package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.dto.PrestitoDto;
import com.example.libreriafilm.entity.Prestito;
import com.example.libreriafilm.repository.FilmRepository;
import com.example.libreriafilm.repository.PrestitoRepository;
import com.example.libreriafilm.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PrestitoService {

    @Autowired
    private PrestitoRepository prestitoRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
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
