package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.AttoreDto;
import com.example.libreriafilm.entity.Attore;
import com.example.libreriafilm.entity.Film;
import com.example.libreriafilm.repository.AttoreRepository;
import com.example.libreriafilm.repository.FilmRepository;
import com.example.libreriafilm.MapperDto.AttoreMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttoreService {

    private AttoreRepository attoreRepository;
    private FilmRepository filmRepository;

    @Autowired
    public AttoreService(AttoreRepository attoreRepository, FilmRepository filmRepository) {
        this.attoreRepository = attoreRepository;
        this.filmRepository = filmRepository;
    }

    public ResponseEntity<List<AttoreDto>> getAllAttori() {

        if(attoreRepository.findAll().isEmpty()) {return ResponseEntity.notFound().build();}

        return ResponseEntity.ok(attoreRepository.findAll().stream()
                .map(AttoreMapperDto::attoreToAttoreDto).toList());

    }

    public ResponseEntity<AttoreDto> getAttoreById(long id) {

        if(attoreRepository.findById(id).isEmpty()) {return ResponseEntity.notFound().build();}

        return ResponseEntity.ok(attoreRepository.findById(id)
                .map(AttoreMapperDto::attoreToAttoreDto).orElse(null));

    }

    public ResponseEntity<AttoreDto> addAttore(AttoreDto attoreDto) {

        attoreRepository.save(AttoreMapperDto.newAttore(attoreDto));
        return ResponseEntity.ok().body(attoreDto);

    }

    public ResponseEntity<Object> updateAttore(AttoreDto attoreDto, long id) {

        return  attoreRepository.findById(id)
                .map(attore -> {
                            attoreRepository.save(AttoreMapperDto.newAttore(attoreDto));
                            return ResponseEntity.ok().build();
                        }
                ).orElseGet(() -> ResponseEntity.notFound().build());

    }

    public ResponseEntity<AttoreDto> deleteAttore(long id) {

        Optional<Attore> attoreOptional = attoreRepository.findById(id);

        if (attoreOptional.isEmpty()) {return ResponseEntity.notFound().build();}

        Attore attore = attoreOptional.get();

        for(Film f : attore.getFilm()) {
            f.getAttori().remove(attore);
        }

        attore.getFilm().clear();
        attoreRepository.delete(attore);

         return ResponseEntity.ok().build();

    }

    public ResponseEntity<AttoreDto> setAttoriToFilm(List<Long> attoriIds, long filmId) {

        Optional<Film> filmOptional = filmRepository.findById(filmId);
        List<Attore> attori = attoreRepository.findAllById(attoriIds);

        if(filmOptional.isEmpty() || attori.size() != attoriIds.size()) {return ResponseEntity.notFound().build();}

        Film film = filmOptional.get();

        film.setAttori(attori);
        filmRepository.save(film);

        return ResponseEntity.ok().build();

    }

}
