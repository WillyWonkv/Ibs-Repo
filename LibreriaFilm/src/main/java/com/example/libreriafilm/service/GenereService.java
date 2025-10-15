package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.AttoreDto;
import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.dto.GenereDto;
import com.example.libreriafilm.dto.RegistaDto;
import com.example.libreriafilm.repository.GenereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenereService {

    @Autowired
    private GenereRepository genereRepository;

    public ResponseEntity<List<GenereDto>>  getAllGeneri() {

        if (genereRepository.findAll().isEmpty()) {return ResponseEntity.notFound().build();}

        return ResponseEntity.ok(genereRepository.findAll().stream()
                .map(g -> new GenereDto(
                        g.getId(),
                        g.getNome(),
                        g.getFilm().stream()
                                .map(f -> new FilmDto(
                                        f.getId(),
                                        f.getTitolo(),
                                        f.getDescrizione(),
                                        f.getPrezzo(),
                                        f.getAnnoUscita(),
                                        f.getDurata(),
                                        new RegistaDto(
                                                f.getRegista().getId(),
                                                f.getRegista().getNome(),
                                                f.getRegista().getDataNascita(),
                                                null
                                        ),
                                        f.getAttori().stream()
                                                .map(a -> new AttoreDto(
                                                        a.getId(),
                                                        a.getNome(),
                                                        a.getDataNascita(),
                                                        null
                                                )).toList(),
                                        null,
                                        null
                                )).toList()
                )).toList());

    }

    public ResponseEntity<GenereDto> getGenereById(long id){

        if (genereRepository.findById(id).isEmpty()) {return ResponseEntity.notFound().build();}

        return ResponseEntity.ok(genereRepository.findById(id)
                .map(g -> new GenereDto(
                        g.getId(),
                        g.getNome(),
                        g.getFilm().stream()
                                .map(f -> new FilmDto(
                                        f.getId(),
                                        f.getTitolo(),
                                        f.getDescrizione(),
                                        f.getPrezzo(),
                                        f.getAnnoUscita(),
                                        f.getDurata(),
                                        new RegistaDto(
                                                f.getRegista().getId(),
                                                f.getRegista().getNome(),
                                                f.getRegista().getDataNascita(),
                                                null
                                        ),
                                        f.getAttori().stream()
                                                .map(a -> new AttoreDto(
                                                        a.getId(),
                                                        a.getNome(),
                                                        a.getDataNascita(),
                                                        null
                                                )).toList(),
                                        null,
                                        null
                                )).toList()
                )).orElse(null));

    }

}
