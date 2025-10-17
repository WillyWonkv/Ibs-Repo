package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.entity.*;
import com.example.libreriafilm.repository.*;
import com.example.libreriafilm.MapperDto.AttoreMapperDto;
import com.example.libreriafilm.MapperDto.FilmMapperDto;
import com.example.libreriafilm.MapperDto.GenereMapperDto;
import com.example.libreriafilm.MapperDto.RegistaMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final GenereRepository genereRepository;
    private final AttoreRepository attoreRepository;
    private final RegistaRepository registaRepository;
    private final PrestitoRepository prestitoRepository;

    public List<FilmDto> getAllFilm(){

        List<Film> film = filmRepository.findAll();
        if(film.isEmpty()){throw new RuntimeException("Films not found");}
        return film.stream().map(FilmMapperDto::FilmToFilmDto).toList();

    }

    public FilmDto getFilmById(long id){

        Film film = filmRepository.findById(id).orElseThrow(() -> new RuntimeException("Film not found"));
        return FilmMapperDto.FilmToFilmDto(film);

    }

    public FilmDto addFilm(FilmDto filmDto){

        Film film = FilmMapperDto.newFilm(filmDto);

        film.setRegista(registaRepository.findByNome(filmDto.regista().nome())
                .orElseGet(() -> RegistaMapperDto.newRegista(filmDto.regista())));

        List<Attore> attori = new ArrayList<>();
        filmDto.attori().forEach(a ->
                attori.add(attoreRepository.findByNome(a.nome())
                        .orElseGet(() -> AttoreMapperDto.newAttore(a))));
        film.setAttori(attori);

        List<Genere> generi = new ArrayList<>();
        filmDto.generi().forEach(a ->
                generi.add(genereRepository.findByNome((a.nome()))
                        .orElseGet(() -> GenereMapperDto.newGenere(a))));
        film.setGeneri(generi);

        return FilmMapperDto.FilmToFilmDto(filmRepository .save(film));
    }

    public FilmDto updateFilm(FilmDto filmDto, long id){

        Film film = filmRepository.findById(id).orElseThrow(() -> new RuntimeException("Film not found"));
        if(!prestitoRepository.findFilmInPrestito(id).isEmpty()){throw new RuntimeException("Film booked");}

        film.setTitolo(filmDto.titolo());
        film.setDescrizione(filmDto.descrizione());
        film.setPrezzo(filmDto.prezzo());
        film.setAnnoUscita(filmDto.annoUscita());
        film.setDurata(filmDto.durata());

        film.setRegista(registaRepository.findByNome(filmDto.regista().nome())
                .orElseGet(() -> RegistaMapperDto.newRegista(filmDto.regista())));

        List<Attore> attori = new ArrayList<>();
        filmDto.attori().forEach(a ->
                attori.add(attoreRepository.findByNome(a.nome())
                        .orElseGet(() -> AttoreMapperDto.newAttore(a))));
        film.setAttori(attori);

        List<Genere> generi = new ArrayList<>();
        filmDto.generi().forEach(g ->
                generi.add(genereRepository.findByNome((g.nome()))
                        .orElseGet(() -> GenereMapperDto.newGenere(g))));
        film.setGeneri(generi);

        filmRepository.save(film);
        return getFilmById(id);
    }

    public void deleteFilmById(long id){

        Film film = filmRepository.findById(id).orElseThrow(() -> new RuntimeException("Film not found"));
        if(!prestitoRepository.findFilmInPrestito(id).isEmpty()){throw new RuntimeException("Film booked");}

        filmRepository.delete(film);

    }

}
