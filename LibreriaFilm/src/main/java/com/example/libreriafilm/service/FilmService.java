package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.AttoreDto;
import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.dto.GenereDto;
import com.example.libreriafilm.dto.RegistaDto;
import com.example.libreriafilm.entity.Attore;
import com.example.libreriafilm.entity.Film;
import com.example.libreriafilm.entity.Genere;
import com.example.libreriafilm.repository.AttoreRepository;
import com.example.libreriafilm.repository.FilmRepository;
import com.example.libreriafilm.repository.GenereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.border.LineBorder;
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
                        f.getAttore().stream().map(
                                a -> new AttoreDto(
                                        a.getAttore().getId(),
                                        a.getAttore().getNome(),
                                        a.getAttore().getDataNascita(),
                                        null
                                )
                        ).toList(),
                        f.getGenere().stream().map(
                                g -> new GenereDto(
                                        g.getGenere().getId(),
                                        g.getGenere().getNome(),
                                        null
                                )
                        ).toList()
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
                        f.getAttore().stream()
                                .map(
                                        a -> new AttoreDto(
                                                a.getAttore().getId(),
                                                a.getAttore().getNome(),
                                                a.getAttore().getDataNascita(),
                                                null
                                        )
                                ).toList(),
                        f.getGenere().stream()
                                .map(
                                        g -> new GenereDto(
                                                g.getGenere().getId(),
                                                g.getGenere().getNome(),
                                                null
                                        )
                                ).toList()
                )).orElse(null);

    }

    public FilmDto addLibro(FilmDto filmDto){


        return null;

    }

}
