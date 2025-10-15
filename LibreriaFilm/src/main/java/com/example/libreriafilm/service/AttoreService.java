package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.AttoreDto;
import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.dto.GenereDto;
import com.example.libreriafilm.dto.RegistaDto;
import com.example.libreriafilm.entity.Attore;
import com.example.libreriafilm.entity.Film;
import com.example.libreriafilm.repository.AttoreRepository;
import com.example.libreriafilm.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttoreService {

    @Autowired
    private AttoreRepository attoreRepository;
    @Autowired
    private FilmRepository filmRepository;

    public ResponseEntity<List<AttoreDto>> getAllAttori() {

        if(attoreRepository.findAll().isEmpty()) {return ResponseEntity.notFound().build();}

        return ResponseEntity.ok(attoreRepository.findAll().stream()
                .map(a -> new AttoreDto(
                        a.getId(),
                        a.getNome(),
                        a.getDataNascita(),
                        a.getFilm().stream()
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
                                        null,
                                        f.getGeneri().stream()
                                                .map(g -> new GenereDto(
                                                        g.getId(),
                                                        g.getNome(),
                                                        null
                                                )).toList(),
                                        null
                                )).toList()
                )).toList());

    }

    public ResponseEntity<AttoreDto> getAttoreById(long id) {

        if(attoreRepository.findById(id).isEmpty()) {return ResponseEntity.notFound().build();}

        return ResponseEntity.ok(attoreRepository.findById(id)
                .map(a -> new AttoreDto(
                        a.getId(),
                        a.getNome(),
                        a.getDataNascita(),
                        a.getFilm().stream()
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
                                        null,
                                        f.getGeneri().stream()
                                                .map(g -> new GenereDto(
                                                        g.getId(),
                                                        g.getNome(),
                                                        null
                                                )).toList(),
                                        null
                                )).toList()
                )).orElse(null));

    }

    public ResponseEntity<AttoreDto> addAttore(AttoreDto attoreDto) {

        Attore attore = new Attore();

        attore.setNome(attoreDto.nome());
        attore.setDataNascita(attoreDto.dataNascita());

        attoreRepository.save(attore);
        return ResponseEntity.ok(getAttoreById(attore.getId()).getBody());

    }

    public ResponseEntity<AttoreDto> updateAttore(AttoreDto attoreDto, long id) {

        Optional<Attore> attore = attoreRepository.findById(id);

        if (attore.isEmpty()) {return ResponseEntity.notFound().build();}

        Attore attoreUpdate = attore.get();

        attoreUpdate.setNome(attoreDto.nome());
        attoreUpdate.setDataNascita(attoreDto.dataNascita());

        attoreRepository.save(attoreUpdate);

        return ResponseEntity.ok(getAttoreById(attoreUpdate.getId()).getBody());

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
