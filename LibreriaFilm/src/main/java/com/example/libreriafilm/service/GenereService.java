package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.GenereDto;
import com.example.libreriafilm.entity.Genere;
import com.example.libreriafilm.repository.FilmRepository;
import com.example.libreriafilm.repository.GenereRepository;
import com.example.libreriafilm.MapperDto.GenereMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenereService {

    private GenereRepository genereRepository;
    private FilmRepository filmRepository;

    @Autowired
    public GenereService(GenereRepository genereRepository, FilmRepository filmRepository) {
        this.genereRepository = genereRepository;
        this.filmRepository = filmRepository;
    }

    public ResponseEntity<List<GenereDto>>  getAllGeneri() {

        if (genereRepository.findAll().isEmpty()) {return ResponseEntity.notFound().build();}

        return ResponseEntity.ok(genereRepository.findAll().stream()
                .map(GenereMapperDto::genereToGenereDto).toList());

    }

    public ResponseEntity<GenereDto> getGenereById(long id){

        if (genereRepository.findById(id).isEmpty()) {return ResponseEntity.notFound().build();}

        return ResponseEntity.ok(genereRepository.findById(id)
                .map(GenereMapperDto::genereToGenereDto).orElse(null));

    }

    public ResponseEntity<GenereDto> addGenere(GenereDto genereDto) {

        genereRepository.save(GenereMapperDto.newGenere(genereDto));

        return ResponseEntity.ok().build();

    }

    public ResponseEntity<Object> updateGenere(long id, GenereDto genereDto) {

        return genereRepository.findById(id)
                .map(genere -> {
                    genereRepository.save(GenereMapperDto.newGenere(genereDto));
                    return ResponseEntity.ok().build();
                }
        ).orElseGet(() -> ResponseEntity.notFound().build());

    }

    public ResponseEntity<Object> deleteGenere(long id) {

        return genereRepository.findById(id).map(genere -> {

                    genere.getFilm().forEach(f -> f.getGeneri().remove(genere));
                    genere.getFilm().clear();
                    genereRepository.delete(genere);
                    return ResponseEntity.ok().build();

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> setGeneriToFilm(List<Long> generiIds, long filmId) {

        return filmRepository.findById(filmId)
                .map(film -> {

                    List<Genere> generi = genereRepository.findAllById(generiIds);

                    if(generi.size() != generiIds.size()) {return ResponseEntity.notFound().build();}

                    film.setGeneri(generi);
                    filmRepository.save(film);
                    return ResponseEntity.ok().build();

                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

}
