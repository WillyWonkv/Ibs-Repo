package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.AttoreDto;
import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.dto.GenereDto;
import com.example.libreriafilm.dto.RegistaDto;
import com.example.libreriafilm.entity.*;
import com.example.libreriafilm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private GenereRepository genereRepository;
    @Autowired
    private AttoreRepository attoreRepository;
    @Autowired
    private RegistaRepository registaRepository;
    @Autowired
    private PrestitoRepository prestitoRepository;

    public ResponseEntity<List<FilmDto>> getAllFilm(){

        if(filmRepository.findAll().isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}

        return ResponseEntity.ok(filmRepository.findAll()
                .stream()
                .map(FilmMapperDto::FilmToFilmDto).toList());

    }

    public ResponseEntity<FilmDto> getFilmById(long id){

        if(filmRepository.findById(id).isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}

        return ResponseEntity.ok(filmRepository.findById(id)
                .map(FilmMapperDto::FilmToFilmDto).orElse(null));

    }

    public ResponseEntity<FilmDto> addLibro(FilmDto filmDto){

        Film film = new Film();

        film.setTitolo(filmDto.titolo());
        film.setDescrizione(filmDto.descrizione());
        film.setPrezzo(filmDto.prezzo());
        film.setAnnoUscita(filmDto.annoUscita());
        film.setDurata(filmDto.durata());

        film.setRegista(registaRepository.findByNome(filmDto.regista().nome())
                .orElseGet(() -> {
                    Regista reg = new Regista();
                    reg.setNome(filmDto.regista().nome());
                    reg.setDataNascita(filmDto.regista().dataNascita());
                    return reg;
                }));


        List<Attore> attori = new ArrayList<>();
        filmDto.attori().forEach(a ->
                attori.add(attoreRepository.findByNome(a.nome())
                        .orElseGet(() -> {
                            Attore at = new Attore();
                            at.setNome(a.nome());
                            at.setDataNascita(a.dataNascita());
                            return at;
                        })));
        film.setAttori(attori);

        List<Genere> generi = new ArrayList<>();
        filmDto.generi().forEach(a ->
                generi.add(genereRepository.findByNome((a.nome()))
                        .orElseGet(() -> {
                            Genere g = new Genere();
                            g.setNome(a.nome());
                            return g;
                        })));
        film.setGeneri(generi);

        filmRepository.save(film);

        return ResponseEntity.ok(getFilmById(film.getId()).getBody());


    }

    public ResponseEntity<FilmDto> updateFilm(FilmDto filmDto, long id){

        Optional<Film> filmOptional = filmRepository.findById(id);

        if(filmOptional.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}

        if(!prestitoRepository.findFilmInPrestito(id).isEmpty()){return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}

        Film film = filmOptional.get();

        film.setTitolo(filmDto.titolo());
        film.setDescrizione(filmDto.descrizione());
        film.setPrezzo(filmDto.prezzo());
        film.setAnnoUscita(filmDto.annoUscita());
        film.setDurata(filmDto.durata());

        film.setRegista(registaRepository.findByNome(filmDto.regista().nome())
                .orElseGet(() -> {
                    Regista reg = new Regista();
                    reg.setNome(filmDto.regista().nome());
                    reg.setDataNascita(filmDto.regista().dataNascita());
                    return reg;
                }));

        List<Attore> attori = new ArrayList<>();
        filmDto.attori().forEach(a ->
                attori.add(attoreRepository.findByNome(a.nome())
                        .orElseGet(() -> {
                            Attore at = new Attore();
                            at.setNome(a.nome());
                            at.setDataNascita(a.dataNascita());
                            return at;
                        })));
        film.setAttori(attori);

        List<Genere> generi = new ArrayList<>();
        filmDto.generi().forEach(g ->
                generi.add(genereRepository.findByNome((g.nome()))
                        .orElseGet(() -> {
                            Genere gen = new Genere();
                            gen.setNome(g.nome());
                            return gen;
                        })));
        film.setGeneri(generi);

        filmRepository.save(film);

        return ResponseEntity.ok(getFilmById(film.getId()).getBody());

    }

    public ResponseEntity<Film> deleteFilmById(long id){

        Optional<Film> filmOptional = filmRepository.findById(id);
        if(filmOptional.isEmpty()){return ResponseEntity.notFound().build();}

        if(!prestitoRepository.findFilmInPrestito(id).isEmpty()){return ResponseEntity.badRequest().build();}

        filmRepository.delete(filmOptional.get());

        return ResponseEntity.ok().build();

    }

}
