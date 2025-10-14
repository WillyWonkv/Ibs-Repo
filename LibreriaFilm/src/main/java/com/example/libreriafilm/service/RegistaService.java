package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.AttoreDto;
import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.dto.GenereDto;
import com.example.libreriafilm.dto.RegistaDto;
import com.example.libreriafilm.repository.RegistaRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistaService{

    @Autowired
    private RegistaRepository registaRepository;

    public List<RegistaDto> getAllRegisti(){

        return registaRepository.findAll().stream()
                .map(r -> new RegistaDto(
                        r.getId(),
                        r.getNome(),
                        r.getDataNascita(),
                        r.getFilm().stream()
                                .map(f -> new FilmDto(
                                        f.getId(),
                                        f.getTitolo(),
                                        f.getDescrizione(),
                                        f.getPrezzo(),
                                        f.getAnnoUscita(),
                                        f.getDurata(),
                                        null,
                                        f.getAttori().stream()
                                                .map(a -> new AttoreDto(
                                                        a.getId(),
                                                        a.getNome(),
                                                        a.getDataNascita(),
                                                        null
                                                )).toList(),
                                        f.getGeneri().stream()
                                                .map(g -> new GenereDto(
                                                        g.getId(),
                                                        g.getNome(),
                                                        null
                                                )).toList(),
                                        null
                                )).toList()
                )).toList();

    }

}
