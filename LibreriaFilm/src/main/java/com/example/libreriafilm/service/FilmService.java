package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.AttoreDto;
import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.dto.GenereDto;
import com.example.libreriafilm.dto.RegistaDto;
import com.example.libreriafilm.entity.*;
import com.example.libreriafilm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<FilmDto> getAllFilm(){

        return filmRepository.findAll()
                .stream()
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
                        f.getAttori().stream().map(
                                a -> new AttoreDto(
                                        a.getId(),
                                        a.getNome(),
                                        a.getDataNascita(),
                                        null
                                )
                        ).toList(),
                        f.getGeneri().stream().map(
                                g -> new GenereDto(
                                        g.getId(),
                                        g.getNome(),
                                        null
                                )
                        ).toList(),
                        null
                )).toList();

    }

    public FilmDto getFilmById(long id){

        return filmRepository.findById(id)
                .map( f -> new FilmDto(
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
                                .map(
                                        a -> new AttoreDto(
                                                a.getId(),
                                                a.getNome(),
                                                a.getDataNascita(),
                                                null
                                        )
                                ).toList(),
                        f.getGeneri().stream()
                                .map(
                                        g -> new GenereDto(
                                                g.getId(),
                                                g.getNome(),
                                                null
                                        )
                                ).toList(),
                        null
                )).orElse(null);

    }

    public Film addLibro(FilmDto filmDto){

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

        return film;


    }

    public FilmDto updateFilm(FilmDto filmDto, long id){

        Optional<Film> filmOptional = filmRepository.findById(id);

        if(filmOptional.isEmpty()){return null;}

        if(!prestitoRepository.findFilmInPrestito(id).isEmpty()){return null;}

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

        return getFilmById(film.getId());

    }

    public ResponseEntity<FilmDto> deleteFilmById(long id){

        Optional<Film> filmOptional = filmRepository.findById(id);
        if(filmOptional.isEmpty()){return ResponseEntity.notFound().build();}

        if(!prestitoRepository.findFilmInPrestito(id).isEmpty()){return ResponseEntity.badRequest().build();}

        filmRepository.delete(filmOptional.get());

        return ResponseEntity.ok().build();

    }

}
